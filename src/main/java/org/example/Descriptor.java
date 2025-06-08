package org.example;

public class Descriptor {
    public Message decrypt(byte[] message) {
        return Message.deserialize(message);
    }
}
