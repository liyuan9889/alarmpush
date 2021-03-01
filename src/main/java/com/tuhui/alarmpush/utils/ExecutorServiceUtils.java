package com.tuhui.alarmpush.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceUtils {

    private  static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static synchronized ExecutorService  getExecutorService(){
        return cachedThreadPool;
    }
}
