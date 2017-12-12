/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishextreme;

import java.util.ArrayList;

/**
 *
 * @author Joaquin
 */
public class DataDump {
    private static int size = 0;
    private static String[][] data = new String[size][4];
    
    
    public static synchronized String getData(int index, int index2){
        return data[index][index2];
    }
    
    public static synchronized void setData(int index, int index2, String val){
         data[index][index2] = val;
    }
    
    public static int getSize(){
        return size;
    }
    
    public static String test(){
        return "This works.";
    }
}
