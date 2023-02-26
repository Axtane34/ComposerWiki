package ru.axtane.CAHI.dto;

import ru.axtane.CAHI.models.*;
import ru.axtane.CAHI.models.enums.AccessLevel;

import java.util.ArrayList;
import java.util.List;

public class PersonDTO {
    private int id;
    private String username;
    private String password;
    private String email;
    private AccessLevel accessLevel;
    private List<Chorus> choirs = new ArrayList<>();
    private List<Arrangement> arrangements = new ArrayList<>();
    private List<Chants> chants = new ArrayList<>();
    private List<FolkProcessing> folkProcessingList = new ArrayList<>();
    private List<OpusDPS> opusDPS = new ArrayList<>();
    private List<Composer> composers = new ArrayList<>();
    private List<Draft> drafts = new ArrayList<>();
    private boolean publicationsEmpty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Arrangement> getArrangements() {
        return arrangements;
    }

    public void setArrangements(List<Arrangement> arrangements) {
        this.arrangements = arrangements;
    }

    public List<Chants> getChants() {
        return chants;
    }

    public void setChants(List<Chants> chants) {
        this.chants = chants;
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

    public List<Composer> getComposers() {
        return composers;
    }

    public void setComposers(List<Composer> composers) {
        this.composers = composers;
    }

    public List<Draft> getDrafts() {
        return drafts;
    }

    public void setDrafts(List<Draft> drafts) {
        this.drafts = drafts;
    }

    public boolean isPublicationsEmpty() {
        return publicationsEmpty;
    }

    public void setPublicationsEmpty(boolean publicationsEmpty) {
        this.publicationsEmpty = publicationsEmpty;
    }
}
