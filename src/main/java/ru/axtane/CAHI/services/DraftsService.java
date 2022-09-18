package ru.axtane.CAHI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axtane.CAHI.models.Draft;
import ru.axtane.CAHI.models.enums.DraftType;
import ru.axtane.CAHI.repositories.DraftsRepository;

@Service
@Transactional(readOnly = true)
public class DraftsService {
    private final DraftsRepository draftsRepository;
    private final PeopleService peopleService;

    @Autowired
    public DraftsService(DraftsRepository draftsRepository, PeopleService peopleService) {
        this.draftsRepository = draftsRepository;
        this.peopleService = peopleService;
    }

    public Draft findById (int id){
        return draftsRepository.findById(id);
    }

    public int getUserId(int id){
        Draft draft = draftsRepository.findById(id);
        return draft.getUserAuthor().getId();
    }

    public DraftType setType(String draftType){
        String [] mass = draftType.split("/");
        draftType = mass[3];
        DraftType type;
        switch (draftType){
            case "arrangement" :
                type = DraftType.ARRANGEMENT;
                break;
            case "chants" :
                type = DraftType.CHANTS;
                break;
            case "chorus" :
                type = DraftType.CHORUS;
                break;
            case "composer" :
                type = DraftType.COMPOSER;
                break;
            case "folkProcessing" :
                type = DraftType.FOLK_PROCESSING;
                break;
            case "opusAS" :
                type = DraftType.OPUS_AS;
                break;
            case "opusDPS" :
                type = DraftType.OPUS_DPS;
                break;
            default : type = DraftType.NOTHING;
        }
        return type;
    }

    @Transactional
    public void save(Draft draft){
        draft.setDraftName(draft.getDraftType().toString().toLowerCase() + ": " + draft.getDraftName());
        draft.getUserAuthor().addDraft(draft);
        draftsRepository.save(draft);
    }

    @Transactional
    public void update(int id, Draft updatedDraft){
        Draft draft = findById(id);
        draft.setPageStatement(updatedDraft.getPageStatement());
        draftsRepository.save(draft);
    }

    @Transactional
    public void delete(int id){
        draftsRepository.deleteById(id);
    }
}
