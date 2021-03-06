/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.heart.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @author ja */
public class HMRModel {
    private static final String TYPE = "xtype.*]\\.";
    private static final String ATTR = "xattr.*]\\.";
    private static final String TYPEG = "xtpgr.*]\\.";
    private static final String ATTRG = "xatgr.*]\\.";
    private static final String SCHEME = "xschm.*]\\.";
    private static final String RULE = "xrule.*]\\.";
    private static final String STATE = "xstat.*]\\.";
    private static final String CBK = "xcall.*]\\.";
    private static final String ACTION = "xactn.*]\\.";
    private static final String VERIFICATION = "xhalv.*]\\.";
    private static final String TRAJECTORY = "xtraj.*]\\.";
    
    private List<String> types = new ArrayList<String>();
    private List<String> attributes = new ArrayList<String>();
    private List<String> typeGroups = new ArrayList<String>();
    private List<String> attGroups = new ArrayList<String>();
    private List<String> schemes = new ArrayList<String>();
    private List<String> rules = new ArrayList<String>();
    private List<String> states = new ArrayList<String>();
    private List<String> callbacks = new ArrayList<String>();
    private List<String> actions = new ArrayList<String>();
    private List<String> verifications = new ArrayList<String>();
    private List<String> trajectories = new ArrayList<String>();
    private List<String> schemeNames = new ArrayList<String>();
    
    private String list2str(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s: list) {
            sb.append(s + '\n');
        }
        return sb.toString();
    }
    
    public HMRModel() {
        
    }
    
    public HMRModel(String hmrDef) {
        parseData(hmrDef);
    }
    
    public void parseData(String hmrDef) {
        Matcher m = Pattern.compile(TYPE).matcher(hmrDef);
        while (m.find()) {
            types.add(m.group());
        }
        m = Pattern.compile(ATTR).matcher(hmrDef);
        while (m.find()) {
            attributes.add(m.group());
        }
        m = Pattern.compile(TYPEG).matcher(hmrDef);
        while (m.find()) {
            typeGroups.add(m.group());
        }
        m = Pattern.compile(ATTRG).matcher(hmrDef);
        while (m.find()) {
            attGroups.add(m.group());
        }
        m = Pattern.compile(SCHEME).matcher(hmrDef);
        while (m.find()) {
            String g = m.group();
            schemes.add(g);
            extractSchemeName(g);
        }
        //
        m = Pattern.compile(RULE).matcher(hmrDef);
        while (m.find()) {
            rules.add(m.group());
        }
        m = Pattern.compile(STATE).matcher(hmrDef);
        while (m.find()) {
            states.add(m.group());
        }
        m = Pattern.compile(CBK).matcher(hmrDef);
        while (m.find()) {
            callbacks.add(m.group());
        }
        m = Pattern.compile(ACTION).matcher(hmrDef);
        while (m.find()) {
            actions.add(m.group());
        }
        m = Pattern.compile(VERIFICATION).matcher(hmrDef);
        while (m.find()) {
            verifications.add(m.group());
        }
        m = Pattern.compile(TRAJECTORY).matcher(hmrDef);
        while (m.find()) {
            trajectories.add(m.group());
        }
    }
    
//    public void parseData(String hmrDef) {
//        String[] modelLines = hmrDef.split("]\\.");
//        for (String foo: modelLines) {
//            String line = foo.trim().replace("\n", "") + "].";
//            if (line.matches(TYPE)) {
//                types.add(line);
//            }
//            if (line.matches(ATTR)) {
//                attributes.add(line);
//            }
//            if (line.matches(TYPEG)) {
//                typeGroups.add(line);
//            }
//            if (line.matches(ATTRG)) {
//                attGroups.add(line);
//            }
//            if (line.matches(SCHEME)) {
//                schemes.add(line);
//                extractSchemeName(line);
//            }
//            if (line.matches(RULE)) {
//                rules.add(line);
//            }
//            if (line.matches(STATE)) {
//                states.add(line);
//            }
//            if (line.matches(CBK)) {
//                callbacks.add(line);
//            }
//            if (line.matches(ACTION)) {
//                actions.add(line);
//            }
//            if (line.matches(VERIFICATION)) {
//                verifications.add(line);
//            }
//            if (line.matches(TRAJECTORY)) {
//                trajectories.add(line);
//            }
//        }
//    }
    
    public String getTypes() {
        return list2str(types);
    }
    
    public String getAttributes() {
        return list2str(attributes);
    }
    
    public String getSchemesAndRules() {
        return list2str(schemes) + "\n" + list2str(rules);
    }
    
    public String getCallbacks() {
        return list2str(callbacks);
    }
    
    public List<String> getSchemeNames() {
        return schemeNames;
    }
    
    private void extractSchemeName(String schemeLine) {
        String name = schemeLine.split("xschm", 2)[1].split(":", 2)[0].trim();
        if (name.charAt(0) == '\'') {
            name = name.substring(1, name.length() - 1);
        }
        schemeNames.add(name);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(":- dynamic(xtype)/1.\n\n");
        sb.append(list2str(types) + '\n');
        sb.append(":- dynamic(xtpgr)/1.\n\n");
        sb.append(list2str(typeGroups) + '\n');
        sb.append(":- dynamic(xattr)/1.\n\n");
        sb.append(list2str(attributes) + '\n');
        sb.append(":- dynamic(xatgr)/1.\n\n");
        sb.append(list2str(attGroups) + '\n');
        sb.append(":- dynamic(xschm)/1.\n\n");
        sb.append(list2str(schemes) + '\n');
        sb.append(":- dynamic(xrule)/1.\n\n");
        sb.append(list2str(rules) + '\n');
        sb.append(":- dynamic(xstat)/1.\n\n");
        sb.append(list2str(states) + '\n');
        sb.append(":- dynamic(xcall)/1.\n\n");
        sb.append(list2str(callbacks) + '\n');
        sb.append(":- dynamic(xactn)/1.\n\n");
        sb.append(list2str(actions) + '\n');
        sb.append(":- dynamic(xhalv)/1.\n\n");
        sb.append(list2str(verifications) + '\n');
        sb.append(":- dynamic(xtraj)/1.\n\n");
        sb.append(list2str(trajectories) + '\n');
        return sb.toString();
    }
}
