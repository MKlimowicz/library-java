package org.example.filemanager;

import org.example.domain.BookFields;
import org.example.domain.MagazineFields;
import org.example.domain.Publication;
import org.example.domain.PublicationType;

import java.util.List;

public interface LibraryFileManager {

     void createPublication(PublicationType publicationType, Publication publication);

     List<Publication> findAllPublication(PublicationType publicationType);

     void deleteBook(Long publicationId);
     void deleteMagazine(Long publicationId);

     List<Publication> findPublicationByTitle(String title);

     void updatePublication(String newValue, BookFields bookField, MagazineFields magazineFields, Long bookId, PublicationType book);
}
