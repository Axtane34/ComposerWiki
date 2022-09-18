package ru.axtane.CAHI.models.enums;

public enum DraftType {
    ARRANGEMENT("arrangement/newArrangement"),
    CHANTS("chants/newChants"),
    CHORUS("chorus/newChorus"),
    COMPOSER("composer/newComposer"),
    FOLK_PROCESSING("folkProcessing/newFolkProcessing"),
    OPUS_AS("opusAS/newOpusAS"),
    OPUS_DPS("opusDPS/newOpusDPS"),
    NOTHING("nothing");
    private final String value;

    DraftType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
