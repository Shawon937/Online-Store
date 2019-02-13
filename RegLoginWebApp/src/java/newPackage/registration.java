
package newPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class registration extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String name = request.getParameter("name");
            String username = request.getParameter("uname");
            String email = request.getParameter("email");
            String password = request.getParameter("pass");
            String confPassword = request.getParameter("confPass");

            MyDB db = new MyDB();
            db.getConnection();

            db.insertDataToDB("INSERT INTO users(name,username,email,password) VALUES('"+name+"', '" + username + "','"+email+"' , '" + password + "')");
            //System.out.println("[system] data insertion : " + dataSaved);
            db.disconnectFromDB();
            request.getRequestDispatcher("completeRegHTML.html").forward(request, response);
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
