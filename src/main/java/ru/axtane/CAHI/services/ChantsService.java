package ru.axtane.CAHI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axtane.CAHI.models.Chants;
import ru.axtane.CAHI.models.enums.PublicationStatus;
import ru.axtane.CAHI.repositories.ChantsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ChantsService {
    private final ChantsRepository chantsRepository;

    @Autowired
    public ChantsService(ChantsRepository chantsRepository) {
        this.chantsRepository = chantsRepository;
    }

    public Chants findById(int id){
        return chantsRepository.findById(id);
    }

    @Transactional
    public void save(Chants chants){
            chants.setPublicationStatus(PublicationStatus.PUBLISHED);
        chants.getUserAuthor().addChants(chants);
        chantsRepository.save(chants);
    }

    @Transactional
    public void delete(int id){
        chantsRepository.deleteById(id);
    }
}

