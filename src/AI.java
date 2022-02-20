import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class AI {

    private static LinkedList<Word> potentials;
    private static int[] frequencies = new int[26];

    public AI() {
        potentials = Utils.fileToList(Utils.Source.VALIDS);
        this.setUp();
    }

    public void setUp() {
        calculateFrequencies();
        calculateSums();
        Collections.sort(potentials);
    }

    public void filterByFeedback(String green, String yellow, String grey) {
        potentials.removeIf(word -> !word.checkValid(green, yellow, grey));
    }

    public void printAllSums() {
        int i=1;
        for (Word word : potentials) {
            System.out.printf("%5d. %s\t%5d\n", i++, word.value, word.sum);
        }
    }

    private void calculateFrequencies() {
        Arrays.fill(frequencies, 0);
        for(Word word: potentials) {
            for(int i=0; i < word.value.length(); i++) {
                int asciiVal = word.value.charAt(i);
                frequencies[asciiVal-'A']++;
            }
        }
    }

    private void calculateSums() {
        for(Word word: potentials)
            word.calculateSum();
    }

    public String getSuggestion() {
        assert potentials.size() > 0;
        return potentials.get(0).value;
    }

    public int listSize() {
        return potentials.size();
    }

    static class Word implements Comparable<Word> {

        public String value;
        public int sum;

        public Word(String word) {
            this.value=word;
        }

        private void calculateSum() {
            boolean[] used = new boolean[26];
            sum=0;
            for(char c: value.toCharArray()) {
                int i=c-'A';
                if(!used[i]) {
                    sum+=frequencies[i];
                    used[i]=true;
                }
            }
        }

        public boolean checkValid(String green, String yellow, String grey) {
            return greenValid(green) && yellowValid(yellow) && greyValid(grey);
        }

        private boolean greenValid(String pattern) {
            char[] pc = pattern.toCharArray();
            char[] wc = value.toCharArray();
            for(int i=0; i < pc.length; i++) {
                if(pc[i] != '_' && pc[i] != wc[i]) {
                    //System.out.println(value + " failed GREEN");
                    return false;
                }
            }
            return true;
        }

        private boolean yellowValid(String pattern) {
            char[] p = pattern.toCharArray();
            for(int i=0; i < p.length; i++) {
                if(p[i] != '_' && !containsElsewhere(value, p[i], i)) {
                    //System.out.println(value + " failed YELLOW");
                    return false;
                }
            }
            return true;
        }

        private boolean greyValid(String pattern) {
            for(char pc : pattern.toCharArray()) {
                if(pc != '_' && value.contains(pc + "")) {
                    //System.out.println(value + " failed GREY");
                    return false;
                }
            }
            return true;
        }

        private static boolean containsElsewhere(String word, char c, int pos) {
            if(!word.contains(c + ""))
                return false;
            for(int i=0; i < word.length(); i++) {
                if(i!=pos && word.charAt(i) == c)
                    return true;
            }
            return false;
        }

        @Override
        public int compareTo(Word other) {
            return Integer.compare(other.sum, this.sum);
        }
    }
}