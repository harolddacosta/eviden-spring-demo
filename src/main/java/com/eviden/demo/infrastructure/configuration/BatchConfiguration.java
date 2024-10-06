/* EVIDEN (C)2024 */
package com.eviden.demo.infrastructure.configuration;

import com.eviden.demo.infrastructure.adapters.outgoing.batch.listeners.PurchaseOrderChunkListener;
import com.eviden.demo.infrastructure.adapters.outgoing.batch.processors.PurchaseOrderProcessor;
import com.eviden.demo.infrastructure.adapters.outgoing.batch.readers.PurchaseOrderReader;
import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.entities.PurchaseOrderEntity;
import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.repositories.PurchaseOrderJpaRepository;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
public class BatchConfiguration {

    @Bean
    @StepScope
    ItemReader<PurchaseOrderEntity> purchaseOrderReader(PurchaseOrderJpaRepository repository) {
        return new PurchaseOrderReader(repository);
    }

    @Bean
    PurchaseOrderProcessor purchaseOrderProcessor() {
        return new PurchaseOrderProcessor();
    }

    @Bean
    RepositoryItemWriter<PurchaseOrderEntity> purchaseOrderWriter(
            PurchaseOrderJpaRepository repository) {
        return new RepositoryItemWriterBuilder<PurchaseOrderEntity>()
                .repository(repository)
                .methodName("save")
                .build();
    }

    @Bean
    PurchaseOrderChunkListener purchaseOrderChunkListener() {
        return new PurchaseOrderChunkListener();
    }

    @Bean
    Job job(
            JobRepository jobRepository,
            JpaTransactionManager jpaTransactionManager,
            ItemReader<PurchaseOrderEntity> purchaseOrderReader,
            RepositoryItemWriter<PurchaseOrderEntity> purchaseOrderWriter) {
        return new JobBuilder("jpaSampleJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(
                        new StepBuilder("load_orders", jobRepository)
                                .<PurchaseOrderEntity, PurchaseOrderEntity>chunk(
                                        10, jpaTransactionManager)
                                .listener(purchaseOrderChunkListener())
                                .reader(purchaseOrderReader)
                                .processor(purchaseOrderProcessor())
                                .writer(purchaseOrderWriter)
                                .allowStartIfComplete(true)
                                .build())
                .build();
    }
}
