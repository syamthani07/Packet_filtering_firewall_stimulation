package firewall;

public class Packet {
    private String sourceIP;
    private String destIP;
    private int sourcePort;
    private int destPort;
    private String protocol;

    public Packet(String sourceIP, String destIP, int sourcePort, int destPort, String protocol) {
        this.sourceIP = sourceIP;
        this.destIP = destIP;
        this.sourcePort = sourcePort;
        this.destPort = destPort;
        this.protocol = protocol;
    }

    public String getSourceIP() { return sourceIP; }
    public String getDestIP() { return destIP; }
    public int getSourcePort() { return sourcePort; }
    public int getDestPort() { return destPort; }
    public String getProtocol() { return protocol; }

    @Override
    public String toString() {
        return "Packet{" +
                "sourceIP='" + sourceIP + '\'' +
                ", destIP='" + destIP + '\'' +
                ", sourcePort=" + sourcePort +
                ", destPort=" + destPort +
                ", protocol='" + protocol + '\'' +
                '}';
    }
}
