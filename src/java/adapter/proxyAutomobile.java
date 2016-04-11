/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adapter;

import CustomException.AutomobileCustomException;
import java.util.LinkedHashMap;
import model.Automobile;
import util.FileUtil;

/**
 *
 * @author keerthanathangaraju
 */
public abstract class proxyAutomobile {
    private static LinkedHashMap<String, Automobile> automobiles = new LinkedHashMap<String, Automobile>();
    private final FileUtil util = new FileUtil();
    private AutomobileCustomException ex;
    public void BuildAuto(String filename, String filetype) throws AutomobileCustomException{
        Automobile a ;
        if("properties".equals(filetype))
            a = util.buildAutoPropFile(filename);
        else
            a = util.buildAutoObject(filename);
        automobiles.put(a.getName(), a);
    }

    public void printAuto(String Modelname){
        if(automobiles.get(Modelname) != null){
            System.out.println(automobiles.get(Modelname).toString());
        }
    }
    
    public float getAutoPrice(String Modelname){
        if(automobiles.get(Modelname) != null){
            return automobiles.get(Modelname).calculatePrice();
        }
        return 0;
    }
    
    public String getOptionSetChoice(String Modelname, String Optionname) {
        if(automobiles.get(Modelname) != null){
            return automobiles.get(Modelname).getOpsetSelected(Optionname);
        }
        return null;
    }

    
    public void updateOptionSetName(String Modelname, String OptionSetname, String newName){
        if(automobiles.get(Modelname) != null){
             automobiles.get(Modelname).updateOptionSetName(OptionSetname, newName);
        }
    }

    public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice){
        if(automobiles.get(Modelname) != null){
             automobiles.get(Modelname).updateOptionSetChoice(Optionname, Option, newprice);
        }
    }
    
    public void deleteOptionSet(String Modelname, String OptionSetname){
        if(automobiles.get(Modelname) != null){
             automobiles.get(Modelname).deleteOptionSet(OptionSetname);
        }
    }

    public void deleteOptionSetChoice(String Modelname, String OptionSetname, String Option){
        if(automobiles.get(Modelname) != null){
             automobiles.get(Modelname).deleteOptionSetChoice(OptionSetname, Option);
        }
    }
    
    public void updateOptionSetChoiceSelected(String Modelname, String Optionname, String Option, boolean selected){
        if(automobiles.get(Modelname) != null){
             automobiles.get(Modelname).updateOptionSetChoiceSelected(Optionname, Option, selected);
        }
    }

    
    public void serializeAuto(String filename) throws AutomobileCustomException{
        util.serializeAuto(automobiles, filename);
    }
    
    public void deserializeAuto(String filename) throws AutomobileCustomException{
        automobiles = util.deserializeAuto(filename);
    }
    
    public String fix(Exception e) {
        ex = (AutomobileCustomException) e;
        return ex.fix();
    }
}
