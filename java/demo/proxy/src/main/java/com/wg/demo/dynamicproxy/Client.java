package com.wg.demo.dynamicproxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        Hello hello = new Hello();
        InvocationHandler handler = new HelloProxy(hello);
        HelloInterface helloInterface = (HelloInterface) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                                                                                hello.getClass().getInterfaces(),
                                                                                handler);
        helloInterface.sayHello();

    }
}
