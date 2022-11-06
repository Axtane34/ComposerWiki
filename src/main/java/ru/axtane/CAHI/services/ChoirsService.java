package ru.axtane.CAHI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axtane.CAHI.models.Chorus;
import ru.axtane.CAHI.models.Composer;
import ru.axtane.CAHI.models.enums.PublicationStatus;
import ru.axtane.CAHI.repositories.ChoirsRepository;
import ru.axtane.CAHI.repositories.ComposersRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ChoirsService {
    private final ChoirsRepository choirsRepository;
    private final ComposersRepository composersRepository;

    @Autowired
    public ChoirsService(ChoirsRepository choirsRepository, ComposersRepository composersRepository) {
        this.choirsRepository = choirsRepository;
        this.composersRepository = composersRepository;
    }

    public Chorus findById(int id){
        return choirsRepository.findById(id);
    }

    public List<Chorus> findByComposerFio(String fio){
        return choirsRepository.findByComposerFio(fio);
    }

    public Composer findComposer(String fio){
        return composersRepository.findByFio(fio);
    }

    @Transactional
    public void save(Chorus chorus){
        chorus.setPublicationStatus(PublicationStatus.PUBLISHED);
        chorus.getUserAuthor().addChorus(chorus);
        Composer composer = findComposer(chorus.getComposerFio());
        if (composer != null){
            composer.addChorus(chorus);
        }
        choirsRepository.save(chorus);
    }

    @Transactional
    public void delete(int id){
        choirsRepository.deleteById(id);
    }
}