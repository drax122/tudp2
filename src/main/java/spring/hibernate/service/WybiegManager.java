package spring.hibernate.service;
import spring.hibernate.domain.*;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * Created by draxeer on 2016-12-22.
 */
@Component
@Transactional
public class WybiegManager implements WybiegInterface {

    @Autowired
    private SessionFactory session;

    public SessionFactory getSessionFactory(){
        return session;
    }
    public void setSessionFactory(SessionFactory session){
        this.session = session;
    }

    @SuppressWarnings("unchecked")
    public List<Wybieg> getAllWybiegi(){
        return session.getCurrentSession().getNamedQuery("wybieg.all").list();
    }
    public Wybieg getWybiegPoId(Wybieg wybieg){
        return (Wybieg) session.getCurrentSession().get(Wybieg.class, wybieg.getWybieg_id());
    }
    @SuppressWarnings("unchecked")
    public List<Wybieg> getWybiegPoTyp(String typ){
        return session.getCurrentSession().getNamedQuery("wybieg.typ_wybiegu").setString("typ",typ).list();
    }

    public void addWybieg(Wybieg wybieg){
        wybieg.setWybieg_id(null);
        session.getCurrentSession().save(wybieg);
    }
    public void editWybieg(Wybieg wybieg){
        session.getCurrentSession().update(wybieg);
    }
    public void deleteWybieg(Wybieg wybieg){
        session.getCurrentSession().delete(wybieg);
    }

}
