package com.wg.demo.dynamicproxy;

public class Hello implements HelloInterface {
    @Override
    public void sayHello(){
        System.out.println("Hello gay!");
    }
}
