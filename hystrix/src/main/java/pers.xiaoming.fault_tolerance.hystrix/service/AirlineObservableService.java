package pers.xiaoming.fault_tolerance.hystrix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixObservableCommandFactory;
import rx.Observable;

@Service
public class AirlineObservableService {
    private HttpClient client;
    private HystrixObservableCommandFactory hystrixObservableCommandFactory;

    @Autowired
    public AirlineObservableService(
            @Qualifier("airlineClient") HttpClient client,
            @Qualifier("airlineHystrixObservableCommandFactory") HystrixObservableCommandFactory hystrixCommandFactory) {
        this.client = client;
        this.hystrixObservableCommandFactory = hystrixCommandFactory;
    }

    public Observable<String> getHotObservable(long id) {
        return hystrixObservableCommandFactory.createCommand(client, id).observe();
    }

    public Observable<String> getColdObservable(long id) {
        return hystrixObservableCommandFactory.createCommand(client, id).toObservable();
    }
}
