package pers.xiaoming.fault_tolerance.common.test.executor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import pers.xiaoming.fault_tolerance.common.entity.TripInfo;
import pers.xiaoming.fault_tolerance.common.test.constant.TestConstants;

@Slf4j
public class FallbackTestExecutor {

    public static void execute(ControllerFunction<Integer, TripInfo> func) {
        TripInfo fallbackTripInfo = null;
        while(true) {
            try {
                fallbackTripInfo = func.get(TestConstants.DEMO_TRIP_ID);
            } catch (Exception e) {
                // no log will be printed
                log.info(e.getMessage());
            }

            if (fallbackTripInfo != null) {
                break;
            }
        }
        Assert.assertEquals(fallbackTripInfo.getHotelInfo(), TestConstants.FALLBACK_HOTEL_INFO);
    }
}
