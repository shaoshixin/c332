package c352.pack.namespace;

import java.util.HashMap;

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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.sharesdk.ShareModel;
import com.example.sharesdk.SharePopupWindow;

public class single extends Activity implements PlatformActionListener{

	private RequestQueue mQueue;
	//private ImageLoader mImageLoader;
	int n=0;
	int LIST_SIZE=100 ;
	TextView count,usename,text,dizhitext;
	NetworkImageView img;
	String stril,dz;
	private com.example.sharesdk.SharePopupWindow share;
	String[] stri2;
	String tex;
	String []use=new String[LIST_SIZE];
	String []textc=new String[LIST_SIZE];
	final String []countc=new String[LIST_SIZE];
	final String []idc=new String[LIST_SIZE];
	
	//private String textf = "这是我的分享测试数据";
	//private final String imageurl = "http://ssp352.hicp.net/images/xieq1.jpg";
	private String title = "污染随手拍";
	//private String url = "www.baidu.com";
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ashamed_detail1);
		
		Intent intent=getIntent();
		n=intent.getIntExtra("c352.pack.namespace.username",0);
		//dz=intent.getStringExtra("c352.pack.namespace.dz");
		usename=(TextView)findViewById(R.id.Detail_UserNameID);
        text=(TextView)findViewById(R.id.Detail_MainTextID);
        count=(TextView)findViewById(R.id.Detail_Down_textID);
        img=(NetworkImageView)findViewById(R.id.Detail_MainImgID);
        dizhitext=(TextView)findViewById(R.id.xiangxitext);
        stril = Web.selectAllLocation();
        LIST_SIZE=(stril.split("-").length)/7;
		 stri2 = stril.split("-");
		
		for(int i1=0,j=2;i1<LIST_SIZE;i1++){
			use[i1]=stri2[j];
			j+=7;
		}
		for(int i1=0,j=5;i1<LIST_SIZE;i1++){
			textc[i1]=stri2[j];
			j+=7;
		}
		for(int i1=0,j=1;i1<LIST_SIZE;i1++){
			idc[i1]=stri2[j];
			j+=7;
		}
		for(int i1=0,j=4;i1<LIST_SIZE;i1++){
			countc[i1]=stri2[j];
			j+=7;
		}
		usename.setText(use[n]);
		//text.setText(textc[n]);
		text.setText("照片：");
		count.setText(countc[n]);
		dz=Quanjubianliang.s;
		Intent intent1=getIntent();
		tex=intent1.getStringExtra("c352.pack.namespace.tex");
		dizhitext.setText("\u3000\u3000"+textc[n]);
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
		img.setImageUrl(idc[n]+".jpg",imageLoader);
		Button dwbutton=(Button)findViewById(R.id.bttonAdd);
		dwbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Quanjubianliang.point0=Quanjubianliang.point1;
				Intent intent =new Intent(single.this,Text4Activity.class);
				
				startActivity(intent);
			}
		});
		Button shareButton = (Button)findViewById(R.id.share);
		shareButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				share = new SharePopupWindow(single.this);
				share.setPlatformActionListener(single.this);
				ShareModel model = new ShareModel();
				model.setImageUrl(idc[n]+".jpg");
				model.setText("污染监督："+textc[n]);
				model.setTitle(title);
				model.setUrl(idc[n]+".jpg");
				share.initShareParams(model);
				share.showShareWindow();
				// 显示窗口 (设置layout在PopupWindow中显示的位置)
				share.showAtLocation(single.this
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
				Intent intent =new Intent(single.this,Pinglun.class);
				
				intent.putExtra("c352.pack.namespace.poi", n);
				intent.putExtra("c352.pack.namespace.id", idc[n]);
				
				startActivity(intent);
				
			}
		});
		Button zanlayout=(Button)findViewById(R.id.Detail_Down_ImgID);
		zanlayout.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(Quanjubianliang.isClick!=n)
					Quanjubianliang.isRunning=false;
				
				if(Quanjubianliang.isRunning==false){
					Web.updateInfo(idc[n]);
					updata();
					Quanjubianliang.isRunning=true;
					Quanjubianliang.isClick=n;}
				else {
					Web.updateInfo1(idc[n]);
					Quanjubianliang.isRunning=false;
					updata();
				}
			}
			
		});
		ShareSDK.initSDK(this);
	}

	
	public void updata(){
		
        //count=(TextView)findViewById(R.id.Detail_Down_textID);
         stril = Web.selectAllLocation();
		
		 stri2 = stril.split("-");
		
		for(int i1=0,j=4;i1<LIST_SIZE;i1++){
			countc[i1]=stri2[j];
			j+=7;
		}
		
		count.setText(countc[n]);
	}
	/*public boolean onKeyDown(int keyCode, KeyEvent event)
    {
		  if(keyCode==KeyEvent.KEYCODE_BACK)
		  {
			  this.moveTaskToBack(true);
		  }
		  return super.onKeyDown(keyCode, event);
	}*/
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//single.this.finish();
		//this.moveTaskToBack(false);
		/*Intent intent = new Intent(Intent.ACTION_MAIN);  
		intent.addCategory(Intent.CATEGORY_HOME);  
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		startActivity(intent); */
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
