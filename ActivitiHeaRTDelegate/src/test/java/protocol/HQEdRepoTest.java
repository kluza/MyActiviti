/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package protocol;

import org.junit.Test;
import pl.edu.agh.heart.comm.HQEdHttpConnector;
import pl.edu.agh.heart.comm.HeartRepository;

/** @author ja */
public class HQEdRepoTest {
    
    @Test
    public void test() throws Exception {
        HeartRepository heartRepo = new HeartRepository();
        HeartRepository hqRepo = new HeartRepository(true);
        
        String request =
                "[model,add,hmr,'MyName','Username','xtype [ name: week_days, base: symbolic, ordered: yes, domain: [moday,tuesday], desc: \'This is only one definition\'].'].";
        
//        String plocHmr = heartRepo.getModelHMR("ploc", "jBPM");
//        for (int i = 0; i < 10; i++) {
//            hqRepo.pushModelHMR("ploc", "jBPM", plocHmr);
//        }
        
        HQEdHttpConnector http = new HQEdHttpConnector(true, "127.0.0.1", 8082);
        for (int i = 0; i < 10; i++) {
            String response = http.performRequest(request);
            System.out.println(response);
        }
    }
}
