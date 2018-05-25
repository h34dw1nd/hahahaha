package com.tctf.utils;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class HttpReq {

    private static final DateFormat MONTH_FORMAT = new SimpleDateFormat(
            "yyyyMMddHHmmss");

    private static final String[] IMAGE_EXT = new String[] { "jpg", "jpeg",
            "gif", "png", "bmp" };


    private static boolean endWithImg(String imgUrl){
        if(StringUtils.isNotBlank(imgUrl)&&(imgUrl.endsWith(".bmp")||imgUrl.endsWith(".gif")
                ||imgUrl.endsWith(".jpeg")||imgUrl.endsWith(".jpg")
                ||imgUrl.endsWith(".png"))){
            return true;
        }else{
            return false;
        }
    }

    public static String Download(String urlString,String path){
        String filename = "default.jpg";
        if(endWithImg(urlString)) {
            try {
                URL url = new URL(urlString);
                if(urlString.contains("file://")){
                    throw new Exception("error");
                }
                URLConnection urlConnection = url.openConnection();
                urlConnection.setReadTimeout(5*1000);
                InputStream is = urlConnection.getInputStream();
                byte[] bs = new byte[1024];
                int len;
                int count = 0;
                filename = generateRamdonFilename(getFileSufix(urlString));
                String outfilename = path + filename;
                OutputStream os = new FileOutputStream(outfilename);
                while ((len = is.read(bs)) != -1) {
                    count = count + 1;
                    if(count >5) {
                        os.write(bs, 0, len);
                    }else{
                        throw new Exception("file too large");
                    }
                }
                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filename;
    }

    private static String generateRamdonFilename(String ext) {
        return  MONTH_FORMAT.format(Calendar.getInstance().getTime())
                + RandomStringUtils.randomAlphabetic(5) + "." + ext;
    }

    private static String getFileSufix(String fileName) {
        boolean normalImg=false;
        for(String imgExt:IMAGE_EXT){
            if(fileName.endsWith(imgExt)){
                normalImg=true;
            }
        }
        String suffix="";
        if(normalImg){
            int splitIndex = fileName.lastIndexOf(".");
            suffix=fileName.substring(splitIndex + 1);
        }else{
            suffix=IMAGE_EXT[0];
        }
        return suffix;
    }

}
