**Summary:**

PacketAnalyzer is a Java application that reads network packets (TCP/UDP/ICMP) as input and displays a detailed summary of those packets.

**Features:**
1. Reads pcap formatted files. Skips first 40 bytes of pcap file as pcap header length.
2. Reads and displays Ethernet headers.
3. Reads and displays IPv4 packets.
4. Reads and displays TCP, UDP and ICMP packets.

**Contents:**

PacketAnalyzerMain.java - Main program that reads and displays entire packet format.

EtherHeader.java - Stores Ether packet structure.

IPHeader.java - Stores IPv4 packet structure.

TCPHeader.java - Stores TCP packet structure.

UDPHeader.java - Stores UDP packet structure.

ICMPHeader.java - Stores ICMP packet structure.

ByteUtils.java - Contains utility methods for byte conversions.

tcp.pcap & tcp2.pcap - Sample TCP pcap hex files.

udp.pcap & udp2.pcap - Sample UDP pcap hex files.

icmp.pcap & icmp2.pcap - Sample ICMP pcap hex files.

**How to use:**

Extract the PacketAnalyzer.zip file.

Go to src directory inside PacketAnalyzer. Compile all the java classes in src folder using

javac -c *.java

Run PacketAnalyzerMain.java with a pcap file as command line argument.

Example 1: java PacketAnalyzerMain tcp.pcap

Example 2: java PacketAnalyzerMain udp.pcap