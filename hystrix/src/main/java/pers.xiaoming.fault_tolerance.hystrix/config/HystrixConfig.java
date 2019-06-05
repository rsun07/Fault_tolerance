package pers.xiaoming.fault_tolerance.hystrix.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixCommandFactory;
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
}
