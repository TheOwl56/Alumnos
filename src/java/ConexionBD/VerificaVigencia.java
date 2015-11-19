/*
 * Método para validar el periodo de registro de aspirantes
 */
package ConexionBD;

import beans.FechaRenovar;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.GeneraAuditoria;
import oracle.jdbc.OracleTypes;

public class VerificaVigencia {

    public static void main(String[] args) throws ParseException {
        VerificaVigencia cf = new VerificaVigencia();
        System.out.println(cf.period2());
    }

    
    /**
     * Metodo que retorna la validacion del periodo y el año para la convocatoria
     * @return cadena compuesta con el codigo de error (posicion 0)
     *         y por la descripcion del error (posicion 1)
     */
    public String period2() {

        String val_per = "0" + "&" + "Convocatoria valida";

        try {
            String user = "ASPIRANTE_ITT";
            String pass = "ASP1R4NT3";
            String resultado_error;
            String descrip_error;
            String fi = "";
            String ff = "";
            String fh = "";
            CallableStatement cst = null;
            Conexion con = new Conexion(user, pass);
            cst = con.getConnection().prepareCall("{call FICHAS.PQ_CHECK_ADMIN_1.CHECK_VIGENCIA_CONVOC_SP(?,?,?,?,?)}");
            cst.registerOutParameter("paFechaInicio", OracleTypes.VARCHAR);
            cst.registerOutParameter("paFechaFin", OracleTypes.VARCHAR);
            cst.registerOutParameter("paFechaActual", OracleTypes.VARCHAR);
            cst.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cst.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
            cst.execute();
            resultado_error = cst.getString("paCodigoError");
            descrip_error = cst.getString("paMjeDescError");
            System.out.println("Codigo error de CHECK_VIGENCIA_CONVOC : " + resultado_error);

            if ("0".equals(resultado_error)) {
                fi = cst.getString("paFechaInicio");
                //System.out.println("inicio: " + fi);
                ff = cst.getString("paFechaFin");
                //System.out.println("final:  " + ff);
                fh = cst.getString("paFechaActual");
                //System.out.println("hoy:    " + fh);

                //cambios para pruebas
                //fi = "10/08/2015";
                //ff = "/09/2015";
                //fh = "10/08/2015";

                if (fi == null || ff == null || ff == null) {
                    val_per = "1 & No hay fechas disponibles para la convocatoria";
                } else {
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    Date fechah = formateador.parse(fh);
                    Date fechai = formateador.parse(fi);
                    Date fechaf = formateador.parse(ff);

                    if (fechah.equals(fechai) || fechah.equals(fechaf)) {
//                    System.out.println("es valido");
                        val_per = "0 & Convocatoria valida";
                    }
                    if (fechah.before(fechaf) && fechah.after(fechai)) {
//                    System.out.println("Es válido");
                        val_per = "0 & Convocatoria valida";
                    }if(fechah.after(fechaf) || fechah.before(fechai)){
                        val_per = "1 & Aún no se llega a un periodo valido de convocatoria";
                    }
                }
                cst.close();
                con.CerraConexion();
                return val_per;
            }
            else {
                GeneraAuditoria audit = new GeneraAuditoria();
                    System.out.println("Metodo Periodo2"
                            + "\nOcurrió un error"
                            + "\n       Con numero de error: " + resultado_error
                            + "\n       Descripcion de error: " + descrip_error);
                    audit.crea_archivo(resultado_error, descrip_error, "Error al momento de obtener la convocatoria para el periodo y año actual");
                    cst.close();
                    con.CerraConexion();
                    return resultado_error + "&" + descrip_error;
            }

        } catch (SQLException ex) {
            Logger.getLogger(VerificaVigencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerificaVigencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(VerificaVigencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return val_per;
    }
    
    /**
     * 
     * @return: Arreglo de cadenas de fechas de inicio y fin del periodo de renovacion, codigo de error y mensaje del error
     * provenientes de la BD
     * @throws java.sql.SQLException
     */
    public ArrayList<FechaRenovar> getPeriodoRenovacion() throws SQLException{
        ArrayList<FechaRenovar>fechaList = new ArrayList();
        
        try{
            String user = "ASPIRANTE_ITT";
            String pass = "ASP1R4NT3";
            String resultado_error;
            String descrip_error;
            String fi = "";
            String ff = "";
            String fh = "";
            CallableStatement cs = null;
            Conexion con = new Conexion(user, pass);
            cs = con.getConnection().prepareCall("{call FICHAS.PQ_CHECK_ASPIRANTE_2.CHECK_PERIODO_RENOVAR_SP(?,?,?,?)}");
            cs.registerOutParameter("paFechaIniRenov", oracle.jdbc.driver.OracleTypes.VARCHAR);
            cs.registerOutParameter("paFechaFinRenov", oracle.jdbc.driver.OracleTypes.VARCHAR);
            cs.registerOutParameter("paCodigoError", oracle.jdbc.driver.OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", oracle.jdbc.driver.OracleTypes.VARCHAR);
            cs.execute();
            FechaRenovar fecha = new FechaRenovar();
            
            fecha.setFechaIni(cs.getString("paFechaIniRenov"));
            fecha.setFechaFin(cs.getString("paFechaFinRenov"));
            fecha.setCodError(cs.getInt("paCodigoError"));
            fecha.setDescError(cs.getString("paMjeDescError"));
            
            fechaList.add(fecha);
            //System.out.println("Info de la renovacion" + "\nCodigo: " + fecha.getCodError() + "\nDescripcion " + fecha.getDescError());
            cs.close(); 
            con.CerraConexion();
        }catch(SQLException | ClassNotFoundException o){
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, o);
           
        }
        return fechaList;
    }
}
