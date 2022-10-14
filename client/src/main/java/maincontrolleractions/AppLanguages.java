package maincontrolleractions;

public enum AppLanguages {

    RUSSIAN("Russian"),
    ICELANDIC("Icelandic"),
    FRENCH("French"),
    SPANISH("Spanish");

    private final String title;

    AppLanguages(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
