package mian;

import java.util.function.DoubleToIntFunction;

/**
 * Description:
 *
 * @author mark
 * Date 2020/5/15
 */
@FunctionalInterface
interface DoubleNum{
    int doubleNum(int i);

    default int sum(int x, int y){
        x = this.doubleNum(x);
        return x + y;
    }
}

public class Lambda {
    public static void main(String[] args) {
        // lambda
        DoubleNum d1 = (i) -> i *2;

        // 常用写法
        DoubleNum d2 =  i -> i * 3;

        DoubleNum d3 = i -> {
            return i*2;
        };
        System.out.println(d1.doubleNum(2));    // 4
        System.out.println(d1.sum(2, 2));   // 6
        System.out.println(d2.doubleNum(3));    // 9
        System.out.println(d2.sum(3, 3));   // 12
        System.out.println(d3.doubleNum(4));    // 8
        System.out.println(d3.sum(4, 4));   // 12
    }
}
