package pers.xiaoming.fault_tolerance.common.test.executor;

import java.io.IOException;

@FunctionalInterface
public interface ControllerFunction<T, R> {
    R get(T t) throws IOException;
}
