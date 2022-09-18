package ru.axtane.CAHI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.axtane.CAHI.models.Composer;

public interface ComposersRepository extends JpaRepository<Composer, Integer> {
    Composer findById(int id);
    Composer findByLastName(String lastname);
}
