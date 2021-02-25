import org.junit.Test;
import ru.job4j.concurrent.SimpleBlockingQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(size);
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

    @Test
    public void testTwo() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(size);
        Thread producer  = new Thread(() -> queue.offer(1));
        producer.start();
        producer.join();
        assertThat(queue.poll(), is(1));
    }
}
