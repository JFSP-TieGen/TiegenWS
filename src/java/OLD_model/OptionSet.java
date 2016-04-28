/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OLD_model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author keerthanathangaraju
 */
public class OptionSet implements Serializable{
    private ArrayList<Option> opt;
    private String name;
    
    private boolean multiple;
    
    //add option type
    
    
    
    OptionSet(String n, int size) {
        opt = new ArrayList<Option>(size);
        name = n;
        multiple = false;
    }
    
    OptionSet(String n, int size, boolean isMultiple) {
        opt = new ArrayList<Option>(size);
        name = n;
        multiple = isMultiple;
    }
    
    OptionSet() {
        this(new String(), 1);
        multiple = false;
    }

    protected ArrayList<Option> getOpt() {
        return opt;
    }
   
    protected String getName() {
        return name;
    }
    
    /**
     * returns price of selected options in an optionSet
     * @return
     */
    protected float getPriceOfSelected(){
        Iterator<Option> it = opt.iterator();
        float price = 0;
        while (it.hasNext()) {
            Option option = it.next();
            if(option.isSelected())
                price +=option.getPrice();
        }
        return price;
    }
    
    /**
     * gets the list of selected options as a string
     * @return
     */
    protected String getOptionSelected() {
        Iterator<Option> it = opt.iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            Option option = it.next();
            if (option.isSelected()) {
                sb.append(option.getName());
                sb.append("=");
                sb.append(option.getPrice());
                sb.append(",");
            }
        }
        sb.setLength(sb.lastIndexOf(","));
        return sb.toString();
    }
    
    protected String[] getOptionsAsStringArray(String optionname) {
        Iterator<Option> it = opt.iterator();
        String result[] = new String[opt.size()];
        int i =0;
        while (it.hasNext()) {
            StringBuilder sb = new StringBuilder();

            Option option = it.next();
                sb.append(option.getName());
                sb.append("($");
                sb.append(option.getPrice());
                sb.append(")");
            result[i++] = sb.toString();
        }
        return result;
    }
    
    protected boolean isMultiple(){
        return multiple;
    }
    
    /**
     * checks if the optionset can be set to multiple choice, returns true if set / false if not
     * @param multiple
     * @return
     */
    protected boolean setMultiple(boolean multiple){
        if(this.multiple != multiple && !multiple){
            if(canBeSingle()){
                this.multiple = false;
                return true;
            }else{
                return false;
            }
        }else{
            this.multiple = multiple;
            return true;
        }
        
    }
    
    /**
     * checks if the optionset can be made single
     * @return
     */
    protected boolean canBeSingle(){
        Iterator<Option> it = opt.iterator();
        boolean flag = false;
        while (it.hasNext()) {
            Option option = it.next();
            if (!flag && option.isSelected()) {
                flag = true;
            } else if (option.isSelected()) {
                return false; // cannot reset since some options are already selected
            }
        }
        return true;
    }
    
    /**
     * set option parameters by sending optionName and optionPrice
     * @param index
     * @param name
     * @param price
     */
    protected void setOpt(int index, String name, float price){
        
        if( index > -1 && index < opt.size()){
            opt.get(index).setName(name);
            opt.get(index).setPrice(price);
        }
        
    }
    
    protected void setOpt(ArrayList<Option> opt) {
        this.opt = opt;
    }

    protected void setName(String name) {
        this.name = name;
    }
    
    /**
     * find option by name and return index
     * @param n
     * @return
     */
    protected int findOpt(String n){
        Iterator<Option> it = opt.iterator();
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
     * add new option in optionSet
     * @param name
     * @param price
     * @param selected
     */
    protected void addOpt(String name, float price, boolean selected){
        Option option = new Option(name, price, selected);
        this.opt.add(option);
        
    }
    
    /**
     * update price of an option by passing its name
     * @param name
     * @param price
     * @return
     */
    protected boolean updateOpt(String name, float price){
        int index = findOpt(name);
        return updateOpt(index, name) && updateOpt(index, price);
    }

    /**
     * update name of an option by passing its index
     * @param index
     * @param name
     * @return
     */
    protected boolean updateOpt(int index, String name){
        
        if( index > -1 && index < opt.size()){
            opt.get(index).setName(name); 
            return true;
        }
        
        return false;
        
    }
    
    /**
     * update price of an option by passing its index
     * @param index
     * @param price
     * @return
     */
    protected boolean updateOpt(int index, float price){
        
        if( index > -1 && index < opt.size()){
            opt.get(index).setPrice(price);
            return true;
        }
        return false;
        
    }
    
    /**
     *
     * @param name
     * @param selected
     * @return
     */
    protected boolean updateOptSelected(String name, boolean selected){
       
        if(!multiple && selected){
            Iterator<Option> it = opt.iterator();
            while (it.hasNext()) {
                Option option = it.next();
                if (option.isSelected()) {
                    return false; // cannot update
                } 
            }
        }
        int index = findOpt(name);
        if( index > -1 && index < opt.size()){
            opt.get(index).setSelected(selected);
            return true;
        }
        return false;
        
    }
    
    /**
     * delete option by passing its index
     * @param index
     * @return
     */
    protected boolean deleteOpt(int index){
        
        if( index > -1 && index < opt.size()){
            opt.remove(index);
            return true;
        }
        return false;
        
    }
    
    /**
     * delete option by passing its name 
     * @param name
     * @return
     */
    protected boolean deleteOpt(String name){
        int index = findOpt(name);
        return deleteOpt(index);
        
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(name);
        sb.append(":");
        if(multiple)
            sb.append(":");
        for (Option opt1 : opt) {
            if (!opt1.getName().isEmpty()) {
                sb.append(opt1.toString());
                sb.append(",");
            }
        }
        sb.setLength(sb.lastIndexOf(","));
        return sb.toString();
    }
    
    
    
    class Option implements Serializable{
        private String name;
        private float price;
        private boolean selected;
        
        protected Option(String name, float price, boolean isSelected) {
            this.name = name;
            this.price = price;
            this.selected = isSelected;
        }
        
        protected Option(String name,float price){
            this.name = name;
            this.price = price;
            this.selected = false;
        }

        Option() {
            this(new String(), 0);
        }

        protected String getName() {
            return name;
        }

        protected void setName(String name) {
            this.name = name;
        }

        protected float getPrice() {
            return price;
        }

        protected void setPrice(float price) {
            this.price = price;
        }

        protected boolean isSelected() {
            return selected;
        }

        protected void setSelected(boolean selected) {
            this.selected = selected;
        }
        
        

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append("=");
            sb.append(price);
            if(selected)
                sb.append("<selected>");
            return sb.toString();
        }
        
        
        
    }
}





