import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        //FIlE SOURCE READER
        ArrayList<String> movieTitle = readMovie("src/movielist.txt");

        //FIELDS:
        int score = 0;
        String guessedLetters = "";
        String selectedMovie = getRandomMovie(movieTitle);
        System.out.println(selectedMovie);

        //GAME LOOP
        while(score < 10 && !checkCompleteWord(selectedMovie, guessedLetters)) {
            String maskedTitle = checkGuess(selectedMovie, guessedLetters);
            System.out.println("You are guessing: " + maskedTitle);

            String guess = getGuess();

            for (char letters : guess.toCharArray()) {
                if(Character.isLetter(letters)) {
                    if (selectedMovie.contains(String.valueOf(letters))) {
                        guessedLetters += letters;
                    } else {
                        score++;
                        System.out.println("You have guessed (" + score + ") letters:");
                    }
                }
            }
        }

        checkWin(selectedMovie,guessedLetters);


    }
    public static ArrayList<String> readMovie(String fileName) throws Exception  {
        Scanner read = new Scanner(new FileReader(fileName));
        ArrayList<String> movies = new ArrayList<>();
        while(read.hasNextLine()) {
            movies.add(read.nextLine());
        }
        return movies;
    }
    public static String getRandomMovie(ArrayList<String> movies)  {
        Random random = new Random();
        int randomIndex = random.nextInt(movies.size());
        return movies.get(randomIndex);
    }

    public static String checkGuess(String movieTitle, String guessedLetters) {
        StringBuilder maskedTitle = new StringBuilder();
        for (char letters: movieTitle.toCharArray()) {
            if(guessedLetters.contains(String.valueOf(letters))){
                maskedTitle.append(letters);
            }else {
                maskedTitle.append('_');
            }
        }
        return maskedTitle.toString();
    }

    public static String getGuess() {
        Scanner guess = new Scanner(System.in);
        System.out.print("Guess a letter: ");
        return guess.nextLine().toLowerCase();
    }

    public static boolean checkCompleteWord(String movieTitle, String guessedLetters) {
        for (char letters: movieTitle.toCharArray()) {
            if(!guessedLetters.contains(String.valueOf(letters)) && Character.isLetter(letters)) {
                return false;
            }
        }
        return true;
    }

    public static void checkWin(String selectedMovie, String guessedLetters) {
        //CHECK WIN
        if(checkCompleteWord(selectedMovie, guessedLetters)) {
            System.out.println("You have guessed '" + selectedMovie + "' correctly.");
        }else {
            System.out.println("Out of guess, the word is '" + selectedMovie + "'.");
        }
    }
}