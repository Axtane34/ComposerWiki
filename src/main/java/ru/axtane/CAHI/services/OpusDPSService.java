package ru.axtane.CAHI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axtane.CAHI.models.Composer;
import ru.axtane.CAHI.models.Essay;
import ru.axtane.CAHI.models.OpusDPS;
import ru.axtane.CAHI.models.enums.PublicationStatus;
import ru.axtane.CAHI.repositories.ComposersRepository;
import ru.axtane.CAHI.repositories.OpusDPSRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OpusDPSService {
    private final OpusDPSRepository opusDPSRepository;
    private final ComposersRepository composersRepository;

    @Autowired
    public OpusDPSService(OpusDPSRepository opusDPSRepository, ComposersRepository composersRepository) {
        this.opusDPSRepository = opusDPSRepository;
        this.composersRepository = composersRepository;
    }

    public List<Essay> findAll(){
        return new ArrayList<>(opusDPSRepository.findAll());
    }

    public OpusDPS findById(int id){
        return opusDPSRepository.findById(id);
    }

    public List<OpusDPS> findByComposerFio(String fio){
        return opusDPSRepository.findByComposerFio(fio);
    }

    public Composer findComposer(String fio){
        return composersRepository.findByFio(fio);
    }

    @Transactional
    public void save(OpusDPS opusDPS){
            opusDPS.setPublicationStatus(PublicationStatus.PUBLISHED);
        opusDPS.getUserAuthor().addOpusDPS(opusDPS);
        if (opusDPS.getComposer() != null){
            findComposer(opusDPS.getComposer().getFio()).addOpusDPS(opusDPS);
        }
        opusDPSRepository.save(opusDPS);
    }

    @Transactional
    public void delete(int id){
        opusDPSRepository.deleteById(id);
    }
}