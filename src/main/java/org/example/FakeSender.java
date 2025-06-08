package org.example;

import java.net.InetAddress;

public class FakeSender {
    public void sendMessage(byte[] message, InetAddress target) {
        Message msg = Message.deserialize(message);
        System.out.println("Sent to " + target.getHostName() + ": " + msg.command + " " + msg.item + " " + msg.amount);
    }
}
