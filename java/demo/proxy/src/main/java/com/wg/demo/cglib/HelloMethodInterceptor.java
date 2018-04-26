package com.wg.demo.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Date;

public class HelloMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        beforeProxy(method);
        Object object = methodProxy.invokeSuper(o, objects);
        afterProxy(method);
        return object;
    }

    private void beforeProxy(Method method){
        System.out.println("[" + new Date().toString()+ "]" +method.getName()+"\tBefore proxy.");
    }

    private void afterProxy(Method method){
        System.out.println("[" + new Date().toString()+ "]" +method.getName()+"\tAfter proxy.");
    }
}
