
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="JQueryClases/PAES_js.js" type="text/javascript"></script>
        <script src="JQueryClases/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="JQueryClases/bootstrap.min.js" type="text/javascript"></script>
        <script src="JQueryClases/jquery.validate.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $(".tooltip-examples a").tooltip({
                    placement: 'top'
                });
            });
        </script>
    </head>
    <body style="background-color: #EAEAEA">
        <br>
        <!--llenar aqui-->
        <div id="carrera" class="demo ui-tabs ui-widget ui-corner-all tooltip-examples">
            <div id="pestana_carrera">
                <ul id="ul_carrera" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" role="tablist">
                    <p id="li_carrera" class="ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
                        <a class="ui-tabs-anchor" role="presentation" tabindex="-1"><b>Registro del Aspirante</b></a>
                    </p>
                </ul>
                <label id="centrar_inf"><h4>Le damos la más cordial bienvenida  a la página de registro para aspirantes del Instituto Tecnológico de Toluca, en esta sección podrá realizar su registro como aspirante a la convocatoria , para ello tenga a la mano los siguientes elementos que le facilitarán el llenado de los  formularios:
                    </h4></label>
                <ul id="sangria">
                    <li>Clave CURP<a href="http://consultas.curp.gob.mx/CurpSP/"target="_blank"><div id="aqui">consultar aquí</div></a></li>
                    <li>Clave de la escuela de procedencia<a href="http://www.snie.sep.gob.mx/SNIESC/" target="_blank"><div id="aqui_escuela">consultar aquí</div></a></li>
                    <li><div id="link_toolt"><a data-toggle="tooltip" data-original-title="Federal, Estatal, Privada"  >Tipo de la escuela de procedencia</a></div></li>
                    <li>Tipo de sangre</li>
                    <li>Acta de nacimiento</li>
                </ul>
                <label id="centrar">
                    <h4>
                        Es importante recordarle que la cuenta de correo que ingrese deberá ser verídica y de uso personal debido a que durante la convocatoria  se le enviarán notificaciones e indicaciones relativas al proceso que está a punto de iniciar.<br>
                        <br>
                        <br>
                        <label>Para más información sobre la convocatoria consulte <a href="http://www.ittoluca.edu.mx/" target="_blank">aquí.</a></label><br>
                        <label> Para más información sobre el proceso de registro consulte <a href="http://192.168.40.105:8080/MODULO_ASPIRANTE/manualgenerar" target="_blank"> aquí.</a></label>
                    </h4>
                </label>
                <div>
                    <center>
                        <h2>Importante.</h2> 
                    </center>
                    <div id="Contenido_atencion">
                        <div id="import">
                            <label style="text-align: justify">
                                <li>Es responsabilidad del aspirante  que los datos de su registro sean verídicos. </li>
                                <li>Al iniciar su registro acepta que  ha le&iacute;do la informaci&oacute;n anterior  y  que est&aacute; conforme con sus términos.</li>
                                <li>Para cualquier duda o claración  diríjase al apartado de "Contacto".</li>
                                <li>Tome en cuenta que es responsabilidad del aspirante cumplir con todas las etapas de este proceso
                                    para finalizar su registro de lo contrario su solicitud ser&aacute; rechazada.</li>
                                <li>Es importante considerar que una vez realizado su registro tendr&aacute; dos d&iacute;as h&aacute;biles para realizar su pago.</li>
                                <li>
                                    En caso de que su preficha expire usted podr&aacute; renovarla en el apartado de "Renovar Referencia" durante el 
                                    <label id="dateIniRenovIni"></label> al <label id="dateFinRenovIni"></label> contemplando que:
                                    <ol id="sangria2">
                                        <li>Al realizar su registro y no realizar el pago oportuno de su preficha, su lugar en el Instituto no 
                                            ser&aacute; contemplado.</li>
                                        <li>La renovaci&oacute;n de Prefichas est&aacute; sujeta a la disponibilidad de cupo en el Instituto.</li>
                                        <li>El aspirante podr&aacute; renovar su preficha solamente dos veces. </li>
                                    </ol>
                                </li>
                                <li id="pagoAviso">Es indispensable que el aspirante reflexione si realmente efectuar&aacute; el pago de su preficha dentro de los dos días h&aacute;biles correspondientes.</li>
                            </label>
                        </div>
                    </div>    
                </div>
            </div>
            <br>
            <label>Por favor para iniciar con el proceso de registro seleccione  "He leído esta información" y 
                a continuación de clic en el botón de "Aceptar".</label>
            <br>
            <br>
            <div id="Contenedor_radioButton">
                <label>
                    <h5><input id="comprobar" type="checkbox">He le&iacute;do esta informaci&oacute;n. <br></h5>
                </label>
                <br>
                <a><label><input id="heleido" type="button" class="btn btn-info" value="Aceptar" style="display: none;background-color: #337ab7"></label></a>
            </div>
        </div>
    </body>
</html>