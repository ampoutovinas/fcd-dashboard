/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.utils;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import org.omnifaces.util.Messages;

/**
 *
 * @author anmpout
 */
@ApplicationScoped
public class Utils implements Serializable {
       public static void addDetailMessage(String message) {
        addDetailMessage(message, null);
    }

    public static void addDetailMessage(String message, FacesMessage.Severity severity) {

        FacesMessage facesMessage = Messages.create("").detail(message).get();
        if (severity != null && severity != FacesMessage.SEVERITY_INFO) {
            facesMessage.setSeverity(severity);
        }
        Messages.add(null, facesMessage);
    }
    public static Double convertMetersToKm(Double meters){
        Double returnKm =0.0;
        returnKm = meters * 0.001;
        
        
    return (int)Math.round(returnKm * 100)/(double)100;
    }
    
//        private String createPathJSONString() {
//        String jsonString = "";
//        JSONA
//        for
//        
//        
//        return jsonString;
//
//    }
}
