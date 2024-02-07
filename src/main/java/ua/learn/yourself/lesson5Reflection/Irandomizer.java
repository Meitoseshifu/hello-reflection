package ua.learn.yourself.lesson5Reflection;

import java.util.List;

public interface Irandomizer {
    <T> T randomize(List<T> list);
}
