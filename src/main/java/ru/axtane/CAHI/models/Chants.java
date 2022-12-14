package ru.axtane.CAHI.models;

import ru.axtane.CAHI.models.enums.PublicationStatus;

import javax.persistence.*;

@Entity
@Table(name = "chants")
public class Chants implements Essay {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "region")
    private String region;
    @Column(name = "incipit")
    private String incipit;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "chorusType")
    private String chorusType;
    @Column(name = "voiceCount")
    private String voiceCount;
    @Column(name = "chorusStructure")
    private String chorusStructure;
    @Column(name = "chorusAmount")
    private String chorusAmount;
    @Column(name = "chorusVoicesList")
    private String chorusVoicesList;
    @Column(name = "timing")
    private String timing;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Person userAuthor;
    @Enumerated(EnumType.STRING)
    private PublicationStatus publicationStatus;

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return this.getIncipit();
    }

    @Override
    public Person getAuthor() {
        return this.getUserAuthor();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIncipit() {
        return incipit;
    }

    public void setIncipit(String incipit) {
        this.incipit = incipit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChorusType() {
        return chorusType;
    }

    public void setChorusType(String chorusType) {
        this.chorusType = chorusType;
    }

    public String getVoiceCount() {
        return voiceCount;
    }

    public void setVoiceCount(String voiceCount) {
        this.voiceCount = voiceCount;
    }

    public String getChorusStructure() {
        return chorusStructure;
    }

    public void setChorusStructure(String chorusStructure) {
        this.chorusStructure = chorusStructure;
    }

    public String getChorusAmount() {
        return chorusAmount;
    }

    public void setChorusAmount(String chorusAmount) {
        this.chorusAmount = chorusAmount;
    }

    public String getChorusVoicesList() {
        return chorusVoicesList;
    }

    public void setChorusVoicesList(String chorusVoicesList) {
        this.chorusVoicesList = chorusVoicesList;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
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
}
