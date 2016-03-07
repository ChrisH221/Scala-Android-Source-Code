package my.android.project

import _root_.android.app.Activity
import _root_.android.os.Bundle
import scala.language.implicitConversions
import android.view.View
import akka.actor.{ActorSystem, ActorLogging, Actor, Props}
import com.typesafe.config.ConfigFactory
import java.io.File
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.content.Intent
import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.util.Log
import scala.io.Source
import java.io.File
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import scala.util.Success
import scala.concurrent.{ future, promise }
import scala.concurrent.Future
import scala.concurrent.ExecutionContext._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.implicitConversions


class login extends Activity with TypedActivity with helpers{
  override def onCreate(bundle: Bundle) {
  
    super.onCreate(bundle)
	setContentView(R.layout.login)
	
	 Log.d("MyTAG","heyyy")
	 val button1 = findView(TR.login)
	button1.setOnClickListener((v : View) => {
	   	Log.d("MyTAG","heyyy2")
	  	loginUI()
		
	
    })
	
	 val button2 = findView(TR.create)
	button2.setOnClickListener((v : View) => {
	   Log.d("MyTAG","heyyy3")
	 createAccount()
	
    })
	
  }
  
  
 def createAccount(){

   setContentView(R.layout.check)
 

	
	val button = findView(TR.submit)
	button.setOnClickListener((v : View) => {
		
	 val p = promise[String] 
	 val f = p. future 
	 
	 future { 
	 
	 val site = "http://192.168.1.67/addUser.php"
     try {
	val u = findView(TR.username)
	val ps = findView(TR.password)
     val url = new URL(site)
    val urlConn = url.openConnection()
    val httpConn = urlConn.asInstanceOf[HttpURLConnection]
    httpConn.setDoOutput(true)
    val os = httpConn.getOutputStream
    val POST_PARAMS = "username=" + u.getText().toString() + "&password=" + ps.getText().toString()
	 Log.d("MyTAG",POST_PARAMS)
    os.write(POST_PARAMS.getBytes)
    val responseCode = httpConn.getResponseCode
    httpConn.connect()
	
      val input = httpConn.getInputStream
      val reader = new BufferedReader(new InputStreamReader(input))
      val result = new StringBuilder()
      var line: String = null
	  val str = Stream.continually(reader.readLine()).takeWhile(_ != null).mkString("\n")
	  
	
	  
	 p success str 
    	
	} catch {
	    case e: Exception => {
        println("Error: " + e)
        e.printStackTrace()
        null
      }
    }	 
	 } 
	f onSuccess {  case result =>  
	
		
		var intent= new Intent (this,classOf[main])
		startActivity(intent)
	
	
	}
	 
	
    })
	

}

def existingUser(user:String){

	 Log.d("MyTAG","3" + user)
	 val p = promise[String] 
	 val f = p. future 
	 
	 future { 
	 
	 val site = "http://monad/loginScala.php"
     try {
	
     val url = new URL(site)
    val urlConn = url.openConnection()
    val httpConn = urlConn.asInstanceOf[HttpURLConnection]
    httpConn.setDoOutput(true)
    val os = httpConn.getOutputStream
    val POST_PARAMS = "username=" + user
    os.write(POST_PARAMS.getBytes)
    val responseCode = httpConn.getResponseCode
    httpConn.connect()
	
      val input = httpConn.getInputStream
      val reader = new BufferedReader(new InputStreamReader(input))
      val result = new StringBuilder()
      var line: String = null
	  val str = Stream.continually(reader.readLine()).takeWhile(_ != null).mkString("\n")
	  
	 
	  
	 p success str 
    	
	} catch {
	    case e: Exception => {
        println("Error: " + e)
        e.printStackTrace()
        null
      }
    }	 
	 } 
	f onSuccess {  case result =>  
		val res = "\"" + """result""" + "\""
	if (res == "true"){
		var intent= new Intent (this,classOf[main])
		startActivity(intent)
	}
	else{
	
	val inform = findView(TR.inform)
	inform.setText("Incorrect details")
	}
	
	}
	
		
	val button = findView(TR.submit)
	button.setOnClickListener((v : View) => {
	
	// var intent= new Intent (this,classOf[main])
	// startActivity(intent)
	
    })


}
  
  def parseResult(result:JSONArray)={
  
    if (result.length > 0)  {
	
	var intent= new Intent (this,classOf[main])
	startActivity(intent)
	
	}
	else {
	
	
	} 
  
  }
  
  
def loginUI(){

 setContentView(R.layout.check)
 

	
	val button = findView(TR.submit)
	button.setOnClickListener((v : View) => {
	val u = findView(TR.username)
	val ps = findView(TR.password)
	
	 val p = promise[JSONArray] 
	 val f = p. future 
	 
	 future { 
	 
	 val site = "http://192.168.1.67/login.php"
     try {
	
     val url = new URL(site)
    val urlConn = url.openConnection()
    val httpConn = urlConn.asInstanceOf[HttpURLConnection]
    httpConn.setDoOutput(true)
    val os = httpConn.getOutputStream
    val POST_PARAMS = "username=" + u.getText().toString() + "&password=" + ps.getText().toString() 
    os.write(POST_PARAMS.getBytes)
    val responseCode = httpConn.getResponseCode
    httpConn.connect()
	
      val input = httpConn.getInputStream
      val reader = new BufferedReader(new InputStreamReader(input))
      val result = new StringBuilder()
      var line: String = null
	  val str = Stream.continually(reader.readLine()).takeWhile(_ != null).mkString("\n")
	 val j = new JSONArray(str)	 
	  
	// Log.d("MyTAG",str)
	  
	 p success j
    	
	} catch {
	    case e: Exception => {
        println("Error: " + e)
        e.printStackTrace()
        null
      }
    }	 
	 } 
	f onSuccess {  case result =>  runOnUiThread{parseResult(result)}}
	
	
	
	}
	
//	if (result == "true"){
//		var intent= new Intent (this,classOf[main])
	//	startActivity(intent)
//	}
	//else{
	
	//val inform = findView(TR.inform)
	//inform.setText("Incorrect details")
//	}
	
	
	 
	
    )}
	
	
}

