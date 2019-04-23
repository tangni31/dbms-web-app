package com.group8.dbms;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Tool {


    public Tool() {
    }


  public static String generateRandomId(int length){
      String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+=-_";
      Random random = new Random();
      StringBuffer sb = new StringBuffer();
      for(int i=0; i<length; i++){
          int number=random.nextInt(66);
          sb.append(str.charAt(number));
      }
      return sb.toString();
  }

  public static String getDate() {
      Date date = new Date( );
      SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
      return ft.format(date);
  }

}
