package spring.hibernate.service;

import java.util.List;
import spring.hibernate.domain.*;

public interface WybiegInterface {
    List<Wybieg> getAllWybiegi();

    Wybieg getWybiegPoId(Wybieg wybieg);

    void deleteWybieg(Wybieg wybieg);
    void addWybieg(Wybieg wybieg);
    void editWybieg(Wybieg wybieg);

    List<Wybieg> getWybiegPoTyp(String typ);

}