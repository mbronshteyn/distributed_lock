package com.example.distlock.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@Slf4j
public class DelayService {
    final long delay = 100000;

    final
    RetryTemplate retryTemplate;

    public DelayService(RetryTemplate retryTemplate) {
        this.retryTemplate = retryTemplate;
    }

    @Retryable(value = { RuntimeException.class }, maxAttempts = 4, backoff = @Backoff(delay = delay))
    public void retryServiceWithCustomization() {
        log.info("inside retryable method");
        throw new RuntimeException("retry exception");
    }

    public void templateRetryService() {
        log.info("throw RuntimeException in method templateRetryService()");
        throw new RuntimeException();
    }

    public String hello() {
        return "Hello World!!!";
    }
}

