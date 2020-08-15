package abhishek.scattered.springcloudfunction.core;

import java.util.function.Function;

public class CoreFunction implements Function<String, String> {

    public String apply(String s) {
        return s + "123";
    }
}
