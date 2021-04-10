package com.bootcamp.finalProject.utils;

public class OrderNumberCMUtil {


    public static String getNumberCE(String orderNumberCM){
        return splitOrderNumber(orderNumberCM)[0];
    }

    public static String getNumberOR(String orderNumberCM){
        return splitOrderNumber(orderNumberCM)[1];
    }

    public static String[] splitOrderNumber(String orderNumberCM){

        return orderNumberCM.split("-");

    }
}
