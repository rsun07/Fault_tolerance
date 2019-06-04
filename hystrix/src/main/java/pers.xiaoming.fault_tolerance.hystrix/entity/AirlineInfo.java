package pers.xiaoming.fault_tolerance.hystrix.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirlineInfo {
    private int refNum;
    private String name;
    private String departureAirportName;
    private String arrivalAirportName;
    private String departureTime;
}
