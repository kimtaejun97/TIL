package com.study.springbatch.config;

import com.study.springbatch.CustomItemProcessor;
import com.study.springbatch.CustomItemReader;
import com.study.springbatch.CustomItemWriter;
import com.study.springbatch.Member;
import com.study.springbatch.MyTasklet;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ChunkConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
            .start(chunkStep1())
            .next(myStep2())
//            .incrementer(new RunIdIncrementer())
            .build();
    }

    @Bean
    public Step chunkStep1() {
        return stepBuilderFactory.get("chunkStep1")
            .<Member, Member>chunk(2)
            .reader(itemReader())
            .processor(itemProcessor())
            .writer(itemWriter())
            .build();
    }

    @Bean
    public Step myStep2() {
        return stepBuilderFactory.get("myStep2")
            .tasklet(new MyTasklet("myStep2"))
            .build();
    }

    @Bean
    public ItemReader<Member> itemReader() {
        return new CustomItemReader(Arrays.asList(
            new Member("1", "user1"),
            new Member("2", "user2"),
            new Member("3", "user3"),
            new Member("4", "user4"),
            new Member("5", "user5"),
            new Member("6", "user6"),
            new Member("7", "user7"),
            new Member("8", "user8"),
            new Member("9", "user9"),
            new Member("10", "user10")));
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
