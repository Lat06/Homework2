import org.example.Message;
import org.example.Warehouse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.*;

public class WarehouseTest {

    @Test
    public void testConcurrentAddition() throws InterruptedException {
        Warehouse warehouse = new Warehouse();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; i++) {
            executor.submit(() ->
                    warehouse.execute(new Message("ADD_ITEM", "Гречка", 1))
            );
        }

        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);

        assertEquals(1000, warehouse.getAmount("Гречка"));
    }
}
