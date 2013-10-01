/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package protocol;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;
import pl.edu.agh.heart.comm.HeartRepository;
import pl.edu.agh.heart.model.HMRModel;

/** @author ja */
public class HMRObjectModel {
    
    @Test
    public void test() throws Exception {
        HeartRepository hr = new HeartRepository();
//        String modeldef = hr.getModelHMR("first", "jBPM");
//        HMRModel model = new HMRModel(modeldef);
//        
//        System.out.println(model.getSchemesAndRules());
//        
//        String foo = "xrule ms/1:[month in [january,february,december]]==>[season set summer].";
//        model.parseData(foo);
//        System.out.println(hr.pushModelHMR("test", "jBPM", model.toString()));
//        System.out.println(model.getSchemeNames());
        String schemepat = "xschm.*\\].";
        Pattern schemePattern = Pattern.compile(schemepat);
        String hmr = hr.getModelHMR("ploc", "jBPM");
//        System.out.println(hmr);
        HMRModel hmrModel = new HMRModel(hmr);
        System.out.println(hmrModel.getSchemeNames());
        Matcher matcher = schemePattern.matcher(hmr);
        while (matcher.find()) {
            String m = matcher.group();
            System.out.println(m);
            System.out.println(m.split("xschm")[1].split(":")[0].trim());
        }
    }
}
