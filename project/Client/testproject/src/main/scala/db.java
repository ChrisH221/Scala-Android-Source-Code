package my.android.project;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;


class db{

   public void query(){
      
	  
	  Thread thread1 = new Thread(new Runnable(){

            @Override
            public void run() {
         String site = "http://192.168.1.67:80/getchat.php";
		 try {
				URL url = new URL(site );
				URLConnection urlConn = url.openConnection();
				HttpURLConnection httpConn = (HttpURLConnection) urlConn;
				httpConn.setRequestMethod("GET");
				httpConn.connect();
				httpConn.getResponseCode();


            } catch (Exception e) {
                System.out.println("Error: " + e);
                e.printStackTrace();
            }	
               }

        });
        thread1.start();
	  
		

    }
	
	
	public void getKey(){
      
	  
	  Thread thread1 = new Thread(new Runnable(){

         @Override
         public void run() {
         String site = "http://192.168.1.67:80/getchat.php";
		 try {
				URL url = new URL(site );
				URLConnection urlConn = url.openConnection();
				HttpURLConnection httpConn = (HttpURLConnection) urlConn;
				httpConn.setRequestMethod("GET");
				httpConn.connect();
				httpConn.getResponseCode();


            } catch (Exception e) {
                System.out.println("Error: " + e);
                e.printStackTrace();
            }	
               }

        });
        thread1.start();
	  
		

    }
	



}



