/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OLD_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Automobile;

/**
 *
 * @author keerthanathangaraju
 */
@WebServlet("/getAutomobile")
public class GetAutomobileServlet extends ServletUtilities {

    public String getResult(String modelName) {
        StringBuilder result;
        result = new StringBuilder();
        try {
            if (!modelName.isEmpty()) {
                out.writeObject("ModelName=" + modelName);
                Object o = in.readObject();
                if (o instanceof Automobile) {
                    Automobile auto = (Automobile) o;
                    result.append("<CENTER><FORM ACTION=\"showOptions.jsp\"><table><tbody>");
                    result.append("<tr><td> Model: </td><td><SPAN>").append(auto.getName()).append("</SPAN></td></tr>");
                    result.append("<tr><td> Make: </td><td><SPAN>").append(auto.getMake()).append("</SPAN></td></tr>");
                    result.append("<tr><td> Base Price: </td><td><SPAN id=\"basePrice\">$").append(auto.getBasePrice()).append("</SPAN></td></tr>");
                    result.append(" <input type=\"hidden\" name=\"").append(auto.getMake()).append(" ").append(auto.getName()).append("\" value=\"BasePrice($").append(auto.getBasePrice()).append(")\">");
                    HashMap<String, String[]> opset = auto.getOptionsAsStringArray();
                    Iterator<String> it = opset.keySet().iterator();

                    while (it.hasNext()) {
                        String name = it.next();
                        result.append("<tr><td>").append(name).append(":</td><td> <SELECT NAME=\"").append(name).append("\" >");
                        String[] options = opset.get(name);
                        for (String option : options) {
                            result.append("<OPTION VALUE=\"").append(option).append("\">").append(option).append("</OPTION>");
                        }
                        result.append("</SELECT></td></tr>");
                    }
                    result.append("</tbody></table><BR><INPUT TYPE=\"SUBMIT\" VALUE=\"Submit Order\"></CENTER>");

                } else {
                    result.append((String) o);

                }
            }

        } catch (Exception ex) {
            result.append(ex.getMessage());
        }
        return result.toString();
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        openConnection();
        String result = getResult(request.getParameter("modelName"));
        closeSession();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Select model options";
        String docType
                = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
                + "Transitional//EN\">\n";
        out.println(docType
                + "<HTML>\n"
                + "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n"
                + "<style>\n"
                + "table, th, td {\n"
                + "    border: 1px solid black;\n"
                + "}\n"
                + "</style>"
                + "<BODY BGCOLOR=\"#FDF5E6\">\n"
                + "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n"
                + "\n"
                + result
                + "</BODY></HTML>");
    }

}
