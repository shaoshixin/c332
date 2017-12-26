package c352.pack.namespace;


import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class WelcomeActivity extends Activity implements AnimationListener {
	private ImageView  imageView = null;
	private TextView  textView = null;
	private TextView  textView1 = null;
	private Animation alphaAnimation = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		textView1=(TextView)findViewById(R.id.textView2);
		imageView = (ImageView)findViewById(R.id.welcome_image_view);
		textView =(TextView)findViewById(R.id.time);
	
		imageView.setAnimation(alphaAnimation);
		textView.setAnimation(alphaAnimation);
		textView1.setAnimation(alphaAnimation);
		init();
 	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		this.finish();
	}

	public void onAnimationStart(Animation animation) {
		
	}
	
	public void onAnimationEnd(Animation animation) {
		//动画结束时结束欢迎界面并转到软件的主界面
		Intent intent = new Intent(this, Text4Activity.class);
		startActivity(intent);
		this.finish();
	}
	
	public void onAnimationRepeat(Animation animation) {
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//在欢迎界面屏蔽BACK键
		if(keyCode==KeyEvent.KEYCODE_BACK) {
			WelcomeActivity.this.finish();
			return false;
		}
		return false;
	}

	 public void init() {
	        if (NetUtil.checkNet(this)) {
	        	
	            Intent startService = new Intent("com.mpros.service.MainService");
	            this.startService(startService);
	            
	            final Intent it = new Intent(this, Text4Activity.class); //你要转向的Activity   
	    		Timer timer = new Timer(); 
	    		TimerTask task = new TimerTask() {  
	    		    @Override  
	    		    public void run() {   
	    		    startActivity(it); //执行  
	    		     } 
	    		 };
	    		timer.schedule(task, 1000 * 1); //1秒后
	            
	        } else {
	        	 Toast.makeText(this,"亲，没连网哟！", Toast.LENGTH_LONG).show();  
	        	 dialog();
	        }
	        }


	 protected void dialog(){
		 AlertDialog.Builder builder = new Builder(WelcomeActivity.this);
		 builder.setMessage("亲，没连网呦！");
		 builder.setTitle("退出？");
		 builder.setPositiveButton("确认", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			  dialog.dismiss();
			  WelcomeActivity.this.finish();
			}
		});
		 builder.setNegativeButton("取消", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		 builder.create().show();
	 }
}
