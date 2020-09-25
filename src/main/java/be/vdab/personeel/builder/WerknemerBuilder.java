package be.vdab.personeel.builder;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.domain.Werknemer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class WerknemerBuilder {
    private String familienaam;
    private String voornaam;
    private String email;
    private long chefid;
    private BigDecimal salaris;
    private String paswoord;
    private LocalDate geboorte;
    private Jobtitel jobtitel;
    private Werknemer manager;
    private Set<Werknemer> ondergeschikten;

    public WerknemerBuilder metVoornaam(String voornaam) {
        this.voornaam = voornaam;
        return this;
    }

    public WerknemerBuilder metFamilienaam(String familienaam) {
        this.familienaam = familienaam;
        return this;
    }

    public WerknemerBuilder metEmail(String email) {
        this.email = email;
        return this;
    }

    public WerknemerBuilder metSalaris(BigDecimal salaris) {
        this.salaris = salaris;
        return this;
    }

    public WerknemerBuilder metPaswoord(String paswoord) {
        this.paswoord = paswoord;
        return this;
    }

    public WerknemerBuilder metGeboorte(LocalDate geboorte) {
        this.geboorte = geboorte;
        return this;
    }

    public WerknemerBuilder metJobtitel(Jobtitel jobtitel) {
        this.jobtitel = jobtitel;
        return this;
    }

    public WerknemerBuilder metManager(Werknemer manager) {
        this.manager = manager;
        return this;
    }

    public WerknemerBuilder metOndergeschikten() {
        this.ondergeschikten = new HashSet<>();
        return this;
    }

    public Werknemer maakWerknemer() {
        return new Werknemer(familienaam, voornaam, email,
                salaris, paswoord, geboorte,
                 jobtitel,
                manager);
    }
}
