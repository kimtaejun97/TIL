package com.study.springbatch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job myJob() {
        return jobBuilderFactory.get("myJob")
            .start(myStep())
            .next(myStep2())
            .build();
    }

    @Bean
    public Step myStep() {
        return stepBuilderFactory.get("myStep1")
            .tasklet((contribution, chunkContext) -> {
                System.out.println("================ My Step1 =============");
                return RepeatStatus.FINISHED;
            })
            .build();
    }

    @Bean
    public Step myStep2() {
        return stepBuilderFactory.get("muStep2")
            .tasklet((contribution, chunkContext) -> {
                System.out.println("================ My Step2 =============");
                return RepeatStatus.FINISHED;
            })
            .build();
    }
}
