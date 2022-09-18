package ru.axtane.CAHI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.axtane.CAHI.models.OpusAS;

import java.util.List;

public interface OpusASRepository extends JpaRepository<OpusAS, Integer> {
    OpusAS findById(int id);
    List<OpusAS> findByComposerName(String lastname);
}
