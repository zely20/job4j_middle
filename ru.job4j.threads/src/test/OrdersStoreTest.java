import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.integration_test.Order;
import ru.job4j.integration_test.OrdersStore;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("/Users/alexandr/IdeaProjects/job4j_middle/db/create.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenSaveOrderAndFindByName() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("name1", "description1"));
        Order result = store.findByName("name1");
        assertThat(result.getName(), is("name1"));
    }

    @Test
    public void whenSaveOrderAndReplace() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("name1", "description1"));
        store.save(Order.of("name2", "description2"));
        Order result = Order.of("replace", "eplace");
        store.replace(1, result);
        assertThat(store.findById(1).getName(), is("replace"));
        assertThat(store.findById(1).getDescription(), is("eplace"));
    }
}
