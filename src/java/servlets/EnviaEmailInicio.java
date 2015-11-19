/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import ConexionBD.Procedimientos;
import beans.BMail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Encripta;
import modelos.GeneraAuditoria;
import modelos.Mail;
import modelos.VerificaCupo;

/**
 * Envía el primer correo de Verificación para continuar con el registro desde
 * un link
 *
 * @author ElyyzZ BaRruEtA
 */
public class EnviaEmailInicio extends HttpServlet {

    Encripta e = new Encripta();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            response.setContentType("text/html;charset=ISO-8859-1");
            request.setCharacterEncoding("UTF8");
            String correo = request.getParameter("correo");

            PrintWriter out = response.getWriter();
            Procedimientos p = new Procedimientos();
            String Url = "http://192.168.40.112:8080/MODULO_ASPIRANTE/Datos_Aspirante.jsp";
          //  String Url = "http://localhost:9090/MODULO_ASPIRANTE/Datos_Aspirante.jsp";
            //String home = "http://hermes.ittoluca.edu.mx:8100/MODULO_ASPIRANTE/";
            String home = "http://192.168.40.112:8080/MODULO_ASPIRANTE/";
            String mailAdm = "creyesman@hotmail.com";
            String CorreoEnc = e.encryptURL(correo);
            String UrlEnc = e.encryptURL(Url);
            String liga = Url + "?correo=" + CorreoEnc;

