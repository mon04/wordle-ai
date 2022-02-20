import java.util.Arrays;

public class Game {

    private final String[] valids = Utils.fileToArray(Utils.Source.VALIDS);
    public String answer = Utils.randomFrom(Utils.Source.ANSWERS);
    public final int MAX_TURNS;
    public int turn=0;
    private final String[] guesses;
    private final String[] feedback;

    public Game(int MAX_TURNS) {
        this.MAX_TURNS=MAX_TURNS;
        guesses = new String[MAX_TURNS];
        feedback = new String[MAX_TURNS];
    }

    public Game(int MAX_TURNS, String answer) {
        this(MAX_TURNS);
        this.answer=answer;
    }

    public void submitGuess(String guess) {
        guesses[turn] = guess;
        feedback[turn] = getFeedback(guess);
        turn++;
    }


    private String getFeedback(String guess) {
        char[] ansTemp = answer.toCharArray();
        char[] fbArray = new char[ansTemp.length];
        Arrays.fill(fbArray, '3');
        if(guess.equals(answer)) {
            return "22222";
        }
        for(int i=0; i < ansTemp.length; i++) {
            char gc = guess.charAt(i);
            if(gc == ansTemp[i]) {
                ansTemp[i] = '_';
                fbArray[i] = '2';
            }
        }
        for(int i=0; i < ansTemp.length; i++) {
            if(fbArray[i] != '2' && yellowCheck(guess, ansTemp, i)) {
                fbArray[i] = '1';
            }
        }
        for(int i=0; i < ansTemp.length; i++) {
            if(fbArray[i] != '2' && fbArray[i] != '1')
                fbArray[i] = '0';
        }
        StringBuilder sb = new StringBuilder();
        for(char c: fbArray)
            sb.append(c);
        return sb.toString();
    }

    public String getLatestFeedback() {
        return feedback[turn-1];
    }

    private boolean yellowCheck(String guess, char[] ansTemp, int i) {
        char c = guess.charAt(i);
        for(int j=0; j < guess.length(); j++) {
            if(i != j && c == ansTemp[j]) {
                ansTemp[j] = '_';
                return true;
            }
        }
        return false;
    }

    public boolean isValid(String word) {
        for(String validWord: valids) {
            if(word.equals(validWord))
                return true;
        }
        return false;
    }
}