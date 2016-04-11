package CustomException;

public class FixProblem {

    protected float fixProblemMissingPrice() {
        System.out.println("got here --> fixProblemMissingPrice");
        return 0;
    }

    protected String fixProblemMissingOptionSet() {
        System.out.println("got here --> fixProblemMissingOptionSet");
        return "Selected=0";
    }

    protected String fixProblemMissingOption() {
        System.out.println("got here --> fixProblemMissingOption");
        return "Selected=0";
    }

    protected String fixProblemWithInputFile() {
        String a = "inputFile.txt";
        System.out.println("got here --> fixProblemWithFile");
        return a;
    }

    protected String fixProblemWithSerializeFile() {
        String a = "autoOut.dat";
        System.out.println("got here --> fixProblemWithSerializeFile");
        return a;
    }
}
