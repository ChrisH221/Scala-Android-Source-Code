package my.android.project

import _root_.android.app.Activity
import _root_.android.os.Bundle
import scala.language.implicitConversions
import android.view.View
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
import scala.util.Try


class login extends Activity with TypedActivity with helpers{
    
    
    
    implicit def onClickListener(f: (View => Unit)): View.OnClickListener = {
        new View.OnClickListener() {
            override def onClick(v: View) {
                f(v)
            }
        }
    }
    
    
    
    override def onCreate(bundle: Bundle) {
        
        super.onCreate(bundle)
        setContentView(R.layout.login)
        
        
        val button1 = findView(TR.login)
        button1.setOnClickListener((v : View) => {loginUI()}) 
       
        
        val button2 = findView(TR.create)
        button2.setOnClickListener((v : View) => {createAccount()})         
            
            
        
        
    }
    
    def changeScreen(result:String){
        Log.d("MyTAG","3 " + result)
        if(result.trim == "0"){
            var intent= new Intent (this,classOf[main])
            val username = findView(TR.username).getText.toString
            intent.putExtra("user", username)
            startActivity(intent)
        }
        else {
            
            val inform = findView(TR.inform)
            inform.setText("Username already taken                                                                         ")
            
        }
        
    }
    
    
    
    def parseResult(result:JSONArray,username:String)={
        
        if (result.length > 0)  {
            
            var intent= new Intent (this,classOf[main])
            intent.putExtra("user", username)
            
            startActivity(intent)
            
        }
        else {
            
            val inform = findView(TR.inform)
            inform.setText("Incorrect username or password                                                                          ")
            val username = findView(TR.username).setText("")
            val password = findView(TR.password).setText("")
        }
        
    }
    
    
    def loginUI(){
        
        setContentView(R.layout.check)
        
        val button1 = findView(TR.submit)
        button1.setOnClickListener((v : View) => {
            
            val p = promise[JSONArray]
            val f = p. future
            
            future {
                
                val site = "http://www.monad.uk/login_scala.php"
                try {
                    
                    val url = new URL(site)
                    val urlConn = url.openConnection()
                    val httpConn = urlConn.asInstanceOf[HttpURLConnection]
                    httpConn.setDoOutput(true)
                    val os = httpConn.getOutputStream
                    
                    val username = findView(TR.username)
                    val pass = findView(TR.password)
                    
                    val POST_PARAMS = "username=" + username.getText.toString + "&password="+ pass.getText.toString +""
                    os.write(POST_PARAMS.getBytes)
                    val responseCode = httpConn.getResponseCode
                    httpConn.connect()
                    
                    val input = httpConn.getInputStream
                    val reader = new BufferedReader(new InputStreamReader(input))
                    val result = new StringBuilder()
                    var line: String = null
                    val str = Stream.continually(reader.readLine()).takeWhile(_ != null).mkString("n")
                    val j = new JSONArray(str)
                    p success j
                    
                    } catch {
                    case e: Exception => {
                        println("Error: " + e)
                        e.printStackTrace()
                        null
                    }
                }
            }
            f onSuccess {  case result =>  runOnUiThread{
                    val username = findView(TR.username)
            parseResult(result,username.getText.toString)}}
        })
        
    }
    
    
    
    def createAccount(){
        
        setContentView(R.layout.check)
        
        val button1 = findView(TR.submit)
        button1.setOnClickListener((v : View) => {
            
            val p = promise[String]
            val f = p. future
            
            future {
                
                val site = "http://www.monad.uk/addUser_scala.php"
                try {
                    
                    val url = new URL(site)
                    val urlConn = url.openConnection()
                    val httpConn = urlConn.asInstanceOf[HttpURLConnection]
                    httpConn.setDoOutput(true)
                    val os = httpConn.getOutputStream
                    
                    val username = findView(TR.username)
                    val pass = findView(TR.password)
                    
                    val POST_PARAMS = "username=" + username.getText.toString + "&password="+ pass.getText.toString +""
                    os.write(POST_PARAMS.getBytes)
                    val responseCode = httpConn.getResponseCode
                    httpConn.connect()
                    
                    val input = httpConn.getInputStream
                    val reader = new BufferedReader(new InputStreamReader(input))
                    val result = new StringBuilder()
                    var line: String = null
                    val str = Stream.continually(reader.readLine()).takeWhile(_ != null).mkString("n")
                    
                    Log.d("MyTAG","2" + result)
                    
                    p success str
                    
                    } catch {
                    case e: Exception => {
                        println("Error: " + e)
                        e.printStackTrace()
                        null
                    }
                }
            }
            f onSuccess {  case result =>  runOnUiThread{changeScreen(result)}}
            
            
        })
        
        
        
    }
    
    
}