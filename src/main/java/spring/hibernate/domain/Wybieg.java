package spring.hibernate.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Created by draxeer on 2016-12-22.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "wybieg.all", query = "Select w from Wybieg w"),
        @NamedQuery(name = "wybieg.typ_wybiegu", query = "Select w from Wybieg w where w.typ_wybiegu= :typ")
})

public class Wybieg {

    private Long wybieg_id;
    private String nazwa_wybiegu;
    private String powierzchnia;
    private String typ_wybiegu;

    private List<Zwierz> zwierzeta = new ArrayList<Zwierz>();

    public Wybieg(){}
    public Wybieg(String nazwa_wybiegu, String powierzchnia, String typ_wybiegu) {
        this.nazwa_wybiegu = nazwa_wybiegu;
        this.powierzchnia = powierzchnia;
        this.typ_wybiegu = typ_wybiegu;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getWybieg_id() {
        return wybieg_id;
    }

    public void setWybieg_id(Long wybieg_ID) {
        this.wybieg_id = wybieg_ID;
    }

    public String getNazwa_wybiegu() {
        return nazwa_wybiegu;
    }

    public void setNazwa_wybiegu(String nazwa_wybiegu) {
        this.nazwa_wybiegu = nazwa_wybiegu;
    }

    public String getPowierzchnia() {
        return powierzchnia;
    }

    public void setPowierzchnia(String powierzchnia) {
        this.powierzchnia = powierzchnia;
    }

    public String getTyp_wybiegu() {
        return typ_wybiegu;
    }

    public void setTyp_wybiegu(String typ_wybiegu) {
        this.typ_wybiegu = typ_wybiegu;
    }

    @OneToMany(mappedBy = "wybieg", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Zwierz> getZwierz() {
        return zwierzeta;
    }

    public void setZwierz(List<Zwierz> Zwierzeta) {
        this.zwierzeta = Zwierzeta;
    }

}
