/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexformatter.legacy;

/**
 *
 * @author Admin
 */
public class TraceHexToPcapHexFormat {
    
    private static final String PCAP_HEX = ""
            + "62 81 d9 48 04 1d 08 01 2c 6b 1e 28 1c 06 07 00\n"
            + "11 86 05 01 01 01 a0 11 60 0f 80 02 07 80 a1 09\n"
            + "06 07 04 00 00 01 00 0b 03 6c 80 a1 81 ac 02 01\n"
            + "7f 02 01 44 a3 80 80 07 62 f2 20 10 89 33 7f a2\n"
            + "80 0a 01 01 04 5e 00 5c 10 0b 07 01 0a a1 91 81\n"
            + "a5 05 0a 09 0a 3c b6 6c e6 49 24 4b a2 12 03 53\n"
            + "59 a6 05 08 00 02 f4 40 00 d5 7e 60 06 01 35 05\n"
            + "08 00 62 f2 20 10 89 33 7f 04 01 0d 13 0a 60 14\n"
            + "04 62 88 10 01 21 e0 00 3a 10 06 01 00 2c 01 04\n"
            + "fd 08 53 35 44 80 44 48 32 23 08 08 29 26 20 32\n"
            + "30 96 17 58 00 00 84 08 62 02 22 03 63 79 81 f5\n"
            + "85 12 00 00 0d 94 27 f3 0a aa b2 08 e1 f2 25 45\n"
            + "4c 14 d7 72 86 12 08 80 ce 2f d5 ee 73 1e ee 10\n"
            + "1e ff bb be 7c 84 c0 c8 00 00 00 00";
    
    private static final String TRACE_HEX = "6281\n"
            + "     d948 041d 0801 2c6b 1e28 1c06 0700 1186\n"
            + "     0501 0101 a011 600f 8002 0780 a109 0607\n"
            + "     0400 0001 000b 036c 80a1 81ac 0201 7f02\n"
            + "     0144 a380 8007 62f2 2010 8933 7fa2 800a\n"
            + "     0101 045e 005c 100b 0701 0aa1 9181 a505\n"
            + "     0a09 0a3c b66c e649 244b a212 0353 59a6\n"
            + "     0508 0002 f440 00d5 7e60 0601 3505 0800\n"
            + "     62f2 2010 8933 7f04 010d 130a 6014 0462\n"
            + "     8810 0121 e000 3a10 0601 002c 0104 fd08\n"
            + "     5335 4480 4448 3223 0808 2926 2032 3096\n"
            + "     1758 0000 8408 6202 2203 6379 81f5 8512\n"
            + "     0000 0d94 27f3 0aaa b208 e1f2 2545 4c14\n"
            + "     d772 8612 0880 ce2f d5ee 731e ee10 1eff\n"
            + "     bbbe 7c84 c0c8 0000 0000";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        reformat();
    }
    
    private static void reformat() {
        
        String reformattedPcapHex = reformat(PCAP_HEX);
        String reformattedTraceHex = reformat(TRACE_HEX);
        
        System.out.println("Reformatted PCAP Hex..");
        System.out.println(reformattedPcapHex);
        System.out.println();
        
        System.out.println("Reformatted Trace Hex...");
        System.out.println(reformattedTraceHex);
        System.out.println();
        
        System.out.print("Match = ");
        System.out.println(reformattedPcapHex.equals(reformattedTraceHex) ? "TRUE" : "FALSE");
    }
    
    private static String reformat(String hexDump) {
        
        StringBuilder builder = new StringBuilder();
        String[] lines = hexDump.split("\n");
        
        for (String line : lines) {
            builder.append(line.replaceAll(" ", ""));
        }
        
        String hexLine = builder.toString();
        builder.setLength(0);
        
        if (hexLine.length() % 2 == 0) {
            for (int i = 0; i < hexLine.length(); i = i + 2) {
                if (i != 0) {
                    if (i % 32 == 0) {
                        builder.append("\n");
                    } else {
                        builder.append(" ");
                    }
                }
                builder.append(hexLine.substring(i, i+2));
            }
        } else {
            System.out.println("Hex is not evenly divisible by 2");
        }
        return builder.toString();
    }
    
}
