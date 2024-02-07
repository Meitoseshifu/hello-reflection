package ua.learn.yourself.lesson5Reflection;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DemoApp {
    @SneakyThrows
    public static void main(String[] args) {
        //get metadata
        var randomizerClass = Class.forName("lesson5Reflection.Randomizer"); //first approach
        var methods = randomizerClass.getDeclaredMethods();
        System.out.println(Arrays.toString(methods));
        var randomizeMethod = methods[0];
        System.out.println(Arrays.toString(randomizeMethod.getParameters()));

        //invoke method
        var randomizerClazz = Randomizer.class; //second approach
        var randomizerConstructor = randomizerClazz.getConstructor();
        var randomizer = randomizerConstructor.newInstance();
        var result = randomizeMethod.invoke(randomizer, List.of(1,2,3,4,5));
        System.out.println(result);

        //implement interface logic
        var randomizerInterface = Class.forName("lesson5Reflection.Irandomizer");
        var classloader = randomizerInterface.getClassLoader();
        var interfaceToImplement = new Class<?>[]{randomizerInterface};
        InvocationHandler handler = (proxy, method, args1) -> {
            if (method.getName().equals("randomize")) {
                var list = (List<?>) args1[0];
                var index = ThreadLocalRandom.current().nextInt(list.size());
                return list.get(index);
            }
            throw new UnsupportedOperationException();
        };

        var randomizerInstance = Proxy.newProxyInstance(classloader, interfaceToImplement, handler);
        var randomizeMethod1 = randomizerInstance.getClass().getMethod("randomize", List.class);
        var result1 = randomizeMethod1.invoke(randomizerInstance, List.of(1,2,3,4,5));
        System.out.println(result1);
    }
}
