package ru.sberbank.jschool.homework.classloaders.first;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassloader extends URLClassLoader {


    public MyClassloader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name, false);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {

        // First, check if the class has already been loaded
        Class<?> c = findLoadedClass(name);
        try {
            c = findClass(name);
        }catch (ClassNotFoundException e){
//            c = parent.loadClass(name, false);
        }

        if (resolve) {
            resolveClass(c);
        }
        return c;
    }
    //    private byte[] loadClassByte(String pathToClass){
//        byte[] result = null;
//        Path path = Paths.get(pathToClass);
//        try {
//            result = Files.readAllBytes(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
}
