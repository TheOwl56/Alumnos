<%-- 
    Document   : InicioEnviaCorreo
    Created on : 12/11/2014, 11:13:17 AM
    Author     : Desarrollo de sistem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="../../JQueryClases/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="../../JQueryClases/bootstrap.min.js" type="text/javascript"></script>
        <script src="Estilos/css/bootstrap.min.css.css" type="text/javascript"></script>
        <link rel="stylesheet" href="Estilos/DivCargando.css" media="all" type="text/css">
        <script src="../../JQueryClases/PAES_js.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="divmarcoEnvCorreo" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <div class="modal-content">
                    <div class="modal-header">
                        <button id="btnXIngresCorreo" type="button" class="close" >&times;</button>
                        <h4>Ingresa tu correo</h4>
                    </div>
                    <div class="modal-body">
                        
                        <div id="contenedor_inCorreo">
                            <fieldset>
                                <br>
                                <label id="etq_CorreoI">Ingrese su Correo Electrónico: </label>
                                <input id="InCorreoE" placeholder="ejemplo@smtp.com" type="text">
                                <input id="btnCorreoAcep" type="button" class="btn btn-info btn-default" value="Enviar" style="background-color: #337ab7">

                                <center>
                                    <label id="labelVerifica">Por favor   verifíque  que esté escrito correctamente  antes de enviar.</label>
                                </center>                
                                <div id="cargandoDivAnimacion">
                                    <center>
                                        <img src="Imagenes/loading.gif"/> 
                                    </center>
                                </div>
                            </fieldset>                    
                        </div>
                        
                    </div>
                    <div class="modal-footer">
                            <button id="btnCorreoCancel" class="btn btn-default">Cancelar</button>
                        </div>
                </div>

            </div> 
        </div>
    </body>
</html>
