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
