import java.io.IOException;

import core.Cache;


public class cache_simulator {
    public static void main(String[] args) throws IOException {

        Cache cache = Cache.build(args[0], args[1], args[2], args[3], args[4], args[5]);
        cache.initializeValidAndTag();
        cache.readAddresses();

    }
}
