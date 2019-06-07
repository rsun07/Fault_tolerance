package pers.xiaoming.fault_tolerance.resilience4j.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xiaoming.fault_tolerance.common.entity.AirlineInfo;
import pers.xiaoming.fault_tolerance.common.entity.HotelInfo;
import pers.xiaoming.fault_tolerance.common.entity.TripInfo;

import java.io.IOException;

@Service
public class TripService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final HotelService hotelService;
    private final AirlineService airlineService;

    @Autowired
    public TripService(HotelService hotelService, AirlineService airlineService) {
        this.hotelService = hotelService;
        this.airlineService = airlineService;
    }

    public TripInfo get(long id) throws Exception {
        String hotelInfoStr = hotelService.get(id);
        String airlineInfoStr = airlineService.get(id);

        return createTripInfo(id, hotelInfoStr, airlineInfoStr);
    }

    private TripInfo createTripInfo(long id, String hotelInfoStr, String airlineInfoStr) throws IOException {
        HotelInfo hotelInfo = OBJECT_MAPPER.readValue(hotelInfoStr, HotelInfo.class);
        AirlineInfo airlineInfo = OBJECT_MAPPER.readValue(airlineInfoStr, AirlineInfo.class);

        return TripInfo.builder()
                .id(id)
                .hotelInfo(hotelInfo)
                .airlineInfo(airlineInfo)
                .build();
    }
}
