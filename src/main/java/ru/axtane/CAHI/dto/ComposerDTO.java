package ru.axtane.CAHI.dto;

import ru.axtane.CAHI.models.*;
import ru.axtane.CAHI.models.enums.PublicationStatus;

import java.util.ArrayList;
import java.util.List;

public class ComposerDTO {
    private String firstName;
    private String lastName;
    private String middleName;
    private String epoch;
    private String dateOfBirth;
    private String placeOfBirth;
    private String activity;
    private String dateOfDeath;
    private String reasonOfDeath;
    private String placeOfDeath;
    private String buried;
    private String composQuotes;
    private String positiveQuotes;
    private String negativeQuotes;
    private String facts;
    private String article;
    private String letterTo;
    private String letterFrom;
    private String mistake;
    private String bioMini;
    private String bio;
    private Person userAuthor;
    private PublicationStatus publicationStatus;
    private List<Chorus> choirs = new ArrayList<>();
    private List<Arrangement> arrangements = new ArrayList<>();
    private List<FolkProcessing> folkProcessingList = new ArrayList<>();
    private List<OpusDPS> opusDPS = new ArrayList<>();
    private boolean publicationsEmpty;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEpoch() {
        return epoch;
    }

    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(String dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getReasonOfDeath() {
        return reasonOfDeath;
    }

    public void setReasonOfDeath(String reasonOfDeath) {
        this.reasonOfDeath = reasonOfDeath;
    }

    public String getPlaceOfDeath() {
        return placeOfDeath;
    }

    public void setPlaceOfDeath(String placeOfDeath) {
        this.placeOfDeath = placeOfDeath;
    }

    public String getBuried() {
        return buried;
    }

    public void setBuried(String buried) {
        this.buried = buried;
    }

    public String getComposQuotes() {
        return composQuotes;
    }

    public void setComposQuotes(String composQuotes) {
        this.composQuotes = composQuotes;
    }

    public String getPositiveQuotes() {
        return positiveQuotes;
    }

    public void setPositiveQuotes(String positiveQuotes) {
        this.positiveQuotes = positiveQuotes;
    }

    public String getNegativeQuotes() {
        return negativeQuotes;
    }

    public void setNegativeQuotes(String negativeQuotes) {
        this.negativeQuotes = negativeQuotes;
    }

    public String getFacts() {
        return facts;
    }

    public void setFacts(String facts) {
        this.facts = facts;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getLetterTo() {
        return letterTo;
    }

    public void setLetterTo(String letterTo) {
        this.letterTo = letterTo;
    }

    public String getLetterFrom() {
        return letterFrom;
    }

    public void setLetterFrom(String letterFrom) {
        this.letterFrom = letterFrom;
    }

    public String getMistake() {
        return mistake;
    }

    public void setMistake(String mistake) {
        this.mistake = mistake;
    }

    public String getBioMini() {
        return bioMini;
    }

    public void setBioMini(String bioMini) {
        this.bioMini = bioMini;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Person getUserAuthor() {
        return userAuthor;
    }

    public void setUserAuthor(Person userAuthor) {
        this.userAuthor = userAuthor;
    }

    public PublicationStatus getPublicationStatus() {
        return publicationStatus;
    }

    public void setPublicationStatus(PublicationStatus publicationStatus) {
        this.publicationStatus = publicationStatus;
    }

    public List<Chorus> getChoirs() {
        return choirs;
    }

    public void setChoirs(List<Chorus> choirs) {
        this.choirs = choirs;
    }

    public List<Arrangement> getArrangements() {
        return arrangements;
    }

    public void setArrangements(List<Arrangement> arrangements) {
        this.arrangements = arrangements;
    }

    public List<FolkProcessing> getFolkProcessingList() {
        return folkProcessingList;
    }

    public void setFolkProcessingList(List<FolkProcessing> folkProcessingList) {
        this.folkProcessingList = folkProcessingList;
    }

    public List<OpusDPS> getOpusDPS() {
        return opusDPS;
    }

    public void setOpusDPS(List<OpusDPS> opusDPS) {
        this.opusDPS = opusDPS;
    }

    public boolean isPublicationsEmpty() {
        return publicationsEmpty;
    }

    public void setPublicationsEmpty(boolean publicationsEmpty) {
        this.publicationsEmpty = publicationsEmpty;
    }
}
