package org.example.filemanager;

import org.apache.commons.collections4.CollectionUtils;
import org.example.constants.PathConstants;
import org.example.domain.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import java.io.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class LibraryFileManagerImpl implements LibraryFileManager {


    @Override
    public void createPublication(PublicationType publicationType, Publication publication) {
        switch (publicationType) {
            case BOOK -> createBook((Book) publication);
            case MAGAZINE -> createMagazine((Magazine) publication);
            default ->
                    System.out.println("Nie można utworzyć takiego typu publikacji");
        }
    }

    @Override
    public List<Publication> findAllPublication(PublicationType publicationType) {
        return switch (publicationType) {
            case BOOK -> mapToPublication(findAllBooks());
            case MAGAZINE -> mapToPublication(findAllMagazines());
            default -> Collections.emptyList();
        };
    }

    @Override
    public void deleteBook(Long bookId) {
        var books = findAllBooks();
        if (CollectionUtils.isNotEmpty(books)) {
            books = books.stream().filter(book -> !book.getId().equals(bookId)).toList();
            saveJsonFile(books, PathConstants.BOOK_FILE_PATH);
        } else {
            System.out.println("Ksiazka o takim identyfikatorze nie istnieje: " + bookId);
        }
    }

    @Override
    public void deleteMagazine(Long magazineId) {
        var magazines = findAllMagazines();
        if (CollectionUtils.isNotEmpty(magazines)) {
            magazines = magazines.stream().filter(magazine -> !magazine.getId().equals(magazineId)).toList();
            saveJsonFile(magazines, PathConstants.MAGAZINE_FILE_PATH);
        } else {
            System.out.println("Ksiazka o takim identyfikatorze nie istnieje: " + magazineId);
        }
    }

    @Override
    public List<Publication> findPublicationByTitle(String title) {
        return List.of(filterByTitleAndMapToPublication(findAllBooks(), title),filterByTitleAndMapToPublication(findAllMagazines(), title)).stream().flatMap(Collection::stream).toList();
    }

    @Override
    public void updatePublication(String newValue, BookFields bookField, MagazineFields magazineFields,  Long publicationId, PublicationType publicationType) {
        if (PublicationType.BOOK.equals(publicationType)) {
            updateBook(newValue, bookField, publicationId);
        } else if (PublicationType.MAGAZINE.equals(publicationType)) {
            updateMagazine(newValue, magazineFields, publicationId);
        }
    }

    private void updateBook(String newValue, BookFields bookField, Long bookId) {
        var bookById = findBookById(bookId);
        switch (bookField) {
            case ISBN -> bookById.setIsbn(newValue);
            case YEAR -> bookById.setYear(Integer.parseInt(newValue));
            case PAGES -> bookById.setPages(newValue);
            case TITLE -> bookById.setTitle(newValue);
            case AUTHOR -> bookById.setAuthor(newValue);
            case PUBLISHER -> bookById.setPublisher(newValue);
            default -> System.out.println("Nie ma takiego pola");
        }

        deleteBook(bookId);
        createBook(bookById);
    }

    private void updateMagazine(String newValue, MagazineFields bookField, Long magazineId) {
        var magazineById = findMagazineById(magazineId);
        switch (bookField) {
            case PUBLICATION_DATE -> magazineById.setPublicationDate(newValue);
            case YEAR -> magazineById.setYear(Integer.parseInt(newValue));
            case LANGUAGE -> magazineById.setLanguage(newValue);
            case TITLE -> magazineById.setTitle(newValue);
            case PUBLISHER -> magazineById.setPublisher(newValue);
            default -> System.out.println("Nie ma takiego pola");
        }

        deleteMagazine(magazineId);
        createMagazine(magazineById);
    }

    private Book findBookById(Long bookId) {
        return findAllBooks().stream().filter(book -> Objects.equals(book.getId(), bookId)).toList().get(0);
    }

    private Magazine findMagazineById(Long magazineId) {
        return findAllMagazines().stream().filter(magazine -> Objects.equals(magazine.getId(), magazineId)).toList().get(0);
    }
    private <TPublication extends Publication> List<Publication> mapToPublication(List<TPublication> tPublications) {
        return tPublications.stream().map(tp -> (Publication) tp).toList();
    }

    private <TPublication extends Publication> List<Publication> filterByTitleAndMapToPublication(List<TPublication> tPublications, String title) {
        return mapToPublication(tPublications.stream().filter(publication -> publication.getTitle().contains(title)).toList());
    }

    private void createBook(Book book) {
        var books = findAllBooks();
        if (book.getId() == null || book.getId()  == 0) {
            if (CollectionUtils.isNotEmpty(books)) {
                book.setId( books.get(books.size() - 1).getId() + 1);
            } else {
                book.setId(1L);
            }
        }
        books.add(book);
        saveJsonFile(books, PathConstants.BOOK_FILE_PATH);
    }

    private void createMagazine(Magazine magazine) {
        var magazines = findAllMagazines();
        if (magazine.getId() == null || magazine.getId()  == 0) {
            if (CollectionUtils.isNotEmpty(magazines)) {
                magazine.setId(magazines.get(magazines.size() - 1).getId() + 1);
            } else {
                magazine.setId(1L);
            }
        }
        magazines.add(magazine);
        saveJsonFile(magazines, PathConstants.MAGAZINE_FILE_PATH);
    }

    private List<Magazine> findAllMagazines() {
        return readJsonFile(PathConstants.MAGAZINE_FILE_PATH, Magazine.class);
    }

    private List<Book> findAllBooks() {
        return readJsonFile(PathConstants.BOOK_FILE_PATH, Book.class).stream().sorted(Comparator.comparing(Book::getId)).collect(Collectors.toList());
    }

    public <TPublication extends Publication> List<TPublication> readJsonFile(String fileName, Class<TPublication> publicationClass) {
        var gson = new Gson();
        var publications = new ArrayList<TPublication>();

        try (var reader = new FileReader(fileName)) {
            publications = gson.fromJson(reader, TypeToken.getParameterized(List.class, publicationClass).getType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (publications == null) {
            return new ArrayList<>();
        }

        return publications;
    }

    public  <TPublication extends Publication> void saveJsonFile(List<TPublication> publications, String fileName) {
        var gson = new GsonBuilder().setPrettyPrinting().create();
        try (var writer = new FileWriter(fileName)) {
            gson.toJson(publications, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
