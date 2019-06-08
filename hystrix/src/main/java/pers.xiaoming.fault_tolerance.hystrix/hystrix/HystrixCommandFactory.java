package pers.xiaoming.fault_tolerance.hystrix.hystrix;

import com.netflix.hystrix.HystrixCommand;
import pers.xiaoming.fault_tolerance.common.backends.HttpGetInterface;

import java.io.IOException;

public class HystrixCommandFactory<T> {
    private final HystrixCommand.Setter hystrixCommandSetter;

    private final T fallback;

    public HystrixCommandFactory(String groupKey, String commandKey, HystrixConfigsManager<T> configsManager) {
        fallback = configsManager.getFallback();

        this.hystrixCommandSetter = configsManager.getHystrixCommandSetter(groupKey, commandKey);
    }

    public HystrixCommand<T> createCommand(HttpGetInterface<T> httpGetFunc) {
        return new HystrixCommand<T>(hystrixCommandSetter) {
            @Override
            protected T run() throws IOException {
                return httpGetFunc.get();
            }

            @Override
            protected T getFallback() {
                return fallback;
            }
        };
    }
}
