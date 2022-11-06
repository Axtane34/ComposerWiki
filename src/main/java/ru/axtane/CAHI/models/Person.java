package ru.axtane.CAHI.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.axtane.CAHI.models.enums.AccessLevel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "people")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max=100, message = "Имя должно быть от 2 до 100 символов длиной")
    @Column(name = "username")
    private String username;
    @NotEmpty(message = "Пароль не должен быть пустым")
    @Column(name = "password")
    private String password;
    @Email(message = "Корректно укажите адрес электронной почты")
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    private AccessLevel accessLevel;
    @OneToMany(mappedBy = "userAuthor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Chorus> choirs = new ArrayList<>();
    @OneToMany(mappedBy = "userAuthor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Arrangement> arrangements = new ArrayList<>();
    @OneToMany(mappedBy = "userAuthor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Chants> chants = new ArrayList<>();
    @OneToMany(mappedBy = "userAuthor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<FolkProcessing> folkProcessingList = new ArrayList<>();
    @OneToMany(mappedBy = "userAuthor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<OpusDPS> opusDPS = new ArrayList<>();
    @OneToMany(mappedBy = "userAuthor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Composer> composers = new ArrayList<>();
    @OneToMany(mappedBy = "userAuthor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Draft> drafts = new ArrayList<>();
    @Transient
    private boolean publicationsEmpty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPublicationsEmpty() {
        return publicationsEmpty;
    }

    public void setPublicationsEmpty(boolean publicationsEmpty) {
        this.publicationsEmpty = publicationsEmpty;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public List<Chorus> getChoirs() {
        return choirs;
    }

    public void setChoirs(List<Chorus> choirs) {
        this.choirs = choirs;
    }

    public void addChorus(Chorus chorus){
        if (this.getChoirs()==null)
            this.setChoirs(new ArrayList<>());
        this.getChoirs().add(chorus);
        chorus.setUserAuthor(this);
    }

    public List<Arrangement> getArrangements() {
        return arrangements;
    }

    public void setArrangements(List<Arrangement> arrangements) {
        this.arrangements = arrangements;
    }

    public void addArrangement(Arrangement arrangement){
        if (this.getArrangements()==null)
            this.setArrangements(new ArrayList<>());
        this.getArrangements().add(arrangement);
        arrangement.setUserAuthor(this);
    }

    public List<Chants> getChants() {
        return chants;
    }

    public void setChants(List<Chants> chants) {
        this.chants = chants;
    }

    public void addChants(Chants chants){
        if (this.getChants()==null)
            this.setChants(new ArrayList<>());
        this.getChants().add(chants);
        chants.setUserAuthor(this);
    }

    public List<FolkProcessing> getFolkProcessingList() {
        return folkProcessingList;
    }

    public void setFolkProcessingList(List<FolkProcessing> folkProcessingList) {
        this.folkProcessingList = folkProcessingList;
    }

    public void addFolkProcessing(FolkProcessing folkProcessing){
        if (this.getFolkProcessingList()==null)
            this.setFolkProcessingList(new ArrayList<>());
        this.getFolkProcessingList().add(folkProcessing);
        folkProcessing.setUserAuthor(this);
    }

    public List<OpusDPS> getOpusDPS() {
        return opusDPS;
    }

    public void setOpusDPS(List<OpusDPS> opusDPS) {
        this.opusDPS = opusDPS;
    }

    public void addOpusDPS(OpusDPS opusDPS){
        if (this.getOpusDPS()==null)
            this.setOpusDPS(new ArrayList<>());
        this.getOpusDPS().add(opusDPS);
        opusDPS.setUserAuthor(this);
    }

    public List<Composer> getComposers() {
        return composers;
    }

    public void setComposers(List<Composer> composers) {
        this.composers = composers;
    }

    public void addComposer(Composer composer){
        if (this.getComposers()==null)
            this.setComposers(new ArrayList<>());
        this.getComposers().add(composer);
        composer.setUserAuthor(this);
    }

    public List<Draft> getDrafts() {
        return drafts;
    }

    public void setDrafts(List<Draft> drafts) {
        this.drafts = drafts;
    }
    public void addDraft(Draft draft){
        if (this.getDrafts()==null)
            this.setDrafts(new ArrayList<>());
        this.getDrafts().add(draft);
        draft.setUserAuthor(this);
    }

}
