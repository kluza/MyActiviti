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
        
        System.out.println(model.getSchemesAndRules());
        
        String foo = "xrule ms/1:[month in [january,february,december]]==>[season set summer].";
        model.parseData(foo);
//        System.out.println(hr.pushModelHMR("test", "jBPM", model.toString()));
        System.out.println(model.getSchemeNames());
        System.out.println(hr.verifyModel("first", "jBPM", "vsubsume", "nigger"));
    }
}
