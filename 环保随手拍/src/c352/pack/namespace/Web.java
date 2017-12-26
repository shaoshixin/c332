package c352.pack.namespace;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Web {
	
	 static String NAMESPACE = "http://suishoupai/";
	 //static String URL = "http://192.168.72.108/Service1.asmx";
	 static String URL = "http://qianxiang.vicp.cc/Service1.asmx";
	 static String selectAllLocation_NAME = "selectAllLocation";
	 static String login_NAME = "login";
	 static String zhuce_NAME="insertCargoInfo";
	 static String shangchuang_NAME="insertInfo";
	 static String dianzan_NAME="updateInfo";
	 static String zanquxiao_NAME="updateInfo1";
	 static String FileUploadImage_NAME ="FileUploadImage";
	 static String insertInfo_NAME ="insertInfo";
	 static String insertTalk_NAME ="insertTalk";
	 static String getTalk_NAME ="getTalk";
	 static Object detail;
	 //static Object detail1;
	 //static boolean bool1;
//获取所有信息
	 public static String selectAllLocation () {
		 
	        final HttpTransportSE httpSE = new HttpTransportSE(URL);  
	        httpSE.debug = true;  
	        SoapObject soapObject = new SoapObject(NAMESPACE, selectAllLocation_NAME); 
	        final SoapSerializationEnvelope soapserial = new SoapSerializationEnvelope(  
	                SoapEnvelope.VER11);  
	        soapserial.bodyOut = soapObject;  
	        soapserial.dotNet = true;  
	        soapserial.setOutputSoapObject(soapObject);
	        FutureTask<String> future = new FutureTask<String>(  
	                new Callable<String>() { 
	                	 @Override  
	                     public String call() throws Exception {
	                		 String shu = null;
	                         httpSE.call(NAMESPACE+selectAllLocation_NAME, soapserial);  
	                         detail = soapserial.getResponse();
	                         if (detail != null) {  
	                        	 for (int i = 0; i < ((SoapObject) detail).getPropertyCount(); i++) { 
	                        		 shu +="-"+(((SoapObject) detail).getProperty(i).toString()); 
	                         }
							return shu;  
	                     }
							return null; 
	                	 }
	                });  
	        new Thread(future).start();  
	        try {  
	            return future.get();  
	        } catch (InterruptedException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (ExecutionException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }
//登录
	 public static String login(int num,String paw) {  
	        final HttpTransportSE httpSE = new HttpTransportSE(URL);  
	        httpSE.debug = true;  
	        SoapObject soapObject = new SoapObject(NAMESPACE, login_NAME);
	        soapObject.addProperty("Unum", num);//参数
	        soapObject.addProperty("password", paw);
	        final SoapSerializationEnvelope soapserial = new SoapSerializationEnvelope(  
	                SoapEnvelope.VER11);  
	        soapserial.bodyOut = soapObject;  
	        soapserial.dotNet = true;  
	        soapserial.setOutputSoapObject(soapObject);
	        FutureTask<String> future = new FutureTask<String>(  
	                new Callable<String>() { 
	                	
	                	 @Override  
	                     public String call() throws Exception {  
	                		 String citys = null;  
	                         httpSE.call(NAMESPACE+login_NAME, soapserial);  
	                         detail = soapserial.getResponse();
	                         if (detail != null) { 
	                        		 citys = detail.toString();
	                         }
						return citys;  
	                         } 
	                });  
	        new Thread(future).start();  
	        try {  
	            return future.get();  
	        } catch (InterruptedException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (ExecutionException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }
//注册
	 public static String insertCargoInfo(int num,String nick,String paw) {  
	        final HttpTransportSE httpSE = new HttpTransportSE(URL);  
	        httpSE.debug = true;  
	        SoapObject soapObject = new SoapObject(NAMESPACE, zhuce_NAME);
	        soapObject.addProperty("Unum", num);//参数
	        soapObject.addProperty("nickname", nick);
	        soapObject.addProperty("password", paw);
	        final SoapSerializationEnvelope soapserial = new SoapSerializationEnvelope(  
	                SoapEnvelope.VER11);  
	        soapserial.bodyOut = soapObject;  
	        soapserial.dotNet = true;  
	        soapserial.setOutputSoapObject(soapObject);
	        FutureTask<String> future = new FutureTask<String>(  
	                new Callable<String>() { 
	                	
	                	 @Override  
	                     public String call() throws Exception {  
	                		 String citys = null;  
	                         // ����HttpTransportSE�����call���������� webserice  
	                         httpSE.call(NAMESPACE+zhuce_NAME, soapserial);  
	                         detail = soapserial.getResponse();
	                         if (detail != null) { 
	                        
	                        	 
	                        	  
	                        		 citys = detail.toString();
	                               
	                              
	                         }
						return citys;  
	                          
	                         } 

	                });  
	        new Thread(future).start();  
	        try {  
	            return future.get();  
	        } catch (InterruptedException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (ExecutionException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }
//点赞
	 public static String updateInfo(String id) {  
	        final HttpTransportSE httpSE = new HttpTransportSE(URL);  
	        httpSE.debug = true;  
	        SoapObject soapObject = new SoapObject(NAMESPACE, dianzan_NAME);
	        soapObject.addProperty("id", id);//参数
	       
	        final SoapSerializationEnvelope soapserial = new SoapSerializationEnvelope(  
	                SoapEnvelope.VER11);  
	        soapserial.bodyOut = soapObject;  
	        soapserial.dotNet = true;  
	        soapserial.setOutputSoapObject(soapObject);
	        FutureTask<String> future = new FutureTask<String>(  
	                new Callable<String>() { 
	                	
	                	 @Override  
	                     public String call() throws Exception {  
	                		 String citys = null;  
	                         // ����HttpTransportSE�����call���������� webserice  
	                         httpSE.call(NAMESPACE+dianzan_NAME, soapserial);  
	                         detail = soapserial.getResponse();
	                         if (detail != null) { 
	                        
	                        	 
	                        	  
	                        		 citys = detail.toString();
	                               
	                              
	                         }
						return citys;  
	                          
	                         } 

	                });  
	        new Thread(future).start();  
	        try {  
	            return future.get();  
	        } catch (InterruptedException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (ExecutionException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }
//取消点赞
	 public static String updateInfo1(String id) {  
	        final HttpTransportSE httpSE = new HttpTransportSE(URL);  
	        httpSE.debug = true;  
	        SoapObject soapObject = new SoapObject(NAMESPACE, zanquxiao_NAME);
	        soapObject.addProperty("id", id);//参数
	       
	        final SoapSerializationEnvelope soapserial = new SoapSerializationEnvelope(  
	                SoapEnvelope.VER11);  
	        soapserial.bodyOut = soapObject;  
	        soapserial.dotNet = true;  
	        soapserial.setOutputSoapObject(soapObject);
	        FutureTask<String> future = new FutureTask<String>(  
	                new Callable<String>() { 
	                	
	                	 @Override  
	                     public String call() throws Exception {  
	                		 String citys = null;  
	                         // ����HttpTransportSE�����call���������� webserice  
	                         httpSE.call(NAMESPACE+zanquxiao_NAME, soapserial);  
	                         detail = soapserial.getResponse();
	                         if (detail != null) { 
	                        
	                        	 
	                        	  
	                        		 citys = detail.toString();
	                               
	                              
	                         }
						return citys;  
	                          
	                         } 

	                });  
	        new Thread(future).start();  
	        try {  
	            return future.get();  
	        } catch (InterruptedException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (ExecutionException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }
//上传
	 public static String insertInfo(String id,int user,int count,String time,String text,String x,String y) {  
	        final HttpTransportSE httpSE = new HttpTransportSE(URL);  
	        httpSE.debug = true;  
	        SoapObject soapObject = new SoapObject(NAMESPACE, insertInfo_NAME);
	        soapObject.addProperty("id", id);//参数
	        soapObject.addProperty("Tuser", user);
	        soapObject.addProperty("count", count);
	        soapObject.addProperty("time", time);
	        soapObject.addProperty("text", text);
	        soapObject.addProperty("x", x);
	        soapObject.addProperty("y", y);
	        final SoapSerializationEnvelope soapserial = new SoapSerializationEnvelope(  
	                SoapEnvelope.VER11);  
	        soapserial.bodyOut = soapObject;  
	        soapserial.dotNet = true;  
	        soapserial.setOutputSoapObject(soapObject);
	        FutureTask<String> future = new FutureTask<String>(  
	                new Callable<String>() { 
	                	 @Override  
	                     public String call() throws Exception {  
	                		 String citys = null;  
	                         httpSE.call(NAMESPACE+insertInfo_NAME, soapserial);  
	                         detail = soapserial.getResponse();
	                         if (detail != null) { 
	                        		 citys = detail.toString();
	                         }
						return citys;  
	                         } 
	                });  
	        new Thread(future).start();  
	        try {  
	            return future.get();  
	        } catch (InterruptedException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (ExecutionException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }
//上传图片
	 public static String FileUploadImage(String num,String paw) {  
	        final HttpTransportSE httpSE = new HttpTransportSE(URL);  
	        httpSE.debug = true;  
	        SoapObject soapObject = new SoapObject(NAMESPACE, FileUploadImage_NAME);
	        soapObject.addProperty("ImagefileName", num);//参数
	        soapObject.addProperty("image", paw);
	        final SoapSerializationEnvelope soapserial = new SoapSerializationEnvelope(  
	                SoapEnvelope.VER11);  
	        soapserial.bodyOut = soapObject;  
	        soapserial.dotNet = true;  
	        soapserial.setOutputSoapObject(soapObject);
	        FutureTask<String> future = new FutureTask<String>(  
	                new Callable<String>() { 
	                	 @Override  
	                     public String call() throws Exception {  
	                		 String citys = null;  
	                         httpSE.call(NAMESPACE+FileUploadImage_NAME, soapserial);  
	                         detail = soapserial.getResponse();
	                         if (detail != null) { 
	                        		 citys = detail.toString();
	                         }
						return citys;  
	                         } 
	                });  
	        new Thread(future).start();  
	        try {  
	            return future.get();  
	        } catch (InterruptedException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (ExecutionException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }
//上传评论
	 public static String insertTalk (String id,int talker,String talk) {  
	        final HttpTransportSE httpSE = new HttpTransportSE(URL);  
	        httpSE.debug = true;  
	        SoapObject soapObject = new SoapObject(NAMESPACE, insertTalk_NAME);
	        soapObject.addProperty("id", id);//参数
	        soapObject.addProperty("talker", talker);
	        soapObject.addProperty("talk", talk);
	        final SoapSerializationEnvelope soapserial = new SoapSerializationEnvelope(  
	                SoapEnvelope.VER11);  
	        soapserial.bodyOut = soapObject;  
	        soapserial.dotNet = true;  
	        soapserial.setOutputSoapObject(soapObject);
	        FutureTask<String> future = new FutureTask<String>(  
	                new Callable<String>() { 
	                	 @Override  
	                     public String call() throws Exception {  
	                		 String citys = null;  
	                         httpSE.call(NAMESPACE+insertTalk_NAME, soapserial);  
	                         detail = soapserial.getResponse();
	                         if (detail != null) { 
	                        		 citys = detail.toString();
	                         }
						return citys;  
	                         } 
	                });  
	        new Thread(future).start();  
	        try {  
	            return future.get();  
	        } catch (InterruptedException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (ExecutionException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }
//获取评论
	 public static String getTalk(String id) {  
	        final HttpTransportSE httpSE = new HttpTransportSE(URL);  
	        httpSE.debug = true;  
	        SoapObject soapObject = new SoapObject(NAMESPACE, getTalk_NAME);
	        soapObject.addProperty("id", id);//参数
	        final SoapSerializationEnvelope soapserial = new SoapSerializationEnvelope(  
	                SoapEnvelope.VER11);  
	        soapserial.bodyOut = soapObject;  
	        soapserial.dotNet = true;  
	        soapserial.setOutputSoapObject(soapObject);
	        FutureTask<String> future = new FutureTask<String>(  
	                new Callable<String>() { 
	                	 @Override  
	                     public String call() throws Exception {  
	                		 String shu = null;  
	                         httpSE.call(NAMESPACE+getTalk_NAME, soapserial);  
	                         detail = soapserial.getResponse();
	                         if (detail != null) {  
	                        	 for (int i = 0; i < ((SoapObject) detail).getPropertyCount(); i++) { 
	                        		 shu +="-"+(((SoapObject) detail).getProperty(i).toString()); 
	                         }
							return shu;  
	                     }
	                         return shu; 
	                	 }
	                });  
	        new Thread(future).start();  
	        try {  
	            return future.get();  
	        } catch (InterruptedException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (ExecutionException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }
}
