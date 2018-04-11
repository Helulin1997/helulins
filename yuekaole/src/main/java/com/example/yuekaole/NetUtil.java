package com.example.yuekaole;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * author:Created by WangZhiQiang on 2018/4/7.
 */

class NetUtil {

     public  static   String   getNetJson(String  urlString){
         try {
             URL   url=new  URL(urlString);
             HttpURLConnection   connection=(HttpURLConnection) url.openConnection();
             int responseCode = connection.getResponseCode();
             if (responseCode==200){
                 InputStream inputStream = connection.getInputStream();
                 BufferedReader   bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                 StringBuilder    stringBuilder=new StringBuilder();
                 String  temp="";
                 while ((temp=bufferedReader.readLine())!=null){
                      stringBuilder.append(temp);


                 }
                 String string = stringBuilder.toString();
                 return string;


             }


         } catch (MalformedURLException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }


         return "";
     }
    public static Bitmap getNetBitmap(String urlBitmap) {
        try {
            URL url = new URL(urlBitmap);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = urlConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    }
