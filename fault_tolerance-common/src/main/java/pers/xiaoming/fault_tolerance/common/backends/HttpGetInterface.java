package pers.xiaoming.fault_tolerance.common.backends;

import java.io.IOException;

@FunctionalInterface
public interface HttpGetInterface<T> {
    T get() throws IOException;
}
