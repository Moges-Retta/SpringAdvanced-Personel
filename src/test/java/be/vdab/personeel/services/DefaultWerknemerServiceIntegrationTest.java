package be.vdab.personeel.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(DefaultWerknemerService.class)
@ComponentScan(value = "be.vdab.personeel.repositories",
        resourcePattern = "WerknemerRepository.class")
@Sql({"/insertJobtitel.sql","/insertWerknemer.sql"})
public class DefaultWerknemerServiceIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final DefaultWerknemerService service;
    private final EntityManager manager;

    public DefaultWerknemerServiceIntegrationTest(DefaultWerknemerService service, EntityManager manager) {
        this.service = service;
        this.manager = manager;
    }
    private long idVanTestWerknemer() {
        return super.jdbcTemplate.queryForObject(
                "select id from werknemers where voornaam ='test'", Long.class);
    }
    @Test
    void updateSalaris(){
        var id = idVanTestWerknemer();
        service.Opslag(id, BigDecimal.ONE);
        manager.flush();
        assertThat(super.jdbcTemplate.queryForObject(
                "select salaris from werknemers where id=?", BigDecimal.class, id))
                .isEqualByComparingTo(BigDecimal.valueOf(2));
    }

}
