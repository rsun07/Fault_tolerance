package pers.xiaoming.fault_tolerance.hystrix.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelInfo {
    private int refNum;
    private String name;
    private String checkinDate;
    private String checkoutDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelInfo hotelInfo = (HotelInfo) o;
        return refNum == hotelInfo.refNum &&
                Objects.equals(name, hotelInfo.name) &&
                Objects.equals(checkinDate, hotelInfo.checkinDate) &&
                Objects.equals(checkoutDate, hotelInfo.checkoutDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), refNum, name, checkinDate, checkoutDate);
    }
}
