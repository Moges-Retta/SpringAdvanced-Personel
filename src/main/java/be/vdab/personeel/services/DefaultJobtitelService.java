package be.vdab.personeel.services;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.repositories.JobtitelRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultJobtitelService implements JobtitelService{
    private final JobtitelRepository repository;

    public DefaultJobtitelService(JobtitelRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Jobtitel> findAllByOrderBynaamAsc() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "naam"));
    }

    @Override
    public Optional<Jobtitel> findById(long id) {
        return repository.findById(id);
    }
}
