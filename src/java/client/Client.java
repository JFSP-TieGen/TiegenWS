/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import CustomException.AutomobileCustomException;
import java.io.*;
import java.net.*;
import java.util.Properties;
import model.Automobile;

/**
 *
 * @author keerthanathangaraju
 */
public class Client extends Thread implements SocketClientInterface,
        SocketClientConstants {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private CarModelOptionsIO carIO;
    private static String strHost = "localhost";
    private static int iPort = 4444;
    private SelectCarOption carOption;

    private Client() {
        setPort(iPort);
        setHost(strHost);
        carIO = new CarModelOptionsIO();
        carOption = new SelectCarOption();
    }//constructor

    @Override
    public void run() {
        if (openConnection()) {
            handleSession();
            closeSession();
        }
    }

    @Override
    public boolean openConnection() {

        try {
            socket = new Socket(strHost, iPort);
        } catch (IOException socketError) {
            return false;
        }
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            System.out.println("Streams created.");
        } catch (Exception e) {
            if (DEBUG) {
                System.err.println("Unable to obtain stream to/from " + strHost);
            }
            return false;
        }
        return true;
    }

    @Override
    public void handleSession() {
        boolean fixed = true;
        do {

            fixed = true;
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//                System.out.println("Please enter the file name containing auto properties:");
                String filename;// = br.readLine();

//                if (filename != null) {
//                    handleInput(filename);
//                }

                boolean contin = true;
                while (contin) {
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("Please enter option \n1. To enter another file\n2. To view the list of models available\n3. To configure a model\n Any other option to stop");
                    String option = br.readLine();
                    switch (option) {
                        case "1":
                            System.out.println("Please enter the file name containing auto properties:");
                            filename = br.readLine();
                            if (filename != null) {
                                handleInput(filename);
                            }
                            break;
                        case "2":
                            sendOutput("getModelList");
                            String modelNames = (String) in.readObject();
                            System.out.println("------------------------------------------------------------------");
                            System.out.println("Models Available:");
                            System.out.println(modelNames);
                            break;
                        case "3":
                            System.out.println("Please enter the model name to configure:");
                            String modelName = br.readLine();
                            if (!modelName.isEmpty()) {
                                out.writeObject("ModelName=" + modelName);
                                Object o = in.readObject();
                                if (o instanceof Automobile) {
                                    Automobile auto = (Automobile) o;
                                    carOption.display(auto);
                                } else {
                                    System.out.println("------------------------------------------------------------------");
                                    System.out.println((String) o);
                                    
                                }
                            }
                            break;
                        default:
                            contin = false;
                            break;
                    }
                }
            } catch (Exception e) {
                fixed = false;
            }
        } while (!fixed);
    }

    public void sendOutput(Object ob) {
        try {
            out.writeObject(ob);
        } catch (IOException e) {
            if (DEBUG) {
                System.out.println("Error writing to " + strHost);
            }
        }
    }

    public void handleInput(String filename) throws AutomobileCustomException, IOException, ClassNotFoundException {
        Properties prop = carIO.getPropertyObject(filename);
        sendOutput(prop);
        System.out.println((String) in.readObject());
    }

    @Override
    public void closeSession() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            if (DEBUG) {
                System.err.println("Error closing socket to " + strHost);
            }
        }
    }

    public void setHost(String strHost) {
        this.strHost = strHost;
    }

    public void setPort(int iPort) {
        this.iPort = iPort;
    }

    public static void main(String arg[]) {
        /* debug main; does daytime on local host */

        Client d = new Client();
        d.start();
    }

}
