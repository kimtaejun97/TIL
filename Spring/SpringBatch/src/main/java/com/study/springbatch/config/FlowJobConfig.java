package com.study.springbatch.config;


import com.study.springbatch.MyTasklet;
import com.study.springbatch.PasscheckingListener;
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
            .start(flowA())
            .next(stepA())
            .on("*")
            .end()
            .next(flowB())
            .end()
            .build();
    }

    @Bean
    public Flow flowA() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flowA");

        return flowBuilder
            .start(myStep1())
            .on("COMPLETED")
            .to(myStep2())
            .on("PASS")
            .stop()
            .next(myStep3())
            .end();
    }

    private Step stepA() {
        return stepBuilderFactory.get("stepA")
            .tasklet(new MyTasklet("stepA"))
            .build();
    }

    private Flow flowB() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flowA");
        return flowBuilder
            .start(myStep1())
                .on("FAILED")
                .stop()
            .from(myStep1())
                .on("COMPLETED")
                .end()
            .from(myStep1())
                .on("*")
                .stopAndRestart(myStep1())
            .end();
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
            .listener(new PasscheckingListener())
            .build();
    }

    @Bean
    public Step myStep3() {
        return stepBuilderFactory.get("myStep3")
            .tasklet(new MyTasklet("myStep3"))
            .build();
    }

    @Bean
    public Step flowStep() {
        return stepBuilderFactory.get("flowStep")
            .flow(flowA())
            .build();
    }
}
