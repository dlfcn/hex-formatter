/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexformatter.legacy;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class PrependBytes {

    private static String HEX = " 0980 0309 0e06 c9fe fe08 2005 05c3 fe05\n" +
"         74f4 3de2 3bc7 0403 0071 00e8 33e9 31cf\n" +
"         0171 d002 8301 f228 aa0b 8409 0100 110a\n" +
"         8066 7674 5084 0902 0021 0a16 3800 7701\n" +
"         8406 0700 0103 6300 df45 0100 df48 0101";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        translateHexToByteArray(HEX);
    }
    
    public static byte[] translateHexToByteArray(String hex) {
        
        System.out.println(String.format("HEX...\n" + hex));
        
        if (hex != null) {
            hex = hex.replaceAll("\\s", "");

            if (hex.length() > 0 && hex.length() % 2 == 0) {

                int length = hex.length() / 2;
                byte[] bytes = new byte[length];
                int byteIndex = 0;

                for (int i = 0; i < hex.length(); i = i + 2) {

                    String octet = hex.substring(i, (i + 2));
                    int decimal = Integer.parseInt(octet, 16);
                    bytes[byteIndex++] = (byte) decimal;
                }
                System.out.println(String.format("Hex is DONE"));
                return bytes;
                
            } else {
                System.out.println(String.format("Invalid Hex Stream, length must be even...\n hex stream = [%s]\n length = [%s]", hex, hex.length()));
            }
        } else {
            System.out.println(String.format("1- Hex is NULL"));
        }
        System.out.println(String.format("2- Hex is NULL"));
        return null;
    }
    
    private static void prependBytes(String hex) {
        
        String hex2 = hex.replaceAll("\n", " ");
        String[] split = hex2.trim().split(" ");
        
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
            System.out.println(i + " = " + split[i]);
        }
        
        System.out.println();
        ArrayList<String> bytes = new ArrayList<>();
        
        for (String b : split) {
            
            String b1;
            String b2;
            
            if (b.length() == 2) {
                b1 = b.substring(0, 2);
                b2 = null;
            } else {
                b1 = b.substring(0, 2);
                b2 = b.substring(2, 4);
            }
            
            bytes.add(b1);
            
            if (b2 != null) {
                bytes.add(b2);
            }
        }
        
        StringBuilder builder = new StringBuilder();
        int index = 0;
        
        for (int i = 0; i < bytes.size(); i++) {

            builder.append("(byte) 0x");
            builder.append(bytes.get(i));
            
            if ((i < bytes.size()-1) && builder.length() > 0) {
                builder.append(", ");
            }
            
            if (++index == 5) {
                builder.append("\n");
                index = 0;
            }
        }
        System.out.println(builder.toString());
    }
    
}
