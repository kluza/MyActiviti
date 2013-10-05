/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package protocol;

import org.junit.Test;
import pl.edu.agh.heart.comm.HeartRepository;

/** @author ja */
public class HQEdRepoTest {
    
    @Test
    public void test() throws Exception {
        HeartRepository heartRepo = new HeartRepository();
        HeartRepository hqRepo = new HeartRepository(true);
        
        String plocHmr = heartRepo.getModelHMR("ploc", "jBPM");
        hqRepo.pushModelHMR("ploc", "jBPM", plocHmr);
    }
}
