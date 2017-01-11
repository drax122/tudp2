package spring.hibernate.service;
import spring.hibernate.domain.*;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
/**
 * Created by draxeer on 2016-12-22.
 */
public class ZwierzManager implements ZwierzInterface {
    @Autowired
    private SessionFactory session;
    public SessionFactory getSessionFactory(){
        return session;
    }
    public void setSessionFactory(SessionFactory session){
        this.session = session;
    }
    @SuppressWarnings("unchecked")

    public List<Zwierz> getAllZwierze() {
        return session.getCurrentSession().getNamedQuery("zwierz.all").list();
    }

    public Zwierz getZwierzePoId(Zwierz zwierz) {
        return (Zwierz) session.getCurrentSession().get(Zwierz.class, zwierz.getZwierz_ID());
    }

    public void addZwierz(Zwierz zwierz) {
        zwierz.setZwierz_ID(null);
        session.getCurrentSession().persist(zwierz);
    }

    public void editZwierz(Zwierz zwierz) {
        session.getCurrentSession().update(zwierz);
    }

    public void deleteZwierz(Zwierz zwierz) {
        session.getCurrentSession().delete(zwierz);
    }
    @SuppressWarnings("unchecked")
    public List<Zwierz> getZwierzZWybiegu(Wybieg wybieg){
        return session.getCurrentSession().getNamedQuery("zwierz.getZwierzFromWybieg").setLong("idWybieg", wybieg.getWybieg_id()).list();
    }


}
