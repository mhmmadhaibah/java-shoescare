/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.shoescare.model;

import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author mhmmadhaibah
 */
public class MyReport
{
    public void viewReport(String fileName, boolean isExitOnClose)
    {
        Connection conn = MyConnection.getConnection();
        
        URL fileUrl = getClass().getResource("/reports/" + fileName + ".jasper");
        URL iconImageUrl = getClass().getResource("/images/coffee_stain.png");
        
        Map params = new HashMap();
        params.put("TITTLE", "emha Shoescare");
        params.put("ICON_IMAGE", iconImageUrl.toString());
        
        try
        {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileUrl);
            JasperPrint  jasperPrint  = JasperFillManager.fillReport(jasperReport, params, conn);
            
            JasperViewer.viewReport(jasperPrint, isExitOnClose);
        }
        catch(JRException e)
        {
            e.printStackTrace();
        }
    }
}
