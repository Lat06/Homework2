package org.example;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class Warehouse {
    private final Map<String, Integer> inventory = new ConcurrentHashMap<>();

    public synchronized Message execute(Message m) {
        switch (m.command) {
            case "ADD_ITEM":
                inventory.merge(m.item, m.amount, Integer::sum);
                return new Message("OK");
            case "REMOVE_ITEM":
                inventory.merge(m.item, -m.amount, Integer::sum);
                return new Message("OK");
            case "QUERY":
                return new Message("OK", m.item, inventory.getOrDefault(m.item, 0));
            default:
                return new Message("ERROR");
        }
    }

    public int getAmount(String item) {
        return inventory.getOrDefault(item, 0);
    }
}
