package pers.xiaoming.fault_tolerance.resilience4j.config;

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
    public HystrixCommandFactory getHotelHystrixCommandFactory() {
        return new HystrixCommandFactory("Hotel", "Get_Hotel_Info", new HystrixOptionalConfigs());
    }

    @Bean
    @Qualifier("airlineHystrixCommandFactory")
    public HystrixCommandFactory getAirlineHystrixCommandFactory() {
        return new HystrixCommandFactory("Airline", "Get_Airline_Info", new HystrixOptionalConfigs());
    }

    @Bean
    @Qualifier("hotelHystrixObservableCommandFactory")
    public HystrixObservableCommandFactory getHotelHystrixObservableCommandFactory() {
        return new HystrixObservableCommandFactory("Hotel", "Get_Hotel_Info_Observable", new HystrixOptionalConfigs());
    }

    @Bean
    @Qualifier("airlineHystrixObservableCommandFactory")
    public HystrixObservableCommandFactory getAirlineHystrixObservableCommandFactory() {
        return new HystrixObservableCommandFactory("Airline", "Get_Airline_Info_Observable", new HystrixOptionalConfigs());
    }
}
