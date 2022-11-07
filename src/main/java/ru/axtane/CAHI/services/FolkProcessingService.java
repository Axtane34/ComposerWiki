package ru.axtane.CAHI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axtane.CAHI.models.Composer;
import ru.axtane.CAHI.models.FolkProcessing;
import ru.axtane.CAHI.models.enums.PublicationStatus;
import ru.axtane.CAHI.repositories.ComposersRepository;
import ru.axtane.CAHI.repositories.FolkProcessingRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FolkProcessingService {
    private final FolkProcessingRepository folkProcessingRepository;
    private final ComposersRepository composersRepository;

    @Autowired
    public FolkProcessingService(FolkProcessingRepository folkProcessingRepository, ComposersRepository composersRepository) {
        this.folkProcessingRepository = folkProcessingRepository;
        this.composersRepository = composersRepository;
    }

    public FolkProcessing findById(int id){
        return folkProcessingRepository.findById(id);
    }

    public List<FolkProcessing> findByComposerFio(String fio){
        return folkProcessingRepository.findByComposerFio(fio);
    }

    public Composer findComposer(String fio){
        return composersRepository.findByFio(fio);
    }

    @Transactional
    public void save(FolkProcessing folkProcessing){
            folkProcessing.setPublicationStatus(PublicationStatus.PUBLISHED);
        folkProcessing.getUserAuthor().addFolkProcessing(folkProcessing);
        if (folkProcessing.getComposer() != null){
            findComposer(folkProcessing.getComposer().getFio()).addFolkProcessing(folkProcessing);
        }
        folkProcessingRepository.save(folkProcessing);
    }

    @Transactional
    public void delete(int id){
        folkProcessingRepository.deleteById(id);
    }
}