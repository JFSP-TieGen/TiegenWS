package OLD_CustomException;

import java.sql.Timestamp;

public class AutomobileCustomException extends Exception {

    private int errorno;
    private String errormsg;

    public enum ErrorCode {

        MISSING_PRICE(101),
        MISSING_OPTIONSET(102),
        MISSING_OPTION(103),
        MISSING_INPUT_FILE(104),
        MISSING_SERIALIZATION_FILE(105),
        DEFAULT(999);
        private final int number;

        private ErrorCode(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

    }

    public AutomobileCustomException() {
        super();
        printmyproblem();
    }

    public AutomobileCustomException(String errormsg) {
        super();
        this.errormsg = errormsg;
        printmyproblem();
    }

    public AutomobileCustomException(int errorno) {
        super();
        this.errorno = errorno;
        printmyproblem();
    }

    public AutomobileCustomException(int errorno, String errormsg) {
        super();
        this.errorno = errorno;
        this.errormsg = errormsg;
        printmyproblem();
    }

    public int getErrorno() {
        return errorno;
    }

    public void setErrorno(int errorno) {
        this.errorno = errorno;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public void printmyproblem() {
        java.util.Date date = new java.util.Date();
        System.out.print("EXCEPTION:::::"+new Timestamp(date.getTime()));
        System.out.println(": AutomotiveCustomException [errorno=" + errorno + ", errormsg=" + errormsg+"]");
    }

    public String fix() {
        FixProblem f1 = new FixProblem();
        System.out.println("Using default values!");
        switch (this.errorno) {
            case 101:
                return f1.fixProblemMissingPrice()+"";
            case 102:
                return f1.fixProblemMissingOptionSet();
            case 103:
                return f1.fixProblemMissingOption();
            case 104:
                return f1.fixProblemWithInputFile();
            case 105:
                return f1.fixProblemWithSerializeFile();
        }
        return null;
    }
    
}
