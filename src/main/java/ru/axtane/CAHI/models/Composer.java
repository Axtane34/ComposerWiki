package ru.axtane.CAHI.models;

import ru.axtane.CAHI.models.enums.PublicationStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "composer")
public class Composer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "middleName")
    private String middleName;
    @Column(name = "epoch")
    private String epoch;
    @Column(name = "dateOfBirth")
    private String dateOfBirth;
    @Column(name = "placeOfBirth")
    private String placeOfBirth;
    @Column(name = "activity")
    private String activity;
    @Column(name = "dateOfDeath")
    private String dateOfDeath;
    @Column(name = "reasonOfDeath")
    private String reasonOfDeath;
    @Column(name = "placeOfDeath")
    private String placeOfDeath;
    @Column(name = "buried")
    private String buried;
    @Column(name = "composQuotes")
    private String composQuotes;
    @Column(name = "positiveQuotes")
    private String positiveQuotes;
    @Column(name = "negativeQuotes")
    private String negativeQuotes;
    @Column(name = "facts")
    private String facts;
    @Column(name = "article")
    private String article;
    @Column(name = "letterTo")
    private String letterTo;
    @Column(name = "letterFrom")
    private String letterFrom;
    @Column(name = "mistake")
    private String mistake;
    @Column(name = "bioMini")
    private String bioMini;
    @Column(name = "bio")
    private String bio;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Person userAuthor;
    @Enumerated(EnumType.STRING)
    private PublicationStatus publicationStatus;
    @OneToMany(mappedBy = "composer")
    private List<Chorus> choirs = new ArrayList<>();
    @OneToMany(mappedBy = "composer")
    private List<Arrangement> arrangements = new ArrayList<>();
    @OneToMany(mappedBy = "composer")
    private List<FolkProcessing> folkProcessingList = new ArrayList<>();
    @OneToMany(mappedBy = "composer")
    private List<OpusAS> opusAS = new ArrayList<>();
    @OneToMany(mappedBy = "composer")
    private List<OpusDPS> opusDPS = new ArrayList<>();
    @Transient
    private boolean publicationsEmpty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<OpusAS> getOpusAS() {
        return opusAS;
    }

    public void setOpusAS(List<OpusAS> opusAS) {
        this.opusAS = opusAS;
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
    public void addOpusDPS(OpusDPS opusDPS){
        if (this.getOpusDPS()==null)
            this.setOpusDPS(new ArrayList<>());
        this.getOpusDPS().add(opusDPS);
        opusDPS.setComposer(this);
    }
    public void addOpusAS(OpusAS opusAS){
        if (this.getOpusAS()==null)
            this.setOpusAS(new ArrayList<>());
        this.getOpusAS().add(opusAS);
        opusAS.setComposer(this);
    }
    public void addFolkProcessing(FolkProcessing folkProcessing){
        if (this.getFolkProcessingList()==null)
            this.setFolkProcessingList(new ArrayList<>());
        this.getFolkProcessingList().add(folkProcessing);
        folkProcessing.setComposer(this);
    }
    public void addArrangement(Arrangement arrangement){
        if (this.getArrangements()==null)
            this.setArrangements(new ArrayList<>());
        this.getArrangements().add(arrangement);
        arrangement.setComposer(this);
    }
    public void addChorus(Chorus chorus){
        if (this.getChoirs()==null)
            this.setChoirs(new ArrayList<>());
        this.getChoirs().add(chorus);
        chorus.setComposer(this);
    }

}