package ru.sberbank.jschool.homework.classloaders.first;

import java.nio.file.Paths;

public class Test {
    public static void main(String[] args) {
        try {
            PluginManager p = new PluginManager("MyPlugins");
            String[] s = {"DDDDD", "FJFJFJFJ"};
            Plugin plug = p.loadPlugin("experiment.impl.PluginB");
            plug.run(s);
            plug = p.loadPlugin("PluginImpl");
            plug.run(s);
        } catch (PluginNotFoundException e) {
            e.printStackTrace();
        }
    }
}
