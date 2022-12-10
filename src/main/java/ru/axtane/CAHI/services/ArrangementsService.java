package ru.axtane.CAHI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axtane.CAHI.models.Arrangement;
import ru.axtane.CAHI.models.Composer;
import ru.axtane.CAHI.models.Essay;
import ru.axtane.CAHI.models.enums.PublicationStatus;
import ru.axtane.CAHI.repositories.ArrangementsRepository;
import ru.axtane.CAHI.repositories.ComposersRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArrangementsService {
    private final ArrangementsRepository arrangementsRepository;
    private final ComposersRepository composersRepository;

    @Autowired
    public ArrangementsService(ArrangementsRepository arrangementsRepository, ComposersRepository composersRepository) {
        this.arrangementsRepository = arrangementsRepository;
        this.composersRepository = composersRepository;
    }
    public List<Essay> findAll(){
        return new ArrayList<>(arrangementsRepository.findAll());
    }

    public Arrangement findById(int id){
        return arrangementsRepository.findById(id);
    }

    public List<Arrangement> findByComposerFio(String fio){
        return arrangementsRepository.findByComposerFio(fio);
    }

    public Composer findComposer(String fio){
        return composersRepository.findByFio(fio);
    }
    
    @Transactional
    public void save(Arrangement arrangement){
        arrangement.setPublicationStatus(PublicationStatus.PUBLISHED);
        arrangement.getUserAuthor().addArrangement(arrangement);
        if (arrangement.getComposer() != null){
            findComposer(arrangement.getComposer().getFio()).addArrangement(arrangement);
        }
        arrangementsRepository.save(arrangement);
    }

    @Transactional
    public void delete(int id){
        arrangementsRepository.deleteById(id);
    }
}