package c352.pack.namespace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ShezhiActivity extends Activity {
	RelativeLayout dengluwin;
	RelativeLayout jubaowin;
	RelativeLayout huanbaowin;
	 private ImageView back;
	TextView textView;
	String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab4_shezhimain1);
		//返回键
		back = (ImageView) findViewById(R.id.backview);
		back.setOnClickListener(new OnClickListener() { 

			@Override
			public void onClick(View v) {
				ShezhiActivity.this.finish();
			}
		});
		textView=(TextView)findViewById(R.id.Detail_UserName);
		dengluwin = (RelativeLayout) findViewById(R.id.denglu);
		dengluwin.setOnClickListener(new OnClickListener(){
				
				public void onClick(View v) {
					Intent intent =new Intent(ShezhiActivity.this,zhuceActivity.class);
					startActivity(intent);
					ShezhiActivity.this.finish();
					
				}
	});
		jubaowin = (RelativeLayout) findViewById(R.id.jubao);
		jubaowin.setOnClickListener(new OnClickListener(){
				
				public void onClick(View v) {
					Intent intent =new Intent(ShezhiActivity.this,jubaoActivity.class);
					startActivity(intent);
				}
	});

		huanbaowin = (RelativeLayout) findViewById(R.id.huanbao);
		huanbaowin.setOnClickListener(new OnClickListener(){
				
				public void onClick(View v) {
					Intent intent =new Intent(ShezhiActivity.this,huanbaoActivity.class);
					startActivity(intent);
				}
	});
	

	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		/*Intent intent =new Intent();
		
		intent.putExtra("username", name);
		
		startActivity(intent);*/
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onRestart()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		Intent intent=getIntent();
//		name=intent.getStringExtra("c352.pack.namespace.username");
		name=String.valueOf(Quanjubianliang.Zusername);
		textView.setText(name);
	}
	
	
}