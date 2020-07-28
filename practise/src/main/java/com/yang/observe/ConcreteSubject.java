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
 * 2020/5/25    1.0        mark       File Creation
 */
package com.yang.observe;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author mark
 * Date 2020/5/25
 */
public class ConcreteSubject implements Subject<String> {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private final Set<Observer<String>> observerSet = new CopyOnWriteArraySet<>();
    @Override
    public void registerObserver(Observer<String> observer) {
        observerSet.add(observer);
    }

    @Override
    public void unRegisterObserver(Observer<String> observer) {
        observerSet.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {

//        observerSet.forEach(observer -> observer.observe(event));
        observerSet.forEach(observer -> executorService.submit(() -> observer.observe(event)));
    }
}
