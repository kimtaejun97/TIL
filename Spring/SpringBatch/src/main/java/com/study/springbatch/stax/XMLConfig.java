package com.study.springbatch.stax;

import com.study.springbatch.CustomItemProcessor;
import com.study.springbatch.CustomItemWriter;
import com.study.springbatch.Member;
import com.thoughtworks.xstream.XStream;
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
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;

@RequiredArgsConstructor
@Configuration
public class XMLConfig {

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
        return new StaxEventItemReaderBuilder<Member>()
            .name("staXml")
            .resource(new ClassPathResource("/member.xml"))
            .addFragmentRootElements("member")
            .unmarshaller(itemUnmarshaller())
            .build();
    }

    @Bean
    public Unmarshaller itemUnmarshaller() {
        Map<String, Class<?>> aliases = new HashMap<>();
        aliases.put("member", Member.class);
        aliases.put("name", String.class);
        aliases.put("id", String.class);

        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        xStreamMarshaller.setAliases(aliases);

        return xStreamMarshaller;
    }

    @Bean
    public ItemProcessor<? super Member, Member> itemProcessor() {
        return new CustomItemProcessor();
    }

    @Bean
    public ItemWriter<? super Member> itemWriter() {
        return new StaxEventItemWriterBuilder<>()
            .name("staxItemWriter")
            .resource(new FileSystemResource("/Users/a1101720/IdeaProjects/TIL/Spring/SpringBatch/src/main/resources/memberout.xml"))
            .rootTagName("member")
            .marshaller(itemMarshaller())
            .overwriteOutput(true)
            .build();
    }

    @Bean
    public Marshaller itemMarshaller() {
        Map<String, Class<?>> aliases = new HashMap<>();
        aliases.put("member", Member.class);
        aliases.put("name", String.class);
        aliases.put("id", String.class);

        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        xStreamMarshaller.setAliases(aliases);

        return xStreamMarshaller;
    }
}
