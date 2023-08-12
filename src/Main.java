import java.io.FileNotFoundException;
import java.util.List;

import core.Cache;
import utils.Reader;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Reader r = new Reader();
        r.readFile("resources/addresses/" + args[5]);
        List<String> addr = r.getAddresses();
        Cache cache = Cache.build(args[0], args[1], args[2], args[3], args[4]);
        System.out.println(cache);
    }
}
