package org.example;

public class Processor {
    private final Warehouse warehouse;

    public Processor(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Message process(Message message) {
        return warehouse.execute(message);
    }
}
