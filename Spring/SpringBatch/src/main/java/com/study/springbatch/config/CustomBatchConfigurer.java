package com.study.springbatch.config;

import com.zaxxer.hikari.util.IsolationLevel;
import javax.sql.DataSource;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Isolation;

@Configuration
public class CustomBatchConfigurer extends BasicBatchConfigurer {

    private final DataSource dataSource;

    protected CustomBatchConfigurer(BatchProperties properties, DataSource dataSource,
        TransactionManagerCustomizers transactionManagerCustomizers) {
        super(properties, dataSource, transactionManagerCustomizers);
        this.dataSource = dataSource;
    }

    @Override
    protected JobRepository createJobRepository() throws Exception {
        JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
        factoryBean.setDataSource(dataSource); // 설정하지 않아도 기본적으로 설정 됨.
        factoryBean.setTransactionManager(getTransactionManager()); // BasicBatchConfigurer에 있는 메서드
        factoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");

//        factoryBean.setTablePrefix("LOG_BATCH");

        return factoryBean.getObject();
    }
}
