package pers.xiaoming.fault_tolerance.hystrix.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelInfo {
    private int refNum;
    private String name;
    private String departureAirportName;
    private String arrivalAirportName;
    private LocalDateTime departureTime;
}
