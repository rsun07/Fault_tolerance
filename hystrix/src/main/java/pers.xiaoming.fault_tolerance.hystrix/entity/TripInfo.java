package pers.xiaoming.fault_tolerance.hystrix.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class TripInfo {
    private long id;
    private HotelInfo hotelInfo;
    private AirlineInfo airlineInfo;

    public TripInfo(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripInfo tripInfo = (TripInfo) o;
        return id == tripInfo.id &&
                Objects.equals(hotelInfo, tripInfo.hotelInfo) &&
                Objects.equals(airlineInfo, tripInfo.airlineInfo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, hotelInfo, airlineInfo);
    }
}
