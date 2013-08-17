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
import pl.edu.agh.heart.model.HMRModel;

/** @author ja */
public class HMRObjectModel {
    
    @Test
    public void test() throws Exception {
        HeartRepository hr = new HeartRepository();
        String modeldef = hr.getModelHMR("first", "jBPM");
        HMRModel model = new HMRModel(modeldef);
        
        System.out.println(model.getAttributes());
    }
    
}