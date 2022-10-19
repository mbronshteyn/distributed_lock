package com.example.distlock.services;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ShedLockTaskScheduler {

    @SchedulerLock(
            name = "UNIQUE_KEY_FOR_SHEDLOCK_SCHEDULER",
            lockAtLeastFor = "2s", // lock for at least 5 seconds
            lockAtMostFor = "10s" // lock for at most 10 seconds
    )
    // @Scheduled(cron = "*/5 * * * * ?")
    public void scheduledTaskToRun() {
        LockAssert.assertLocked();
        log.info("Test " + Thread.currentThread().getName());
    }

}
