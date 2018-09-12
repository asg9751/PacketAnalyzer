/**
 * @author Amol Gaikwad
 * Model class for Ether header. Contains the header structure of ether header.
 */

import java.io.IOException;

public class EtherHeader {
    byte[] destinationAddress;
    byte[] sourceAddress;
    byte[] type;

    public EtherHeader(){
        destinationAddress = new byte[6];
        sourceAddress = new byte[6];
        type = new byte[2];
    }


    public void printHeader() throws IOException{
        // Print Ether header information
        System.out.print("Destination address = ");
        ByteUtils.printSeparatedByteArray(destinationAddress);
        System.out.print("\n");

        System.out.print("Source address = ");
        ByteUtils.printSeparatedByteArray(sourceAddress);
        System.out.print("\n");

        System.out.print("Ether Type = ");
        System.out.printf("0x");
        ByteUtils.printByteArray(type);
        System.out.print("\n");

    }

}
