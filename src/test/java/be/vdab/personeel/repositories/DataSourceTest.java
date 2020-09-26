package be.vdab.personeel.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@DataJpaTest
public class DataSourceTest {
        private final DataSource dataSource;
        public DataSourceTest(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Test
        void getConnection() throws SQLException {
            try (var connection = dataSource.getConnection()) {
            }
        }
}
