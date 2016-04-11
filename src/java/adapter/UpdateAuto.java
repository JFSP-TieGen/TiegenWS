/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adapter;

import CustomException.AutomobileCustomException;

/**
 *
 * @author keerthanathangaraju
 */
public interface UpdateAuto {
    public void updateOptionSetName(String Modelname, String OptionSetname, String newName);
    public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice);
    public void updateOptionSetChoiceSelected(String Modelname, String Optionname, String Option, boolean selected);
    public void deleteOptionSet(String Modelname, String OptionSetname);
    public void deleteOptionSetChoice(String Modelname, String Optionname, String Option);
    public void deserializeAuto(String filename) throws AutomobileCustomException;
    
}
