package ru.axtane.CAHI.models;

import ru.axtane.CAHI.models.enums.PublicationStatus;

import javax.persistence.*;

@Entity
@Table(name = "opusDPS")
public class OpusDPS implements Essay {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "writingStyle")
    private String writingStyle;
    @Column(name = "composerFio")
    private String composerFio;
    @Column(name = "incipit")
    private String incipit;
    @Column(name = "compilerDecoder")
    private String compilerDecoder;
    @Column(name = "cypher")
    private String cypher;
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
    @Column(name = "writeDate")
    private String writeDate;
    @Column(name = "premiereDate")
    private String premiereDate;
    @Column(name = "premierePlace")
    private String premierePlace;
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
        return this.getIncipit();
    }

    @Override
    public Person getAuthor() {
        return getUserAuthor();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWritingStyle() {
        return writingStyle;
    }

    public void setWritingStyle(String writingStyle) {
        this.writingStyle = writingStyle;
    }

    public String getComposerFio() {
        return composerFio;
    }

    public void setComposerFio(String composerFio) {
        this.composerFio = composerFio;
    }

    public String getIncipit() {
        return incipit;
    }

    public void setIncipit(String incipit) {
        this.incipit = incipit;
    }

    public String getCompilerDecoder() {
        return compilerDecoder;
    }

    public void setCompilerDecoder(String compilerDecoder) {
        this.compilerDecoder = compilerDecoder;
    }

    public String getCypher() {
        return cypher;
    }

    public void setCypher(String cypher) {
        this.cypher = cypher;
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
