package com.wg.demo.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

public class HelloProxy implements InvocationHandler {
    private Object subject;

    public HelloProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforeProxy(method);
        method.invoke(subject, args);
        afterProxy(method);
        return null;
    }

    private void beforeProxy(Method method){
        System.out.println("[" + new Date().toString()+ "]" +method.getName()+"\tBefore proxy.");
    }

    private void afterProxy(Method method){
        System.out.println("[" + new Date().toString()+ "]" +method.getName()+"\tAfter proxy.");
    }
}
