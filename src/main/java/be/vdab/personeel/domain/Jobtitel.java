package be.vdab.personeel.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "jobtitels")
public class Jobtitel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @Version
    private long versie;
    @OneToMany(mappedBy = "jobtitel")
    private Set<Werknemer> werknemers;

    protected Jobtitel(){}

    public Jobtitel(String naam) {
        this.naam = naam;
        this.werknemers=new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public long getVersie() {
        return versie;
    }

    public Set<Werknemer> getWerknemers() {
        return Collections.unmodifiableSet(werknemers);
    }
}
