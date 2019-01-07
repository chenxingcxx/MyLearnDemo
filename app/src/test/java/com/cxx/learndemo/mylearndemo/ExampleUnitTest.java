package com.cxx.learndemo.mylearndemo;

import android.util.Log;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void myTest() {
        Map<Integer, String> map = new HashMap();
        map.put(1, "8");
        map.put(1, "20");
        map.put(2, "9");
        map.replace(1, "222222");
        System.out.println("map.get(1):" + map.get(1));
        System.out.println("MyEnum.order1:" + MyEnum.order1);
    }
}