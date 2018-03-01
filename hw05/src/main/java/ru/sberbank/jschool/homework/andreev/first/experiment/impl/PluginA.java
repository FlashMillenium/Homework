package experiment.impl;

import ru.sberbank.jschool.homework.andreev.first.Plugin;

import java.util.Arrays;

public class PluginA implements Plugin {
    @Override
    public void run(String[] urls) {
        Arrays.stream(urls).forEach(e -> System.out.println("A " + e));
    }
}
