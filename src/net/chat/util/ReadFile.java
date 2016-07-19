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
      * ��ȡĳ���ļ����µ�����MP3�ļ���
      * @param String filepath
      *                   Ҫ��ȡ���ļ���·��
      * @param ArrayList<String> files
      *                   ������Ŷ�ȡ�����ļ�����list
      * @return Boolean
      *                   �����ȡ�ɹ�
      *                       ���� true
      *                         ����
      *                       ����false
      */
     public static boolean readfile(String filepath,ArrayList<String> files) 
    		 throws FileNotFoundException, IOException {
    	 	Pattern pattern=Pattern.compile("(.+)(\\.mp3)");
             try {

                     File file = new File(filepath);
                     //��������ļ���
                     if (!file.isDirectory()) {
                    	 //�����MP3�ļ�
                    	 if(pattern.matcher(file.getName()).matches())
                    	 {
                    		 //files���һ���ļ�������������׺��
                    		 String filename=file.getName();
                    		 int dot = filename.lastIndexOf('.'); 
                             if ((dot >-1) && (dot < (filename.length()))) { 
                            	 files.add(filename.substring(0, dot)); 
                             } 
                    	 }                         
                     } 
                     //������ļ���,���ȡ�ļ���·���µ�MP3�ļ����ڱ�ϵͳ��Ӧ��֤��filepath���ļ��У�
                     else if (file.isDirectory()) {
                             String[] filelist = file.list();
                             for (int i = 0; i < filelist.length; i++) {
                                     File readfile = new File(filepath + "/" + filelist[i]);
                                     //��������ļ���
                                     if (!readfile.isDirectory()) {
                                    	//�����MP3�ļ�
                                    	 if(pattern.matcher(readfile.getName()).matches())
                                    	 {
                                    		//files���һ���ļ�������������׺��
                                    		 String filename=readfile.getName();
                                    		 int dot = filename.lastIndexOf('.'); 
                                             if ((dot >-1) && (dot < (filename.length()))) { 
                                            	 files.add(filename.substring(0, dot)); 
                                             } 
                                    	 }
                                     } 
                                     //������ļ��У����Զ�����
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
