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
