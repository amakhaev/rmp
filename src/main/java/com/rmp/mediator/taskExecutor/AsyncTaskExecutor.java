package com.rmp.mediator.taskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Provides the async task executor to make any jobs in another thread
 */
public class AsyncTaskExecutor {

    private static final int POOL_SIZE = 3;

    private ExecutorService executorService;

    /**
     * Initialize new instance of {@link AsyncTaskExecutor}
     */
    public AsyncTaskExecutor() {
        this.executorService = Executors.newFixedThreadPool(POOL_SIZE);
    }

    /**
     * Executes the task in another thread
     *
     * @param task - the task to execute
     */
    public void executeTask(Runnable task) {
        this.executorService.execute(task);
    }
}
