import java.io.IOException;
import java.util.List;
import core.Cache;
import utils.Reader;


public class Main {
    public static void main(String[] args) throws IOException {

        Cache cache = Cache.build(args[0], args[1], args[2], args[3], args[4], args[5]);
        cache.readAddresses();
        System.out.println(cache);
    }
}
