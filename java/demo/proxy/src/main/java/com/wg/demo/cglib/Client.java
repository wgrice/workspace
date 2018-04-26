package com.wg.demo.cglib;

import net.sf.cglib.proxy.Enhancer;

public class Client {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloServiceImpl.class);
        enhancer.setCallback(new HelloMethodInterceptor());
        HelloServiceImpl helloService = (HelloServiceImpl) enhancer.create();
        helloService.sayHello();

    }
}
