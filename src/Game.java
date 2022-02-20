import java.util.Arrays;

public class Game {

    private String[] valids = Utils.fileToArray(Utils.Source.VALIDS);
    public String answer = Utils.randomFrom(Utils.Source.VALIDS);
    public final int MAX_TURNS;
    public int turnsUsed=0;

    public Game(int MAX_TURNS) {
        this.MAX_TURNS=MAX_TURNS;
    }


    public String getFeedback(String guess) {
        turnsUsed++;
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