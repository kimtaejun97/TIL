package com.study.springbatch.jpa;

import com.study.springbatch.CustomItemProcessor;
import com.study.springbatch.CustomItemWriter;
import com.study.springbatch.Member;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
//@Configuration
public class JpaPaging {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

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
            .<Member, Member>chunk(5)
            .reader(itemReader())
            .processor(itemProcessor())
            .writer(itemWriter())
            .build();
    }

    @Bean
    public ItemReader<Member> itemReader() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "user%");

        return new JpaPagingItemReaderBuilder<Member>()
            .name("jpaCursorItemReader")
            .pageSize(5)
            .entityManagerFactory(entityManagerFactory)
            .queryString("select m from Member m where name like :name")
            .parameterValues(params)
            .build();
    }

    @Bean
    public ItemProcessor<? super Member, Member> itemProcessor() {
        return new CustomItemProcessor();
    }

    @Bean
    public ItemWriter<? super Member> itemWriter() {
        return new CustomItemWriter();
    }

}
