/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scale;

import adapter.UpdateAuto;

/**
 * EditOptions uses the UpdateAuto Interface Object to access the update methods of Options
 * It obtains monitor on the model name of Automobile Object that needs to be updated.
 * @author keerthanathangaraju
 */
public class EditOptions extends Thread {

    UpdateAuto buildAuto;
    boolean choice;
    float price;
    String model;
    String optionSet;
    String option;

    /**
     * Constructor to set options to update option choice
     * @param uAuto
     * @param Modelname
     * @param Optionname
     * @param Option
     * @param choice
     */
    public EditOptions(UpdateAuto uAuto, String Modelname, String Optionname, String Option, boolean choice) {
        this.model = Modelname;
        this.optionSet = Optionname;
        this.option = Option;
        this.choice = choice;
        this.buildAuto = uAuto;
        this.price = -1;
    }

    /**
     * Constructor to set options to update price
     * @param uAuto
     * @param Modelname
     * @param Optionname
     * @param Option
     * @param price
     */
    public EditOptions(UpdateAuto uAuto, String Modelname, String Optionname, String Option, float price) {
        this.model = Modelname;
        this.optionSet = Optionname;
        this.option = Option;
        this.price = price;
        this.buildAuto = uAuto;
    }
    
    /**
     * Obtains monitor on the model name of Automobile Object that needs to be updated
     * Reuses updateOptionPrice method of UpdateAuto Interface
     */
    private void updateOptionPrice() {
        String modelSync = this.model.intern();
        synchronized (modelSync) {
            System.out.println("Update " + this.getName() + " started");
            this.buildAuto.updateOptionPrice(modelSync, optionSet, option, price);
            //added for demonstration
            randomWait();
            System.out.println("Update " + this.getName() + " finished");
        }
    }

    /**
     * Obtains monitor on the model name of Automobile Object that needs to be updated
     * Reuses updateOptionSetChoiceSelected method of UpdateAuto Interface
     */
    private void updateOptionSetChoiceSelected() {
        String modelSync = this.model.intern();
        synchronized (modelSync) {
            System.out.println("Update " + this.getName() + "started");
            this.buildAuto.updateOptionSetChoiceSelected(modelSync, optionSet, option, choice);
            //added for demonstration
            randomWait();
            System.out.println("Update " + this.getName() + "finished");
        }
    }

    
    void randomWait() {
        try {
            Thread.currentThread().sleep((long) (3000 * Math.random()));
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
        }
    }

    @Override
    public void run() {
        System.out.println("New " + this.getName() + " spawned");
        if (price > 0) {
            updateOptionPrice();
        }else{
            updateOptionSetChoiceSelected();
        }
    }
}
