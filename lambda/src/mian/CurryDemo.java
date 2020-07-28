package mian;


import java.util.function.Function;

/**
 * Description:
 *
 * @author mark
 * Date 2020/6/2
 */
public class CurryDemo {

    public static void main(String[] args) {
        Function<Integer, Function<Integer, Function<Integer, Integer>>> func = x -> y -> z -> x + y + z;

        System.out.println(func.apply(2).apply(3).apply(4));
        System.out.println(func.apply(2).apply(3));
    }

}
