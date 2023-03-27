package org.example.services.library;

import org.example.domain.*;
import org.example.filemanager.LibraryFileManager;

import java.util.List;

public class LibraryServiceImpl implements LibraryService {


    private LibraryFileManager libraryFileManager;


    public LibraryServiceImpl(LibraryFileManager libraryFileManager) {
        this.libraryFileManager = libraryFileManager;
    }

    @Override
    public void addBook(Book book) {
        libraryFileManager.createPublication(PublicationType.BOOK, book);
    }

    @Override
    public void addMagazine(Magazine magazine) {
        libraryFileManager.createPublication(PublicationType.MAGAZINE, magazine);
    }

    @Override
    public List<Book> findAllBook() {
        return libraryFileManager
                .findAllPublication(PublicationType.BOOK)
                .stream()
                .map(book -> (Book) book)
                .toList();
    }

    @Override
    public List<Magazine> findAllMagazine() {
        return libraryFileManager
                .findAllPublication(PublicationType.MAGAZINE)
                .stream()
                .map(magazine -> (Magazine) magazine)
                .toList();
    }

    @Override
    public void deleteBook(Long publicationId) {
         libraryFileManager.deleteBook(publicationId);
    }

    @Override
    public void deleteMagazine(Long publicationId) {
        libraryFileManager.deleteMagazine(publicationId);
    }

    @Override
    public List<Publication> findPublicationByTitle(String title) {
        return libraryFileManager.findPublicationByTitle(title);
    }

    @Override
    public void updateBook(BookFields bookField, String newValue, Long bookId) {
        libraryFileManager.updatePublication(newValue, bookField, null, bookId, PublicationType.BOOK);
    }

    @Override
    public void updateMagazine(MagazineFields magazineFields, String newValue, Long magazineId) {
        libraryFileManager.updatePublication(newValue, null, magazineFields, magazineId, PublicationType.MAGAZINE);
    }


}
