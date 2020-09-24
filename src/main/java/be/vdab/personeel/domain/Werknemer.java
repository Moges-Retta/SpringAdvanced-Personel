package be.vdab.personeel.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
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
    private long chefid;
    private BigDecimal salaries;
    private String paswoord;
    private LocalDate geboorte;
    private BigInteger rijksregisternr;
    @Version
    private long versie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jobtitelid")
    private Jobtitel jobtitel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chefid")
    private Werknemer manager;

    @OneToMany(mappedBy = "manager")
    private Set<Werknemer> ondergeschikten;

    protected Werknemer() {
    }

    public Werknemer(String familienaam, String voornaam, String email, long chefid,
                     BigDecimal salaries, String paswoord, LocalDate geboorte,
                     BigInteger rijksregisternr, Jobtitel jobtitel,
                     Werknemer manager) {
        this.familienaam = familienaam;
        this.voornaam = voornaam;
        this.email = email;
        this.chefid = chefid;
        this.salaries = salaries;
        this.paswoord = paswoord;
        this.geboorte = geboorte;
        this.rijksregisternr = rijksregisternr;
        this.jobtitel = jobtitel;
        this.manager = manager;
        this.ondergeschikten = new HashSet<>();
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getEmail() {
        return email;
    }

    public long getChefid() {
        return chefid;
    }

    public BigDecimal getSalaries() {
        return salaries;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public LocalDate getGeboorte() {
        return geboorte;
    }

    public BigInteger getRijksregisternr() {
        return rijksregisternr;
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

    public Werknemer getManager() {
        return manager;
    }

    public Set<Werknemer> getOndergeschikten() {
        return Collections.unmodifiableSet(ondergeschikten);
    }

    public String getVoornaam() {
        return voornaam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Werknemer)) return false;
        Werknemer werknemer = (Werknemer) o;
        return Objects.equals(email, werknemer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
    // geeft opsalg aan de werknemer
    public void opslag(BigDecimal bedrag){
        if (bedrag.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException();
        }
        salaries = salaries.add(bedrag);
    }
}
