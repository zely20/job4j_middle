import org.junit.Test;
import ru.job4j.concurrent.RolColSum;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RolColSumTest {

    @Test
    public void sumCol() throws InterruptedException {
        RolColSum.Sums[] sums = RolColSum.sum(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        sums[1].getColSum();
        assertThat(sums[0].getColSum(), is(12));
    }

    @Test
    public void sumRow() throws InterruptedException {
        RolColSum.Sums[] sums = RolColSum.sum(new int[][]{{1,2,3},{4,5,6},{7,8,9}});

        assertThat(sums[0].getRowSum(), is(6));
    }

    @Test
    public void aSumCol() throws InterruptedException, ExecutionException {
        RolColSum.Sums[] sums = RolColSum.asyncSum(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        assertThat(sums[0].getColSum(), is(12));
    }

}
