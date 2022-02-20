public class Main {
    public static void main(String[] args) {
        String greenTest = "_A__T";
        String yellowTest = "__I__";
        String greyTest = "P__N_";
        System.out.println(greenTest);
        System.out.println(yellowTest);
        System.out.println(greyTest);
        AI ai = new AI();
        ai.filterByFeedback(greenTest, yellowTest, greyTest);
        System.out.println("Filtered down to "+ai.listSize());
        ai.printAllSums();
    }
}
