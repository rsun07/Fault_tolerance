package pers.xiaoming.fault_tolerance.common.test.executor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import pers.xiaoming.fault_tolerance.common.entity.TripInfo;
import pers.xiaoming.fault_tolerance.common.test.client.CircuitBreakerTestHotelHttpClient;
import pers.xiaoming.fault_tolerance.common.test.constant.TestConstants;

@Slf4j
public class CircuitBreakerTestExecutor {

    public static void execute(int totalRounds, String shotCircuitMsg,
                               CircuitBreakerTestHotelHttpClient client, ControllerFunction<Integer, TripInfo> func)
            throws InterruptedException {

        String lastMessage = null;
        boolean isCurcuitBreakerOpen = false;
        TripInfo tripInfo = null;

        for (int i = 0; i < totalRounds; i++) {
            client.setI(i);

            try {
                tripInfo = func.get(TestConstants.DEMO_TRIP_ID);
                isCurcuitBreakerOpen = false;
            } catch (Exception e) {
                log.info(e.getMessage());

                tripInfo = null;

                lastMessage = e.getMessage();
                if (shotCircuitMsg.equals(lastMessage)) {
                    isCurcuitBreakerOpen = true;
                }
            }

            // pers.xiaoming.fault_tolerance.common.test.client.CircuitBreakerTestHotelHttpClient
            // 0-20 should be success call
            // 21-60 should be failed call
            // 61-100 should be success call
            switch(i) {
                case 12:
                case 92:
                    log.info("Checking at i = {}", i);
                    Assert.assertSame(false, isCurcuitBreakerOpen);
                    Assert.assertEquals(tripInfo, TestConstants.DEMO_TRIP_INFO);
                    log.info("Check passed at i = {}", i);
                    break;
                case 52 :
                    log.info("Checking at i = {}", i);
                    Assert.assertSame(true, isCurcuitBreakerOpen);
                    Assert.assertEquals(shotCircuitMsg, lastMessage);
                    log.info("Check passed at i = {}", i);
                    break;
            }

            Thread.sleep(10);
        }
    }
}
