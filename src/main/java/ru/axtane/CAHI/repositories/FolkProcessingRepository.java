package ru.axtane.CAHI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.axtane.CAHI.models.FolkProcessing;

import java.util.List;

public interface FolkProcessingRepository extends JpaRepository<FolkProcessing, Integer> {
    FolkProcessing findById(int id);
    List<FolkProcessing> findByFolkProcessingAuthor(String lastname);
}
