import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.List;

import core.Cache;
import utils.Reader;

import utils.Reader;


public class Main {
    public static void main(String[] args) throws IOException {
//        Reader r = new Reader();
//        r.readFile("resources/addresses/" + args[5]);
//        List<String> addr = r.getAddresses();
//        Cache cache = Cache.build(args[0], args[1], args[2], args[3], args[4]);
//        System.out.println(cache);
//        int intValue = ByteBuffer.wrap(buffer).getInt();
//        System.out.println("Valor lido: " + intValue);

        Reader r = new Reader();
        r.readFile("C:\\workspaces\\cache-simulator-aoc2\\src\\resources\\addresses\\bin_100.bin");
    }
}
