package firewall;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Firewall {
    private List<Rule> rules = new ArrayList<>();
    private File logFile;

    public Firewall() {
        try {
            File logDir = new File("logs");
            if (!logDir.exists()) logDir.mkdir();

            logFile = new File("logs/firewall_log.txt");
            if (!logFile.exists()) logFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public boolean checkPacket(Packet packet) {
        for (Rule rule : rules) {
            if (rule.matches(packet)) {
                log(packet, rule.isAllow());
                return rule.isAllow();
            }
        }
        log(packet, false); // default deny if no rule matches
        return false;
    }

    private void log(Packet packet, boolean allowed) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
            writer.println(packet.toString() + " -> " + (allowed ? "ALLOWED" : "DENIED"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

