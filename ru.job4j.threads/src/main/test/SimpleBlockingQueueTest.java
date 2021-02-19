import org.junit.Test;
import ru.job4j.concurrent.SimpleBlockingQueue;
import ru.job4j.concurrent.SingleLockList;

import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer  = new Thread(() -> queue.offer(1));
        Thread consumer = new Thread(() -> {
            try {
                Integer temp = queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(queue.size(), is(0));
    }
}
