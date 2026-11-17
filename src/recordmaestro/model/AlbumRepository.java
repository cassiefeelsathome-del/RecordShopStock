package recordmaestro.model;

import java.util.List;

/**
 * The contract (Unit 11). Same four operations as Unit 13's CoffeeRepository.
 * In M2 it has one implementation (InMemoryCoffeeRepository); in M4 a
 * persistent one (e.g. OracleCoffeeRepository) implements the same interface,
 * with no methods added, and is swapped in by changing one line of Main.
 * AlbumRepository
 */
public interface AlbumRepository {
    List<Album> findAll();
    
    Album findById(int id);

    Album save(Album album);
    
    void deleteById(int id);


}
