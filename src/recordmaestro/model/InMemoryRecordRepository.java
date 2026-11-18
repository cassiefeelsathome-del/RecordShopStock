package recordmaestro.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores albums in an ArrayList and nothing else; everything is lost when the
 * program ends. Written first (brief 2.2) so the View and Controller can be
 * built in M3 before any database exists - and kept as the demo fallback.
 */


public class InMemoryRecordRepository implements AlbumRepository{

    private final List<Album> albums = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<Album> findAll() {
        return new ArrayList<>(albums);
    }

    @Override
    public Album findById(int id) {
        for (Album a: albums){
            if (a.getId() == id){
                return a;
            }
        }
        return null;
    }

    @Override
    public Album save(Album album) {
        if(album == null){
            throw new IllegalArgumentException("Cannot save a missing album");
        }
        if(album.getId() == 0){
            album.setId(nextId++);
            albums.add(album);
            return album;
        }
        for(int i = 0; i< albums.size(); i++){
            if(albums.get(i).getId() == album.getId()){
                albums.set(i, album);
                return album;
            }
        }
        throw new IllegalArgumentException("No album with id " + album.getId() + " to update");
    }

    @Override
    public void deleteById(int id) {
        albums.removeIf(album -> album.getId() == id);
    }
    
    
    
}
