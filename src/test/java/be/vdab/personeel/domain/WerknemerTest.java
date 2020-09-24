package be.vdab.personeel.domain;

import be.vdab.personeel.builder.WerknemerBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class WerknemerTest {
    private Werknemer werknemer;

    @BeforeEach
    void beforeEach() {
        var builder = new WerknemerBuilder();
        werknemer = builder.metFamilienaam("test")
                .metVoornaam("test")
                .metEmail("test")
                .metGeboorte(LocalDate.of(2000,1,1))
                .metJobtitel(new Jobtitel("test"))
                .metPaswoord("test")
                .metSalaries(BigDecimal.ONE)
                .metRijksregisternr(BigInteger.ONE)
                .metChefid(1)
                .metVersie(1L)
                .metManager(new Werknemer())
                .metOndergeschikten()
                .maakWerknemer();
    }
    @Test
    void opslag() {
        werknemer.opslag(BigDecimal.TEN);
        assertThat(werknemer.getSalaries()).isEqualByComparingTo("11");
    }
    @Test
    void opslagMetNullMislukt() {
        assertThatNullPointerException().isThrownBy(() -> werknemer.opslag(null));
    }
    @Test
    void opslagMet0Mislukt() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> werknemer.opslag(BigDecimal.ZERO));
    }
    @Test
    void negatieveOpslagMislukt() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> werknemer.opslag(BigDecimal.valueOf(-1)));
    }
}