/**
 * @author Amol Gaikwad
 * Main program that takes pcap file as input, reads the packet and prints out the result. Supports IPv4.
 */

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class PacketAnalyzerMain {

    public static void main(String[] args) {
        /** The main method. The pcap file is passed as a command line argument. Calls the analyzePacket method to parse
         *   the pcap file.
         */

        try {
            BufferedInputStream sourceStream = null;
            PacketAnalyzerMain pmain = new PacketAnalyzerMain();
            if(args.length==1) {
                sourceStream = new BufferedInputStream(new FileInputStream(args[0]));
                pmain.analyzePacket(sourceStream);
            }else {
                System.out.println("Invalid number of arguments");
            }
        } catch ( Exception e ) {
            System.out.println("Exception"+e);
            e.printStackTrace();
        }
    }

    private void analyzePacket(InputStream stream) throws IOException {
        /** This method reads the pcap file in a byte list. Then, it calls the appropriate methods to populate and print
         * Ether, IP and TCP/UDP/ICMP headers based on the protocol.
         */

        int byteval = 0;
        int bytecount = 0;
        int offset = 0;
        int protocol = 0;

        List<Byte> list = new ArrayList<Byte>();

        while ((byteval = stream.read()) != -1) {
            bytecount ++;
            // Skip pcap header bytes
            if(bytecount > 40)
                list.add((byte) byteval);
        }

        offset = analyzeEtherPacket(list,offset);

        int res[] = analyzeIPPacket(list,offset);
        offset = res[0];
        protocol = res[1];

        // Check protocol
        if (protocol == 6){
            // TCP packet
            offset = analyzeTCPPacket(list,offset);
        } else if(protocol == 17){
            // UDP packet
            offset = analyzeUDPPacket(list,offset);
        }else if(protocol == 1){
            // ICMP packet
            offset = analyzeICMPPacket(list,offset);
        }

    }

    private int analyzeEtherPacket(List<Byte> list, int offset) throws IOException{
        /** This method populates and prints Ether header. It returns the offset which is a marker upto which bytes have
         *  been read from byte list.
         */

        // Populate ether header
        EtherHeader ethHead = new EtherHeader();
        ethHead.destinationAddress = ByteUtils.getByteArray(list, offset, ethHead.destinationAddress.length);
        offset += ethHead.destinationAddress.length;
        ethHead.sourceAddress = ByteUtils.getByteArray(list, offset, ethHead.sourceAddress.length);
        offset += ethHead.sourceAddress.length;
        ethHead.type = ByteUtils.getByteArray(list, offset, ethHead.type.length);
        offset += ethHead.type.length;

        // Print Ether header
        System.out.println("************* Ethernet header **************\n");
        ethHead.printHeader();
        System.out.println("\n************* End **************************\n");

        return offset;
    }

    private int[] analyzeIPPacket(List<Byte> list, int offset) throws IOException{
        /** This method populates and prints IP header. It returns an int array that contains the offset which is a
         *  marker upto which bytes have been read from byte list and the protocol such as TCP/UDP/ICMP.
         */

        int result[] = new int[2];
        // Populate IP header
        IPHeader ipHead = new IPHeader();
        ipHead.version = ByteUtils.getByteArray(list, offset, 1)[0];
        offset++;
        ipHead.serviceType = ByteUtils.getByteArray(list, offset, 1)[0];
        offset++;
        ipHead.size = ByteUtils.getByteArray(list, offset, ipHead.size.length);
        offset += ipHead.size.length;
        ipHead.packetID = ByteUtils.getByteArray(list, offset, ipHead.packetID.length);
        offset += ipHead.packetID.length;
        ipHead.flagsandoffset = ByteUtils.getByteArray(list, offset, ipHead.flagsandoffset.length);
        offset += ipHead.flagsandoffset.length;
        ipHead.ttl = ByteUtils.getByteArray(list, offset, 1)[0];
        offset++;
        ipHead.protocol = ByteUtils.getByteArray(list, offset, 1)[0];
        offset++;
        ipHead.checksum = ByteUtils.getByteArray(list, offset, ipHead.checksum.length);
        offset += ipHead.checksum.length;
        ipHead.sourceAddress = ByteUtils.getByteArray(list, offset, ipHead.sourceAddress.length);
        offset += ipHead.sourceAddress.length;
        ipHead.destinationAddress = ByteUtils.getByteArray(list, offset, ipHead.destinationAddress.length);
        offset += ipHead.destinationAddress.length;

        // Print IP header
        System.out.println("\n\n************* IP header ********************\n");
        ipHead.printHeader();
        System.out.println("\n************* End **************************\n");

        // Get protocol from ipheader
        result[0] = offset;
        result[1] = ipHead.protocol;

        return result;
    }

    private int analyzeTCPPacket(List<Byte> list, int offset) throws IOException{
        /** This method populates and prints TCP header. It returns the offset which is a marker upto which bytes have
         *  been read from byte list.
         */

        // Populate TCP header
        TCPHeader tcpHead = new TCPHeader();
        tcpHead.sourcePort = ByteUtils.getByteArray(list, offset, tcpHead.sourcePort.length);
        offset += tcpHead.sourcePort.length;
        tcpHead.destinationPort = ByteUtils.getByteArray(list, offset, tcpHead.destinationPort.length);
        offset += tcpHead.destinationPort.length;
        tcpHead.seqNo = ByteUtils.getByteArray(list, offset, tcpHead.seqNo.length);
        offset += tcpHead.seqNo.length;
        tcpHead.ackNo = ByteUtils.getByteArray(list, offset, tcpHead.ackNo.length);
        offset += tcpHead.ackNo.length;
        tcpHead.reserved = ByteUtils.getByteArray(list, offset, tcpHead.reserved.length);
        offset += tcpHead.reserved.length;
        tcpHead.tcpWindow = ByteUtils.getByteArray(list, offset, tcpHead.tcpWindow.length);
        offset += tcpHead.tcpWindow.length;
        tcpHead.checkSum = ByteUtils.getByteArray(list, offset, tcpHead.checkSum.length);
        offset += tcpHead.checkSum.length;
        tcpHead.urgent = ByteUtils.getByteArray(list, offset, tcpHead.urgent.length);
        offset += tcpHead.urgent.length;
        if(list.subList(offset, list.size()).size() > 64) {
            // If payload size is greater than 64 bytes, copy only first 64 bytes
            tcpHead.payload = list.subList(offset, offset + 65);
        }else {
            // If payload size is less than 64 bytes, copy all bytes
            tcpHead.payload = list.subList(offset, list.size());
        }
        offset += tcpHead.payload.size();

        // Print TCP header
        System.out.println("\n\n************* TCP header ********************\n");
        tcpHead.printHeader();
        System.out.println("\n************* End **************************\n");

        return offset;
    }

    private int analyzeUDPPacket(List<Byte> list, int offset) throws IOException{
        /** This method populates and prints UDP header. It returns the offset which is a marker upto which bytes have
         *  been read from byte list.
         */

        // Populate UDP header
        UDPHeader udpHead = new UDPHeader();
        udpHead.sourcePort = ByteUtils.getByteArray(list, offset, udpHead.sourcePort.length);
        offset += udpHead.sourcePort.length;
        udpHead.destinationPort = ByteUtils.getByteArray(list, offset, udpHead.destinationPort.length);
        offset += udpHead.destinationPort.length;
        udpHead.size = ByteUtils.getByteArray(list, offset, udpHead.size.length);
        offset += udpHead.size.length;
        udpHead.checkSum = ByteUtils.getByteArray(list, offset, udpHead.checkSum.length);
        offset += udpHead.checkSum.length;
        if(list.subList(offset, list.size()).size() > 64) {
            // If payload size is greater than 64 bytes, copy only first 64 bytes
            udpHead.payload = list.subList(offset, offset + 65);
        }else {
            // If payload size is less than 64 bytes, copy all bytes
            udpHead.payload = list.subList(offset, list.size());
        }
        offset += udpHead.payload.size();

        // Print UDP header
        System.out.println("\n\n************* UDP header ********************\n");
        udpHead.printHeader();
        System.out.println("\n************* End **************************\n");

        return offset;
    }

    private int analyzeICMPPacket(List<Byte> list, int offset) throws IOException{
        /** This method populates and prints ICMP header. It returns the offset which is a marker upto which bytes have
         *  been read from byte list.
         */

        // Populate ICMP header
        ICMPHeader icmpHead = new ICMPHeader();
        icmpHead.type = ByteUtils.getByteArray(list, offset, 1)[0];
        offset++;
        icmpHead.code = ByteUtils.getByteArray(list, offset, 1)[0];
        offset++;
        icmpHead.checkSum = ByteUtils.getByteArray(list, offset, icmpHead.checkSum.length);
        offset += icmpHead.checkSum.length;

        // Print ICMP header
        System.out.println("\n\n************* ICMP header ********************\n");
        icmpHead.printHeader();
        System.out.println("\n************* End **************************\n");

        return offset;
    }

}
