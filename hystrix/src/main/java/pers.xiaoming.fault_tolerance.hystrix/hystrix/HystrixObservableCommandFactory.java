package pers.xiaoming.fault_tolerance.hystrix.hystrix;

import com.netflix.hystrix.HystrixObservableCommand;
import pers.xiaoming.fault_tolerance.common.backends.HttpGetInterface;
import rx.Observable;

public class HystrixObservableCommandFactory<T> {
    private final HystrixObservableCommand.Setter hystrixObservableCommandSetter;

    private final T fallback;

    public HystrixObservableCommandFactory(String groupKey, String commandKey, HystrixConfigsManager<T> configsManager) {
        this.fallback = configsManager.getFallback();

        this.hystrixObservableCommandSetter = configsManager.getHystrixObservableCommandSetter(groupKey, commandKey);
    }

    public HystrixObservableCommand<T> createCommand(HttpGetInterface<T> httpGetFunc) {
        return new HystrixObservableCommand<T>(hystrixObservableCommandSetter) {
            @Override
            protected Observable<T> construct() {
                return Observable.fromCallable(httpGetFunc::get);
            }

            @Override
            protected Observable<T> resumeWithFallback() {
                return Observable.just(fallback);
            }
        };
    }
}
