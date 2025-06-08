package org.example;
import java.net.InetAddress;
import java.util.concurrent.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BlockingQueue<byte[]> incoming = new LinkedBlockingQueue<>();
        BlockingQueue<Message> decrypted = new LinkedBlockingQueue<>();
        BlockingQueue<Message> processed = new LinkedBlockingQueue<>();

        FakeSender sender = new FakeSender();
        Descriptor descriptor = new Descriptor();
        Encriptor encriptor = new Encriptor();
        Warehouse warehouse = new Warehouse();
        Processor processor = new Processor(warehouse);

        FakeReceiver receiver = new FakeReceiver(incoming);
        receiver.receiveMessage();

        ExecutorService pool = Executors.newFixedThreadPool(5);

        pool.submit(() -> {
            while (true) {
                byte[] b = incoming.take();
                Message m = descriptor.decrypt(b);
                decrypted.put(m);
            }
        });

        pool.submit(() -> {
            while (true) {
                Message msg = decrypted.take();
                Message response = processor.process(msg);
                processed.put(response);
            }
        });

        pool.submit(() -> {
            while (true) {
                Message response = processed.take();
                byte[] data = encriptor.encrypt(response);
                sender.sendMessage(data, InetAddress.getLoopbackAddress());
            }
        });

        Thread.sleep(3000);
        pool.shutdownNow();
        System.out.println("Final amount: " + warehouse.getAmount("Гречка"));
    }
}
