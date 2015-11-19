package servlets;

import ConexionBD.Procedimientos;
import beans.DomicilioAspirante;
import beans.EscProcedenciaAsp;
import beans.PersonalesAspirante;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.llenarBD;

/**
 * 1.-Setea beans de Datos personales de Aspirante y valida curp y correo 3.-
 * función botón regresar
 *
 * @author ElyyzZ BaRruEtA
 */
public class DatosPersonalesAsp extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Procedimientos p = new Procedimientos();
    llenarBD b = new llenarBD();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        request.setCharacterEncoding("UTF8");
        PersonalesAspirante aspirante = new PersonalesAspirante();
        DomicilioAspirante AspDomicilio = new DomicilioAspirante();
        EscProcedenciaAsp AspEscuela = new EscProcedenciaAsp();
        PrintWriter out = response.getWriter();
        String opc = request.getParameter("opcion");

        switch (opc) {
            case "1":
                String op1 = request.getParameter("op1carre");
                String op2 = request.getParameter("op2carre");
                String op3 = request.getParameter("op3carre");
//                System.out.println("Entra  servlet");
                boolean continua = false;
                String datos = request.getParameter("TodNulos");
                String telcel = request.getParameter("telefonopersonal");
                String telfijo = request.getParameter("telefonofijo");
                String zipCode = request.getParameter("zipCode");

                int tel1 = -1;
                int tel2 = -1;
                try {
                    tel2 = Integer.parseInt(telcel.trim());
                    
//                    System.out.println("cel : " + tel2);
                } catch (NumberFormatException e) {
                    out.print("5");
//                    System.out.println("No  pudo  convertir  cel");
                }
                try {
                    tel1 = Integer.parseInt(telfijo.trim());
//                    System.out.println("fijo : " + tel1);
                } catch (NumberFormatException e) {
                    out.print("5");
//                    System.out.println("no  pudo  convertir fijo");
                }
                try{
                    Integer.parseInt(zipCode);
                }catch(NumberFormatException e){
                    out.print("7");
                }

                if (tel1 == 0 || tel2 == 0) {
                    out.print("5");
                } else if (op1.equals(op2) || op1.equals(op3) || op3.equals(op2)) {
                    out.print("6");
                } else {
                    ArrayList<String> listaAsp = b.Formatea(datos);
                    SetearBean(listaAsp, aspirante, AspDomicilio, AspEscuela);
                    String CurpAsp = aspirante.getCurp();
                    String correoAsp = aspirante.getCorreo();
                    String fechaNac = aspirante.getFechaNac();
                    char[] caract = new char[CurpAsp.length()];
                    for (int i = 0; i < CurpAsp.length(); i++) {
                        caract[i] = CurpAsp.charAt(i);
                    }
                    String fechaNacimiento = "" + caract[8] + caract[9] + "/" + caract[6] + caract[7] + "/19" + caract[4] + caract[5];
                    if (!fechaNac.equals(fechaNacimiento)) {
                        out.print("3");
                    } else {
                        int existeCurp = p.GetValidaCurp(CurpAsp, correoAsp);
                        if (existeCurp == 0) {
                            out.print("0");
                        }
                        if (existeCurp == -1) {
                            out.print("-1");
                        }
                        if (existeCurp > 0) {
                            out.print(String.valueOf(existeCurp));
                        }
                    }
                    request.getSession().setAttribute("aspirante", aspirante);
                    request.getSession().setAttribute("AspDomicilio", AspDomicilio);
                    request.getSession().setAttribute("AspEscuela", AspEscuela);
                }

                break;

            case "2":
                //clic  en  botón  aceptar 
                request.getSession().removeAttribute("aspirante");
                request.getSession().removeAttribute("AspDomicilio");
                request.getSession().removeAttribute("AspEscuela");
                request.getSession().removeAttribute("AspSocioecono");
                request.getSession().removeAttribute("contacto");
                break;
            case "3":

                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void SetearBean(ArrayList<String> listaAsp, PersonalesAspirante aspirante, DomicilioAspirante AspDomicilio, EscProcedenciaAsp AspEscuela) {
        aspirante.setCurp(listaAsp.get(0));
        aspirante.setNombre(listaAsp.get(1));
        aspirante.setAppat(listaAsp.get(2));
        aspirante.setApmat(listaAsp.get(3));
        aspirante.setFechaNac(listaAsp.get(4));
        aspirante.setPaisNac(listaAsp.get(5));
        aspirante.setEdoNac(listaAsp.get(6));
        aspirante.setMpioNac(listaAsp.get(7));
        aspirante.setLocNac(listaAsp.get(8));
        aspirante.setSexo(listaAsp.get(9).charAt(0));
        aspirante.setEdoCivil(listaAsp.get(10));
        aspirante.setTipoSangre(listaAsp.get(11));
        aspirante.setCapacidadDif(listaAsp.get(12));
        aspirante.setCorreo(listaAsp.get(13));
        //Domicilio de  Aspirante
        AspDomicilio.setEstadoVive(listaAsp.get(14).trim());
        AspDomicilio.setMunicipioVive(listaAsp.get(15).trim());
        AspDomicilio.setLocalidadVive(listaAsp.get(16).trim());
        AspDomicilio.setColoniaVive(listaAsp.get(17));
        AspDomicilio.setCalleVive(listaAsp.get(18));
        AspDomicilio.setNumExt(listaAsp.get(19).trim());
        if ("null".equals(listaAsp.get(20))) {
            AspDomicilio.setNumInt("");
        } else {
            AspDomicilio.setNumInt(listaAsp.get(20).trim());
        }
        AspDomicilio.setCodPostal(listaAsp.get(21).trim());
        AspDomicilio.setTelCelular(listaAsp.get(22).trim());
        AspDomicilio.setTelFijo(listaAsp.get(23).trim());
        //Datos de  escuela de procedencia
        AspEscuela.setTipoEsc(listaAsp.get(26));
        if (!"null".equals(listaAsp.get(28))) {
            AspEscuela.setEscuela(listaAsp.get(28));
        } else {
            AspEscuela.setEscuela(listaAsp.get(27));
        }
        AspEscuela.setClaveEsc(listaAsp.get(29).trim());
        AspEscuela.setMesInicio(listaAsp.get(30).trim());
        AspEscuela.setAnioInicio(listaAsp.get(31).trim());
        AspEscuela.setMesFin(listaAsp.get(32).trim());
        AspEscuela.setAnioFin(listaAsp.get(33).trim());
        AspEscuela.setPromedio(listaAsp.get(34).trim());
        aspirante.setCarrOp1(Integer.parseInt(listaAsp.get(35).trim()));
        aspirante.setCarrOp2(Integer.parseInt(listaAsp.get(36).trim()));
        aspirante.setCarrOp3(Integer.parseInt(listaAsp.get(37).trim()));
        aspirante.setCurso(listaAsp.get(38).trim());

    }

}
