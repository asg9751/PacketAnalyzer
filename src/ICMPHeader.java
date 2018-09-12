/**
 * @author Amol Gaikwad
 * Model class for ICMP header. Contains the header structure of ICMP header.
 */

import java.io.IOException;

public class ICMPHeader {

    byte type;
    byte code;
    byte[] checkSum;

    public ICMPHeader(){
        type = 0;
        code = 0;
        checkSum = new byte[2];
    }

    public void printHeader() throws IOException {
        // Print ICMP header information
        System.out.print("Type = ");
        System.out.printf("%d", type);
        System.out.print("\n");

        System.out.print("Code = ");
        System.out.printf("%d", code);
        System.out.print("\n");

        System.out.print("Checksum = ");
        System.out.printf("0x");
        ByteUtils.printByteArray(checkSum);
        System.out.print("\n");

    }

}
