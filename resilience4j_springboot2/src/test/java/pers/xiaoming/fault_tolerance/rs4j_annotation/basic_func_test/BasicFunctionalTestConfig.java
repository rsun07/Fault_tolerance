package pers.xiaoming.fault_tolerance.rs4j_annotation.basic_func_test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.test.client.TestAirlineDefaultValueHttpClient;
import pers.xiaoming.fault_tolerance.common.test.client.TestHotelDefaultValueHttpClient;

@Profile("rs4j-annotation-basic-func-test")
@Configuration
public class BasicFunctionalTestConfig {
    // Indicates that a bean should be given preference when multiple candidates
    // are qualified to autowire a single-valued dependency.
    @Primary
    @Bean
    @Qualifier("hotelClient")
    public HttpClient getMockHotelClient() {
        return new TestHotelDefaultValueHttpClient();
    }

    @Primary
    @Bean
    @Qualifier("airlineClient")
    public HttpClient getMockAirlineClient() {
        return new TestAirlineDefaultValueHttpClient();
    }
}
