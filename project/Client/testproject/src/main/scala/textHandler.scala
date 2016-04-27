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
* This class handles encoding and decoding text files using huffman.
* It also uses spray libraries to convert a case class to JSON and from JSON to a case class.
*/

class textHandler extends helpers {
    
    /**
    * Takes a text file path and encodeds it using Huffman
    * @returns (List[Int],List[(Char,List[Int])])
    */
    def processEncode(path:String):(List[Int],List[(Char,List[Int])])={
	
        val myFileStream = new FileInputStream(path)
        val bufferedReader = new BufferedReader(new InputStreamReader(myFileStream))
        val stringBuffer = new StringBuffer()
        var line: String = null
        while( {line = bufferedReader.readLine();  line!= null} )
        {
            stringBuffer.append(line).append(System.getProperty("line.separator"))
        }
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
    * @returns List[(Char,List[Int])]
    */
    
    def JsonToKey(json:String):List[(Char,List[Int])]={
        
        case class HCodeMap(list:List[(Char,List[Int])])
        
        implicit val testFormat: JsonFormat[HCodeMap] = jsonFormat(HCodeMap.apply _, "list")
        
        
        val map2 = json.parseJson
        val map3 = map2.convertTo[HCodeMap]
        val t = "hello"
        map3.list
    }
    
    
    
    /**
    * Takes a code map for decoding and then writes to a file
    * @returns Unit
    */
    def writeDecodedText(key:(List[Int],List[(Char,List[Int])]),noExtension:String){
        
        
        val h = new handler
        
        val decoded = h.decode(new StringBuffer,key)
        val result = URLDecoder.decode(decoded, "UTF-16")
        val folder = new File("/sdcard/decoded")
        
        if (!folder.exists()){folder.mkdir()}
        
        val Card = new File("/sdcard/decoded/", noExtension + ".png")
        
        Card.createNewFile()
        
        val fw = new FileWriter(Card)
        
        fw.write(result)
        
        fw.close()
        
        
        
    }
    
    
    
    
    
    
}