package org.csu.edu.util;

public class MStrUtil {
    /*
     * 拼接路径，路径不能以/结尾
     * @param: dirRoot文件目录
     * @param: type指的是分类
     * @param: classIndex分类下的具体类小标
     * @return: String
     */
    public static String concatPath(String dirRoot, String type, int classIndex){
        String result = String.format("%s/%s/%d", dirRoot, type, classIndex);
        return  result;
    }

    /*
    * 拼接路径，路径不能以/结尾
    * @param: 文件目录
    * @param: 既可以是文件名，也可以是文件目录
    * @return: String
     */
    public static String concatPath(String dirRoot, String filename){
        String result = String.format("%s/%s", dirRoot, filename);
        return  result;
    }

//    /*
//    * 默认判决文书路径为judgements目录，返回判决文书目录
//    * @param: dirRoot 目录路径，此处可指具体分到哪一类
//    * @return: String
//     */
//    public static String getJudgmentPath(String dirRoot){
//        return concatPath(dirRoot, "judgements");
//    }

    /*
    * 将源字符串中用tag标记出来的文本替换为rplTexts数组中的内容
    * @param: source 源字符串
    * @param: rplTexts 用来替换的文本数组
    * @param: tag 用来标记被替换文本的标记
    * @return: String
     */
    public static String replaceByTag(String source, String[] rplTexts, String tag){
        String target = chg2Str(rplTexts);
        source.replaceAll(tag, target);
        return  target;
    }

    /*
    * 将字符串数组连接成连续的文本
    * @param: rplTexts  字符串数组
    * @return: String
     */
    public static String chg2Str(String[] rplTexts){
        return String.join(",", rplTexts);
    }

    public static boolean contains(String keyword, String str){
//        上面的代码是针对如果strs是String[] 来准备的
//        for (String str : strs){
//            if (str.contains(keyword)){
//                return true;
//            }
//        }
        if (str.contains(keyword)){
            return true;
        }
        return false;
    }
}
