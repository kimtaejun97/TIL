package com.study.springbatch.config;

import com.study.springbatch.MyTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobExecutionListener jobExecutionListener;

    @Bean
    public Job myJob() {
        return jobBuilderFactory.get("myJob")
            .start(myStep1())
            .next(myStep2())
            .next(myStep3())
            .next(myStep4())
            .listener(jobExecutionListener)
            .build();
    }

    @Bean
    public Step myStep1() {
        return stepBuilderFactory.get("myStep1")
            .tasklet(new MyTasklet("myStep1"))
            .build();
    }

    @Bean
    public Step myStep2() {
        return stepBuilderFactory.get("myStep2")
            .tasklet(new MyTasklet("myStep2"))
            .build();
    }

    @Bean
    public Step myStep3() {
        return stepBuilderFactory.get("myStep3")
            .tasklet(new MyTasklet("myStep3"))
            .build();
    }

    @Bean
    public Step myStep4() {
        return stepBuilderFactory.get("myStep4")
            .tasklet(new MyTasklet("myStep4"))
            .build();
    }
}
