/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.utils;

import com.anmpout.fcddashboard.model.Point;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import org.json.JSONArray;
import org.json.JSONObject;
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

    public static String createPathJSONString(List<Point> points) {
        String jsonString = "";
         JSONObject jSONObject =  new JSONObject();
          JSONArray pointArray = new JSONArray();
        for (Point point:points){
            JSONArray tmpArray = new JSONArray();
            tmpArray.put(point.getLatitude());
            tmpArray.put(point.getLongitude());
            pointArray.put(tmpArray);
        }
        jSONObject.put("points", pointArray);
        
        return jSONObject.toString();
    }
        public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}
