package ru.axtane.CAHI.models;

import ru.axtane.CAHI.models.enums.DraftType;

import javax.persistence.*;

@Entity
@Table(name = "drafts")
public class Draft {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "draftName")
    private String draftName;
    @Column(name = "pageStatement")
    private String pageStatement;
    @Enumerated(EnumType.STRING)
    private DraftType draftType;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Person userAuthor;
    @Transient
    private String typeUrl;

    public String getTypeUrl() {
        return this.getDraftType().getValue();
    }

    public void setTypeUrl(String typeUrl) {
        this.typeUrl = typeUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDraftName() {
        return draftName;
    }

    public void setDraftName(String draftName) {
        this.draftName = draftName;
    }

    public String getPageStatement() {
        return pageStatement;
    }

    public void setPageStatement(String pageStatement) {
        this.pageStatement = pageStatement;
    }

    public DraftType getDraftType() {
        return draftType;
    }

    public void setDraftType(DraftType draftType) {
        this.draftType = draftType;
    }

    public Person getUserAuthor() {
        return userAuthor;
    }

    public void setUserAuthor(Person userAuthor) {
        this.userAuthor = userAuthor;
    }
}
