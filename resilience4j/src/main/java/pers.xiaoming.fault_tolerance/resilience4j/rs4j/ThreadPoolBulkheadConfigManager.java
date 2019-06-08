package pers.xiaoming.fault_tolerance.resilience4j.rs4j;

import io.github.resilience4j.bulkhead.ThreadPoolBulkheadConfig;

public class ThreadPoolBulkheadConfigManager {
    private static final int QUEUE_CAPACITY = 100;
    private static final int KEEP_ALIVE_TIME_IN_MILLIS = 100;

    private int coreThreadPoolSize;
    private int maxThreadPoolSize;
    private int queueCapacity;
    private int keepAliveTimeInMillis;

    public ThreadPoolBulkheadConfig getThreadPoolBulkheadConfig() {
        ThreadPoolBulkheadConfig.Builder builder = ThreadPoolBulkheadConfig.custom();

        if (coreThreadPoolSize > 0) {
            builder.coreThreadPoolSize(coreThreadPoolSize);
        }

        if (maxThreadPoolSize > 0) {
            builder.maxThreadPoolSize(maxThreadPoolSize);
        }

        return builder
                .queueCapacity(queueCapacity > 0 ? queueCapacity : QUEUE_CAPACITY)
                .keepAliveTime(keepAliveTimeInMillis > 0 ? keepAliveTimeInMillis : KEEP_ALIVE_TIME_IN_MILLIS)
                .build();
    }
}
