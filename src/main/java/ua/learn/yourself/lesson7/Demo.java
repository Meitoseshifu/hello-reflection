package ua.learn.yourself.lesson7;

import java.util.Arrays;
import java.util.function.Consumer;

public class Demo {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("Hello");

        var methods = Demo.class.getDeclaredMethods();
        System.out.println(Arrays.toString(methods));
    }
}
