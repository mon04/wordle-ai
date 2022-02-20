public class Main {
    public static void main(String[] args) {
        AI ai = new AI();
        Game game = new Game(6);
        System.out.println("answer: "+game.answer);
        while(game.turnsUsed < game.MAX_TURNS) {
            String guess = ai.getSuggestion();
            String feedback = game.getFeedback(guess);
            System.out.printf("%s\t%s\n", guess, feedback);
            String green = feedback('2', feedback, guess);
            String yellow = feedback('1', feedback, guess);
            String grey = feedback('0', feedback, guess);
            ai.filterByFeedback(green, yellow, grey);
            System.out.println("words left: "+ai.listSize());
        }
    }

    public static String feedback(char type, String genFb, String guess) {
        StringBuilder fb = new StringBuilder();
        for(int i=0; i < genFb.length(); i++) {
            char c = genFb.charAt(i);
            char gc = guess.charAt(i);
            fb.append((c == type) ?gc :'_');
        }
        return fb.toString();
    }
}