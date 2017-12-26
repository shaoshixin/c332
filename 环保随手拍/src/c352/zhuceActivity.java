package c352.pack.namespace;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class zhuceActivity extends Activity {
	//RelativeLayout dengluwin;
	EditText editnum,editpass;
	Button buttonlogin,buttoninsert,buttonca;
	String s;
	int username;
	String password,nickname,password1;
	//public Handler handler;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.denglu_dialog);
		//实现登陆的函数
		editnum=(EditText)findViewById(R.id.login_edit_account);
		editpass=(EditText)findViewById(R.id.login_edit_pwd);
		
		buttonlogin=(Button)findViewById(R.id.login_btn_login);
		buttonlogin.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				s=editnum.getText().toString().trim();
				username=Integer.parseInt(s);
				password=editpass.getText().toString();
				String s1=Web.login(username,password);
				//Toast.makeText(ShezhiActivity.this,username+":"+s+password, Toast.LENGTH_LONG).show();
				if(s1.equals("登录成功"))
					{Toast.makeText(zhuceActivity.this,"登录成功", Toast.LENGTH_LONG).show();
					Intent intent =new Intent(zhuceActivity.this,ShezhiActivity.class);
					Quanjubianliang.Zusername=username;
					//intent.putExtra("c352.pack.namespace.username", s);
					/*Intent intent =new Intent();
					intent.setClass(zhuceActivity.this,ShezhiActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("username", s);
					intent.putExtras(bundle);*/
					startActivity(intent);
					zhuceActivity.this.finish();}
				else {
					Toast.makeText(zhuceActivity.this,"登录失败，检查帐号密码是否正确", Toast.LENGTH_LONG).show();
				}
				

			}
		});
		//实现注册
		buttoninsert=(Button)findViewById(R.id.login_btn_register);
		buttoninsert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = LayoutInflater.from(zhuceActivity.this);  
		        final View textEntryView = inflater.inflate(  
		                R.layout.zhuce, null);  
		        final EditText edtnum=(EditText)textEntryView.findViewById(R.id.numID);
		        final EditText edtnic=(EditText)textEntryView.findViewById(R.id.nickID);
		        final EditText edtpass=(EditText)textEntryView.findViewById(R.id.passID);
		        final EditText edtpasure=(EditText)textEntryView.findViewById(R.id.passsureID);
		        final Button button=(Button)textEntryView.findViewById(R.id.buttonID);
		        final Button button1=(Button)textEntryView.findViewById(R.id.buttoncanID);
		        final TextView text=(TextView)findViewById(R.id.time);
		        final AlertDialog.Builder builder = new AlertDialog.Builder(zhuceActivity.this);  
		        builder.setCancelable(false);  
		        //builder.setIcon(R.drawable.fj);  
		        builder.setTitle("注册");  
		        builder.setView(textEntryView);
		        final AlertDialog dialog = builder.show();
		        //builder.show();
		        dialog.show();
		        
		        button.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						s=edtnum.getText().toString().trim();
						username=Integer.parseInt(s);
						nickname=edtnic.getText().toString();
						password=edtpass.getText().toString();
						password1=edtpasure.getText().toString();
						if(password.equals(password1))
						{
							String s2=Web.insertCargoInfo(username, nickname, password);
							if(s2.equals("注册成功"))
								Toast.makeText(zhuceActivity.this,"注册成功，可以去登录喽", Toast.LENGTH_LONG).show();
							else {
								Toast.makeText(zhuceActivity.this,"失败了，检查无误后再重新注册吧", Toast.LENGTH_LONG).show();
								}
						}
						else {
							Toast.makeText(zhuceActivity.this,"密码确认不一致哟", Toast.LENGTH_LONG).show();
						}
						
					}
				});
		        button1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
		        	
		        });
				/*final AlertDialog alertDialog = new AlertDialog.Builder(zhuceActivity.this).create();
				alertDialog.show();
				Window win = alertDialog.getWindow();

				WindowManager.LayoutParams lp = win.getAttributes();
				win.setGravity(Gravity.TOP| Gravity.BOTTOM);
				lp.alpha = 0.7f;
				win.setAttributes(lp);
				win.setContentView(R.layout.zhuce);*/
			}
			
		});

		buttonca=(Button)findViewById(R.id.login_btn_cancle);
		buttonca.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				zhuceActivity.this.finish();
			}
			
		});
	
	

	}
	
	
	
	
}
