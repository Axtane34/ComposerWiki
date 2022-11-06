package ru.axtane.CAHI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.axtane.CAHI.models.Arrangement;

import java.util.List;

public interface ArrangementsRepository extends JpaRepository<Arrangement, Integer> {
    Arrangement findById(int id);
    List<Arrangement> findByComposerFio(String fio);
}
