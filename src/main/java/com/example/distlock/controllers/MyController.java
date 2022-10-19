package com.example.distlock.controllers;

import com.example.distlock.services.DelayService;
import com.example.distlock.services.LockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/")
public class MyController {
    private final LockService lockService;

    private final DelayService delayService;

    public MyController(LockService lockService, DelayService delayService) {
        this.lockService = lockService;
        this.delayService = delayService;
    }

    @PutMapping("/lock")
    public String lock(){
        return lockService.lock();
    }

    @PutMapping("/properLock")
    public String properLock(){
        return lockService.properLock();
    }

    @PutMapping("/failLock")
    public String failLock(){
        lockService.failLock();
        return "fail lock called, output in logs";
    }

    @PutMapping("/delayLock")
    public String delayLock(){
        lockService.delayLock();
        return "delay lock called, output in logs";
    }

    @GetMapping("/delay")
    public String testDelay() {

        try {
            delayService.retryServiceWithCustomization();
            // retryServiceWithCustomization();

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "test delay called";
    }

    @Retryable(value = { Exception.class }, maxAttempts = 4, backoff = @Backoff(delay = 10000))
    public void retryServiceWithCustomization() throws Exception {
        log.info("inside retryable method");
        throw new Exception("retry exception");
    }

//    @PutMapping("/testWaitLock")
//    public String waitLock(@RequestParam long waitTime, @RequestParam long sleepTime){
//        return lockService.testWaitLock(waitTime, sleepTime);
//    }
}
