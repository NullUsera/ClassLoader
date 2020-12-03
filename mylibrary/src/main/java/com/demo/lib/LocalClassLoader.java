package com.demo.lib;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 自定义ClassLoader加载指定目录下的类
 */
public class LocalClassLoader extends ClassLoader {
    //待加载类的绝对路径(路径中不要带类名)
    private final String filePath;

    public LocalClassLoader(String filePath) {
        this.filePath = filePath;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected Class<?> findClass(String name) {
        String classPath = filePath + name + ".class";
        byte[] clazzBytes = null;
        try {
            Path path = Paths.get(new URI(classPath));
            clazzBytes = Files.readAllBytes(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用defineClass来创建Class
        return defineClass(name, clazzBytes, 0, clazzBytes.length);
    }
}
