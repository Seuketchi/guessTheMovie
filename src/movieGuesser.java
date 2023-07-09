import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class movieGuesser {

    public static void main(String[] args) throws Exception{

        String movies;
        String movie;
        char guess;
        int randomMovie;
        String blankMovie;

        Scanner guesser = new Scanner(System.in); //OBJECT FOR USER INPUT

        //STORE MOVIES
        List<String> list = new ArrayList<>();
        List<Character> letters = new ArrayList<>();
        List<Character> hideLetters= new ArrayList<>();


        File file = new File("src/movielist.txt");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            movies = scanner.nextLine();
            list.add(movies);
        }


        //RANDOM MOVIE GENERATOR
        randomMovie = (int)(Math.random() * list.size());
        movie = list.get(randomMovie);
        System.out.println(movie);

        //HIDE LETTERS
        for (int i = 0; i < movie.length(); i++) {
            char characters = movie.charAt(0);
            letters.add(characters);
            hideLetters.add('_');
        }

        for (char character : hideLetters) {
            System.out.print(character);
        }


    }

}
