package utils;


import java.io.File;
import java.io.FileNotFoundException;
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

    public void readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            this.addresses.add(sc.nextLine());
        }
        convertToBinary();
    }

    public void convertToBinary() {
        List<String> binaryAddresses = new ArrayList<>();
        for (String address : this.addresses) {
            Integer intAddress = Integer.parseInt(address);
            binaryAddresses.add(Integer.toBinaryString(intAddress));
        }
        this.addresses = binaryAddresses;
        fulfill32Bits();
    }

    public void fulfill32Bits() {
        List<String> addresses32bits = new ArrayList<>();
        for (String address : this.addresses) {
            StringBuilder builder = new StringBuilder();
            int bitsToFill = 32 - address.length();
            for (int i = 0; i < bitsToFill; i++) {
                builder.append("0");
            }
            builder.append(address);
            addresses32bits.add(builder.toString());
        }
        this.addresses = addresses32bits;
        System.out.println(this.addresses);
    }
}

