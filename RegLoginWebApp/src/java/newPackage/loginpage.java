package newPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class loginpage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name=null;
            String username=null;
            String email=null;
            String password=null;
            //String[] array = new String[4];
            String usernameIn = request.getParameter("uname");
            String passwordIn = request.getParameter("pass");
            
            //out.print(usernameIn+" "+passwordIn);

            MyDB db = new MyDB();
            db.getConnection();

            ResultSet results = db.getFromDB("SELECT * FROM users WHERE username = '"+usernameIn+"' AND password = '"+passwordIn+"'");
            
            if(results != null) {
                while (results.next()) {
                    name = results.getString("name");
                    username = results.getString("username");
                    email = results.getString("email");
                    password = results.getString("password");
                }
            }
            
            if(name==null && email==null) {
                request.getRequestDispatcher("loginFailedHTML.html").forward(request, response);
            }
            
            out.print(name+" "+username+" "+email+" "+password);
            
            db.disconnectFromDB();

        } catch (SQLException ex) {
            Logger.getLogger(loginpage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
