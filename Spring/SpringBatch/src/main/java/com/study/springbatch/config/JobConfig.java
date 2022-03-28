package com.study.springbatch.config;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
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
            .start(myStep1())
            .next(myStep2())
            .build();
    }

    @Bean
    public Step myStep1() {
       return stepBuilderFactory.get("myStep1")
           .tasklet((contribution, chunkContext) -> {
               System.out.println("================ Execute myStep1");
               JobParameters jobParameters = contribution.getStepExecution().getJobExecution().getJobParameters();
               System.out.println("============= Parameter name: " + jobParameters.getString("name"));

               Map<String, Object> chunkJobParameters = chunkContext.getStepContext().getJobParameters();
               return RepeatStatus.FINISHED;
           })
           .build();
    }

    @Bean
    public Step myStep2() {
        return stepBuilderFactory.get("myStep2")
            .tasklet((contribution, chunkContext) -> {
                System.out.println("================ Execute myStep2");
//                throw new RuntimeException("Step2 Failed");
                return RepeatStatus.FINISHED;
            })
            .build();
    }
}
