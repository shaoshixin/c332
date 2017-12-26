package c352.pack.namespace;

import java.util.HashMap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.example.sharesdk.ShareModel;
import com.example.sharesdk.SharePopupWindow;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;

public class Singles extends Activity implements PlatformActionListener{

	private RequestQueue mQueue;
	TextView count,usename,text,dizhitext;
	NetworkImageView img;
	private com.example.sharesdk.SharePopupWindow share;
	private String title = "污染随手拍";
	String user,tex,id,cou;
	boolean click=false;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ashamed_detail1);
		
		Intent intent=getIntent();
		user=intent.getStringExtra("c352.pack.namespace.username");
		tex=intent.getStringExtra("c352.pack.namespace.tex");
		id=intent.getStringExtra("c352.pack.namespace.id");
		cou=intent.getStringExtra("c352.pack.namespace.cou");
		usename=(TextView)findViewById(R.id.Detail_UserNameID);
        text=(TextView)findViewById(R.id.Detail_MainTextID);
        count=(TextView)findViewById(R.id.Detail_Down_textID);
        img=(NetworkImageView)findViewById(R.id.Detail_MainImgID);
        dizhitext=(TextView)findViewById(R.id.xiangxitext);
        
        usename.setText(user);
		text.setText("图片：");
		count.setText(cou);
		dizhitext.setText("\u3000\u3000"+tex);
		
		mQueue = Volley.newRequestQueue(this);
		ImageLoader imageLoader = new ImageLoader(mQueue, new ImageCache() {  
            @Override  
            public void putBitmap(String url, Bitmap bitmap) {  
            }  
          
            @Override  
            public Bitmap getBitmap(String url) {  
                return null;  
            }  
        });  
		img.setImageUrl(id+".jpg",imageLoader);
		Button dwbutton=(Button)findViewById(R.id.bttonAdd);
		dwbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(Singles.this,Text4Activity.class);
				
				startActivity(intent);
			}
		});
		Button shareButton = (Button)findViewById(R.id.share);
		shareButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				share = new SharePopupWindow(Singles.this);
				share.setPlatformActionListener(Singles.this);
				ShareModel model = new ShareModel();
				model.setImageUrl(id+".jpg");
				model.setText("污染监督："+tex);
				model.setTitle(title);
				model.setUrl(id+".jpg");
				share.initShareParams(model);
				share.showShareWindow();
				// 显示窗口 (设置layout在PopupWindow中显示的位置)
				share.showAtLocation(Singles.this
						.findViewById(R.id.sharewindows),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
						0);
			}
		});
		Button jubao=(Button)findViewById(R.id.jubaodh);
		jubao.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:12369"));  
				  
                startActivity(intent);
			}
		});
		Button pinglun=(Button)findViewById(R.id.pinglun);
		pinglun.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent =new Intent(Singles.this,Pinglun.class);
				
				
				intent.putExtra("c352.pack.namespace.id", id);
				
				startActivity(intent);
				
			}
		});
		Button zanlayout=(Button)findViewById(R.id.Detail_Down_ImgID);
		zanlayout.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				int zan=Integer.parseInt(cou);
				if(click==false){
					Web.updateInfo(id);
					count.setText(String.valueOf(zan+1));
					click=true;
					}
				else {
					Web.updateInfo1(id);
					click=false;
					count.setText(String.valueOf(zan));
				}
			}
			
		});
		ShareSDK.initSDK(this);
	}

	
		
	
	@Override
	public void onCancel(Platform arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Platform arg0, int arg1, Throwable arg2) {
		// TODO Auto-generated method stub
		
	}

}
