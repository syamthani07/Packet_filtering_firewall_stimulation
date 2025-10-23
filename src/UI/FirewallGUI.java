package ui;

import firewall.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FirewallGUI extends JFrame {
    private Firewall firewall;
    private JComboBox<String> protocolBox, actionBox;
    private JTextField portField;
    private JTable ruleTable;
    private DefaultTableModel ruleModel;
    private JTextArea logArea;

    public FirewallGUI() {
        firewall = new Firewall();

        setTitle("🛡 Firewall Dashboard");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(10, 15, 28));

        JLabel title = new JLabel("Firewall Dashboard", SwingConstants.CENTER);
        title.setForeground(new Color(100, 200, 255));
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        add(createRulePanel(), BorderLayout.WEST);
        add(createLogPanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createRulePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(300, 0));
        panel.setBackground(new Color(16, 23, 40));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Rule Management");
        label.setForeground(new Color(0, 255, 255));
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(label, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(0, 1, 10, 10));
        form.setBackground(new Color(16, 23, 40));

        protocolBox = new JComboBox<>(new String[]{"TCP", "UDP", "ICMP"});
        portField = new JTextField();
        actionBox = new JComboBox<>(new String[]{"Allow", "Deny"});

        JButton addRuleBtn = new JButton("+ Add Rule");
        addRuleBtn.setBackground(new Color(0, 180, 255));
        addRuleBtn.setForeground(Color.WHITE);
        addRuleBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addRuleBtn.addActionListener(e -> addRule());

        form.add(new JLabel("Protocol:"));
        form.add(protocolBox);
        form.add(new JLabel("Port:"));
        form.add(portField);
        form.add(new JLabel("Action:"));
        form.add(actionBox);
        form.add(addRuleBtn);

        panel.add(form, BorderLayout.CENTER);

        ruleModel = new DefaultTableModel(new String[]{"Protocol", "Port", "Action"}, 0);
        ruleTable = new JTable(ruleModel);
        JScrollPane tableScroll = new JScrollPane(ruleTable);
        tableScroll.setPreferredSize(new Dimension(250, 200));
        panel.add(tableScroll, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createLogPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(16, 23, 40));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Real-Time Logs");
        label.setForeground(new Color(180, 140, 255));
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(label, BorderLayout.NORTH);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setBackground(new Color(12, 19, 36));
        logArea.setForeground(Color.WHITE);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(logArea);
        panel.add(scroll, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        btnPanel.setBackground(new Color(16, 23, 40));

        JButton genBtn = new JButton("⚡ Generate Packet");
        JButton testBtn = new JButton("Test Packet");
        genBtn.setBackground(new Color(150, 70, 255));
        genBtn.setForeground(Color.WHITE);
        testBtn.setBackground(new Color(0, 200, 100));
        testBtn.setForeground(Color.WHITE);

        genBtn.addActionListener(e -> generatePacket());
        testBtn.addActionListener(e -> generatePacket());

        btnPanel.add(genBtn);
        btnPanel.add(testBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void addRule() {
        try {
            String protocol = (String) protocolBox.getSelectedItem();
            int port = Integer.parseInt(portField.getText());
            boolean allow = actionBox.getSelectedItem().equals("Allow");

            Rule rule = new Rule(protocol, port, allow);
            firewall.addRule(rule);
            ruleModel.addRow(new Object[]{protocol, port, allow ? "Allow" : "Deny"});

            JOptionPane.showMessageDialog(this, "Rule added successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter valid input!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generatePacket() {
        Packet p = PacketGenerator.generatePacket();
        boolean allowed = firewall.checkPacket(p);

        String result = String.format(
                "[%s] %s | %s:%d → %s:%d  →  %s%n",
                new java.util.Date(),
                p.getProtocol(), p.getSourceIP(), p.getSourcePort(),
                p.getDestIP(), p.getDestPort(),
                allowed ? "✅ ALLOWED" : "❌ BLOCKED"
        );
        logArea.insert(result, 0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FirewallGUI::new);
    }
}
