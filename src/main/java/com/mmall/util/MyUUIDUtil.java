package com.mmall.util;

import java.util.UUID;

public class MyUUIDUtil {
     public static long getUUIDLong(){
         long result =0;
         result=System.currentTimeMillis()*128+ 127&UUID.randomUUID().hashCode();
         return result;
     }
}
