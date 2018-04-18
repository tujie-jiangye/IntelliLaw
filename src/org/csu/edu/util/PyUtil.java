package org.csu.edu.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class PyUtil {
    public static String InvokePy(){
        String result = null, line = null;
        try {
            String pyArgs[] = {"python",
                    "/home/tujie/workplace/judgmentProcess_02/InvokeChance.py",
                    "/home/tujie/workplace/judgmentProcess_02/data.py"};
            Process pr = Runtime.getRuntime().exec(pyArgs);
            InputStreamReader isReader = new InputStreamReader(pr.getInputStream());
            LineNumberReader lnReader = new LineNumberReader(isReader);
            while ((line = lnReader.readLine()) != null){
                result = line;
            }
            lnReader.close();
            isReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;

    }
}
