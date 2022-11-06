package ru.axtane.CAHI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.axtane.CAHI.models.OpusDPS;

import java.util.List;

public interface OpusDPSRepository extends JpaRepository<OpusDPS, Integer> {
    OpusDPS findById(int id);
    List<OpusDPS> findByComposerFio(String fio);
}
