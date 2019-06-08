package pers.xiaoming.fault_tolerance.hystrix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixCommandFactory;
import rx.Observable;

import java.util.concurrent.Future;

@Service
public class HotelService {
    private HttpClient client;
    private HystrixCommandFactory<String> hystrixCommandFactory;

    @Autowired
    public HotelService(
            @Qualifier("hotelClient") HttpClient client,
            @Qualifier("hotelHystrixCommandFactory") HystrixCommandFactory<String> hystrixCommandFactory) {
        this.client = client;
        this.hystrixCommandFactory = hystrixCommandFactory;
    }

    public String get(long id) {
        return hystrixCommandFactory.createCommand(() -> client.get(id)).execute();
    }

    public Future<String> getAsync(long id) {
        return hystrixCommandFactory.createCommand(() -> client.get(id)).queue();
    }

    public Observable<String> getHotObservable(long id) {
        return hystrixCommandFactory.createCommand(() -> client.get(id)).observe();
    }

    public Observable<String> getColdObservable(long id) {
        return hystrixCommandFactory.createCommand(() -> client.get(id)).toObservable();
    }
}
