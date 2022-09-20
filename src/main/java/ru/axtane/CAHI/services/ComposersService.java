package ru.axtane.CAHI.services;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axtane.CAHI.dto.ComposerDTO;
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
    private final ModelMapper modelMapper;


    @Autowired
    public ComposersService(ComposersRepository composersRepository, ArrangementsService arrangementsService, ChoirsService choirsService, FolkProcessingService folkProcessingService, OpusASService opusASService, OpusDPSService opusDPSService, ModelMapper modelMapper) {
        this.composersRepository = composersRepository;
        this.arrangementsService = arrangementsService;
        this.choirsService = choirsService;
        this.folkProcessingService = folkProcessingService;
        this.opusASService = opusASService;
        this.opusDPSService = opusDPSService;
        this.modelMapper = modelMapper;
    }
    
    public List<Composer> findAll(){
        return composersRepository.findAll();
    }

    public Composer findById(int id){
        return composersRepository.findById(id).orElse(null);
    }

    public Composer findByLastName(String lastname){
        return composersRepository.findByLastName(lastname);
    }

    public ComposerDTO findChoirs(PublicationStatus publicationStatus, Composer composer, boolean isCapella){
        Hibernate.initialize(composer);
        ComposerDTO composerDTO = convertToComposerDTO(composer);
            if (isCapella) {
                composerDTO.getOpusAS().removeIf(opusAS -> !opusAS.getPublicationStatus().equals(publicationStatus)
                        || !opusAS.getMusic().startsWith("Cappella"));
                composerDTO.getFolkProcessingList().removeIf(folkProcessing -> !folkProcessing.getPublicationStatus().equals(publicationStatus)
                        || !folkProcessing.getMusic().startsWith("Cappella"));
                composerDTO.getArrangements().removeIf(arrangement -> !arrangement.getPublicationStatus().equals(publicationStatus)
                        || !arrangement.getMusic().startsWith("Cappella"));
                composerDTO.getChoirs().removeIf(chorus -> !chorus.getPublicationStatus().equals(publicationStatus)
                        || !chorus.getMusic().startsWith("Cappella"));
            } else {
                composerDTO.getOpusDPS().clear();
                composerDTO.getOpusAS().removeIf(opusAS -> !opusAS.getPublicationStatus().equals(publicationStatus)
                        || opusAS.getMusic().startsWith("Cappella"));
                composerDTO.getFolkProcessingList().removeIf(folkProcessing -> !folkProcessing.getPublicationStatus().equals(publicationStatus)
                        || folkProcessing.getMusic().startsWith("Cappella"));
                composerDTO.getArrangements().removeIf(arrangement -> !arrangement.getPublicationStatus().equals(publicationStatus)
                        || arrangement.getMusic().startsWith("Cappella"));
                composerDTO.getChoirs().removeIf(chorus -> !chorus.getPublicationStatus().equals(publicationStatus)
                        || chorus.getMusic().startsWith("Cappella"));
            }
        composerDTO.setPublicationsEmpty(composerDTO.getOpusDPS().isEmpty() && composerDTO.getOpusAS().isEmpty() &&
                composerDTO.getFolkProcessingList().isEmpty() && composerDTO.getArrangements().isEmpty() && composerDTO.getChoirs().isEmpty());
        return composerDTO;
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

    private Composer convertToComposer(ComposerDTO composerDTO) {
        return modelMapper.map(composerDTO, Composer.class);
    }

    private ComposerDTO convertToComposerDTO(Composer composer){
        return modelMapper.map(composer, ComposerDTO.class);
    }
}