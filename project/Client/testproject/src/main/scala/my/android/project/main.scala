package my.android.project

import _root_.android.app.Activity
import _root_.android.os.Bundle
import scala.language.implicitConversions
import android.view.View
import java.io.File
import android.util.Log
import android.content.Intent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView





class main extends Activity with TypedActivity with helpers{
    
    
    
   
    /**
	* Main activity after logging in enables the authenticated user to choose encode or decode
	*/
    override def onCreate(bundle: Bundle) {
        
        super.onCreate(bundle)
        choose()
        
        
    }
    
    
    /**
	* Display encode or decode buttons and handles onClick responses
	*/
    def choose(){
        
        setContentView(R.layout.user)
        
        val button1 = findView(TR.encode)
        button1.setOnClickListener((v : View) => {
            
            var intent= new Intent (this,classOf[ListViewer])
            val inte = getIntent
            val username = inte.getExtras.getString("user")
            
            intent.putExtra("user", username)
            
            startActivity(intent)
            
        })
        
        
        val button2 = findView(TR.decode)
        button2.setOnClickListener((v : View) => {
            val inte = getIntent
            val username = inte.getExtras.getString("user")
            
            
            var intent= new Intent (this,classOf[ListViewerDecode])
            intent.putExtra("user", username)
            startActivity(intent)
            
            
        })
        
        
        
    }
    
      
}
