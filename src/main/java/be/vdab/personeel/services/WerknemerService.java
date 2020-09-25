package be.vdab.personeel.services;

import be.vdab.personeel.domain.Werknemer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface WerknemerService {
    Optional<Werknemer> findById(long id);
    Optional<Werknemer> findChef();
    void Opslag(long id, BigDecimal bedrag);
}
