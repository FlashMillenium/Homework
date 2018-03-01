package ru.sberbank.jschool.homework.andreev.first;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;

public class PluginManager {

    private MyClassloader loader;

    public PluginManager(String rootDirectory) throws PluginNotFoundException {
        System.out.println(Plugin.class.getCanonicalName());
        URL[] rootUrl = new URL[1];
        try {
            rootUrl[0] = new File(System.getProperty("user.dir") + File.separator + rootDirectory + File.separator).toURI().toURL();
        } catch (MalformedURLException e) {
            throw new PluginNotFoundException("Some problem with root directory", e);
        }
        loader = new MyClassloader(rootUrl, this.getClass().getClassLoader());
    }

    public Plugin loadPlugin(String pluginName) throws PluginNotFoundException {
        try {
            return (Plugin) loader.loadClass(pluginName).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new PluginNotFoundException("couldn't locate plugin " + pluginName);
        } catch (ClassCastException e) {
            throw new PluginNotFoundException("*.class file don't implement «Plugin» interface");
        }
    }


}
