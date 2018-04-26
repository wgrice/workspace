package com.wg.demo.staticproxy;

public class HelloProxy implements HelloInterface{
    private HelloInterface hello = new Hello();

    @Override
    public void sayHello(){
        beforeProxy();
        hello.sayHello();
        afterProxy();
    }

    private void beforeProxy(){
        System.out.println("Before proxy.");
    }

    private void afterProxy(){
        System.out.println("After proxy.");
    }
}
