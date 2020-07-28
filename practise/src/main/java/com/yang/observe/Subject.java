package com.yang.observe;

/**
 * Description:
 *
 * @author mark
 * Date 2020/5/25
 */
public interface Subject<T> {

    void registerObserver(Observer<T> observer);

    void unRegisterObserver(Observer<T> observer);

    void notifyObservers(T event);
}
