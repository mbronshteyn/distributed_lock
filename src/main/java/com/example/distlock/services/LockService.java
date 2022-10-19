package com.example.distlock.services;

public interface LockService {
    String lock();
    void failLock();

    void delayLock();
    String properLock();
}
