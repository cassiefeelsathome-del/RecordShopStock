package recordmaestro;

import recordmaestro.model.*;
import java.util.List;

/**
 * Milestone 2: a console-only Main (no GUI). It creates, lists, updates and
 * deletes album titles through the repository and prints the result of each
 * step, then tests the bad-input cases named in the brief (A.4): an empty or
 * negative price, a price with letters, and an id that does not exist.
 *
 * In M3 this file becomes the Swing launcher; this console demo can be kept
 * as ConsoleMain.java.
 * @author Juna
 * @author Lucky
 */

public class Main {
    public static void main(String[] args) {
        AlbumRepository repo = new InMemoryRecordRepository();

        //First option
        heading("1. Create - add ten albums (see Data)");

        //AI generated titles
        repo.save(new Album("Abbey Road", "The Beatles", 24.99, true));
        repo.save(new Album("Rumours", "Fleetwood Mac", 22.50, true));
        repo.save(new Album("Kind of Blue", "Miles Davis", 19.99, true));
        repo.save(new Album("Thriller", "Michael Jackson", 21.99, false));
        repo.save(new Album("Back to Black", "Amy Winehouse", 23.75, true));
        repo.save(new Album("OK Computer", "Radiohead", 25.00, true));
        repo.save(new Album("Blonde", "Frank Ocean", 29.99, false));
        repo.save(new Album("Seed of a Seed", "Haley Hendrix", 27.50, true));
        repo.save(new Album("To Pimp a Butterfly", "Kendrick Lamar", 26.25, true));
        Album currents = repo.save(new Album("Currents", "Tame Impala", 24.00, false));

        System.out.println("Last Album saved was given id " + currents.getId() 
            + " automatically - the user never types id");

        //second option a
        heading("2. Read - list every drink");
        printAll(repo);

        //second option b
        heading("3. Read one - findById(3)");
        System.out.println(repo.findById(3));

        //third option
        heading("4. Update - change price and stock of album #" + currents.getId());
        Album changed = new Album(currents.getId(), "Currents", "Tame Impala", 20.00, true);
        repo.save(changed);
        System.out.println("After update: " + repo.findById(currents.getId()));
        System.out.println("Drinks stored: " + repo.findAll().size() + " (update did not add a row)");

        //option four
        heading("5. Delete - remove album #5");
        System.out.println("Deleting: " + repo.findById(5));
        repo.deleteById(5);
        System.out.println("findById(5) afterwards -> " + repo.findById(5));
        repo.findAll();

        //error handling
        heading("6. bad input - each is rejected and nothing stored changes");
        int before = repo.findAll().size();
        tryCreate(repo, "blank ballad", "price left empty", "");
        tryCreate(repo, "letter album", "price as text", "four dolla");
        tryCreate(repo, "Negative nocture", "price below zero", "-19.99");
        tryCreate(repo, "", "name left empty", "3.00");
        System.out.println("findById(99) -> " + repo.findById(99));
        repo.deleteById(99);
        System.out.println("deleteByID(99) -> no error, nothing removed");
        try {
            repo.save(new Album(99, "Race", "Alex G - update id that dont exsist", 3.00, false));
        } catch (IllegalArgumentException e) {
            System.out.println("update id 99 -> rejected: " + e.getMessage());
        }
        System.out.println("albums stored before: " + before + ", after: " + repo.findAll().size());

        //Defensive copy
        heading("7. Defensive copy - changing the returned list does not change the repo");
        List<Album> copy = repo.findAll();
        copy.clear();
        System.out.println("Cleared the returned list (size " + copy.size() + "); repo still holds " +
            repo.findAll().size() + " drinks.");
        
        
    }

    /**
     * Mimics what the Controller will do with the form in M3: turn the typed
     * price text into a double, then let the entity validate the rest.
     */
    private static void tryCreate(AlbumRepository repo, String title, String artist, String priceText) {
        String label = String.format("%-18s price=\"%s\"", title.isEmpty() ? "(no name)" : title, priceText);
        if (priceText.trim().isEmpty()) {
            System.out.println(label + " -> rejected: Price is required.");
            return;
        }
        double price;
        try {
            price = Double.parseDouble(priceText.trim());
        } catch (NumberFormatException e) {
            System.out.println(label + " -> rejected: Price must be a number, for example 4.50.");
            return;
        }
        try {
            Album saved = repo.save(new Album(title, artist, price, false));
            System.out.println(label + " -> saved as #" + saved.getId());
        } catch (IllegalArgumentException e) {
            System.out.println(label + " -> rejected: " + e.getMessage());
        }
    }


    private static void printAll(AlbumRepository repo) {
        for (Album a : repo.findAll()) {
            System.out.println(a);
        }
        System.out.println("(" + repo.findAll().size() + " albums)");
    }

    private static void heading(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }

}
