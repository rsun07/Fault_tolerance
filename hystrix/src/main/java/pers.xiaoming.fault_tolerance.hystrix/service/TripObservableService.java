package pers.xiaoming.fault_tolerance.hystrix.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xiaoming.fault_tolerance.common.entity.AirlineInfo;
import pers.xiaoming.fault_tolerance.common.entity.HotelInfo;
import pers.xiaoming.fault_tolerance.common.entity.TripInfo;
import rx.Observable;

import java.io.IOException;

@Service
public class TripObservableService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final HotelObservableService hotelService;
    private final AirlineObservableService airlineService;

    @Autowired
    public TripObservableService(HotelObservableService hotelService, AirlineObservableService airlineService) {
        this.hotelService = hotelService;
        this.airlineService = airlineService;
    }

    public TripInfo getFromHotObservable(long id) throws IOException {
        Observable<String> hotelInfoObservable = hotelService.getHotObservable(id);
        Observable<String> airlineInfoObservable = airlineService.getHotObservable(id);

        String hotelInfoStr = hotelInfoObservable.toBlocking().single();
        String airlineInfoStr = airlineInfoObservable.toBlocking().single();

        return createTripInfo(id, hotelInfoStr, airlineInfoStr);
    }

    public TripInfo getFromColdObservable(long id) throws IOException {
        Observable<String> hotelInfoObservable = hotelService.getColdObservable(id);
        Observable<String> airlineInfoObservable = airlineService.getColdObservable(id);

        String hotelInfoStr = hotelInfoObservable.toBlocking().single();
        String airlineInfoStr = airlineInfoObservable.toBlocking().single();

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
