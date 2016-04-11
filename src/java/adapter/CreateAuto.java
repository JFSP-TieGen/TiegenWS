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
public interface CreateAuto {
    
    public void BuildAuto(String filename, String filetype) throws AutomobileCustomException;
    public void printAuto (String Modelname);
    public float getAutoPrice (String Modelname);
    public String getOptionSetChoice(String ModelName, String option);
    public void serializeAuto(String filename) throws AutomobileCustomException;
}

