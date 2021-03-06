package pers.xiaoming.fault_tolerance.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirlineInfo that = (AirlineInfo) o;
        return refNum == that.refNum &&
                Objects.equals(name, that.name) &&
                Objects.equals(departureAirportName, that.departureAirportName) &&
                Objects.equals(arrivalAirportName, that.arrivalAirportName) &&
                Objects.equals(departureTime, that.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), refNum, name, departureAirportName, arrivalAirportName, departureTime);
    }
}
