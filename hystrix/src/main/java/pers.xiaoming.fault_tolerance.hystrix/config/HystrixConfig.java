package pers.xiaoming.fault_tolerance.hystrix.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixCommandFactory;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixObservableCommandFactory;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixOptionalConfigs;

@Configuration
public class HystrixConfig {
    @Bean
    @Qualifier("hotelHystrixCommandFactory")
    public HystrixCommandFactory<String> getHotelHystrixCommandFactory() {
        return new HystrixCommandFactory<String>("Hotel", "Get_Hotel_Info", new HystrixOptionalConfigs<>());
    }

    @Bean
    @Qualifier("airlineHystrixCommandFactory")
    public HystrixCommandFactory<String> getAirlineHystrixCommandFactory() {
        return new HystrixCommandFactory<String>("Airline", "Get_Airline_Info", new HystrixOptionalConfigs<>());
    }

    @Bean
    @Qualifier("hotelHystrixObservableCommandFactory")
    public HystrixObservableCommandFactory<String> getHotelHystrixObservableCommandFactory() {
        return new HystrixObservableCommandFactory<String>("Hotel", "Get_Hotel_Info_Observable", new HystrixOptionalConfigs<>());
    }

    @Bean
    @Qualifier("airlineHystrixObservableCommandFactory")
    public HystrixObservableCommandFactory<String> getAirlineHystrixObservableCommandFactory() {
        return new HystrixObservableCommandFactory<String>("Airline", "Get_Airline_Info_Observable", new HystrixOptionalConfigs<>());
    }
}
