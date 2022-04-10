package com.study.springbatch.json;

import com.study.springbatch.CustomItemProcessor;
import com.study.springbatch.CustomItemWriter;
import com.study.springbatch.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@RequiredArgsConstructor
@Configuration
public class JsonConfig {

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
            .<Member, Member>chunk(2)
            .reader(itemReader())
            .processor(itemProcessor())
            .writer(itemWriter())
            .build();
    }

    @Bean
    public ItemReader<? extends Member> itemReader() {
        return new JsonItemReaderBuilder<Member>()
            .name("JsonReader")
            .resource(new ClassPathResource("/member.json"))
            .jsonObjectReader(new JacksonJsonObjectReader<>(Member.class))
            .build();
    }

    @Bean
    public ItemProcessor<? super Member, Member> itemProcessor() {
        return new CustomItemProcessor();
    }

    @Bean
    public ItemWriter<? super Member> itemWriter() {
        return new JsonFileItemWriterBuilder<>()
            .name("jsonFileItemWriter")
            .resource(new FileSystemResource("/Users/a1101720/IdeaProjects/TIL/Spring/SpringBatch/src/main/resources/memberout.json"))
            .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
            .append(true)
            .build();
    }
}
