package be.vdab.personeel.services;

import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.exceptions.WerknemerNietGevondenException;
import be.vdab.personeel.repositories.WerknemerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class DefaultWerknemerService implements WerknemerService{
    private final WerknemerRepository repository;

    public DefaultWerknemerService(WerknemerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Werknemer> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Werknemer> findChef() {
        return repository.findChef();
    }

    @Override
    public void Opslag(long id, BigDecimal bedrag) {
        repository.findById(id)
                .orElseThrow(WerknemerNietGevondenException::new)
                .opslag(bedrag);
    }
}
