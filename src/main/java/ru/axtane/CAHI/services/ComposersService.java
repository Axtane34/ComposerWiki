package ru.axtane.CAHI.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axtane.CAHI.models.*;
import ru.axtane.CAHI.models.enums.PublicationStatus;
import ru.axtane.CAHI.repositories.ComposersRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ComposersService {
    private final ComposersRepository composersRepository;
    private final ArrangementsService arrangementsService;
    private final ChoirsService choirsService;
    private final FolkProcessingService folkProcessingService;
    private final OpusASService opusASService;
    private final OpusDPSService opusDPSService;


    @Autowired
    public ComposersService(ComposersRepository composersRepository, ArrangementsService arrangementsService, ChoirsService choirsService, FolkProcessingService folkProcessingService, OpusASService opusASService, OpusDPSService opusDPSService) {
        this.composersRepository = composersRepository;
        this.arrangementsService = arrangementsService;
        this.choirsService = choirsService;
        this.folkProcessingService = folkProcessingService;
        this.opusASService = opusASService;
        this.opusDPSService = opusDPSService;
    }
    
    public List<Composer> findAll(){
        return composersRepository.findAll();
    }

    public Composer findById(int id){
        return composersRepository.findById(id);
    }

    public Composer findChoirs(PublicationStatus publicationStatus, int id, boolean isCapella){
        Composer composer = composersRepository.findById(id);
        if(isCapella) {
            Hibernate.initialize(composer.getOpusDPS());
            composer.getOpusAS().removeIf(opusAS -> !opusAS.getPublicationStatus().equals(publicationStatus)
                    || !opusAS.getMusic().startsWith("Cappella"));
            composer.getFolkProcessingList().removeIf(folkProcessing -> !folkProcessing.getPublicationStatus().equals(publicationStatus)
                    || !folkProcessing.getMusic().startsWith("Cappella"));
            composer.getArrangements().removeIf(arrangement -> !arrangement.getPublicationStatus().equals(publicationStatus)
                    || !arrangement.getMusic().startsWith("Cappella"));
            composer.getChoirs().removeIf(chorus -> !chorus.getPublicationStatus().equals(publicationStatus)
                    || !chorus.getMusic().startsWith("Cappella"));
        }else {
            composer.getOpusDPS().clear();
            composer.getOpusAS().removeIf(opusAS -> !opusAS.getPublicationStatus().equals(publicationStatus)
                    || opusAS.getMusic().startsWith("Cappella"));
            composer.getFolkProcessingList().removeIf(folkProcessing -> !folkProcessing.getPublicationStatus().equals(publicationStatus)
                    || folkProcessing.getMusic().startsWith("Cappella"));
            composer.getArrangements().removeIf(arrangement -> !arrangement.getPublicationStatus().equals(publicationStatus)
                    || arrangement.getMusic().startsWith("Cappella"));
            composer.getChoirs().removeIf(chorus -> !chorus.getPublicationStatus().equals(publicationStatus)
                    || chorus.getMusic().startsWith("Cappella"));
        }
        composer.setPublicationsEmpty(composer.getOpusDPS().isEmpty() && composer.getOpusAS().isEmpty() &&
                composer.getFolkProcessingList().isEmpty() && composer.getArrangements().isEmpty() && composer.getChoirs().isEmpty());
        return composer;
    }

    @Transactional
    public void save(Composer composer){
        composer.setPublicationStatus(PublicationStatus.PUBLISHED);
        composer.getUserAuthor().addComposer(composer);
        composersRepository.save(composer);
        for (Arrangement arrangement : arrangementsService.findByComposerName(composer.getLastName())){
            composer.addArrangement(arrangement);
        }
        for (Chorus chorus : choirsService.findByComposerName(composer.getLastName())){
            composer.addChorus(chorus);
        }
        for (FolkProcessing folkProcessing : folkProcessingService.findByComposerName(composer.getLastName())){
            composer.addFolkProcessing(folkProcessing);
        }
        for (OpusAS opusAS : opusASService.findByComposerName(composer.getLastName())){
            composer.addOpusAS(opusAS);
        }
        for (OpusDPS opusDPS : opusDPSService.findByComposerName(composer.getLastName())){
            composer.addOpusDPS(opusDPS);
        }
        composersRepository.save(composer);
    }

    @Transactional
    public void delete(int id){
        composersRepository.deleteById(id);
    }
}