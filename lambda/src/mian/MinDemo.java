package mian;

import java.util.stream.IntStream;

/**
 * Description:
 *
 * @author mark
 * Date 2020/5/15
 */
public class MinDemo {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6};
        int min = Integer.MAX_VALUE;

        System.out.println("before :" + min);

        // 命令式编程
        for(int i: nums){
            if(i < min){
                min = i;
            }
        }
        System.out.println("after :" + min);

        // 函数式编程
        int min2 = IntStream.of(nums).min().getAsInt();
        System.out.println("min2 :" + min2);

        // 如果需要开启多线程
        int min3 = IntStream.of(nums).parallel().min().getAsInt();
        System.out.println("min3 " + min3);
    }
}
