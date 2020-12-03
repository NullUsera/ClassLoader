package com.demo.lib;

import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) {
        LocalClassLoader classLoader = new LocalClassLoader("file:///Users/admin/work_dir/");
        try {
            Class c = classLoader.loadClass("NewTest");
            if (c != null) {
                Object o = c.newInstance();
                Method method = c.getDeclaredMethod("print", null);
                method.invoke(o, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
