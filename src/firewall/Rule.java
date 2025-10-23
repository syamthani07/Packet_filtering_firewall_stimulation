package firewall;

public class Rule {
    private String protocol;
    private int port;
    private boolean allow;

    public Rule(String protocol, int port, boolean allow) {
        this.protocol = protocol;
        this.port = port;
        this.allow = allow;
    }

    public boolean matches(Packet packet) {
        return packet.getProtocol().equalsIgnoreCase(protocol) && packet.getDestPort() == port;
    }

    public boolean isAllow() { return allow; }

    @Override
    public String toString() {
        return "Rule{" +
                "protocol='" + protocol + '\'' +
                ", port=" + port +
                ", allow=" + allow +
                '}';
    }
}
