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
import pl.edu.agh.heart.comm.HeartRequestHandler;
import pl.edu.agh.heart.comm.HttpConnector;
import pl.edu.agh.heart.model.HMRModel;

/** @author ja */
public class ProtocolTest {
    
//    @Test
//    public void test() throws Exception {
//        HeartRepository hr = new HeartRepository();
//        Map<String, List<String>> dupa = hr.getModelNames();
//        for (String user: dupa.keySet()) {
//            System.out.println(user + ':' + dupa.get(user));
//        }
//    }
    
    @Test
    public void hmrTest() throws Exception {
        HttpConnector httpConn = new HttpConnector(true, "localhost", 8090);
        HeartRepository hr = new HeartRepository();
        HeartRequestHandler hrh = new HeartRequestHandler();
        String hmrDef = hr.getModelHMR("first", "jBPM");
        HMRModel model = new HMRModel(hmrDef);
        String request =
                "[model,add,hmr,'MyName','Username','xtype [ name: week_days, base: symbolic, ordered: yes, domain: [moday,tuesday], desc: \\'This is only one definition\\'].']. ";
        System.out.println(request);
        String r = java.util.regex.Matcher.quoteReplacement("\\'");
        System.out.println(r);
//        String request2 = "[model,add,hmr,'second','jBPM','" + model.toString().replaceAll("'", r) + "'].";
//        System.out.println(request2);
//        String response = httpConn.performRequest(request2);
//        System.out.println(response);
        System.out.println(hr.pushModelHMR("third", "jBPM", model.toString()));
        
        String[] tabs = {"ms"};
        String iReq = hrh.inferenceRequest("jBPM", "third", "foi", tabs, "s1");
        String response = httpConn.performRequest(iReq);
        System.out.println(response);
    }
}
