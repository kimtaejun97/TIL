package com.study.springbatch.jdbc;

import com.study.springbatch.CustomItemProcessor;
import com.study.springbatch.CustomItemProcessor2;
import com.study.springbatch.Member;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@RequiredArgsConstructor
@Configuration
public class JdbcCursor {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;
    private int chunkSize = 5;

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
            .start(chunkStep1())
            .incrementer(new RunIdIncrementer())
            .build();
    }

    @Bean
    public Step chunkStep1() {
        return stepBuilderFactory.get("chunkStep1")
            .<Member, Member>chunk(chunkSize)
            .reader(itemReader())
            .processor(itemProcessor())
            .writer(itemWriter())
            .build();
    }

    @Bean
    public ItemReader<Member> itemReader() {
        return new JdbcCursorItemReaderBuilder<Member>()
            .name("jdbcCursorItemReader")
            .fetchSize(chunkSize)
            .sql("select id, name from member where name like ? order by id")
            .queryArguments("user%")
            .beanRowMapper(Member.class)
            .dataSource(dataSource)
            .build();
    }

    @Bean
    public ItemProcessor<? super Member, Member> itemProcessor() {
        List itemProcessors = new ArrayList<>();
        itemProcessors.add(new CustomItemProcessor());
        itemProcessors.add(new CustomItemProcessor2());

        return new CompositeItemProcessorBuilder<>()
            .delegates(itemProcessors)
            .build();
    }

    @Bean
    public ItemWriter<? super Member> itemWriter() {
        return new JdbcBatchItemWriterBuilder<>()
            .dataSource(dataSource)
            .sql("insert into member2(name, id) values(:name, :id)")
            .assertUpdates(true)
            .beanMapped()
//            .columnMapped()
            .build();
    }
}
