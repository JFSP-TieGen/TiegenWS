/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import CustomException.AutomobileCustomException;
import adapter.autoClient;
import adapter.proxyAutomobile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.FileUtil;

/**
 *
 * @author keerthanathangaraju
 */
public class CarModelOptionsIO extends proxyAutomobile implements autoClient {
    public Properties getPropertyObject(String filename) throws AutomobileCustomException{
        Properties props = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(filename);
            props.load(input);
            
        } catch (FileNotFoundException ex) {
            throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.MISSING_INPUT_FILE.getNumber(), " FileNotFoundException ");
        } catch (IOException ex) {
            throw new AutomobileCustomException(AutomobileCustomException.ErrorCode.MISSING_INPUT_FILE.getNumber(), " File IO Exception");
        }
        return props;
    }
}
