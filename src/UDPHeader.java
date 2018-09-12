/**
 * @author Amol Gaikwad
 * Model class for UDP header. Contains the header structure of UDP header.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UDPHeader {

    byte[] sourcePort;
    byte[] destinationPort;
    byte[] size;
    byte[] checkSum;
    List<Byte> payload;

    public UDPHeader(){
        sourcePort = new byte[2];
        destinationPort = new byte[2];
        size = new byte[2];
        checkSum = new byte[2];
        payload = new ArrayList<Byte>();
    }

    public void printHeader() throws IOException {
        // Print UDP header information
        System.out.print("Source port = ");
        System.out.printf("%d", ByteUtils.bytesToLong(sourcePort));
        System.out.print("\n");

        System.out.print("Destination port = ");
        System.out.printf("%d", ByteUtils.bytesToLong(destinationPort));
        System.out.print("\n");

        System.out.print("Length = ");
        System.out.printf("%d", ByteUtils.bytesToLong(size));
        System.out.print("\n");

        System.out.print("Checksum = ");
        System.out.printf("0x");
        ByteUtils.printByteArray(checkSum);
        System.out.print("\n");

        if(payload != null && payload.size() > 0) {
            System.out.print("\nData (first 64 bytes)");
            ByteUtils.printPayload(payload, 64);
            System.out.print("\n");
        }else{
            System.out.print("\nNo data");
        }
    }

}
