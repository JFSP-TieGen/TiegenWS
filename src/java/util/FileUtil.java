/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import CustomException.AutomobileCustomException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import java.util.Properties;
import model.Automobile;

/**
 *
 * @author keerthanathangaraju
 */
public class FileUtil {

    /**
     * builds auto object from a file
     *
     * @param filename
     * @return
     * @throws AutomobileCustomException
     */
    public Automobile buildAutoObject(String filename) throws AutomobileCustomException {
        File f = new File(filename);
        Automobile auto = new Automobile();
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                auto.addLine(line);
            }
            if (auto.getBasePrice() == 0 || auto.getName() == null || auto.getMake() == null) {
                throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.DEFAULT.getNumber(), "Please make sure name, basePrice, model are entered in key=value format!");
            }
        } catch (IOException e) {
            throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.MISSING_INPUT_FILE.getNumber(), " File IO Exception");
        }
        return auto;

    }

    public Automobile buildAutoPropFile(String filename) throws AutomobileCustomException {
        Automobile auto = new Automobile();

        Properties props = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(filename);
            props.load(input);
            auto = this.buildAutoPropObject(auto, props);
        } catch (IOException e) {
            throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.MISSING_INPUT_FILE.getNumber(), " File IO Exception");
        }
        return auto;

    }

    public Automobile buildAutoPropObject(Automobile auto, Properties props) throws AutomobileCustomException {
        String CarModel;
        String CarMake = props.getProperty("Make");
        auto.setMake(CarMake);

        if (CarMake != null) {
            CarModel = props.getProperty("Model");
            auto.setName(CarModel);
            float basePrice = props.getProperty("BasePrice") == null ? 0 : Float.parseFloat(props.getProperty("BasePrice"));
            auto.setBasePrice(basePrice);
            int i = 1;
            while (props.getProperty("Option" + i) != null) {
                String optionSet = props.getProperty("Option" + i);
                auto.addOptionSet(optionSet);
                char j = 'a';
                while (props.getProperty("OptionValue" + i + j) != null) {
                    String choice = props.getProperty("OptionValue" + i + j);
                    float price = props.getProperty("OptionPrice" + i + j) == null ? 0 : Float.parseFloat(props.getProperty("OptionPrice" + i + j));
                    boolean selected = props.getProperty("OptionSelected" + i + j) == null ? false : Boolean.parseBoolean(props.getProperty("OptionSelected" + i + j));
                    auto.addOptionSetChoice(optionSet, choice, price, selected);
                    j++;
                }
                i++;
            }

        } else {
            throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.DEFAULT.getNumber(), "Please make sure Make, BasePrice, Model are entered in the properties file!");
        }
        return auto;
    }

    /**
     * serializes automotive object to a file with the input file name
     *
     * @param auto
     * @param filename
     * @throws AutomobileCustomException
     */
    public void serializeAuto(LinkedHashMap<String, Automobile> auto, String filename) throws AutomobileCustomException {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                f.createNewFile();
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
            out.writeObject(auto);
            out.close();
        } catch (FileNotFoundException ex) {
            throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.MISSING_SERIALIZATION_FILE.getNumber(), "File not found while serializing file!");
        } catch (IOException ex) {
            throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.MISSING_SERIALIZATION_FILE.getNumber(), "File IO Exception Exception while serializing automotive class!");
        }
    }

    /**
     * deserializes a input file with input file name to automotive object
     *
     * @param filename
     * @return
     * @throws AutomobileCustomException
     */
    public LinkedHashMap<String, Automobile> deserializeAuto(String filename) throws AutomobileCustomException {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));

            return (LinkedHashMap<String, Automobile>) in.readObject();
        } catch (ClassNotFoundException ex) {
            throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.MISSING_SERIALIZATION_FILE.getNumber(), "File not found while deserializing file!");
        } catch (IOException ex) {
            throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.DEFAULT.getNumber(), "Exception while deserializing automotive class!");
        }
    }

}
