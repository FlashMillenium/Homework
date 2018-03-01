package ru.sberbank.jschool.homework.andreev.first;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class MyClassloader extends URLClassLoader {

    Map<String, Class<?>> cache = new HashMap<>();

    public MyClassloader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> result;
        if (cache.containsKey(name)) {
            return cache.get(name);
        }
        try {
            result = findClass(name);
            cache.put(name, result);
        } catch (ClassNotFoundException | NoClassDefFoundError e) {
            result = super.loadClass(name);
        }
        return result;
    }


}
