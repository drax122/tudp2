package spring.hibernate.service;

import java.util.List;
import spring.hibernate.domain.*;

public interface ZwierzInterface {
    List<Zwierz> getAllZwierze();

    Zwierz getZwierzePoId(Zwierz zwierz);

    void deleteZwierz(Zwierz zwierz);
    void addZwierz(Zwierz zwierz);
    void editZwierz(Zwierz zwierz);


    List<Zwierz> getZwierzZWybiegu(Wybieg wybieg);
}