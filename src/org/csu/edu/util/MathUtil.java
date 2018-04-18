package org.csu.edu.util;

import java.util.Random;
import java.util.Vector;

public class MathUtil {
    /* 随机产生0~max范围内互不相同的n个数
    * @param: max 范围最大值
    * @param: n 随机数的个数
    * @return: Vector<Integer>
     */
    public static Vector<Integer> getNRDNum(int max, int n){
        Vector<Integer> ints = new Vector<>();
        Random random = new Random();
        n = (max < n) ? max : n;
        max = (max > n) ? max : n;
        boolean[] isUsed = new boolean[max];
        while (ints.size() < n){
            int index = random.nextInt(max);
            if (!isUsed[index]) ints.add(index);
        }
        return  ints;
    }
}
