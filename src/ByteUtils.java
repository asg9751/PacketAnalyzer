/**
 * @author Amol Gaikwad
 * Utility class containing byte utility methods such as printing byte array, converting byte to long and printing payload.
 */

import java.util.List;

public class ByteUtils {

    public static byte[] getByteArray(List<Byte> bytesList, int startIdx, int length) {
        /** This method returns a byte array with specified start index and length from byte list.
         *
         */

        byte[] bytes = new byte[length];
        for (int i = startIdx, byteIdx = 0; i < startIdx+length; i++, byteIdx++) {
            bytes[byteIdx] = bytesList.get(i);
        }

        return bytes;
    }

    public static void printByteArray(byte[] byteArr){
        /** This method prints out the byte array in hex.
         */

        for(byte b: byteArr) {
            System.out.printf("%02X", b);
        }
    }

    public static void printSeparatedByteArray(byte[] byteArr){
        /** This method prints out byte array in hex separated by colon.
         */

        for(byte b: byteArr) {
            System.out.printf("%02X:", b);
        }
    }

    public static long bytesToLong(byte[] byteArr) {
        /** This method converts a byte array to long.
         */

        long val = 0;
        for (int i = 0; i < byteArr.length; i++) {
            val <<= 8;
            val = val | (byteArr[i] & 0xFF);
        }
        return val;
    }

    public static String convertHexToIP(String hex){
        /** This method converts a hex IP address to number string.
         */

        String ipAddress = "";
        Long ipPart;
        for(int i = 0; i < hex.length(); i += 2)
        {
            ipPart = Long.parseLong(hex.substring(i, i + 2), 16);
            ipAddress+= (String.format("%d.", ipPart));
        }
        return ipAddress;
    }

    public static String bytesToString(byte[] byteArr) {
        /** This method converts a byte array into number and returns it as a string.
         */

        String str = "";
        for (byte b : byteArr) {
            str += (String.format("%02x", b));
        }
        return str;
    }

    public static byte[] getFragmentOffsetByteArray(byte[] byteArr) {
        /** This method returns the fragment offset of IP header
         */

        byte[] bytes = new byte[2];
        bytes[0] = byteArr[0];
        bytes[1] = (byte)(byteArr[1] & 0x1F);

        return bytes;
    }

    public static void printPayload(List<Byte> payload, int length){
        /** This method prints the payload in hex with specified length.
         */

        for(int i = 0; i < length; i++) {
            if(i % 8 == 0) {
                System.out.print("\n");
            }
            System.out.printf("%02X ", payload.get(i));

        }
    }

    public static byte[] getFlagByteArray(byte[] byteArr) {
        /** This method prints the flags byte in TCP
         */

        byte[] bytes = new byte[2];
        bytes[0] = (byte)(byteArr[0] & 0x0F);
        bytes[1] = byteArr[1];
        return bytes;
    }
}
