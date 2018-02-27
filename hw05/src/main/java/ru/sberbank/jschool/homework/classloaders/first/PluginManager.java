package ru.sberbank.jschool.homework.classloaders.first;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class PluginManager {

    // directory that contains plugin folders
    private final String rootDirectory;
    private MyClassloader loader;

    public PluginManager(String rootDirectory) throws PluginNotFoundException {
        URL[] rootUrl = new URL[1];
        try {
            rootUrl[0] = new File(System.getProperty("user.dir") + File.separator + rootDirectory + File.separator).toURI().toURL();
        } catch (MalformedURLException e) {
            throw new PluginNotFoundException("Some problem with root directory", e);
        }
//        System.out.println(rootUrl[0].toString());
        loader = new MyClassloader(rootUrl, this.getClass().getClassLoader());
        this.rootDirectory = rootDirectory;
//        String pathToClass = System.getProperty("user.dir") + File.separator + rootDirectory + File.separator + "Plugin" + ".class";
//        String pack = "ru.sberbank.jschool.homework.classloaders.first".replace('.',File.separatorChar);
////        String pack2 = pack.replaceAll(".", File.separator);
//        String pathToClass = System.getProperty("user.dir") + File.separator + "out\\production\\main" + File.separator +  pack +  File.separator + "Plugin" + ".class";
//        System.out.println(pathToClass);


    }

    public Plugin loadPlugin(String pluginName) throws PluginNotFoundException {
        Object result = null;
//        String pathToClass = System.getProperty("user.dir") + File.separator + rootDirectory + File.separator + pluginName + ".class";
//        System.out.println(loader.getURLs()[0]);
        try {
            Plugin pluginImpl = (Plugin) loader.loadClass(pluginName).newInstance();
            return pluginImpl;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new PluginNotFoundException("couldn't locate plugin " + pluginName);
        }
//        System.out.println(pathToClass);
//
//        byte[] b = loadClassByte(pathToClass);
//        System.out.println(this.getClass().getPackage());
//        Class<?> pluginClass = defineClass(pluginName, b, 0, b.length);
//        System.out.println(pluginClass.getCanonicalName());
//        try {
//            return  (Plugin) pluginClass.newInstance();
//        } catch (InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
//        }

    }


}
