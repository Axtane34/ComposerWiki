package ru.axtane.CAHI.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.axtane.CAHI.models.Person;
import ru.axtane.CAHI.services.PeopleService;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (peopleService.findByLogin(person.getUsername())!=null)
        errors.rejectValue("username", "", "Человек с таким именем пользователя уже существует");

        if (peopleService.findByEmail(person.getEmail())!=null)
        errors.rejectValue("email", "", "Человек с такой почтой уже существует");
    }
}
