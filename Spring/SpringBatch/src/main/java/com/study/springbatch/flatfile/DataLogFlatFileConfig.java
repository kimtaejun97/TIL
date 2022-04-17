package com.study.springbatch.flatfile;

import com.study.springbatch.datalog.TrOrdDataLog;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


@RequiredArgsConstructor
@Configuration
public class DataLogFlatFileConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    @Autowired
    @Qualifier("dataLogDatasource")
    private DataSource dataLogDatasource;

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
            .<TrOrdDataLog, TrOrdDataLog>chunk(100)
            .reader(itemReader())
            .writer(itemWriter())
            .build();
    }

    @Bean
    public ItemReader itemReader() {
        return new FlatFileItemReaderBuilder<TrOrdDataLog>()
            .name("flatFile")
            .resource(new ClassPathResource("/log_data.txt"))
            .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
            .targetType(TrOrdDataLog.class)
            .delimited().delimiter("\t")
            .names("dataSeq", "dataType","dataTypeDtls", "data", "ordNo", "prdNo" , "mallClf", "mallClfDtlCd",
                "createIp", "createDt", "createNo")
            .linesToSkip(1)
            .build();
    }
    @Bean
    public ItemWriter<? super TrOrdDataLog> itemWriter() {
        return new JdbcBatchItemWriterBuilder<>()
            .dataSource(dataLogDatasource)
            .sql(
                "insert into TR_ORD_DATA_LOG(data_seq, data_type_dtls, data, ord_no, data_type, prd_no, mall_clf, mall_clf_dtl_cd, create_ip, create_dt, create_no)"
                    + " values(:dataSeq, :dataTypeDtls, :data, :ordNo, :dataType, :prdNo, :mallClf, :mallClfDtlCd, :createIp, :createDt, :createNo)")
            .beanMapped()
            .build();
    }
}
