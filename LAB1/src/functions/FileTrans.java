/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;

public class FileTrans {  
     public static void main(String[] args) throws IOException{  
         trans("E:\\java\\JavaFXApplication1\\test.txt");  
     }  
     //处理原始文件，去掉所有的标点符号并用空格代替
     public static String trans(String txtFilename) throws IOException{
         Reader reader = null;  
         String outfilename=txtFilename.substring(0,txtFilename.lastIndexOf("."))+"temp"+".txt";
    	 try {  
             FileOutputStream out=new FileOutputStream(outfilename);
             OutputStreamWriter writer =new OutputStreamWriter(out);
             reader = new InputStreamReader(new FileInputStream(txtFilename));  
             int tempchar;  
             boolean blank = false;
             boolean beginwrite=false;
             while ((tempchar = reader.read()) != -1) {   
                 if (Character.isLetter((char) tempchar)) { 
                	 if(blank&&beginwrite)
                	 {
                		// System.out.print(' ');  
                                 writer.write(' ');
                	 }
                     //System.out.print((char) tempchar);  
                     writer.write((char)tempchar);
                     beginwrite=true;
                     blank=false;
                 }
                 else
                 {
                     blank=true;
                 }
             }  
             reader.close(); 
             writer.close();
             System.out.println("");
             return outfilename;
         } catch (Exception e) {  
             e.printStackTrace();  
             return "";
         }   
     }  
       
}  
