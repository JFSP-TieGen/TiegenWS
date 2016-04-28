/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OLD_servlets;

import client.SocketClientConstants;
import static client.SocketClientConstants.DEBUG;
import static client.SocketClientConstants.iPort;
import static client.SocketClientConstants.strHost;
import client.SocketClientInterface;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author keerthanathangaraju
 */
public abstract class ServletUtilities extends HttpServlet implements SocketClientInterface ,
        SocketClientConstants {
    public Socket socket;
    public ObjectInputStream in;
    public ObjectOutputStream out;

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
    
    public void sendOutput(Object ob) {
        try {
            out.writeObject(ob);
        } catch (IOException e) {
            if (DEBUG) {
                System.out.println("Error writing to " + strHost);
            }
        }
    }

    
    @Override
    public void closeSession() {
        
        try {
            out.writeObject(1);
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            if (DEBUG) {
                System.err.println("Error closing socket to " + strHost);
            }
        }
    }
    public void handleSession() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
