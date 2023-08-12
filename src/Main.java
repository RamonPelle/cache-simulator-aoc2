import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.List;

import core.Cache;
import utils.Reader;


public class Main {
    public static void main(String[] args) throws IOException {
//        Reader r = new Reader();
//        r.readFile("resources/addresses/" + args[5]);
//        List<String> addr = r.getAddresses();
//        Cache cache = Cache.build(args[0], args[1], args[2], args[3], args[4]);
//        System.out.println(cache);
        byte[] buffer = new byte[4];
        InputStream is = new FileInputStream("src/resources/addresses/bin_100.bin");
        int bytes = is.read(buffer);
        System.out.println(Integer.toBinaryString(buffer[0]));
        System.out.println(Integer.toBinaryString(buffer[1]));
        System.out.println(Integer.toBinaryString(buffer[2]));
        System.out.println(Integer.toBinaryString(buffer[3]));
//        int intValue = ByteBuffer.wrap(buffer).getInt();
//        System.out.println("Valor lido: " + intValue);
    }
}
