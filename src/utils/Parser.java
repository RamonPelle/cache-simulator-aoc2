package utils;


public class Parser {
    public static Boolean stringToBoolean(String flag){
        if(flag.equals("1")){
            return true;
        }else if(flag.equals("0")){
            return false;
        }else{
            throw new RuntimeException("Ratiou porto, flag é 0 ou 1 irmão");
        }
    }
}
