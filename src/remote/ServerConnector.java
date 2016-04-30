package remote;





import entity.User;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import util.Constants;

public class ServerConnector{
    public Object sendRequest(String urlS, User input){
        HttpURLConnection urlConnection = null;
        try {
        URL url = new URL(urlS);

        urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setDoOutput(true);
            urlConnection.setDoOutput(true);
            

            ObjectOutputStream out = new ObjectOutputStream(urlConnection.getOutputStream());
            out.writeObject(input);
            out.close();
            ObjectInputStream in = new ObjectInputStream(urlConnection.getInputStream());
            Object output = in.readObject();
            in.close();
            return  output;

        }catch(Exception e){
            System.out.println("Exception:"+e.getMessage());
        }
        finally {
            urlConnection.disconnect();
        }
        return null;
    }

    public static void main(String args[]){
        ServerConnector conn = new ServerConnector();
        User user = new User("g3","pwd");
        user = (User) conn.sendRequest(Constants.URL.concat(Constants.URL_SIGN_UP),user);
    }
}