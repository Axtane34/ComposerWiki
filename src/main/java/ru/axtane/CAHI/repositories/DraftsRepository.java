package ru.axtane.CAHI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.axtane.CAHI.models.Draft;

public interface DraftsRepository extends JpaRepository<Draft, Integer> {
    Draft findById(int id);
}
