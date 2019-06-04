package pers.xiaoming.fault_tolerance.hystrix.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelInfo {
    private int refNum;
    private String name;
    private String checkinDate;
    private String checkoutDate;
}
