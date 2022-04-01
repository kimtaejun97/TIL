package com.study.springbatch.config;

import com.study.springbatch.CustomJobParametersIncrementer;
import com.study.springbatch.CustomJobParametersValidator;
import com.study.springbatch.MyTasklet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.job.DefaultJobParametersExtractor;
import org.springframework.batch.core.step.job.JobParametersExtractor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
//@Configuration
public class SimpleJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobExecutionListener jobExecutionListener;

    private List<String> strings = new ArrayList<>(Arrays.asList("item1", "item2", "item3", "item4"));

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
            .start(myStep1())
            .next(myStep2())
            .next(chunkStep())
            .next(jobStep(null))
            .listener(jobExecutionListener)
            .validator(new DefaultJobParametersValidator(new String[]{"name"}, new String[]{"date", "weight", "seq"}))
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
            .allowStartIfComplete(true)
            .startLimit(3)
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
                    if (!strings.isEmpty()) {
                        return strings.remove(0);
                    }
                    return null;
                }
            })
            .processor(new ItemProcessor<String, String>() {
                @Override
                public String process(String item) throws Exception {
                    return item.toUpperCase(Locale.ROOT);
                }
            })
            .writer(new ItemWriter<String>() {
                @Override
                public void write(List<? extends String> items) throws Exception {
                    items.forEach(System.out::println);
                }
            })
            .build();
    }

    @Bean
    public Step jobStep(JobLauncher jobLauncher) {
        return stepBuilderFactory.get("jobStep")
            .job(childJob())
            .launcher(jobLauncher)
            .parametersExtractor(jobParametersExtractor())
            .listener(new StepExecutionListener() {
                @Override
                public void beforeStep(StepExecution stepExecution) {
                    stepExecution.getExecutionContext().put("date", new Date());
                }

                @Override
                public ExitStatus afterStep(StepExecution stepExecution) {
                    return null;
                }
            })
            .build();
    }

    private JobParametersExtractor jobParametersExtractor() {
        DefaultJobParametersExtractor extractor = new DefaultJobParametersExtractor();
        extractor.setKeys(new String[]{"date"});
        return extractor;
    }

    public Job childJob() {
        return jobBuilderFactory.get("childJob")
            .start(myStep3())
            .build();

    }

    @Bean
    public Step myStep3() {
        return stepBuilderFactory.get("myStep3")
            .tasklet(new MyTasklet("myStep3"))
            .build();
    }
}
