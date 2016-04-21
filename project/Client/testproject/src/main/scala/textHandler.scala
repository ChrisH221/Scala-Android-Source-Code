package my.android.project

import android.util.Log
import java.io._
import java.io.FileOutputStream
import scala.util.Try
import spray.json._
import DefaultJsonProtocol._
import scala.collection.mutable._
import scala.io.Source
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import scala.collection.mutable.ListBuffer
import java.io.ByteArrayOutputStream
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.net.URLDecoder






/**
* @author Chris Howell
* This object handles converting bitmaps to base64 strings and back again.
* Object instead of class to allow access to the methods contained inside the object without
* needing to create different instances.
*/

class textHandler extends helpers {
    
    
    def processEncode(path:String):(List[Int],List[(Char,List[Int])])={
        val myFileStream = new FileInputStream(path)
        val bufferedReader = new BufferedReader(new InputStreamReader(myFileStream))
        val stringBuffer = new StringBuffer()
        var line: String = null
        while( {line = bufferedReader.readLine();  line!= null} )
        {
            stringBuffer.append(line).append(System.getProperty("line.separator"))
        }
        		Log.d("MyTAG", "1" + stringBuffer.toString)
		val result = URLEncoder.encode(stringBuffer.toString, "UTF-16")
        val h = new handler
        
        var x =  (h.encode(result))
       
        
        x.asInstanceOf[(List[Int], List[(Char, List[Int])])]
        
    }
    
    /**
    * Converts a codemap to a JSON String
    * @returns String
    */
    def keyToJson(list:(List[Int],List[(Char,List[Int])])):String={
        
        case class HCodeMap(list:List[(Char,List[Int])])
        
        implicit val testFormat: JsonFormat[HCodeMap] = jsonFormat(HCodeMap.apply _, "list")
        
        val pair = new HCodeMap(list._2).toJson
        
        
        pair.toString
        
    }
    
    /**
    * Converts a JSON string to a HCodeMap
    * @returns HCodeMap
    */
    
    def JsonToKey(json:String):List[(Char,List[Int])]={
        
        case class HCodeMap(list:List[(Char,List[Int])])
        
        implicit val testFormat: JsonFormat[HCodeMap] = jsonFormat(HCodeMap.apply _, "list")
        
        
        val map2 = json.parseJson
        val map3 = map2.convertTo[HCodeMap]
       
        map3.list
    }
    
    
    
    /**
    * Takes a code map for decoding and then writes to a file
    * @returns Unit
    */
    def writeDecodedText(key:(List[Int],List[(Char,List[Int])]),noExtension:String){
        
        
		val h = new handler
		
		val decoded = h.decode(new StringBuffer(),key)
		val result = URLDecoder.decode(decoded, "UTF-16")
		Log.d("MyTAG", "1" + result)
		
      
            val newTextFile = new File("decoded/", noExtension + ".txt")
			if (!newTextFile.exists()) {
            newTextFile.mkdirs();
			}
            val fw = new FileWriter(newTextFile)
            fw.write(result)
            fw.close()
        
        
        
    }
    
    
    
    
    
    
}