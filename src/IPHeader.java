/**
 * @author Amol Gaikwad
 * Model class for IP header. Contains the header structure of IP header.
 */

import java.io.IOException;

public class IPHeader {
    byte version;
    byte serviceType;
    byte[] size;
    byte[] packetID;
    byte[] flagsandoffset;
    byte ttl;
    byte protocol;
    byte[] checksum;
    byte[] sourceAddress;
    byte[] destinationAddress;

    public IPHeader(){
        version = 0;
        serviceType = 0;
        size = new byte[2];
        packetID = new byte[2];
        flagsandoffset = new byte[2];
        ttl = 0;
        protocol = 0;
        checksum = new byte[2];
        sourceAddress = new byte[4];
        destinationAddress = new byte[4];
    }

    public void printHeader() throws IOException{
        // Print IP header information
        System.out.print("Version = ");
        System.out.printf("%d", (byte) ((version >> 4) & 0x0F));
        System.out.print("\n");

        System.out.print("Header length = ");
        System.out.printf("%d", 4*(byte)(version & 0x0F));
        System.out.print(" bytes\n");

        System.out.print("Service Type = ");
        System.out.printf("0x");
        System.out.printf("%02X ", serviceType);
        System.out.print("\n");

        System.out.print("Total length = ");
        System.out.printf("%d", ByteUtils.bytesToLong(size));
        System.out.print(" bytes\n");

        System.out.print("Packet Identification = ");
        System.out.printf("0x");
        ByteUtils.printByteArray(packetID);
        System.out.print("\n");

        System.out.print("Flags = ");
        System.out.printf("0x");
        ByteUtils.printByteArray(flagsandoffset);
        System.out.print("\n");

        System.out.print("Fragment offset = ");
        System.out.printf("%d",ByteUtils.bytesToLong(ByteUtils.getFragmentOffsetByteArray(flagsandoffset)));
        System.out.print(" bytes\n");

        System.out.print("Time to live = ");
        System.out.printf("%d", ttl & 0xFF);
        System.out.print(" seconds/hops\n");

        System.out.print("Protocol = ");
        System.out.printf("0x");
        System.out.printf("%02X", protocol);
        System.out.print("\n");

        System.out.print("Header checksum = ");
        System.out.printf("0x");
        ByteUtils.printByteArray(checksum);
        System.out.print("\n");

        System.out.print("Source address = ");
        System.out.print(ByteUtils.convertHexToIP(ByteUtils.bytesToString(sourceAddress)));
        System.out.print("\n");

        System.out.print("Destination address = ");
        System.out.print(ByteUtils.convertHexToIP(ByteUtils.bytesToString(destinationAddress)));
        System.out.print("\n");

    }

}
