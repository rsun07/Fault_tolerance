package pers.xiaoming.fault_tolerance.common.test.constant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.xiaoming.fault_tolerance.common.entity.AirlineInfo;
import pers.xiaoming.fault_tolerance.common.entity.HotelInfo;
import pers.xiaoming.fault_tolerance.common.entity.TripInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestConstants {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final HotelInfo DEMO_HOTEL_INFO = HotelInfo.builder()
            .refNum(101)
            .name("Hyatt")
            .checkinDate(LocalDate.now().toString())
            .checkoutDate(LocalDate.now().plusDays(1).toString())
            .build();

    private static final AirlineInfo DEMO_AIRLINE_INFO = AirlineInfo.builder()
            .refNum(101)
            .name("AA")
            .departureAirportName("IAD")
            .arrivalAirportName("LAX")
            .departureTime(LocalDateTime.now().toString())
            .build();

    public static final int DEMO_TRIP_ID = 888;

    public static final TripInfo DEMO_TRIP_INFO = TripInfo.builder()
            .id(DEMO_TRIP_ID)
            .hotelInfo(DEMO_HOTEL_INFO)
            .airlineInfo(DEMO_AIRLINE_INFO)
            .build();

    public static final HotelInfo FALLBACK_HOTEL_INFO = HotelInfo.builder()
            .refNum(888)
            .name("Hilton")
            .checkinDate(LocalDate.now().toString())
            .checkoutDate(LocalDate.now().plusDays(2).toString())
            .build();

    public static String DEMO_HOTEL_INFO_STRING;
    public static String DEMO_AIRLINE_INFO_STRING;
    public static String FALLBACK_HOTEL_INFO_STRING;

    static {
        try {
            DEMO_HOTEL_INFO_STRING = OBJECT_MAPPER.writeValueAsString(DEMO_HOTEL_INFO);
            DEMO_AIRLINE_INFO_STRING = OBJECT_MAPPER.writeValueAsString(DEMO_AIRLINE_INFO);
            FALLBACK_HOTEL_INFO_STRING = OBJECT_MAPPER.writeValueAsString(FALLBACK_HOTEL_INFO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
