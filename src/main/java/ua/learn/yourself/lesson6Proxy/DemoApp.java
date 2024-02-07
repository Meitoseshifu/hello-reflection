package ua.learn.yourself.lesson6Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import ua.learn.yourself.lesson5Reflection.Randomizer;

import java.util.List;

public class DemoApp {
    public static void main(String[] args) {
        Randomizer randomizer = new Randomizer();
        testRandomizer(randomizer, List.of(1,2,3,4,5));
        testRandomizer(new RandomizerProxy(), List.of(1,2,3,4,5));
        //or how spring do it
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Randomizer.class);
        enhancer.setCallback((MethodInterceptor)(obj, method, methodArgs, proxy) -> {
            if (method.getName().equals("randomize")) {
                System.out.println("Hello from cglib");
                return proxy.invokeSuper(obj, methodArgs);
            }
            throw new UnsupportedOperationException();
        });
        var proxyInstance = (Randomizer) enhancer.create();
        testRandomizer(proxyInstance, List.of(1,2,3,4,5));
    }

    private static void testRandomizer(Randomizer randomizer, List<?> list) {
        System.out.println(randomizer.randomize(list));
    }
}
