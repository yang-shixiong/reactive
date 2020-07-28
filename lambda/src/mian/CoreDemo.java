/**
 * Copyright(c) JingHong Technology Co., Ltd.
 * All Rights Reserved.
 * <p>
 * This software is the confidential and proprietary information of JingHong
 * Technology Co.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with JingHong.
 * For more information about JingHong, welcome to https://www.imagego.com
 * <p>
 * Revision History:
 * Date         Version    Name       Description
 * 2020/6/2    1.0        mark       File Creation
 */
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
