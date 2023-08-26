import java.io.IOException;

import core.Cache;


public class Main {
    public static void main(String[] args) throws IOException {
        Cache cache = Cache.build("1", "4", "32", "R", "1", "vortex.in.sem.persons.bin");
        cache.initializeValidAndTag();
        cache.readAddresses();
    }
}
