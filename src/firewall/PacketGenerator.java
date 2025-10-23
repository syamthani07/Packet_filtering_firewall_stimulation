package firewall;

import java.util.Random;

public class PacketGenerator {
    private static final String[] protocols = {"TCP", "UDP", "ICMP"};
    private static final Random random = new Random();

    public static Packet generatePacket() {
        String srcIP = "192.168.1." + random.nextInt(255);
        String destIP = "10.0.0." + random.nextInt(255);
        int srcPort = random.nextInt(65535);
        int destPort = random.nextInt(1024);
        String protocol = protocols[random.nextInt(protocols.length)];
        return new Packet(srcIP, destIP, srcPort, destPort, protocol);
    }
}
