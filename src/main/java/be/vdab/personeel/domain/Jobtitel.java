package be.vdab.personeel.domain;

import javax.persistence.*;

@Entity
@Table(name = "jobtitels")
public class Jobtitel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @Version
    private long versie;

    protected Jobtitel(){}

    public Jobtitel(String naam) {
        this.naam = naam;
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
}
