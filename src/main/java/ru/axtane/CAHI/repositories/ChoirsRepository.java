package ru.axtane.CAHI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.axtane.CAHI.models.Chorus;
import ru.axtane.CAHI.models.Person;

import java.util.List;

public interface ChoirsRepository extends JpaRepository<Chorus, Integer> {
    Chorus findById(int id);
    List<Chorus> findByComposerName(String name);
}
