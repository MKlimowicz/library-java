package org.example.services.library;

import org.example.domain.*;

import java.util.List;

public interface LibraryService {


    void addBook(Book book);
    void addMagazine(Magazine magazine);
    List<Book> findAllBook();
    List<Magazine> findAllMagazine();
    void deleteBook(Long publicationId);
    void deleteMagazine(Long publicationId);

    List<Publication> findPublicationByTitle(String title);

    void updateBook(BookFields bookField, String newValue, Long bookId);

    void updateMagazine(MagazineFields magazineFields, String newValue, Long magazineId);
}
