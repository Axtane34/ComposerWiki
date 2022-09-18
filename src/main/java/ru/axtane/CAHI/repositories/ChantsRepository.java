package ru.axtane.CAHI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.axtane.CAHI.models.Chants;

public interface ChantsRepository extends JpaRepository<Chants, Integer> {
    Chants findById(int id);
}
