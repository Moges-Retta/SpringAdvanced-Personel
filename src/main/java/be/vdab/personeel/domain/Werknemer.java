package be.vdab.personeel.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "werknemers")
public class Werknemer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String familienaam;
    private String voornaam;
    private String email;
    private BigDecimal salaris;
    private String paswoord;
    private LocalDate geboorte;
    @Version
    private long versie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jobtitelid")
    private Jobtitel jobtitel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chefid")
    private Werknemer chef;

    @OneToMany(mappedBy = "chef")
    private Set<Werknemer> ondergeschikten;

    protected Werknemer() {
    }

    public Werknemer(String familienaam, String voornaam, String email,
                     BigDecimal salaris, String paswoord, LocalDate geboorte,
                     Jobtitel jobtitel,
                     Werknemer chef) {
        this.familienaam = familienaam;
        this.voornaam = voornaam;
        this.email = email;
        this.salaris = salaris;
        this.paswoord = paswoord;
        this.geboorte = geboorte;
        this.jobtitel = jobtitel;
        this.chef = chef;
        this.ondergeschikten = new HashSet<>();
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSalaris() {
        return salaris;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public LocalDate getGeboorte() {
        return geboorte;
    }

    public long getVersie() {
        return versie;
    }

    public long getId() {
        return id;
    }

    public Jobtitel getJobtitel() {
        return jobtitel;
    }

    public Werknemer getChef() {
        return chef;
    }

    public Set<Werknemer> getOndergeschikten() {
        return Collections.unmodifiableSet(ondergeschikten);
    }

    public String getVoornaam() {
        return voornaam;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Werknemer)) return false;
        return email.equalsIgnoreCase(((Werknemer) o).email);
    }

    @Override
    public int hashCode() {
        return  email == null ? 0 : email.toLowerCase().hashCode();
    }

    // verhoog de salaries met een bedrag
    public void opslag(BigDecimal bedrag){
        if (bedrag.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException();
        }
        salaris = salaris.add(bedrag);
    }
}
