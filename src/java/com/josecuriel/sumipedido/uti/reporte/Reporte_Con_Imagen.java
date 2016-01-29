package com.josecuriel.sumipedido.uti.reporte;
import com.josecuriel.sumipedido.util.db.database;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 * @web http://jc-mouse.blogspot.com/
 * @author Mouse
 */
public class Reporte_Con_Imagen extends database{
    //direccion para la imagen
    private final String logotipo="/reportes/logo.png";

 public void Ver_Facturas(){
     JasperReport repor;
     JasperPrint re;

     try{
       //se carga el archivo de jasper
       URL  in=this.getClass().getResource("/reportes/factura ctc.jasper");
       repor=(JasperReport)JRLoader.loadObject(in);
       //parametros de entrada
       Map parametros = new HashMap();
       parametros.clear();
       parametros.put("logo", this.getClass().getResourceAsStream(logotipo));
       /* esto es para reportes con conexion a base de datos*/
       re = JasperFillManager.fillReport(repor,parametros,getConnection());

       //se crea el reporte con un origen de datos VACIO
       //re = JasperFillManager.fillReport(repor, parametros, new JREmptyDataSource());
       //se muestra el reporte
//       JasperViewer.viewReport(re,false);
        }catch (JRException E){

      }

    }
}
