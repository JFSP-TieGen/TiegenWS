/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OLD_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 *
 * @author keerthanathangaraju
 */
@WebServlet("/listModel")
public class ListModelServlet extends ServletUtilities {

    public String getResult() {
        StringBuilder result;
        result = new StringBuilder();
        sendOutput("getModelList");
        try {
            String modelNames = (String) in.readObject();
            StringTokenizer st = new StringTokenizer(modelNames, "\n");
            while (st.hasMoreTokens()) {
                String modelName = st.nextToken();
                result.append("<LI><A HREF=\"getAutomobile?modelName=").append(modelName).append("\">").append(modelName).append("</A></LI>");
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
        String result = getResult();
        closeSession();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "List of models available";
        String docType
                = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
                + "Transitional//EN\">\n";
        out.println(docType
                + "<HTML>\n"
                + "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n"
                + "<BODY BGCOLOR=\"#FDF5E6\">\n"
                + "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n"
                + "<CENTER><UL>\n"
                + result
                + "</UL></CENTER></BODY></HTML>");
    }

}
