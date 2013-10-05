/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package protocol;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import pl.edu.agh.heart.comm.HeartRequestHandler;
import pl.edu.agh.heart.comm.HeartHttpConnector;

/** @author ja */
public class PlocInference {
    HeartHttpConnector httpConnector = new HeartHttpConnector(true, "localhost", 8090);
    
    @Test
    public void test() throws Exception {
        HeartRequestHandler heartHandler = new HeartRequestHandler();
        String stateDef = heartHandler.makeStateDef(makeStateMap());
        String request =
                heartHandler.inferenceRequest("activiti", "ploc", "foi", new String[] {"DriverDiscount2"},
                        stateDef);
        System.out.println(request);
        String response = httpConnector.performRequest(request);
        System.out.println(response);
    }
    
    private Map<String, Object> makeStateMap() {
        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("accidentNo", "1");
//        map.put("clientClass", "3");
        map.put("driverAge", 23);
        map.put("driverLicenceAge", 2);
        map.put("driverDiscount", 100);
        return map;
    }
}
