package com.example.distlock.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableAsync
public class ScheduledService {

  private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss");

  // initialDelay = 3 second.
  // fixedDelay = 2 second.
//  @Scheduled(cron = "*/5 * * * * ?")
  @Async
  public void writeCurrentTime() {

    Date now = new Date();

    String nowString = df.format(now);

    System.out.println("ScheduledService: Now is: " + nowString + " thread: " + Thread.currentThread().getName());
  }
}
