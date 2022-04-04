package com.study.springbatch.config;

import com.study.springbatch.CustomDecider;
import com.study.springbatch.MyTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class DeciderTestConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
            .incrementer(new RunIdIncrementer())
            .start(firstStep())
            .next((decider()))
            .on("ODD").to(oddStep())
            .on("EVEN").to(evenStep())
            .from(oddStep())
                .on("*")
                .end()
            .from(evenStep())
                .on("*")
                .end()
            .end()
            .build();
    }

    @Bean
    public JobExecutionDecider decider() {
        return new CustomDecider();
    }

    @Bean
    public Step firstStep() {
        return stepBuilderFactory.get("firstStep")
            .tasklet(new MyTasklet("firstStep"))
            .build();
    }

    @Bean
    public Step oddStep() {
        return stepBuilderFactory.get("oddStep")
            .tasklet(new MyTasklet("oddStep"))
            .build();
    }

    @Bean
    public Step evenStep() {
        return stepBuilderFactory.get("evenStep")
            .tasklet(new MyTasklet("evenStep"))
            .build();
    }


}
