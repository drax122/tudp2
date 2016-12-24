package spring.hibernate.domain;
import spring.hibernate.domain.Wybieg;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Created by draxeer on 2016-12-22.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "zwierz.all", query = "Select z from Zwierz z"),
        @NamedQuery(name = "zwierz.getZwierzFromWybieg", query = "Select z from Zwierz z WHERE z.wybieg = :idWybieg")
})

public class Zwierz {

    private Long zwierz_id;
    private String rasa;
    private String gatunek;
    private String co_szama;
    private Wybieg wybieg;

    public Zwierz(String rasa, String gatunek, String co_szama, Wybieg w) {
        this.rasa = rasa;
        this.gatunek = gatunek;
        this.co_szama = co_szama;
        this.wybieg = w;
    }
    public Zwierz(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getZwierz_ID() {
        return zwierz_id;
    }

    public void setZwierz_ID(Long zwierz_ID) {
        this.zwierz_id = zwierz_ID;
    }
    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    public String getCo_szama() {
        return co_szama;
    }

    public void setCo_szama(String co_szama) {
        this.co_szama = co_szama;
    }

    @ManyToOne
    @JoinColumn(name = "Wybieg_ID", nullable = false)
    public Wybieg getWybieg() {
        return wybieg;
    }

    public void setWybieg(Wybieg wybieg) {
        this.wybieg = wybieg;
    }
}
