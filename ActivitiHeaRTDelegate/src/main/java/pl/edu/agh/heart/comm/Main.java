/* @(#) $Id$
 *
 * Copyright (c) 2000-2013 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.heart.comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** @author ja */
public class Main {
    public static void main(String[] args) {
        BufferedReader inBr = new BufferedReader(new InputStreamReader(System.in));
        HQEdHttpConnector httpConn = new HQEdHttpConnector(true, "127.0.0.1", 8082);
        
        while (true) {
            String inLine;
            try {
                inLine = inBr.readLine();
                String response = httpConn.performRequest(inLine);
                System.out.println(response);
            } catch (IOException exception) {
                // TODO Handle exception using log4j or CLog
                System.out.println("fuck");
            } catch (Exception exception) {
                // TODO Handle exception using log4j or CLog
                System.out.println("double fuck");
            }
        }
    }
}
