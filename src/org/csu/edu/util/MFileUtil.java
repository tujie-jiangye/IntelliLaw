package org.csu.edu.util;

import java.io.*;
import java.util.Vector;

public class MFileUtil {
    public static void main(String[] args) {
        System.out.println("no problem");
    }

    /*
    * 根据给定目录，返回随机几篇判决文书
    * @param: dirPath  判决文书所在目录
    * @param: n 要求返回判决文书的数目
    * @return: String[]
     */
    public String[] getRDJudgment(String dirPath, int n){
        String[] results = new String[n];
        File[] files = new File[0];
        File dirFiles = new File(dirPath);
        if (dirFiles.exists()){
            files = dirFiles.listFiles();
        }else {
            System.out.println("dir does not exist");
        }
        Vector<Integer> ints = MathUtil.getNRDNum(files.length, n);
        int i = 0;
        for (int index : ints){
            String content = readContent(files[index]);
            results[i++] = content;
        }
        return  results;
    }

    /*
    * 读取文件内容并返回
    * @param: file file pointer to target file
    * @return: String
     */
    public String readContent(File file){
        String content = "";
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line = null;
            while ((line = bf.readLine()) != null){
                content += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  content;
    }
}
