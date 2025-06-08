package org.example;

public class Encriptor {
    public byte[] encrypt(Message message) {
        return message.serialize();
    }
}
