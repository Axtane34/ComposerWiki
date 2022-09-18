package ru.axtane.CAHI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axtane.CAHI.models.Composer;
import ru.axtane.CAHI.models.OpusAS;
import ru.axtane.CAHI.models.enums.PublicationStatus;
import ru.axtane.CAHI.repositories.ComposersRepository;
import ru.axtane.CAHI.repositories.OpusASRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OpusASService {
    private final OpusASRepository opusASRepository;
    private final ComposersRepository composersRepository;

    @Autowired
    public OpusASService(OpusASRepository opusASRepository, ComposersRepository composersRepository) {
        this.opusASRepository = opusASRepository;
        this.composersRepository = composersRepository;
    }

    public OpusAS findById(int id){
        return opusASRepository.findById(id);
    }

    public List<OpusAS> findByComposerName(String lastname){
        return opusASRepository.findByComposerName(lastname);
    }

    public Composer findComposer(String lastname){
        return composersRepository.findByLastName(lastname);
    }

    @Transactional
    public void save(OpusAS opusAS){
            opusAS.setPublicationStatus(PublicationStatus.PUBLISHED);
        opusAS.getUserAuthor().addOpusAS(opusAS);
        Composer composer = findComposer(opusAS.getComposerName());
        if (composer != null){
            composer.addOpusAS(opusAS);
        }
        opusASRepository.save(opusAS);
    }

    @Transactional
    public void delete(int id){
        opusASRepository.deleteById(id);
    }
}