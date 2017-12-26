package c352.pack.namespace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Pinglun extends Activity{

	//int m=0;
	int LIST_SIZE=50;
	String id=null;
	String s;
	final String []pluse=new String[LIST_SIZE+1];
	final String []plcou=new String[LIST_SIZE+1];
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pinglun);
		
		Intent intent=getIntent();
		//m=intent.getIntExtra("c352.pack.namespace.poi",0);
		id=intent.getStringExtra("c352.pack.namespace.id");
		final EditText detpl=(EditText)findViewById(R.id.pledit);
        Button buttj=(Button)findViewById(R.id.pltijiao);
        final ListView lstpl=(ListView)findViewById(R.id.pllistview);
       
        		
        s = Web.getTalk(id);
        
        if(s == null || s.length() <= 0){pluse[0]="暂时";plcou[0]="无评论";}
        else{
        
	        String[]s1 = s.split("-");
			 for(int i1=0,j=1;i1<s.split("-").length/2;i1++){
					pluse[i1]=s1[j];
					j+=2;
				}
				for(int i1=0,j=2;i1<s.split("-").length/2;i1++){
					plcou[i1]=s1[j];
					j+=2;
				}
        	}	 
	       
        
      //生成动态数组，加入数据  
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        if(s == null || s.length() <= 0){
	        for(int i=0;i<1;i++)  
	        {  
	            HashMap<String, Object> map = new HashMap<String, Object>();  
	            map.put("pluser",pluse[i]+":");
	            map.put("plcoun",plcou[i]);  
	            listItem.add(map);  
	        } 
        }
        else {
        	for(int i=0;i<s.split("-").length/2;i++)  
	        {  
	            HashMap<String, Object> map = new HashMap<String, Object>();  
	            map.put("pluser",pluse[i]+":");
	            map.put("plcoun","  "+plcou[i]);  
	            listItem.add(map);  
	        } 
		}
        //生成适配器的Item和动态数组对应的元素  
        final SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//数据源   
            R.layout.pinglun_item,//ListItem的XML实现  
            //动态数组与ImageItem对应的子项          
            new String[]{"pluser","plcoun"}, new int[]{R.id.pluser,R.id.plcoun}  
        );  
         
        //添加并且显示  
        lstpl.setAdapter(listItemAdapter);  
        buttj.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(Quanjubianliang.Zusername!=13011800)
				{
					String t=detpl.getText().toString();
					if(t == null || t  .length() <= 0)
					{
						Toast.makeText(Pinglun.this,"评论不能为空哟！", Toast.LENGTH_LONG).show();
						
					}
					else {
						String jg=Web.insertTalk(id, Quanjubianliang.Zusername, t);
						Toast.makeText(Pinglun.this,jg, Toast.LENGTH_LONG).show();
						
					}
				}
				else {
					Toast.makeText(Pinglun.this,"要先登录才可以哟！", Toast.LENGTH_LONG).show();
				}
				/*for(int i=(s.split("-").length/2)-1;i>=0;i--){//依次向后赋值
					pluse[i+1] = pluse[i];}
				pluse[0]=String.valueOf(Quanjubianliang.Zusername);
				for(int i=(s.split("-").length/2)-1;i>=0;i--){//依次向后赋值
					plcou[i+1] = plcou[i];
		        }
				plcou[0]=t;
				lstpl.setAdapter(listItemAdapter);*/
			}
			});
        
		
	}
	

}
