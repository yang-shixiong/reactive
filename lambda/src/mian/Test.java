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
 * 2020/6/8    1.0        mark       File Creation
 */
package mian;

/**
 * Description:
 *
 * @author mark
 * Date 2020/6/8
 */
public class Test {
    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.setFf("tets");
        Demo invoke = TT.invoke(demo);
        System.out.println(invoke);
    }

}

class TT{
    static <T extends BaseEntiry> T invoke(T object){

        object.setFuck("fuck you");

        return object;
    }
}

class BaseEntiry{
    public String fuck;

    public String getFuck() {
        return fuck;
    }

    public void setFuck(String fuck) {
        this.fuck = fuck;
    }
}


class Demo extends BaseEntiry{
    private String ff;

    public String getFf() {
        return ff;
    }

    public void setFf(String ff) {
        this.ff = ff;
    }

    public Demo() {
    }

    @Override
    public String toString() {
        return "Demo{" +
                "fuck='" + fuck + '\'' +
                ", ff='" + ff + '\'' +
                '}';
    }
}