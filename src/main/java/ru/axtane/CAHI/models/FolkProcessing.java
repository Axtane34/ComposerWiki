package ru.axtane.CAHI.models;

import ru.axtane.CAHI.models.enums.PublicationStatus;

import javax.persistence.*;

@Entity
@Table(name = "folkProcessing")
public class FolkProcessing implements Essay {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "composerFio")
    private String composerFio;
    @Column(name = "folkProcessingName")
    private String folkProcessingName;
    @Column(name = "region")
    private String region;
    @Column(name = "dedication")
    private String dedication;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "opus")
    private String opus;
    @Column(name = "music")
    private String music;
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
    @Column(name = "writeDate")
    private String writeDate;
    @Column(name = "premiereDate")
    private String premiereDate;
    @Column(name = "premierePlace")
    private String  premierePlace;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Person userAuthor;
    @ManyToOne
    @JoinColumn(name = "composerId", referencedColumnName = "id")
    private Composer composer;
    @Enumerated(EnumType.STRING)
    private PublicationStatus publicationStatus;

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return this.getFolkProcessingName();
    }

    @Override
    public Person getAuthor() {
        return this.getUserAuthor();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComposerFio() {
        return composerFio;
    }

    public void setComposerFio(String composerFio) {
        this.composerFio = composerFio;
    }

    public String getFolkProcessingName() {
        return folkProcessingName;
    }

    public void setFolkProcessingName(String folkProcessingName) {
        this.folkProcessingName = folkProcessingName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDedication() {
        return dedication;
    }

    public void setDedication(String dedication) {
        this.dedication = dedication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpus() {
        return opus;
    }

    public void setOpus(String opus) {
        this.opus = opus;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
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

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(String premiereDate) {
        this.premiereDate = premiereDate;
    }

    public String getPremierePlace() {
        return premierePlace;
    }

    public void setPremierePlace(String premierePlace) {
        this.premierePlace = premierePlace;
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

    public Composer getComposer() {
        return composer;
    }

    public void setComposer(Composer composer) {
        this.composer = composer;
    }
}
