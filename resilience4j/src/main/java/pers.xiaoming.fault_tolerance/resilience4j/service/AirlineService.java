package pers.xiaoming.fault_tolerance.resilience4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixCommandFactory;
import rx.Observable;

import java.util.concurrent.Future;

@Service
public class AirlineService {
    private HttpClient client;
    private HystrixCommandFactory hystrixCommandFactory;

    @Autowired
    public AirlineService(
            @Qualifier("airlineClient") HttpClient client,
            @Qualifier("airlineHystrixCommandFactory") HystrixCommandFactory hystrixCommandFactory) {
        this.client = client;
        this.hystrixCommandFactory = hystrixCommandFactory;
    }

    public String get(long id) {
        return hystrixCommandFactory.createCommand(client, id).execute();
    }

    public Future<String> getAsync(long id) {
        return hystrixCommandFactory.createCommand(client, id).queue();
    }

    public Observable<String> getHotObservable(long id) {
        return hystrixCommandFactory.createCommand(client, id).observe();
    }

    public Observable<String> getColdObservable(long id) {
        return hystrixCommandFactory.createCommand(client, id).toObservable();
    }
}
