package ru.sberbank.jschool.homework.andreev.first;

public class Test {
    public static void main(String[] args) {
        try {
            PluginManager p = new PluginManager("MyPlugins");
            String[] s = {"DDDDD", "FJFJFJFJ"};
            Plugin plug = p.loadPlugin("PluginImpl");
            plug.run(s);
            plug = p.loadPlugin("experiment.impl.PluginB");
            plug.run(s);
            plug = p.loadPlugin("PluginImpl");
            plug.run(s);
        } catch (PluginNotFoundException e) {
            e.printStackTrace();
        }
    }
}
