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
import pl.edu.agh.heart.comm.HttpConnector;

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
        String hmrDef = hr.getModelHMR("first", "jBPM");
        System.out.println(hmrDef);
        String request =
                "[model,add,hmr,'MyName','Username','xtype [ name: week_days, base: symbolic, ordered: yes, domain: [moday,tuesday], desc: \\'This is only one definition\\'].']. ";
        System.out.println(request);
        String r = java.util.regex.Matcher.quoteReplacement("\\'");
        System.out.println(r);
        String request2 =
                "[model,add,hmr,'second','jBPM','" + hmrDef.replaceAll("\n", "").replaceAll("'", r) + "'].";
        System.out.println(request2);
        String response = httpConn.performRequest(request2);
        System.out.println(response);
    }
}
