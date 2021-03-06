package com.tablaoutviewpagerdemo.a1111.demoxiebo.Power;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.R.attr.name;

/**
 * Created by a1111 on 17/11/10.
 */

public class PowerMonitorId {
    @SerializedName("NAME")
    private String powerMonitorName;
    @SerializedName("LINE_INDEX")
    private String getPowerMonitorId;
    @SerializedName("GD_INDEX")
    private String getCompany;

    public String getGetCompany() {
        return getCompany;
    }

    public void setGetCompany(String getCompany) {
        this.getCompany = getCompany;
    }

    public String getPowerMonitorName() {
        return powerMonitorName;
    }

    public void setPowerMonitorName(String powerMonitorName) {
        this.powerMonitorName = powerMonitorName;
    }

    public String getGetPowerMonitorId() {
        return getPowerMonitorId;
    }

    public void setGetPowerMonitorId(String getPowerMonitorId) {
        this.getPowerMonitorId = getPowerMonitorId;
    }
    public static String[] getPowerMonitorListByCompany(String gdName){
        String[] str = new String[]{""};
        ArrayList<String> arrayList=new ArrayList<String>();
        if(CommonPowerList.powerMonitorIdArrayList.size()>0){
            for(int i=0;i<CommonPowerList.powerMonitorIdArrayList.size();i++){
                if(CommonPowerList.powerMonitorIdArrayList.get(i).getGetCompany().equalsIgnoreCase(gdName)){
                    arrayList.add(CommonPowerList.powerMonitorIdArrayList.get(i).getPowerMonitorName());
                }
            }
            return arrayList.toArray(str);
        }
        return arrayList.toArray(str);
    }
    public static String getPowerMonitorId(String name){
        if(CommonPowerList.powerMonitorIdArrayList.size()>0){
         for(int i=0;i<CommonPowerList.powerMonitorIdArrayList.size();i++){
             if(CommonPowerList.powerMonitorIdArrayList.get(i).getPowerMonitorName().equalsIgnoreCase(name)){
                 return CommonPowerList.powerMonitorIdArrayList.get(i).getGetPowerMonitorId();
             }
         }
         return "";
        }
        return "";
    }
}
