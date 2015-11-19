package servlets;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ConexionBD.CreaPrefichaPDF;
//Recupera  la  preficha  desde el apartado de Recuperar  preficha en el inicio
public class PrefichaPDF extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext d = getServletContext();
        response.setContentType("application/pdf");
        String curp = new String(request.getParameter("curp").getBytes("ISO-8859-1"), "UTF-8");
        CreaPrefichaPDF preficha = new CreaPrefichaPDF();
//        System.out.println("curp"+curp);
        preficha.ElaboraPreficha(curp, response, d);
    }
}
