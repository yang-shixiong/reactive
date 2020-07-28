package mian;

import java.util.function.Consumer;

/**
 * Description:
 *
 * @author mark
 * Date 2020/6/2
 */
public class CoreDemo {

    public static void main(String[] args) {
        Consumer<String> consumer = System.out::println;
        consumer.accept("test");

        System.out.println(consumer.getClass());

        new CoreDemo().test();

    }

    public void test(){
        Consumer<Integer> consumer = s -> {
            System.out.println(this);
            System.out.println(s);
        };
        consumer.accept(2);
        System.out.println(consumer.getClass());
    }
}
