package com.example.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public  class DownloadManager {

    private ThreadPoolExecutor executor;

    public DownloadManager() {
        executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                15, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    }

    public void start(){
        try {
            List<Callable<Object>> subTasks = new ArrayList<>(5);
            for(int i=0;i<5;++i){
                final int ii = i;
                subTasks.add(Executors.callable(new Runnable(){

                    @Override
                    public void run() {
                        System.out.println(ii+" start");
                        if(ii == 4){
                            throw new NullPointerException("aa");
                        }
                        try {
                            Thread.sleep(ii*3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(ii+" end");
                    }
                }));
            }
            executor.invokeAll(subTasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("all end");
        }
    }
}
