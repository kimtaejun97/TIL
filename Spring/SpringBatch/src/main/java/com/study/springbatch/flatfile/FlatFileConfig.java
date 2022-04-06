package com.study.springbatch.flatfile;

import com.study.springbatch.CustomItemProcessor;
import com.study.springbatch.CustomItemWriter;
import com.study.springbatch.Member;
import com.study.springbatch.MyTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


@RequiredArgsConstructor
@Configuration
public class FlatFileConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
            .start(chunkStep1())
            .next(myStep2())
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

//    @Bean
//    public ItemReader<? extends Member> itemReader() {
//        FlatFileItemReader<Member> itemReader = new FlatFileItemReader<>();
//
//        DefaultLineMapper<Member> lineMapper = new DefaultLineMapper<>();
//        lineMapper.setLineTokenizer(new DelimitedLineTokenizer()); // 기본 구분자 ,
//        lineMapper.setFieldSetMapper(new MemberFieldSetMapper());
//
//        itemReader.setLineMapper(lineMapper);
//        itemReader.setResource(new ClassPathResource("/member.csv"));
//        itemReader.setLinesToSkip(1);
//
//        return  itemReader;
//    }

    @Bean
    public ItemReader itemReader() {
        return new FlatFileItemReaderBuilder<Member>()
            .name("flatFile")
            .resource(new ClassPathResource("/member.csv"))
            .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
            .targetType(Member.class)
            .delimited().delimiter(",")
            .names("name", "id")
            .linesToSkip(1)
            .build();
    }

    @Bean
    public Step myStep2() {
        return stepBuilderFactory.get("myStep2")
            .tasklet(new MyTasklet("myStep2"))
            .build();
    }


    @Bean
    public ItemProcessor<? super Member, Member> itemProcessor() {
        return new CustomItemProcessor();
    }

    @Bean
    public ItemWriter<? super Member> itemWriter() {
        return new CustomItemWriter();
    }

}
