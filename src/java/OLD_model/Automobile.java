/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OLD_model;

import OLD_CustomException.AutomobileCustomException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 *
 * @author keerthanathangaraju
 */
public class Automobile implements java.io.Serializable {

    private String name;
    private String make;
    private float basePrice;
    private ArrayList<OptionSet> opset;

    /**
     *
     * @param size
     * @param n
     * @param m
     * @param basePrice
     */
    public Automobile(int size, String n, String m, float basePrice) {
        opset = new ArrayList<OptionSet>(size);
        name = n;
        make = m;
        this.basePrice = basePrice;
    }

    /**
     * default constructor
     */
    public Automobile() {
        this(10, null, null, 0);
    }

    public String getName() {
        return name;
    }

    public String getMake() {
        return make;
    }

    public ArrayList<OptionSet> getOpset() {
        return opset;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public String getOpsetSelected(String option) {
        int index = findOpset(option);
        if (index != -1) {
            return opset.get(index).getOptionSelected();
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public void setOpset(ArrayList<OptionSet> opset) {
        this.opset = opset;
    }

    /**
     * find optionSet index by name
     *
     * @param n
     * @return
     */
    public int findOpset(String n) {
        Iterator<OptionSet> it = opset.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().getName().equals(n)) {
                return i;
            }
            i++;
        }
        return -1;

    }

    /**
     * update optionSet name by index
     *
     * @param index
     * @param name
     * @return
     */
    public boolean updateOptionSetName(int index, String name) {

        if (index > -1 && index < opset.size()) {
            opset.get(index).setName(name);
            return true;
        }
        return false;

    }

    /**
     * update optionSet name by its oldname
     *
     * @param oldName
     * @param name
     * @return
     */
    public boolean updateOptionSetName(String oldName, String name) {
        int index = findOpset(oldName);
        return updateOptionSetName(index, name);
    }

    /**
     * update option in an optionSet by passing its index and parameters -
     * optionName and optionPrice
     *
     * @param index
     * @param optName
     * @param optPrice
     * @return
     */
    public boolean updateOptionSetChoice(int index, String optName, float optPrice) {

        if (index > -1 && index < opset.size()) {
            opset.get(index).updateOpt(optName, optPrice);
            return true;
        }
        return false;

    }

    /**
     * update option in an optionSet by passing name and parameters - optionName
     * and optionPrice
     *
     * @param optionSetName
     * @param optionName
     * @param optionPrice
     * @return
     */
    public boolean updateOptionSetChoice(String optionSetName, String optionName, float optionPrice) {
        int index = findOpset(optionSetName);
        
        return updateOptionSetChoice(index, optionName, optionPrice);
    }

    /**
     * Updates the option in optionset selection attribute
     * @param optionSetName
     * @param optionName
     * @param selected
     * @return
     */
    public boolean updateOptionSetChoiceSelected(String optionSetName, String optionName, boolean selected) {
        int index = findOpset(optionSetName);
        return this.opset.get(index).updateOptSelected(optionName, selected);
    }

    /**
     * delete optionSet at an index
     *
     * @param index
     * @return
     */
    public boolean deleteOptionSet(int index) {

        if (index > -1 && index < opset.size()) {
            opset.remove(index);
            return true;
        }
        return false;

    }

    /**
     * delete optionSet by its name
     *
     * @param name
     * @return
     */
    public boolean deleteOptionSet(String name) {
        int index = findOpset(name);
        return deleteOptionSet(index);
    }

    /**
     * delete optionSetChoice by its name
     *
     * @param optionSetName
     * @param optionName
     * @return
     */
    public boolean deleteOptionSetChoice(String optionSetName, String optionName) {
        int index = findOpset(optionSetName);
        if (index > -1 && index < opset.size()) {
            opset.get(index).deleteOpt(optionName);
            return true;
        }
        return false;
    }

    /**
     * Calculates price of automobile form the options selected
     * @return
     */
    public float calculatePrice() {
        Iterator<OptionSet> it = opset.iterator();
        float price = this.basePrice;
        while (it.hasNext()) {
            OptionSet ops = it.next();
            price += ops.getPriceOfSelected();
        }
        return price;
    }

    /**
     * Parses line from the .txt file to add it in Automobile object
     *
     * @param line
     * @throws AutomobileCustomException
     */
    public void addLine(String line) throws AutomobileCustomException {
        boolean fixed = true;
        do {
            try {
                //single option
                fixed = true;
                if (!line.contains(":")) {
                    StringTokenizer tokens = new StringTokenizer(line, "=");
                    if (tokens.countTokens() != 2) {
                        throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.DEFAULT.getNumber(), "Null or multiple value found after =. Make sure options are set in 'name=value' pattern.");
                    }
                    String name = tokens.nextToken();
                    if (name.equalsIgnoreCase("name")) {
                        this.setName(tokens.nextToken());
                    } else if (name.equalsIgnoreCase("make")) {
                        this.setMake(tokens.nextToken());
                    } else if (name.equalsIgnoreCase("basePrice")) {
                        this.setBasePrice(Float.parseFloat(tokens.nextToken()));
                    } else {
                        throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.DEFAULT.getNumber(), "Option '" + name + "' not recognized! Use only name,make,basePrice values!");
                    }
                } else {
                    StringTokenizer tokens = new StringTokenizer(line, ":");
                    boolean multiple = false;
                    if (line.contains("::")) {
                        multiple = true;
                    }
                    if (tokens.countTokens() == 1) {
                        throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.MISSING_OPTIONSET.getNumber(), "No value found after ':'. Make sure options are set in 'name:option1=value1,..,optionN=value=N' pattern.");
                    } else if (tokens.countTokens() != 2) {
                        throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.DEFAULT.getNumber(), "Null or multiple value found after :. Make sure options are set in 'name:option1=value1,..,optionN=value=N' pattern.");
                    }
                    String optName = tokens.nextToken();
                    tokens = new StringTokenizer(tokens.nextToken(), ",");
                    int optionSize = tokens.countTokens();
                    if (optionSize == 0) {
                        throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.MISSING_OPTION.getNumber(), "No value found after ':'. Make sure options are set in 'name:option1=value1,..,optionN=value=N' pattern.");
                    }
                    OptionSet ops = new OptionSet(optName, optionSize, multiple);
                    while (tokens.hasMoreTokens()) {
                        String options = tokens.nextToken();
                        StringTokenizer option = new StringTokenizer(options, "=");
                        String decimalPattern = "([0-9]*)(\\.[0-9]*)?"; 
                        if (option.countTokens() == 1 && Pattern.matches(decimalPattern,options.replace("=",""))) {//only price is given
                            line = line.replace(options, "").replace(":",":"+options+",");
                            throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.MISSING_OPTION.getNumber(), "No value found after '=' for "+options+". Make sure options are set in 'name:option1=value1,..,optionN=value=N' pattern.");
                        }
                        else if (option.countTokens() == 1) {//only option is given
                            line = line.replace(options, "")+","+options.replace("=","")+"=";
                            throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.MISSING_PRICE.getNumber(), "No value found after '=' for "+options+". Make sure options are set in 'name:option1=value1,..,optionN=value=N' pattern.");
                        }
                        String optionName = option.nextToken();
                        boolean selected = false;
                        String value = option.nextToken();
                        if (value.contains("<selected>")) {
                            value = value.replace("<selected>", "");
                            selected = true;
                        }
                        float optionValue = Float.parseFloat(value);
                        ops.addOpt(optionName, optionValue, selected);
                    }
                    this.opset.add(ops);
                }
            } catch (NullPointerException e) {
                throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.DEFAULT.getNumber(), "Null or multiple value found! Make sure options are set in 'name:option1=value1,..,optionN=value=N' pattern.");
            } catch (AutomobileCustomException e) {
                if (e.getErrorno() != AutomobileCustomException.ErrorCode.DEFAULT.getNumber()) {
                    String fix = e.fix();
                    if (fix != null && !fix.isEmpty()) {
                        fixed = false;
                        if(e.getErrorno() == AutomobileCustomException.ErrorCode.MISSING_OPTION.getNumber())
                            line = line.replace(":",":"+fix);
                        else
                            line = line + fix;
                    }
                } else {
                    throw e;
                }
            }
        } while (!fixed);
    }
    
    public void addOptionSet(String optName) throws AutomobileCustomException {
        if(optName.isEmpty()){
                        throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.MISSING_OPTIONSET.getNumber(), "Option Set Value Empty!!");
        }
        OptionSet ops = new OptionSet(optName, 0);
        this.opset.add(ops);
    }
    
    public void addOptionSetChoice(String optionSetName, String optionName, float optionPrice, boolean selected) {
        int index = findOpset(optionSetName);
        this.opset.get(index).addOpt(optionName, optionPrice, selected);
    }
    
    public HashMap<String, String[]> getOptionsAsStringArray() {
        HashMap<String, String[]> optionset = new HashMap<String, String[]>();
        Iterator<OptionSet> it = opset.iterator();
        while (it.hasNext()) {
            OptionSet os = it.next();
            optionset.put(os.getName(), os.getOptionsAsStringArray(os.getName()));
        }
        return optionset;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Model=");
        sb.append(name);
        sb.append("\nMake=");
        sb.append(make);
        sb.append("\nBasePrice=");
        sb.append(basePrice);
        for (OptionSet opset1 : opset) {
            if (!opset1.getName().isEmpty()) {
                sb.append(opset1.toString());
            }
        }
        return sb.toString();
    }

}
