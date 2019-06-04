package pers.xiaoming.fault_tolerance.hystrix.backends;

import java.io.IOException;

public interface HttpClient {
    String get(long id) throws IOException;
}
