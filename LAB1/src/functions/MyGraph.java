/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;


import java.awt.Image;
import java.util.List;
import java.util.Random;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;
import jdk.nashorn.internal.parser.TokenType;

public class MyGraph{
	public static int MAX_DIS=10000;
        public HashMap<String,ArrayList<WordNode>> maplink;//邻接表
	public ArrayList<String> words;//保存每一个单词再文章中出现的先后顺序
	public String dotfile;//默认文件路径
	public static void main(String[] args) throws IOException{  
        long startTime = System.currentTimeMillis();    //获取开始时间
        MyGraph g=new MyGraph(FileTrans.trans("E:\\java\\JavaFXApplication1\\test.txt"));
       // g.findBridge("next", "play");
       // System.out.println("最短距离:"+g.Dijistra("next", "s"));
        //System.out.println(g.getNewString("WordsLink public s next WordsLink"));
        //g.randomMove("path");
        g.SaveAsPiture();
        long endTime = System.currentTimeMillis();    //获取结束时间

        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        g.Dijistra("b");
     
        g.PrintBridgeWords("c","a");
        //g.printPicture();
    }  
        
         public MyGraph (String s)throws IOException//根据处理过的文件建立图结构
	{
            
            dotfile =s.substring(0,s.lastIndexOf("temp"));
            maplink=new HashMap<String, ArrayList<WordNode>>();
            words=new ArrayList<>();
            ArrayList<WordNode> templist=null;
            String tempword;
            String nextword;
            Scanner reader = null;
            reader = new Scanner(new File(s));
            if(reader.hasNext())
            tempword=reader.next().toLowerCase();
            else
                return;
    	 try { 
             while(reader.hasNext()) 
             {
            	 nextword=reader.next().toLowerCase();
            	// System.out.print(tempword);
                boolean flag=maplink.containsKey(tempword);
            	 if(flag)
            	 {
            		templist=maplink.get(tempword);
            	 }
            	 else
            	 {
            		 templist=new ArrayList<>();
                         maplink.put(tempword, templist);
                         words.add(tempword);
            	 }
            	 if(templist.contains(new WordNode(nextword)))
            	 {
            		 int index=templist.indexOf(new WordNode(nextword));
	            	templist.get(index).addWeight();
            	 }
            	 else
            	 { 
            		 templist.add(new WordNode(nextword));
            		 //System.out.print(tempword);
            	 }
            	 tempword=nextword;
             }
             if(!maplink.containsKey(tempword))
            {
                maplink.put(tempword, new ArrayList<>());
                words.add(tempword);
            }
             reader.close(); 
         } catch (Exception e) {  
         }  
	}
          
        private void BuildDot()throws IOException//根据图结构生成dot文件
	{
            FileOutputStream out=new FileOutputStream(this.dotfile+"dot.dot");
            OutputStreamWriter writer =new OutputStreamWriter(out);
            ArrayList<WordNode> pos=maplink.get(words.get(0));
            writer.write("digraph gra{"+'\n');
            //writer.write("graph [autosize=false, size=\"12, 16\"]\n");
            //writer.write("rankdir=LR\n");
            for(String label:words)
                writer.write("\""+label+"\"\n");
            for (String label:words)
            {
                pos=maplink.get(label);
                if(!pos.isEmpty())
                {
                    for(WordNode w:pos)
                    {
                        writer.write('\"'+label+'\"'+"->"+'\"'+w.label+'\"'+" [ label = "+w.weight+" ] \n");
                    }
                }
            }
            writer.write("}\n");
            writer.close();
	}
        
        public String SaveAsPiture() throws IOException//调用graphviz根据已经生成的dot文件得到GIF图片
	{
	    this.BuildDot();
	    GraphViz gv = new GraphViz();
	    gv.readSource(dotfile+"dot.dot");
	    //System.out.println(gv.getDotSource());
	     
	      String type = "gif";
//			    String type = "dot";
//			    String type = "fig";    // open with xfig
//			    String type = "pdf";
//			    String type = "ps";
//			    String type = "svg";    // open with inkscape
//			    String type = "png";
//			      String type = "plain";
	    //File out = new File("/tmp/simple." + type);   // Linux
	   File out = new File(dotfile +'.'+ type);   // Windows
	    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
            return dotfile +'.'+ type;
	}
          
