package com.supermercado.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import javax.swing.JOptionPane;

public class ReportManager {

    public static void lanzarInforme(String nombreReporte, Map<String, Object> parametros, Connection conn) {
        try {
            // 1. Cargamos el archivo .jasper desde los recursos de Maven
            InputStream reporteStream = ReportManager.class.getResourceAsStream("/informes/" + nombreReporte);
            
            if (reporteStream == null) {
                JOptionPane.showMessageDialog(null, "No se encontró el archivo: " + nombreReporte);
                return;
            }

            // 2. Llenamos el reporte con los datos de la DB
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporteStream, parametros, conn);

            // 3. Abrimos el visor de Jasper
            JasperViewer viewer = new JasperViewer(jasperPrint, false); // false para que no cierre toda la app al cerrar el informe
            viewer.setTitle("Informe SUP/JACHI - " + nombreReporte);
            viewer.setVisible(true);

        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el informe: " + e.getMessage());
            e.printStackTrace();
        }
    }
}