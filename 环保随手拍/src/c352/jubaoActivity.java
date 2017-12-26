package c352.pack.namespace;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;  
import android.net.Uri;  
import android.text.Editable;  
import android.view.View;  
import android.widget.Button;  
import android.widget.EditText;  

public class jubaoActivity extends Activity{
	private Button btnPhone;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jubaorexian1);
		 btnPhone=(Button) findViewById(R.id.callbutton);  
		  
	       btnPhone.setOnClickListener(new View.OnClickListener() {                      
	  
	                     @Override  
	                     public void onClick(View v) {  
	  
	                         // String phoneNum =  btnPhone.getText().toString();  
	  
	                          Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:12369"));  
	  
	                            startActivity(intent);  
	  
	                     }  
	              });  
		
		
		
	
		
  }
}