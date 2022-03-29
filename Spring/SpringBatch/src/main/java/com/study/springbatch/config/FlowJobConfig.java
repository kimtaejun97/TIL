package com.study.springbatch.config;


import com.study.springbatch.MyTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FlowJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowJob() {
        return jobBuilderFactory.get("flowJob")
            .start(myFlow())
            .end()
            .build();
    }


    @Bean
    public Flow myFlow() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("myFlow");
        flowBuilder.start(myStep3())
            .next(myStep4())
            .end();

        return flowBuilder.build();

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
