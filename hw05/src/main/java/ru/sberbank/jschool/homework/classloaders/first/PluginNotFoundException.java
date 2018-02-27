package ru.sberbank.jschool.homework.classloaders.first;

public class PluginNotFoundException extends ClassNotFoundException {
    public PluginNotFoundException(String s) {
        super(s);
    }

    public PluginNotFoundException(String s, Throwable ex) {
        super(s, ex);
    }
}