package ru.axtane.CAHI.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axtane.CAHI.models.Arrangement;
import ru.axtane.CAHI.models.Composer;
import ru.axtane.CAHI.models.enums.PublicationStatus;
import ru.axtane.CAHI.repositories.ArrangementsRepository;
import ru.axtane.CAHI.repositories.ComposersRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArrangementsService {
    private final ArrangementsRepository arrangementsRepository;
    private final ComposersRepository composersRepository;

    public ArrangementsService(ArrangementsRepository arrangementsRepository, ComposersRepository composersRepository) {
        this.arrangementsRepository = arrangementsRepository;
        this.composersRepository = composersRepository;
    }

    public Arrangement findById(int id){
        return arrangementsRepository.findById(id);
    }

    public List<Arrangement> findByComposerName(String lastname){
        return arrangementsRepository.findByArrangementAuthor(lastname);
    }

    public Composer findComposer(String lastname){
        return composersRepository.findByLastName(lastname);
    }
    
    @Transactional
    public void save(Arrangement arrangement){
            arrangement.setPublicationStatus(PublicationStatus.PUBLISHED);
        arrangement.getUserAuthor().addArrangement(arrangement);
        Composer composer = findComposer(arrangement.getArrangementAuthor());
        if (composer != null){
            composer.addArrangement(arrangement);
        }
        arrangementsRepository.save(arrangement);
    }

    @Transactional
    public void delete(int id){
        arrangementsRepository.deleteById(id);
    }
}