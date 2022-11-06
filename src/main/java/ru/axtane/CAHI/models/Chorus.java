package ru.axtane.CAHI.models;

import ru.axtane.CAHI.models.enums.PublicationStatus;

import javax.persistence.*;

@Entity
@Table(name = "chorus")
public class Chorus {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "composerName")
    private String composerName;
    @Column(name = "chorusName")
    private String chorusName;
    @Column(name = "textAuthor")
    private String textAuthor;
    @Column(name = "dedication")
    private String dedication;
    @Column(name = "description")
    private String description;
    @Column(name = "opus")
    private String opus;
    @Column(name = "genre")
    private String genre;
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
    @Enumerated(EnumType.STRING)
    private PublicationStatus publicationStatus;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Person userAuthor;
    @ManyToOne
    @JoinColumn(name = "composerId", referencedColumnName = "id")
    private Composer composer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComposerName() {
        return composerName;
    }

    public void setComposerName(String composerName) {
        this.composerName = composerName;
    }

    public String getChorusName() {
        return chorusName;
    }

    public void setChorusName(String chorusName) {
        this.chorusName = chorusName;
    }

    public String getTextAuthor() {
        return textAuthor;
    }

    public void setTextAuthor(String textAuthor) {
        this.textAuthor = textAuthor;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public PublicationStatus getPublicationStatus() {
        return publicationStatus;
    }

    public void setPublicationStatus(PublicationStatus publicationStatus) {
        this.publicationStatus = publicationStatus;
    }

    public Person getUserAuthor() {
        return userAuthor;
    }

    public void setUserAuthor(Person userAuthor) {
        this.userAuthor = userAuthor;
    }

    public Composer getComposer() {
        return composer;
    }

    public void setComposer(Composer composer) {
        this.composer = composer;
    }
}
