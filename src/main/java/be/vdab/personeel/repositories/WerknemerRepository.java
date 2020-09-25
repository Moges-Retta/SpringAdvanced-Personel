package be.vdab.personeel.repositories;

import be.vdab.personeel.domain.Werknemer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WerknemerRepository extends JpaRepository<Werknemer,Long> {
    @Query("select w from Werknemer w where w.chef.id is null")
    Optional<Werknemer> findChef();
}
