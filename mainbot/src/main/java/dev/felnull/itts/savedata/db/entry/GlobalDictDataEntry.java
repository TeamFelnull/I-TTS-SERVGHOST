package dev.felnull.itts.savedata.db.entry;

public class GlobalDictDataEntry {
    private final String targetWord;
    private String readWord;

    public GlobalDictDataEntry(String targetWord, String readWord) {
        this.targetWord = targetWord;
        this.readWord = readWord;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public String getReadWord() {
        return readWord;
    }

    public void setReadWord(String readWord) {
        this.readWord = readWord;
    }
}
