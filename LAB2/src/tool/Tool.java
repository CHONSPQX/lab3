package tool;

import java.io.UnsupportedEncodingException;

public class Tool {
  public static String Toutf(String str)
  {
	  String res="";
	  try {
		res=new String(str.getBytes("ISO-8859-1"),"utf-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return res;
  }
  public static String Tiso(String str)
  {
	  String res="";
	  try {
		res=new String(str.getBytes("utf-8"),"ISO-8859-1");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return res;
  }
  public static String Tgbk(String str)
  {
	  String res="";
	  try {
		res=new String(str.getBytes("utf-8"),"gb2312");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return res;
  }
}
