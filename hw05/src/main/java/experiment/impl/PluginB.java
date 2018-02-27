package experiment.impl;

import ru.sberbank.jschool.homework.classloaders.first.Plugin;

import java.util.Arrays;

public class PluginB implements Plugin {
    @Override
    public void run(String[] urls) {
        Arrays.stream(urls).forEach(e -> System.out.println("B " + e));
    }
}
