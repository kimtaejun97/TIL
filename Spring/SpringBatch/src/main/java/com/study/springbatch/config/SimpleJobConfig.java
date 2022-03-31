package com.study.springbatch.config;

import com.study.springbatch.CustomJobParametersIncrementer;
import com.study.springbatch.CustomJobParametersValidator;
import com.study.springbatch.MyTasklet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
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
            .start(chunkStep())
            .next(partitionerStep())
            .next(jobStep())
            .listener(jobExecutionListener)
            .validator(new DefaultJobParametersValidator(new String[] {"name"}, new String[] {"date", "weight", "seq"}))
            .validator(new CustomJobParametersValidator())
            .incrementer(new CustomJobParametersIncrementer())
            .incrementer(new RunIdIncrementer())
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

    @Bean
    public Step chunkStep() {
        return stepBuilderFactory.get("chunkStep")
            .<String, String>chunk(3)
            .reader(new ItemReader<String>() {
                @Override
                public String read()
                    throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                    return null;
                }
            })
            .processor(new ItemProcessor<String, String>() {
                @Override
                public String process(String item) throws Exception {
                    return null;
                }
            })
            .writer(new ItemWriter<String>() {
                @Override
                public void write(List<? extends String> items) throws Exception {

                }
            })
            .build();
    }

    @Bean
    public Step partitionerStep() {
        return stepBuilderFactory.get("partitionerStep")
            .partitioner(myStep1())
            .gridSize(2)
            .build();
    }

    @Bean
    public Step jobStep() {
        return stepBuilderFactory.get("jobStep")
            .job(myJob())
            .build();
    }

    @Bean
    public Job myJob() {
        return jobBuilderFactory.get("myJob")
            .start(flowStep())
            .build();
    }


    @Bean
    public Step flowStep() {
        return stepBuilderFactory.get("flowStep")
            .flow(myFlow())
            .build();
    }

    @Bean
    public Flow myFlow() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("myFlow");
        flowBuilder.start(myStep2()).end();

        return  flowBuilder.build();
    }

}
