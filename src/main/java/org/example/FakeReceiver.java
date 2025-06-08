package org.example;

import java.util.concurrent.BlockingQueue;

public class FakeReceiver {
    private final BlockingQueue<byte[]> queue;

    public FakeReceiver(BlockingQueue<byte[]> queue) {
        this.queue = queue;
    }

    public void receiveMessage() {
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("ADD_ITEM", "Гречка", 1);
            byte[] data = new Encriptor().encrypt(msg);
            queue.offer(data);
        }

        Message query = new Message("QUERY", "Гречка", 0);
        byte[] data = new Encriptor().encrypt(query);
        queue.offer(data);
    }

}