        public String PrintBridgeWords(String fore,String behi) throws IOException//查询桥接词，返回包含桥接词信息的图片文件名和桥接词
        {
            ArrayList<String> bridgewords = findBridgeWords(fore, behi);
            StringBuffer bridgeword=new StringBuffer();
            if(bridgewords.isEmpty())
            {
                //System.out.println("no bridge word");
               return  bridgeword.toString();
            }
            else
            {
                for(String str: bridgewords)
                bridgeword.append(str).append('\n');
                //System.out.println("\nfind");
                String filename =BuildBridgeDot(fore,behi,bridgewords);
                String picturenameString=SaveAsPiture(filename);
                bridgeword.insert(0,picturenameString+"\n");
               // bridgewords.add(0,picturenameString);
                return bridgeword.toString();
            }
        }
	     
        private String BuildBridgeDot(String head,String tail,ArrayList<String> bridgeword)throws IOException//根据桥接词查询结果生成dot文件，返回dot文件名
	{
            FileOutputStream out=new FileOutputStream(head+"Bridge"+tail+".dot");
            try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
                ArrayList<WordNode> pos=maplink.get(words.get(0));
                writer.write("digraph gra{"+'\n');
                //writer.write("rankdir=LR\n");
                for(String label:words)
                {
                    if(label.equals(head))
                        writer.write("\""+head+"\""+" [ color = blue ] \n");
                    else if(label.equals(tail))
                        writer.write("\""+tail+"\""+" [ color = blue ] \n");
                    else if(bridgeword.contains(label))
                        writer.write("\""+label+"\""+" [ color = green ] \n");
                    else
                        writer.write("\""+label+"\""+"\n");
                }
                for(String label:words)
                {
                    pos=maplink.get(label);
                    if(!pos.isEmpty())
                    {
                        if(label.equals(head))
                        {
                            for(WordNode w:pos)
                            {
                                if(bridgeword.contains(w.label))
                                    writer.write("\""+label+"\""+ " -> "+"\""+w.label+"\""+" [ label = "+w.weight+" , color = green  ] \n");
                                else
                                    writer.write("\""+label+"\""+" -> "+"\""+w.label+"\""+" [ label = "+w.weight+" ] \n");    
                            }
                        }
                        else if (bridgeword.contains(label)) 
                        {
                            for(WordNode w:pos)
                            {
                                if(w.label.equals(tail))
                                    writer.write("\""+label+"\""+" -> "+"\""+w.label+"\""+" [ label = "+w.weight+" , color = green  ] \n");
                                else
                                    writer.write("\""+label+"\""+" -> "+"\""+w.label+"\""+" [ label = "+w.weight+"  ] \n");   
                            }
                        }
                        else
                        {
                            for(WordNode w:pos)
                            {
                                writer.write("\""+label+"\""+" -> "+"\""+w.label+"\""+" [ label = "+w.weight+" ] \n");
                            }
                        }
                    }

                }
                writer.write("}\n");
                writer.close();
                return head+"Bridge"+tail+".dot";
            }
	}
        
	public String SaveAsPiture(String filename)//根据输入的文件名所对应的dot文件生成GIF图片，返回图片的文件名
	{
	    String input = filename.substring(0,filename.lastIndexOf("."));
	    GraphViz gv = new GraphViz();
	    gv.readSource(filename);
	    //System.out.println(gv.getDotSource());
	     
	    String type = "gif";
//			    String type = "dot";
//			    String type = "fig";    // open with xfig
//			    String type = "pdf";
//			    String type = "ps";
//			    String type = "svg";    // open with inkscape
//			    String type = "png";
//			      String type = "plain";
	    //File out = new File("/tmp/simple." + type);   // Linux
	   File out = new File(input+"Pic"+'.'+ type);   // Windows
	    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
            return input+"Pic"+'.'+ type;
	}
        
        public ArrayList<String> findBridgeWords(String fore,String behi)//返回两个单词之间的所有桥接词      
	{
		ArrayList<String> bridge=new ArrayList<>();
		ArrayList<WordNode> templist=null;
		ArrayList<WordNode> pos=null;
		if(this.words.contains(fore))
			templist=maplink.get(fore);
		else
			return bridge;
		for(WordNode w:templist)
		{
			if(this.words.contains(w.label))
			{
				pos=maplink.get(w.label);
				if(pos.contains(new WordNode(behi)))
				{
					bridge.add(w.label);
				}
			}	
                }
		return bridge;
	}
        	
        public String Dijistra(String str1,String str2)//单源最短路径算法，返回最短路径
	{
		int distance=0;
		ArrayList<WordNode> s=new ArrayList<>();
		ArrayList<WordNode> set=new ArrayList<>();
                HashMap<String,ArrayList<String>> pathinfo=new HashMap<>();
		set.add(new WordNode(str1,0));
                pathinfo.put(str1,new ArrayList<>());
                for(String label:words)
                {
                      if(!label.equals(str1))
                        {
				s.add(new WordNode(label,MAX_DIS));
                                pathinfo.put(label,new ArrayList<>());
                        }
                }
		ArrayList<WordNode> templist=maplink.get(str1);
		for(WordNode w:templist)
		{
			s.get(s.indexOf(w)).setDistance(w.weight);
                        pathinfo.get(w.label).add(str1);
		}
		while(!s.isEmpty())
		{
                    s.sort(null);
                    WordNode min=s.get(0);
                    s.remove(0);
                    set.add(min);
                    templist=maplink.get(min.label);
                    for(WordNode node:s)
                        if(templist.contains(node))
                        {
                            int dis=templist.get(templist.indexOf(node)).weight;
                            if(node.weight>min.weight+dis)
                            {
                                node.setDistance(min.weight+dis);
                                pathinfo.get(node.label).clear();
                                pathinfo.get(node.label).add(min.label);
                            }
                           else if(node.weight==min.weight+dis)
                            {
                                pathinfo.get(node.label).add(min.label);
                            }
                        }
		}
		if(set.contains(new WordNode(str2)))
			distance=set.get(set.indexOf(new WordNode(str2))).weight;
                ArrayList<String> PathOutcome= new ArrayList<>();
                getPaths(pathinfo, str1, str2, PathOutcome, "");
                SaveAllPaths(PathOutcome);
                PathOutcome.add(0, String.valueOf(distance));
                StringBuffer path=new StringBuffer();
                for(String str:PathOutcome)
                    path.append(str).append('\n');
                //for(String pathString: PathOutcome)
                    //System.out.println(pathString);
		return new String(path);
	}
        
         public String Dijistra(String str1) throws IOException//单源最短路径，返回起点到所有其他顶点的最短路径
	{
		int distance=0;
		ArrayList<WordNode> s=new ArrayList<>();
		ArrayList<WordNode> set=new ArrayList<>();
                HashMap<String,String> pathinfo=new HashMap<>();
		set.add(new WordNode(str1,0));
                pathinfo.put(str1,"");
                for(String label:words)
                {
                      if(!label.equals(str1))
                        {
				s.add(new WordNode(label,MAX_DIS));
                                pathinfo.put(label,str1);
                        }
                }
		ArrayList<WordNode> templist=maplink.get(str1);
		for(WordNode w:templist)
		{
			s.get(s.indexOf(w)).setDistance(w.weight);
		}
		while(!s.isEmpty())
		{
                    s.sort(null);
                    WordNode min=s.get(0);
                    s.remove(0);
                    set.add(min);
                    templist=maplink.get(min.label);
                    for(WordNode node:s)
                        if(templist.contains(node))
                        {
                            int dis=templist.get(templist.indexOf(node)).weight;
                            if(node.weight>min.weight+dis)
                            {
                                node.setDistance(min.weight+dis);
                                pathinfo.put(node.label,min.label);
                            }
                        }
		}
                ArrayList<String> PathOutcome= new ArrayList<>();
                for(String str:words)
                {
                    if(!str.equals(str1))
                    {
                        String tempstr=str;
                        StringBuffer temppath=new StringBuffer();
                        while(!pathinfo.get(tempstr).equals(str1))
                        {
                            temppath.insert(0, pathinfo.get(tempstr)+" ");
                            tempstr=pathinfo.get(tempstr);
                        }
                        temppath.insert(0, str1+" ");
                        temppath.append(str);
                        PathOutcome.add(temppath.toString());
                    }
                }
                for(String str:PathOutcome)
                {
                     String fileString=BuildPathDot(str, -1);
                     SaveAsPiture(fileString);
                }
                StringBuffer path=new StringBuffer();
                for(String str:PathOutcome)
                {
                    String end=str.substring(str.lastIndexOf(" ")+1);
                    int dis=set.get(set.indexOf(new WordNode(end))).weight;
                    path.append(str).append(" ").append(String.valueOf(dis)).append('\n');
                }
		return new String(path);
	}
                
        private void getPaths(HashMap<String,ArrayList<String>>pathinfo,String begin,String end,ArrayList<String> PathOutcome , String temppath)//根据单源最短路径算法的计算结果，整理出所有最短路径
        {
            if(pathinfo.get(end).contains(begin))
            {
                temppath=begin +" "+end+ temppath;
                PathOutcome.add(temppath);
            }
            else
            {
               ArrayList<String> tempArrayList=pathinfo.get(end);
               temppath=" "+end + temppath;
               for(String str:tempArrayList)
               {
                   getPaths(pathinfo, begin, str, PathOutcome, temppath);
               }
            }
        }
        
        public void SaveAllPaths(ArrayList<String> paths)//将输入paths中的所有路径信息都分别保存在dot文件中
        {
            int i=0;
            for(String pathString:paths)
            {
                try {
                    String fileString=BuildPathDot(pathString, i);
                    SaveAsPiture(fileString);
                    i++;
                } catch (IOException ex) {
                    Logger.getLogger(MyGraph.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        public String FindPictureOfPath(String path,int i)//根据路径和数字，解析出这条路径所对应的GIF文件名
        {
            if(i>=0)
            {
             String type="gif";
             List<String> pathnodes=Arrays.asList(path.split(" "));
             return pathnodes.get(0)+"to"+pathnodes.get(pathnodes.size()-1)+i+"Pic."+type;
            }
            else
            {
              String type="gif";
              List<String> pathnodes=Arrays.asList(path.split(" "));
              return pathnodes.get(0)+"to"+pathnodes.get(pathnodes.size()-1)+"Pic."+type;
            }
        }
           
        public String BuildPathDot(String path,int i)throws IOException//根据path和数字，首先产生一个dot文件，然后将路径信息写入到dot文件中，返回dot文件名
	{
            List<String> pathnodes=Arrays.asList(path.split(" "));
             FileOutputStream out;
            if(i>=0)
               out=new FileOutputStream(pathnodes.get(0)+"to"+pathnodes.get(pathnodes.size()-1)+i+".dot");
            else
                out=new FileOutputStream(pathnodes.get(0)+"to"+pathnodes.get(pathnodes.size()-1)+".dot");
            try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
                ArrayList<WordNode> pos=null;
                writer.write("digraph gra{"+'\n');
                //writer.write("rankdir=LR\n");
                for(String label:words)
                {
                    if(pathnodes.contains(label))
                       writer.write("\""+label+"\""+" [ color = blue ] \n");
                   else 
                       writer.write("\""+label+"\"\n");
                }
               
               for(String label: words)
               {
                   pos=maplink.get(label);
                   if(!pos.isEmpty())
                   {
                       int poslabel=pathnodes.indexOf(label);
                        int wlabel;
                        for(WordNode w:pos)
                        {
                            //wlabel=pathnodes.indexOf(w.label);
                           // if(wlabel==poslabel+1&&poslabel>=0)
                           //     writer.write("\""+label+"\""+" -> "+"\""+w.label+"\""+" [ label = "+w.weight+", color = green  ] \n");
                            //else 
                            if(poslabel>=0&&(poslabel<pathnodes.size()-1)&&pathnodes.get(poslabel+1).equals(w.label))
                                writer.write("\""+label+"\""+" -> "+"\""+w.label+"\""+" [ label = "+w.weight+", color = green  ] \n");
                            else
                                writer.write("\""+label+"\""+" -> "+"\""+w.label+"\""+" [ label = "+w.weight+" ] \n");   
                        }
                   }
               }
                writer.write("}\n");
                writer.close();
                if(i>=0)
                    return pathnodes.get(0)+"to"+pathnodes.get(pathnodes.size()-1)+i+".dot";
                else
                    return pathnodes.get(0)+"to"+pathnodes.get(pathnodes.size()-1)+".dot";
            }
	}
        
	public String getNewString(String str)//将输入的文本打断，插入桥接词，返回插入桥接词后的新文本
	{
		String [] arr=str.split(" ");
		String outcome=""+arr[0];
		List list;
		for(int i=0;i<arr.length-1;i++)
		{
			list=findBridgeWords(arr[i], arr[i+1]);
			if(list.size()==0)
			{
				outcome=outcome+" "+arr[i+1];
			}
			else if(list.size()==1)
			{
				outcome=outcome+" "+list.get(0)+" "+arr[i+1];
			}
			else 
			{
				int j=new Random().nextInt(list.size()-1);
				outcome=outcome+" "+list.get(j)+" "+arr[i+1];
			}
		}
		return outcome;
	}       
}

