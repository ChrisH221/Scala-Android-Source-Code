package my.android.project

import java.io.BufferedInputStream
import java.io.InputStream
import java.net.MalformedURLException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import java.net.MalformedURLException

import scala.collection.JavaConversions._

class db {

  def query() {
    val thread1 = new Thread(new Runnable() {

      override def run() {
        var site = "http://monad.uk/checkUser.php"
        try {
          var url = new URL(site)
          var urlConn = url.openConnection()
          var httpConn = urlConn.asInstanceOf[HttpURLConnection]
		  httpConn.setDoOutput(true)
		  val os = httpConn.getOutputStream()
          httpConn.setRequestMethod("POST")
		  val post_para = "username=chris&PASSWORD=hey"
		  os.write(post_para.getBytes())
          httpConn.connect()
          val res = httpConn.getResponseCode
        } catch {
          case e: Exception => {
            println("Error: " + e)
            e.printStackTrace()
          }
        }
      }
    })
    thread1.start()
  }

 
}