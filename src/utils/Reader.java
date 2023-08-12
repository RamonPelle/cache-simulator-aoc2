package utils;


import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
        System.out.println(addresses);
        System.out.println(addresses.size());

    }

//    public void convertToBinary() {
//        List<String> binaryAddresses = new ArrayList<>();
//        for (String address : this.addresses) {
//            Integer intAddress = Integer.parseInt(address);
//            binaryAddresses.add(Integer.toBinaryString(intAddress));
//        }
//        this.addresses = binaryAddresses;
//        fulfill32Bits();
//    }

//    public void fulfill32Bits() {
//        List<String> addresses32bits = new ArrayList<>();
//        for (String address : this.addresses) {
//            StringBuilder builder = new StringBuilder();
//            int bitsToFill = 32 - address.length();
//            for (int i = 0; i < bitsToFill; i++) {
//                builder.append("0");
//            }
//            builder.append(address);
//            addresses32bits.add(builder.toString());
//        }
//        this.addresses = addresses32bits;
//        System.out.println(this.addresses);
//    }
}

