/* EVIDEN (C)2024 */
package com.eviden.demo.infrastructure.adapters.outgoing.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SpringBatchJobLauncher {

    @Qualifier("job") private final Job job;

    private final JobLauncher jobLauncher;

    @Scheduled(cron = "0/20 * * * * *")
    public void runSpringBatchPurchaseOrderJob()
            throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
                    JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = new JobParameters();
        jobLauncher.run(job, jobParameters);
    }
}
