import ru.sberbank.jschool.homework.classloaders.first.Plugin;

import java.util.Arrays;

public class PluginImpl<T> implements Plugin<T> {
    @Override
    public void run(String[] urls) {
        Arrays.stream(urls).forEach(System.out::println);
    }
}