package utils;


import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class Reader {
    List<String> addresses = new ArrayList<>();
    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public void readFile(String path) throws IOException {
        byte[] buffer = new byte[4];
        InputStream is = new FileInputStream(path);
        int bytes;
        while ((bytes = is.read(buffer)) == 4) {
            StringBuilder binaryValue = new StringBuilder();
            for (byte b : buffer) {
                for (int i = 7; i >= 0; i--) {
                    binaryValue.append((b & (1 << i)) == 0 ? "0" : "1");
                }
            }
            addresses.add(binaryValue.toString());
        }
    }

}

