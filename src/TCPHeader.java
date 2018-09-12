/**
 * @author Amol Gaikwad
 * Model class for TCP header. Contains the header structure of TCP header.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TCPHeader {

    byte[] sourcePort;
    byte[] destinationPort;
    byte[] seqNo;
    byte[] ackNo;
    byte[] reserved;
    byte[] tcpWindow;
    byte[] checkSum;
    byte[] urgent;
    List<Byte> payload;


    public TCPHeader(){

        sourcePort = new byte[2];
        destinationPort = new byte[2];
        seqNo = new byte[4];
        ackNo = new byte[4];
        reserved = new byte[2];
        tcpWindow = new byte[2];
        checkSum = new byte[2];
        urgent = new byte[2];
        payload = new ArrayList<Byte>();

    }

    public void printHeader() throws IOException {
        // Print TCP header information
        System.out.print("Source port = ");
        System.out.printf("%d", ByteUtils.bytesToLong(sourcePort));
        System.out.print("\n");

        System.out.print("Destination port = ");
        System.out.printf("%d", ByteUtils.bytesToLong(destinationPort));
        System.out.print("\n");

        System.out.print("Sequence number = ");
        System.out.printf("%d", ByteUtils.bytesToLong(seqNo));
        System.out.print("\n");

        System.out.print("Acknowledgement number = ");
        System.out.printf("%d", ByteUtils.bytesToLong(ackNo));
        System.out.print("\n");

        System.out.print("Header length = ");
        System.out.printf("%d", 4*(byte) ((reserved[0] >> 4) & 0x0F));
        System.out.print(" bytes\n");

        System.out.print("Flags = ");
        System.out.printf("0x");
        ByteUtils.printByteArray(ByteUtils.getFlagByteArray(reserved));
        System.out.print("\n");

        System.out.print("Window size = ");
        System.out.printf("%d", ByteUtils.bytesToLong(tcpWindow));
        System.out.print("\n");

        System.out.print("Checksum = ");
        System.out.printf("0x");
        ByteUtils.printByteArray(checkSum);
        System.out.print("\n");

        System.out.print("Urgent pointer = ");
        System.out.printf("0x");
        ByteUtils.printByteArray(urgent);
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

