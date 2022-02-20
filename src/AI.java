import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class AI {

    public static LinkedList<Word> potentials;
    public static int[] frequencies = new int[26];

    public AI() {
        potentials = Utils.getListFromFile("src/words.txt");
        this.setUp();
    }

    public void setUp() {
        calculateFrequencies();
        calculateSums();
        Collections.sort(potentials);
    }

    public void filterByFeedback(String feedback) {
        // write me
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

        @Override
        public int compareTo(Word other) {
            return Integer.compare(other.sum, this.sum);
        }
    }
}