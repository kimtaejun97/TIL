package com.study.springbatch.config;

import com.study.springbatch.CustomJobParametersIncrementer;
import com.study.springbatch.CustomJobParametersValidator;
import com.study.springbatch.MyTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class SimpleJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobExecutionListener jobExecutionListener;

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
            .start(myStep1())
            .next(myStep2())
            .listener(jobExecutionListener)
            .validator(new DefaultJobParametersValidator(new String[] {"name"}, new String[] {"date", "weight", "seq"}))
            .validator(new CustomJobParametersValidator())
            .incrementer(new CustomJobParametersIncrementer())
//            .incrementer(new RunIdIncrementer())
            .preventRestart()
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
}