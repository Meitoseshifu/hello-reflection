package ua.learn.yourself.lesson5Reflection;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {
    public <T> T randomize(List<T> list) {
        var index = ThreadLocalRandom.current().nextInt(list.size());
        return list.get(index);
    }
}
