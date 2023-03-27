package org.example.domain;

public class Magazine extends Publication{

    private String language;
    private String publicationDate;

    public Magazine(String title, String publisher, int year, String language, String publicationDate) {
        super(title, publisher, year);
        this.language = language;
        this.publicationDate = publicationDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }


    @Override
    public String toString() {
        return String.format("" +
                "[Magazine] ID: %s ,Tytuł: %s, " +
                "Publikujacy: %s, " +
                "Rok wydania: %s, " +
                "Język publikacji: %s, " +
                "Dokladna data wydania: %s",
               getId(), getTitle(), getPublisher(), getYear(), language, publicationDate
        );
    }
}
