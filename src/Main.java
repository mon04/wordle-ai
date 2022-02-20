import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        AI ai = new AI();
        Game game = new Game(6);
        System.out.println("answer: "+game.answer+"\n");
        String feedback = "_____";
        String guess = "";
        while(game.turn < game.MAX_TURNS) {
            guess = ai.getSuggestion();
            game.submitGuess(guess);
            feedback = game.getLatestFeedback();
            System.out.println("possible answers: "+ai.listSize());
            ai.filterByFeedback(feedback, guess);

            System.out.println(game.turn+". "+guess);
            System.out.println("   "+feedback+"\n");

            if(feedback.equals("22222")) {
                System.out.printf("Solved in %d/%d turns!", game.turn, game.MAX_TURNS);
                System.exit(0);
            }
        }
        System.out.println("Failed to solve Wordle!");
        try {
            FileWriter fw = new FileWriter(new File("src/fails.txt"), true);
            fw.write("\n"+game.answer.toLowerCase());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}