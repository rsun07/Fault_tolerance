package pers.xiaoming.fault_tolerance.resilience4j.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.xiaoming.fault_tolerance.resilience4j.rs4j.CircuitBreakerConfigManager;
import pers.xiaoming.fault_tolerance.resilience4j.rs4j.Rs4jCommandFactory;

@Configuration
public class Rs4jConfig {
    @Bean
    @Qualifier("hotelRs4jCommandFactory")
    public Rs4jCommandFactory getHotelHystrixCommandFactory() {
        return new Rs4jCommandFactory("Get_Hotel_Info", new CircuitBreakerConfigManager());
    }

    @Bean
    @Qualifier("airlineRs4jCommandFactory")
    public Rs4jCommandFactory getAirlineHystrixCommandFactory() {
        return new Rs4jCommandFactory("Get_Airline_Info", new CircuitBreakerConfigManager());
    }
}
