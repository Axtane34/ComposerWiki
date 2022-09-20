package ru.axtane.CAHI.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axtane.CAHI.models.Person;
import ru.axtane.CAHI.models.enums.PublicationStatus;
import ru.axtane.CAHI.repositories.PeopleRepository;


@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Person findById(int id){
        peopleRepository.findById(id).ifPresent(person -> Hibernate.initialize(person.getChoirs()));
        return peopleRepository.findById(id).orElse(null);
    }

    public Person findByLogin(String username){
        peopleRepository.findByUsername(username).ifPresent(person -> Hibernate.initialize(person.getChoirs()));
        return peopleRepository.findByUsername(username).orElse(null);
    }

    public Person findByEmail(String email){
        return peopleRepository.findByEmail(email);
    }

    public Person findAllWithEnum(PublicationStatus publicationStatus, String login){
        Person person = findByLogin(login);
        person.getComposers().removeIf(composer -> !composer.getPublicationStatus().equals(publicationStatus));
        person.getOpusDPS().removeIf(opusDPS -> !opusDPS.getPublicationStatus().equals(publicationStatus));
        person.getOpusAS().removeIf(opusAS -> !opusAS.getPublicationStatus().equals(publicationStatus));
        person.getChants().removeIf(chants -> !chants.getPublicationStatus().equals(publicationStatus));
        person.getFolkProcessingList().removeIf(folkProcessing -> !folkProcessing.getPublicationStatus().equals(publicationStatus));
        person.getArrangements().removeIf(arrangement -> !arrangement.getPublicationStatus().equals(publicationStatus));
        person.getChoirs().removeIf(chorus -> !chorus.getPublicationStatus().equals(publicationStatus));
        person.setPublicationsEmpty(person.getComposers().isEmpty() && person.getOpusDPS().isEmpty() && person.getOpusAS().isEmpty() && person.getChants().isEmpty() &&
                person.getFolkProcessingList().isEmpty() && person.getArrangements().isEmpty() && person.getChoirs().isEmpty());
        return person;
    }
}
