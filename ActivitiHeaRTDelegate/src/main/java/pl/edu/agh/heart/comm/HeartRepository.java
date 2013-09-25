/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.heart.comm;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import pl.edu.agh.heart.bracketparser.CompositeElement;
import pl.edu.agh.heart.bracketparser.Element;
import pl.edu.agh.heart.constants.Constants;

/** @author ja */
public class HeartRepository {
    private HttpConnector httpConnector;
    
    public HeartRepository() throws IOException {
        Properties props = new Properties();
        InputStream propIs = getClass().getClassLoader().getResourceAsStream(Constants.H_CONN_PATH);
        props.load(propIs);
        String host = props.getProperty("hostName");
        int port = Integer.valueOf(props.getProperty("port"));
        httpConnector = new HttpConnector(true, host, port);
    }
    
    public Map<String, List<String>> getModelNames() throws Exception {
        String modelListRequest = "[model,getlist].";
        String response = httpConnector.performRequest(modelListRequest);
        CompositeElement responseTree = (CompositeElement) Element.parse(response);
        List<Element> modelList = ((CompositeElement) responseTree.getSubs().get(1)).getSubs();
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        for (Element e: modelList) {
            String model = ((CompositeElement) e).getSubs().get(0).toString();
            String user = ((CompositeElement) e).getSubs().get(1).toString();
            List<String> l = result.get(user);
            if (l == null) {
                l = new ArrayList<String>();
                result.put(user, l);
            }
            l.add(model);
        }
        return result;
    }
    
    public String getModelHMR(String modelName, String userName) throws Exception {
        String request = "[model,get,hmr,'" + modelName + "','" + userName + "',[[all]]].";
        String response = httpConnector.performRequest(request);
        if (response.split(",")[0].trim().equals("[true")) {
            String modelDef = response.split(",", 2)[1];
            int length = modelDef.length();
            modelDef = modelDef.substring(1, length - 2);
            modelDef = modelDef.replace(".", ".\n");
            return modelDef;
        }
        return null;
    }
    
    public boolean pushModelHMR(String modelName, String userName, String hmr) throws Exception {
        String r = java.util.regex.Matcher.quoteReplacement("\\'");
        String request =
                "[model,add,hmr,'" + modelName + "','" + userName + "','" + hmr.replaceAll("'", r) + "'].";
        String response = httpConnector.performRequest(request);
        if (response.equals("[true]")) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean verifyModel(String modelName, String userName, String mode, String scheme)
            throws Exception {
        String request = "[model,verify," + mode + ",'" + modelName + "','" + userName + "'," + scheme + "].";
        String response = httpConnector.performRequest(request);
        return new HeartRequestHandler().isSuccess(response);
    }
}
