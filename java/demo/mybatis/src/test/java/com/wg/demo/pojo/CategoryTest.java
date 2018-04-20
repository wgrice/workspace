package com.wg.demo.pojo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {
    private Category c;

    @Before
    public void setUp() throws Exception {
        c = new Category();
        c.setId(1);
        c.setName("hehe");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getId() {
        assert c.getId()==1;
    }

    @Test
    public void setId() {
        assert c.getName()=="hehe";
    }

    @Test
    public void getName() {
    }

    @Test
    public void setName() {
    }
}