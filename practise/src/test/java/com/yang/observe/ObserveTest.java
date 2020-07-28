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
 * 2020/5/26    1.0        mark       File Creation
 */
package com.yang.observe;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

/**
 * Description:
 *
 * @author mark
 * Date 2020/5/26
 */
public class ObserveTest {

    @Test
    public void test(){
        ConcreteSubject subject = new ConcreteSubject();
        ConcreteObserverA observerA = Mockito.spy(new ConcreteObserverA());
        ConcreteObserverC observerC = Mockito.spy(new ConcreteObserverC());

        subject.notifyObservers("no listeners");

        subject.registerObserver(observerA);
        subject.notifyObservers("message for A");

        subject.registerObserver(observerC);
        subject.notifyObservers("message for A & C");

        subject.unRegisterObserver(observerA);
        subject.notifyObservers("message for C");

        subject.unRegisterObserver(observerC);
        subject.notifyObservers("no listeners");

        Mockito.verify(observerA, times(1)).observe("message for A");
        Mockito.verify(observerA, times(1)).observe("message for A & C");
        Mockito.verifyNoMoreInteractions(observerA);

        Mockito.verify(observerC, times(1)).observe("message for A & C");
        Mockito.verify(observerC, times(1)).observe("message for C");
        Mockito.verifyNoMoreInteractions(observerC);


        subject.registerObserver(e -> System.out.println("A: " + e));
        subject.registerObserver(e -> System.out.println("C: " + e));
        subject.notifyObservers("this message will recv A & B");
    }
}