            //Obtenemos la cadena compuesta de la verificacion del correo 
            //  y la transformamos a vector
            String[] cadExiste = p.GetValidaCorreo(correo, UrlEnc).split("&");
            int existe = Integer.parseInt(cadExiste[0]);
            String msg = cadExiste[1].trim();
            System.out.println("*******************************************************-----------------------");
            System.out.println("Resultado de Validacion de correo: " + correo + "  Existe ? :" + existe);
            switch (existe) {

                case 0:
                    BMail beanMail = new BMail();
                    BMail beanMailAdm = new BMail();
                    Mail m = new Mail();
                    VerificaCupo c = new VerificaCupo();
                    String[] listCupo = c.getCupo().split("&");
                    int count = Integer.parseInt(listCupo[0]);

                    //Verificar la cantidad de prefichas antes de enviar cualquier correo
                    // envia correo
                    if (count == 50) {
                        beanMailAdm.setCuerpo("<b><font size=4 face=\"arialblack\" > Estimado administrador."
                                + "<br><br>"
                                + "Por este medio se le informa que la cantidad de prefichas está próxima a agotarse[" + count + "], le pedimos tomar medidas adecuadas."
                                + "</font></b>"
                        );
                        int retAdm = m.sendMail(beanMailAdm, mailAdm, true, "Administrativo Tecnológico de Toluca: Aviso Prefichas");
                        switch (retAdm) {
                            case 0:
                                System.out.println("Correo enviado al administrador.");
                                break;
                            default:
                                GeneraAuditoria o = new GeneraAuditoria();
                                o.crea_archivo("654", "com.sun.mail.util.MailConnectException: Couldn't connect to host, port: mail.ittoluca.edu.mx, 25; timeout -1;", "No se pudo enviar el correo");
                                break;
                        }
                    }
                    beanMail.setCuerpo("<b><font size=4 face=\"arialblack\" > Durante el proceso de registro recibirá los  siguientes  correos, por favor permanezca al pendiente: \n"
                            + "<br><br>"
                            + " 1.-Correo  de generación de preficha, que se le enviará  al concluir el registro de sus datos. \n"
                            + "<br>"
                            + "2.-Correo de liberación de pago, en un periodo máximo de 3 a 4 días hábiles después de haber realizado el pago bancario. \n"
                            + "<br>"
                            + "3.-Correo de alta en Ceneval, minutos después del correo anterior. Es importante que reciba estos dos últimos correos. \n "
                            + "<br>"
                            + "4.-Correo de generación de ficha, que se enviará al concluir el proceso de registro, esto es después de haber entregado sus \n"
                            + "papeles en el departamento de servicios escolares edif. X. \n "
                            + "<br><br>"
                            + "En caso de no recibir alguno de ellos, comuníquese con nostros desde:  "
                            + "<br>"
                            + home + " en el apartado de contacto.</font></b>"
                            + "<br><br>"
                            + "<font size=4 face=\"arialblack\" >Después de su registro  se hace de su conocimiento  que la preficha que usted  deberá  generar cuenta  con una fecha de expiración (misma que podrá  visualizar en la parte superior del número de la referencia bancaria que aparece en  su preficha),"
                            + "  misma que el aspirante podrá renovar en caso de que  esta  expire. <br>"
                            + "Para la renovación de su preficha usted deberá entrar  de (parámetro de la fecha de inicio en la BD para el periodo de renovación) a (parámetro de la fecha de inicio en la BD para el periodo de renovación) a la liga de\n" 
                            +"<a href=" + home +" target=\"_blank\">Aspirantes</a>  en el apartado de “Recuperar Preficha"
                            + "<br><br>"
                            + "Importante: Para realizar cualquier cambio en los datos proporcionados deberá solicitarlo en ventanilla al"
                            + " momento de entregar su documentación en el departamento de Servicios Escolares Edif. X. Así mismo deberá recordar que no habrá cambios en  las opciones de carrera."
                            + "Tome en cuenta que es responsabilidad del aspirante cumplir con todas etapas del proceso\n"
                            + "para finalizar su registro de lo contrario su solicitud será rechazada."
                            + "<br><br>"
                            + "Para continuar con su registro por favor haga click en el siguiente enlace\n"
                            + ". "
                            + "<a href=" + liga + " >  Registro Aspirante </a></font>."
                    );

                    System.out.println("Enviando correo a: " + correo);
                    int ret = m.sendMail(beanMail, correo, true, "Aspirante  Tecnológico de Toluca: Liga Para Registro.");
                    switch (ret) {

                        case 0:
                            out.print("Se ha enviado un enlace a su correo para continuar con su registro. Si no  logra  visualizar el correo en su bandeja  de  entrada no  olvide consultar  la  bandeja de correo no deseado.");
                            break;
                        case -1:
                            out.print("Su dirección de correo  electrónico es inválida. Vuelva a Intentarlo. Revise que este  bien escrita antes de dar clic en enviar");
                            break;
                        case -2:
                            msg = "No se  pudo enviar el correo. Por favor vuelva a intentarlo. Revise  que este escrito correctamente";
                            out.print(msg);
                            GeneraAuditoria ob = new GeneraAuditoria();
                            ob.crea_archivo("1961", "com.sun.mail.util.MailConnectException: Couldn't connect to host, port: mail.ittoluca.edu.mx, 25; timeout -1;", msg);
                            break;
                        default:
                            msg = "No se  pudo enviar el correo. Por favor vuelva a intentarlo. Revise  que este escrito correctamente";
                            out.print(msg);
                            GeneraAuditoria o = new GeneraAuditoria();
                            o.crea_archivo("654", "com.sun.mail.util.MailConnectException: Couldn't connect to host, port: mail.ittoluca.edu.mx, 25; timeout -1;", msg);
                            break;
                    }
                    break;

                case 1:
                    // retorna  que ya existe correo
                    //String msg = "El correo  ya  fue registrado en esta convocatoria";
                    out.print(msg);
                    GeneraAuditoria ob = new GeneraAuditoria();
                    ob.crea_archivo("1", "AUDITORIA, El aspirante con correo: " + correo + " ya se registro en esta convocatoria", msg);
                    System.out.println(" AUDITORIA, El aspirante con correo: " + correo + " ya se registro en esta convocatoria");
                    break;

                case 2:
                    out.print(msg);
                    GeneraAuditoria err2 = new GeneraAuditoria();
                    err2.crea_archivo("2", "AUDITORIA, Surgio un error al generar la liga para el aspirante con correo: " + correo, msg);
                    break;
                case 3:
                    BMail beanNoFichas = new BMail();

                    beanNoFichas.setCuerpo("<b><font size=4 face=\"arialblack\" > Durante el proceso de registro recibirá los  siguientes  correos, por favor permanezca al pendiente: \n"
                            + "<br><br>"
                            + " 1.-Correo  de generación de preficha, que se le enviará  al concluir el registro de sus datos. \n"
                            + "<br>"
                            + "2.-Correo de liberación de pago, en un periodo máximo de 3 a 4 días hábiles después de haber realizado el pago bancario. \n"
                            + "<br>"
                            + "3.-Correo de alta en Ceneval, minutos después del correo anterior. Es importante que reciba estos dos últimos correos. \n "
                            + "<br>"
                            + "4.-Correo de generación de ficha, que se enviará al concluir el proceso de registro, esto es después de haber entregado sus \n"
                            + "papeles en el departamento de servicios escolares edif. X. \n "
                            + "<br><br>"
                            + "En caso de no recibir alguno de ellos, comuníquese con nostros desde:  "
                            + "<br>"
                            + home + " en el apartado de contacto.</font></b>"
                            + "<br><br>"
                            + "Después de su registro  se hace de su conocimiento  que la preficha que usted  deberá  generar cuenta  con una fecha de expiración (misma que podrá  visualizar en la parte superior del número de la referencia bancaria que aparece en  su preficha),"
                            + "  misma que el aspirante podrá renovar en caso de que  esta  expire."
                            + "Para la renovación de su preficha usted deberá entrar  de (parámetro de la fecha de inicio en la BD para el periodo de renovación) a (parámetro de la fecha de inicio en la BD para el periodo de renovación) a la liga de\n" 
                            +"<a href=" + home +" target=\"_blank\">Aspirantes</a>  en el apartado de “Recuperar Preficha"
                            + "<br><br>"
                            + "Importante: Para realizar cualquier cambio en los datos proporcionados deberá solicitarlo en ventanilla al"
                            + " momento de entregar su documentación en el departamento de Servicios Escolares Edif. X. Así mismo deberá recordar que no habrá cambios en  las opciones de carrera."
                            + "Tome en cuenta que es responsabilidad del aspirante cumplir con todas etapas del proceso\n"
                            + "para finalizar su registro de lo contrario su solicitud será rechazada. <br><br>"
                            + "Para continuar con su registro por favor haga click en el siguiente enlace\n"
                            + ". "
                            + "<a href=" + liga + " >  Registro Aspirante </a></font>."
                    );

                    Mail m2 = new Mail();
                    int ret2 = m2.sendMail(beanNoFichas, correo, true, "Aspirante  Tecnológico de Toluca: Liga Para Registro.");
                    switch (ret2) {

                        case 0:
                            out.print("Se ha enviado un enlace a su correo para continuar con su registro. Si no  logra  visualizar el correo en su bandeja  de  entrada no  olvide consultar  la  bandeja de correo no deseado.");
                            break;
                        case -1:
                            out.print("Su dirección de correo  electrónico es inválida. Vuelva a Intentarlo. Revise que este  bien escrita antes de dar clic en enviar");
                            break;
                        case -2:
                            msg = "No se  pudo enviar el correo. Por favor vuelva a intentarlo. Revise  que este escrito correctamente";
                            out.print(msg);
                            GeneraAuditoria ob2 = new GeneraAuditoria();
                            ob2.crea_archivo("1961", "com.sun.mail.util.MailConnectException: Couldn't connect to host, port: mail.ittoluca.edu.mx, 25; timeout -1;", msg);
                            break;
                        default:
                            msg = "No se  pudo enviar el correo. Por favor vuelva a intentarlo. Revise  que este escrito correctamente";
                            out.print(msg);
                            GeneraAuditoria o = new GeneraAuditoria();
                            o.crea_archivo("654", "com.sun.mail.util.MailConnectException: Couldn't connect to host, port: mail.ittoluca.edu.mx, 25; timeout -1;", msg);
                            break;
                    }
                    break;
                case 6:
                    out.print(msg);
                    GeneraAuditoria err6 = new GeneraAuditoria();
                    err6.crea_archivo("6", "Surgio un error al revisar las fichas", msg);
                    break;
                default:
                    // error  inesperado al crear  la liga
                    //msg = "Ha ocurrido un error. Por favor  vuelve intentarlo.";
                    out.print(msg);
                    System.out.println("AUDITORIA   Error al crear la liga");
                    GeneraAuditoria o = new GeneraAuditoria();
                    o.crea_archivo("---", "AUDITORIA   Error al crear la liga", msg);
                    break;

            }
        } catch (SQLException ex) {
            Logger.getLogger(EnviaEmailInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>}
}
