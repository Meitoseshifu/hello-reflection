package ua.learn.yourself.lesson6Proxy;



import ua.learn.yourself.lesson5Reflection.Randomizer;

import java.util.List;

public class RandomizerProxy extends Randomizer {
    @Override
    public <T> T randomize(List<T> list) {
        System.out.println("Hello from Proxy");
        return super.randomize(list);
    }
}
