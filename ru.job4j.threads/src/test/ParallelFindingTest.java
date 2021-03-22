import org.junit.Test;
import ru.job4j.concurrent.ParallelFinding;

import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParallelFindingTest {

    @Test
    public void findValueLowTen(){
        int[] array = new int[] {3,4,5,6};
        int  result = ParallelFinding.findIndex(array, 4);
        assertThat(result,is(1));
    }

    @Test
    public void findValue(){
        int[] array = IntStream.range(1, 31).toArray();
        int  result = ParallelFinding.findIndex(array, 30);
        assertThat(result,is(29));
    }

    @Test
    public void findValueLot(){
        int[] array = IntStream.range(1, 1_000_000).toArray();
        int  result = ParallelFinding.findIndex(array, 100000);
        assertThat(result,is(99999));
    }
}
