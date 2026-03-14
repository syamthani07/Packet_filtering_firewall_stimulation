# Packet Filtering Firewall Simulation

This project simulates a **packet filtering firewall**, a basic network security mechanism used to control incoming and outgoing traffic based on predefined rules.

The firewall inspects packet information such as **source IP address, destination IP address, port numbers, and protocol**, and decides whether the packet should be **allowed or blocked**.

The goal of this project is to demonstrate how **rule-based packet filtering works in network security systems.**

---

## Features

- Simulates packet inspection
- Applies firewall rules to incoming packets
- Allows or blocks packets based on rules
- Demonstrates basic firewall functionality
- Simple implementation for learning packet filtering concepts

---

## How It Works

1. A packet arrives at the firewall.
2. The firewall reads the packet header information.
3. The packet is compared with predefined firewall rules.
4. If the packet matches an **allow rule**, it is accepted.
5. If the packet matches a **deny rule**, it is blocked.

---

## Requirements

- Python 3.x

---

## How to Run

Clone the repository:

```bash
git clone https://github.com/syamthani07/Packet_filtering_firewall_stimulation.git
