import java.io.IOException;

import core.Cache;


public class cache_simulator {
    public static void main(String[] args) throws IOException {
//        Cache cache = Cache.build("256", "4", "1", "R", "0", "bin_100.bin");
//        Cache cache1 = Cache.build("128", "2", "4", "R", "0", "bin_1000.bin");
//        Cache cache2 = Cache.build("16", "2", "8", "R", "0", "bin_10000.bin");
          Cache cache3 = Cache.build("512", "8", "1", "R", "0", "vortex.in.sem.persons.bin");
//        //Cache cache = Cache.build(args[0], args[1], args[2], args[3], args[4], args[5]);
//        cache.initializeValidAndTag();
//        cache.readAddresses();
//
//        cache1.initializeValidAndTag();
//        cache1.readAddresses();
//
//        cache2.initializeValidAndTag();
//        cache2.readAddresses();

        cache3.initializeValidAndTag();
        cache3.readAddresses();
    }
}
