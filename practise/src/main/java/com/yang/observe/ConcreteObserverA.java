
package com.yang.observe;


/**
 * Description:
 *
 * @author mark
 * Date 2020/5/25
 */


public class ConcreteObserverA implements Observer<String> {
    @Override
    public void observe(String event) {
        System.out.println("Observer A:" + event);
    }
}
