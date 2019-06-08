package pers.xiaoming.fault_tolerance.resilience4j.rs4j;

import io.github.resilience4j.bulkhead.BulkheadConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SemaphoreBulkHeadConfigManager {
    private static final int MAX_CONCURRENT_CALLS = 50;
    private static final long MAX_WAIT_TIME_IN_MILLIS = 1000L;

    private int maxConcurrentCalls;
    private long maxWaitTimeInMillis;

    public BulkheadConfig getBulkheadConfig() {
        return BulkheadConfig.custom()
                .maxConcurrentCalls(maxConcurrentCalls > 0 ? maxConcurrentCalls : MAX_CONCURRENT_CALLS)
                .maxWaitTime(maxWaitTimeInMillis > 0 ? maxWaitTimeInMillis : MAX_WAIT_TIME_IN_MILLIS)
                .build();
    }
}
