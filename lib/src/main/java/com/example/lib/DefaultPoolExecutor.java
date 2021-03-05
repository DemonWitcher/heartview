package com.example.lib;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class DefaultPoolExecutor {
    public static Executor getInstance() {
        return new ThreadPoolExecutor(10,10,1000, TimeUnit.HOURS,new LinkedBlockingQueue<Runnable>());
    }
}
