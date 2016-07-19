package net.chat.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ReadFile {
	 public ReadFile() {
     }
     /**
      * 读取某个文件夹下的所有MP3文件名
      * @param String filepath
      *                   要读取的文件夹路径
      * @param ArrayList<String> files
      *                   用来存放读取到的文件名的list
      * @return Boolean
      *                   如果读取成功
      *                       返回 true
      *                         否则
      *                       返回false
      */
     public static boolean readfile(String filepath,ArrayList<String> files) 
    		 throws FileNotFoundException, IOException {
    	 	Pattern pattern=Pattern.compile("(.+)(\\.mp3)");
             try {

                     File file = new File(filepath);
                     //如果不是文件夹
                     if (!file.isDirectory()) {
                    	 //如果是MP3文件
                    	 if(pattern.matcher(file.getName()).matches())
                    	 {
                    		 //files添加一个文件名（不包含后缀）
                    		 String filename=file.getName();
                    		 int dot = filename.lastIndexOf('.'); 
                             if ((dot >-1) && (dot < (filename.length()))) { 
                            	 files.add(filename.substring(0, dot)); 
                             } 
                    	 }                         
                     } 
                     //如果是文件夹,则读取文件夹路径下的MP3文件（在本系统下应保证是filepath是文件夹）
                     else if (file.isDirectory()) {
                             String[] filelist = file.list();
                             for (int i = 0; i < filelist.length; i++) {
                                     File readfile = new File(filepath + "/" + filelist[i]);
                                     //如果不是文件夹
                                     if (!readfile.isDirectory()) {
                                    	//如果是MP3文件
                                    	 if(pattern.matcher(readfile.getName()).matches())
                                    	 {
                                    		//files添加一个文件名（不包含后缀）
                                    		 String filename=readfile.getName();
                                    		 int dot = filename.lastIndexOf('.'); 
                                             if ((dot >-1) && (dot < (filename.length()))) { 
                                            	 files.add(filename.substring(0, dot)); 
                                             } 
                                    	 }
                                     } 
                                     //如果是文件夹，则自动忽略
                                     else if (readfile.isDirectory()) {
                                     }
                             }
                     }

             } catch (Exception e) {
                     return false;
             }
             return true;
     }
}
