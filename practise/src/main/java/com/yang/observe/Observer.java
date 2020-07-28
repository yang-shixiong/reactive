package com.yang.observe;

/**
 * Description:
 *
 * @author mark
 * Date 2020/5/25
 */
@FunctionalInterface
public interface Observer<T> {
    void observe(T event);
}
