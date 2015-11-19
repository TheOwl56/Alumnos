<%-- 
    Document   : RenovarPreficha
    Created on : 20/10/2015, 09:49:55 AM
    Author     : Chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="JQueryClases/PAES_js.js" type="text/javascript"></script>
    </head>
    <body>
        <br>
        <div id="carrera" class="demo ui-tabs ui-widget ui-corner-all">
            <div id="pestana_carrera">
                <ul id="ul_carrera" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" role="tablist">
                <p id="li_carrera" class="ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
                <a class="ui-tabs-anchor" tabindex="-1"><b>Renovar Referencia</b></a>
                </p>
                </ul>
                <br><br><br><br>
                <div id="text_seguimiento_renovar"> 
                    <div>
                        <div id="text_renovar">
                            <br>
                            <h1>
                            Introduzca correctamente su <u>CURP</u> y de clic en el bot&oacute;n 'Preficha' para poder renovar su referencia bancaria.
                            </h1>
                        </div>
                        <br>
                        <div id="elem_recupera" >
                            <form target="_blank" name="crp" id="curpFormRenovar" action="PrefichaPDF" >
                            <center>
                            <input type="text" size="24" name="curp" id="curpRenovar" maxlength="18" onkeyup="this.value = this.value.toUpperCase()" placeholder="Introduce tu CURP"/>
                            <button id="btnCurpRenovar" type=submit value=enviar class="btn btn-default" style="background-color: #337ab7; color: white; font-family: arial">Preficha</button>
                            </center>
                            </form>
                        </div>
                    </div> 
                    <br>
                    <br>
                    <br> 
                    <br>
                    <h1>
                    <b>IMPORTANTE</b>
                    <br>
                    <br>
                    Estimado aspirante, se le recuerda que al renovar su referencia bancaria, solo contar&aacute; con 
                    1 dia h&aacute;bil para efectuar su pago, de modo que es importante que reflexione si realmente
                    efectuar&aacute; el pago.
                    </h1>
                    <br>
                    <br>
                    <br>
                </div>
        <br>
        </div>
    </body>
</html>
