package pers.xiaoming.fault_tolerance.hystrix.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.xiaoming.fault_tolerance.hystrix.backends.HttpClient;
import pers.xiaoming.fault_tolerance.hystrix.backends.HttpClientImpl;

@Configuration
public class BackendsConfig {

    @Value("${backends.hosts.hotel-endpoint}")
    private String hotelEndpoint;

    @Value("${backends.hosts.airline-endpoint}")
    private String airlineEndpoint;

    @Bean
    @Qualifier("hotelClient")
    public HttpClient getHotelClient() {
        return new HttpClientImpl(hotelEndpoint, "/hotel");
    }

    @Bean
    @Qualifier("airlineClient")
    public HttpClient getAirlineClient() {
        return new HttpClientImpl(hotelEndpoint, "/airline");
    }
}
