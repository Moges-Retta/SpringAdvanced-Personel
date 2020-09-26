package be.vdab.personeel.services;

import be.vdab.personeel.builder.WerknemerBuilder;
import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.repositories.WerknemerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultWerknemerServiceUnitTest {
    private WerknemerService service;
    @Mock
    private WerknemerRepository repository;
    private Werknemer werknemer;
    @BeforeEach
    void beforeEach(){
        var builder = new WerknemerBuilder();
        werknemer = builder.metFamilienaam("test")
                .metVoornaam("test")
                .metEmail("test")
                .metGeboorte(LocalDate.of(2000,1,1))
                .metJobtitel(new Jobtitel("test"))
                .metPaswoord("test")
                .metSalaris(BigDecimal.ONE)
                .metOndergeschikten()
                .maakWerknemer();
        service = new DefaultWerknemerService(repository);
    }
    @Test
    void updateSalaris(){
        when(repository.findById(1L)).thenReturn(Optional.of(werknemer));
        service.Opslag(1,BigDecimal.ONE);
        assertThat(werknemer.getSalaris()).isEqualTo(BigDecimal.valueOf(2));
        verify(repository).findById(1L);
    }

}
