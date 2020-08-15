package abhishek.scattered.springcloudfunction.module;

import java.util.function.Function;

public class SimpleFunction implements Function<Long, String> {

    public String apply(Long s) {
        return s + 123 + "";
    }
}