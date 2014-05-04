///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.diplom.util;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
///**
// *
// * @author valik
// */
//public class ApplicationProperties{
//    private static Properties properties;
//    public static void init(String path){
//        try {
//            properties = new Properties();
//            properties.load(new FileInputStream(new File(path)));
//        } catch (IOException ex) {
//            throw new RuntimeException("Properties file not found");
//        }
//    }
//    
//    public static Long getLong(String propertyName){
//         return Long.parseLong(properties.getProperty(propertyName));
//     }
//    
//    public static Integer getInt(String propertyName){
//        return Integer.parseInt(properties.getProperty(propertyName));
//    }
//     
//     public static String getString(String propertyName){
//         return properties.getProperty(propertyName);
//     }
//}
