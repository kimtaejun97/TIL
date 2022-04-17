package com.study.springbatch.classifier;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

;

@RequiredArgsConstructor
//@Configuration
public class ClassifierConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
            .start(chunkStep1())
            .incrementer(new RunIdIncrementer())
            .build();
    }

    @Bean
    public Step chunkStep1() {
        return stepBuilderFactory.get("chunkStep1")
            .<ProcessorInfo, ProcessorInfo>chunk(2)
            .reader(itemReader())
            .processor(itemProcessor())
            .writer(System.out::println)
            .build();
    }

    @Bean
    public ItemReader<ProcessorInfo> itemReader() {
        return new ItemReader<ProcessorInfo>() {

            int id = 0;

            @Override
            public ProcessorInfo read()
                throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

                ProcessorInfo processorInfo = ProcessorInfo.builder()
                    .id(++this.id)
                    .build();

                return id > 3 ? null : processorInfo;
            }
        };
    }

    @Bean
    public ItemProcessor<? super ProcessorInfo, ProcessorInfo> itemProcessor() {
        ClassifierCompositeItemProcessor processor = new ClassifierCompositeItemProcessor();
        ProcessorClassifier<ItemProcessor, ItemProcessor<?, ? extends ProcessorInfo>> processorClassifier = new ProcessorClassifier();

        Map<Integer, ItemProcessor<ProcessorInfo, ProcessorInfo>> processorMap = new HashMap<>();
        processorMap.put(1, new ClassifiableItemProcessor());
        processorMap.put(2, new ClassifiableItemProcessor2());
        processorMap.put(3, new ClassifiableItemProcessor3());

        processorClassifier.setProcessorMap(processorMap);
        processor.setClassifier(processorClassifier);

        return processor;
    }
}
