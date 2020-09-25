package be.vdab.personeel.services;

import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.repositories.WerknemerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

}
