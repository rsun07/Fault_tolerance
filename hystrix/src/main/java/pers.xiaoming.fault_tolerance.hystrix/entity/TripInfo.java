package pers.xiaoming.fault_tolerance.hystrix.entity;

import lombok.Data;

@Data
public class TripInfo {
    private long id;
    private HotelInfo hotelInfo;
    private AirlineInfo airlineInfo;

    public TripInfo(long id) {
        this.id = id;
    }
}
