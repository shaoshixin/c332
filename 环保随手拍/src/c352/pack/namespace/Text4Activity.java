


package c352.pack.namespace;



import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.esri.android.map.GraphicsLayer;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.ItemizedOverlay;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MyLocationOverlay;
import com.tianditu.android.maps.OverlayItem;
import com.tianditu.android.maps.TGeoAddress;
import com.tianditu.android.maps.TGeoDecode;
import com.tianditu.android.maps.TGeoDecode.OnGeoResultListener;

public class Text4Activity extends FragmentActivity implements OnClickListener,OnGeoResultListener {
	
	int ss=0;
	private RelativeLayout mapLayout, cameraLayout, mySpaceLayout,shezhiLayout;

	private Fragment mapFragment;
	private ImageView mapImg,cameraImg, mySpaceImg,shezhiImg;
	private TextView cameraTv, mySpaceTv;
	private MapView mMapView;
	GraphicsLayer graphicsLayer = null;
	String str,strl;
	Button button,buttonadd1,weixing;
	int LIST_SIZE=200 ;
	int i,j;
	String []a=new String[LIST_SIZE];
	String []a1=new String[LIST_SIZE];
	/*Double[] b = new Double[LIST_SIZE];
	Double[] c = new Double[LIST_SIZE];*/
	int[] b = new int[LIST_SIZE];
	int[] c = new int[LIST_SIZE];


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		strl = Web.selectAllLocation();
		
		LIST_SIZE=(strl.split("-").length)/7;
		String[] str2 = strl.split("-");
		for(i=0,j=6;i<LIST_SIZE;i++){
			a1[i]=str2[j];
			j+=7;
		}
		
		for(i=0,j=7;i<LIST_SIZE;i++){
			a[i]=str2[j];
			j+=7;
		}
		/*for (i = 0; i <LIST_SIZE; i++) {
			b[i]=Double.parseDouble(a1[i]);
		} 
		
		for (i = 0; i <LIST_SIZE; i++) {
			c[i]=Double.parseDouble(a[i]);
		}*/
		for (i = 0; i <LIST_SIZE; i++) {
			b[i]=Integer.parseInt(a1[i]);
		} 
		
		for (i = 0; i <LIST_SIZE; i++) {
			c[i]=Integer.parseInt(a[i]);
		}
		
	    initUI();
	    showmap();
		initTab();
		
       
		
		
		
	}


	/**
	 * 界面
	 */
	private void initUI() {
		mapLayout = (RelativeLayout) findViewById(R.id.rl_map);
		cameraLayout = (RelativeLayout) findViewById(R.id.rl_camera);
		mySpaceLayout = (RelativeLayout) findViewById(R.id.rl_myspace);
		shezhiLayout = (RelativeLayout) findViewById(R.id.rl_shezhi);
		
		mapLayout.setOnClickListener(this);
		cameraLayout.setOnClickListener(this);
		mySpaceLayout.setOnClickListener(this);
		shezhiLayout.setOnClickListener(this);
	
        
		mapImg = (ImageView) findViewById(R.id.iv_map);
		cameraImg = (ImageView) findViewById(R.id.iv_camera);
		 mySpaceImg = (ImageView) findViewById(R.id.iv_myspace);
		 shezhiImg =(ImageView) findViewById(R.id.iv_shezhi);
		//mapTv = (TextView) findViewById(R.id.tv_map);
		cameraTv = (TextView) findViewById(R.id.tv_camera);
		 mySpaceTv = (TextView) findViewById(R.id.tv_myspace);
		 //shezhiTv = (TextView) findViewById(R.id.tv_shezhi);
		
         weixing = (Button) findViewById(R.id.weixing);
         weixing.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(ss==0)
				{
					mMapView.setSatellite(true);
					mMapView.setPlaceName(false); //隐藏地名
					weixing.setBackgroundResource(R.drawable.ditu);
					ss=1;
					}
				else{
					mMapView.setSatellite(false);
					mMapView.setPlaceName(true); 
					weixing.setBackgroundResource(R.drawable.weixing);
					ss=0;
				}
				}
			
		});
		 
		
		 buttonadd1=(Button)findViewById(R.id.buttonAdd);
         buttonadd1.setOnClickListener(new OnClickListener() {
		    public void onClick(View v) {
			// TODO Auto-generated method stub
			MyLocationOverlay myLocation = new MyLocationOverlay(Text4Activity.this, mMapView);
			myLocation.enableCompass(); //显示指南针
			myLocation.enableMyLocation(); //显示我的位置
			mMapView.getOverlays().add(myLocation);
			myLocation.setGpsFollow(true);
		}
	});
		 

	}
         
     

	
	/**
	 * 界面切换
	 */
	private void initTab() {
		if(mapFragment == null)
		{  
			mapFragment = new Fragment();
			
		}	
		if (!mapFragment.isAdded()) {
			
			getSupportFragmentManager().beginTransaction()
					.add(R.id.content_layout, mapFragment).commit();

			
			mapImg.setImageResource(R.drawable.map1);
			/*mapTv.setTextColor(getResources()
					.getColor(R.color.bottomtab_press));*/
			cameraImg.setImageResource(R.drawable.camera1);
			cameraTv.setTextColor(getResources().getColor(
					R.color.bottomtab_normal));
			 mySpaceImg.setImageResource(R.drawable.masage);
			 mySpaceTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
			 shezhiImg.setImageResource(R.drawable.geren);
		}

	}

	public void onClick(View view) {
		
		switch (view.getId()) {
		case R.id.rl_map: 
			clickTab1Layout();
			break;
		case R.id.rl_camera: 
			clickTab2Layout();
			break;
		case R.id.rl_myspace:
			clickTab3Layout();
			break;
		case R.id.rl_shezhi:
			clickTab4Layout();
			break;
		default:
			break;
		}
	}

	private void clickTab4Layout() {
		Intent intent =new Intent(Text4Activity.this,ShezhiActivity.class);
		startActivity(intent);	
	 
	}

	/**
	 * 污染地图 map
	 */
	private void clickTab1Layout() {
		showmap();
	    
     
	}

	/**
	 *拍照上传
	 */
	private void clickTab2Layout() {
		MyLocationOverlay myLocation = new MyLocationOverlay(Text4Activity.this, mMapView);
		myLocation.enableCompass(); //显示指南针
		myLocation.enableMyLocation(); //显示我的位置
		mMapView.getOverlays().add(myLocation);
		myLocation.setGpsFollow(true);
		Quanjubianliang.point1=myLocation.getMyLocation();
	    Intent intent =new Intent(Text4Activity.this,PhotoUtilsActivity.class);
		startActivity(intent);
		
	}

	/**
	 * 畅所欲言
	 */
	private void clickTab3Layout() {
		MyLocationOverlay myLocation = new MyLocationOverlay(Text4Activity.this, mMapView);
		myLocation.enableCompass(); //显示指南针
		myLocation.enableMyLocation(); //显示我的位置
		mMapView.getOverlays().add(myLocation);
		myLocation.setGpsFollow(true);
		Quanjubianliang.point1=myLocation.getMyLocation();
		Intent intent =new Intent(Text4Activity.this,MyspaceActivity.class);
		startActivity(intent);
	}
	
	////加载地图
		private void showmap() {
			mMapView = (MapView) findViewById(R.id.main_mapview);
		      //设置启用内置的缩放控件
		      mMapView.setBuiltInZoomControls(true);
		      //得到mMapView的控制权,可以用它控制和驱动平移和缩放
		      MapController mMapController = mMapView.getController();
		      //用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
		      GeoPoint point = Quanjubianliang.point0;
		      //设置地图中心点
		      mMapController.setCenter(point);
		      //设置地图zoom级别
		      mMapController.setZoom(14);
	
		      /*Resources res = getResources();
				Drawable marker = res.getDrawable(R.drawable.pi);
				mMapView.getOverlays().add(new MyOverItemT (marker, Text4Activity.this));*/
   
		      
}
	    
	    protected boolean isRouteDisplayed() {
	        // TODO Auto-generated method stub
	        return false;
	    }
	    

	    public class MyOverItemT extends ItemizedOverlay<OverlayItem> {

	    	private List<OverlayItem> GeoList = new ArrayList<OverlayItem>();
	    	private Context mContext;
	    	//private double mLat1 =Text4Activity.point1.getLatitudeE6();
	    	//private double mLon1 =Text4Activity.point1.getLongitudeE6();
	    	//double[]a={39.90923,39.9022,39.917723};
	    	//double[]b={116.397428,116.3922,116.3722};
	    	
	    	//int i;
//	    	private double mLat1 = 39.90923;
//	    	private double mLon1 = 116.397428;
//	    	private double mLat2 = 39.9022;
//	    	private double mLon2 = 116.3922;
//	    	private double mLat3 = 39.917723;
//	    	private double mLon3 = 116.3722;
	    	public MyOverItemT(Drawable marker, Context context) {
	    		super(boundCenterBottom(marker));
	    		this.mContext = context;
	    		// 用给定的经纬度构造GeoPoint，单位是微度 (度 * 1E6)
	    		/*GeoPoint p1 = new GeoPoint((int) (mLat1 * 1E6), (int) (mLon1 * 1E6));
	    		GeoPoint p2 = new GeoPoint((int) (mLat2 * 1E6), (int) (mLon2 * 1E6));
	    		GeoPoint p3 = new GeoPoint((int) (mLat3 * 1E6), (int) (mLon3 * 1E6));*/
	    		for(int i=0;i<LIST_SIZE;i++){
	    		GeoPoint p = new GeoPoint(b[i], c[i]);
	    		//GeoPoint p3 = new GeoPoint((int) (mLat3 * 1E6), (int) (mLon3 * 1E6));
	    		//GeoList.add(new OverlayItem(p1, "T1", "point1"));
	    		 
	    		GeoList.add(new OverlayItem(p, "", ""+i));}
	    		/*GeoList.add(new OverlayItem(p1, "T1", "point1"));
	    		GeoList.add(new OverlayItem(p2, "T2", "point2"));
	    		GeoList.add(new OverlayItem(p3, "T3", "point3"));*/
	    		//一旦有了数据，在调用其它方法前，必须首先调用这个方法
	    		populate();
	    	}
	    	
	    	@Override
	    	protected OverlayItem createItem(int i) {
	    		// TODO Auto-generated method stub
	    		return GeoList.get(i);
	    	}
	    	@Override
	    	public int size() {
	    		// TODO Auto-generated method stub
	    		return GeoList.size();
	    	}
	    	/* (non-Javadoc)
	    	 * @see com.tianditu.android.maps.ItemizedOverlay#populate()
	    	 */
	    	@Override
	    	protected void populate() {
	    		// TODO Auto-generated method stub
	    		super.populate();
	    	}
	    	/* (non-Javadoc)
			 * @see com.tianditu.android.maps.ItemizedOverlay#onTap(com.tianditu.android.maps.GeoPoint, com.tianditu.android.maps.MapView)
			 */
			@Override
			

			protected boolean onTap(int i) {
				TGeoDecode decode = new TGeoDecode(Text4Activity.this);
//				Quanjubianliang.point1= GeoList.get(i).getPoint();
//				decode.search(Quanjubianliang.point1);
				decode.search(GeoList.get(i).getPoint());
				int n = 0;
				
				n=i;
				
				Intent intent =new Intent(Text4Activity.this,single.class);
				
				intent.putExtra("c352.pack.namespace.username", n);
				//intent.putExtra("c352.pack.namespace.dz", str);
				startActivity(intent);
				
				

				
				
	    		return true;
	    		}
	    }

	   
		@Override 
		protected void onDestroy() { 
			super.onDestroy();
			System.exit(0);
	 }
		@Override
		protected void onPause() {
			super.onPause();
			/*Intent intent=getIntent();
			name=intent.getStringExtra("username");*/
	 }
		@Override 	protected void onResume() {
			super.onResume(); 
			Resources res = getResources();
			Drawable marker = res.getDrawable(R.drawable.pi);
			mMapView.getOverlays().add(new MyOverItemT (marker, Text4Activity.this));
		}

		
		
		public void onGeoDecodeResult(TGeoAddress addr, int errCode) {
			// TODO Auto-generated method stub
			if(errCode != 0)
			{
			Toast.makeText(this, "获取地址失败！", Toast.LENGTH_LONG).show();
			Quanjubianliang.s="获取地址失败！";
			}
			str = "";
			str += "详细地址:"  + "\n";
			str += "全称:" + addr.getFullName() + "\n";
			str += "最近的poi名称:" + addr.getPoiName() + "\n";
			str += "最近poi的方位:" + addr.getPoiDirection() + "\n";
			str += "最近poi的距离:" + addr.getPoiDistance ();		
			str += "最近的地址:" + addr.getAddress() + "\n";
			str += "最近地址的方位:" + addr.getAddrDirection() + "\n";
//			str += "最近地址的距离:" + addr.getAddrDistance() ;
			Quanjubianliang.s=str;
			Toast.makeText(this,str, Toast.LENGTH_LONG).show();
			
		}
			////退出时弹窗提示
				 public boolean onKeyDown(int keyCode, KeyEvent event) {
		            // TODO Auto-generated method stub
		            if (((keyCode == KeyEvent.KEYCODE_BACK) ||
		(keyCode == KeyEvent.KEYCODE_HOME))
		&& event.getRepeatCount() == 0) {
		                   dialog_Exit(Text4Activity.this);
		            }
		            return false;
		           
		            //end onKeyDown
		     }
		
		     public static void dialog_Exit(Context context) {
		      AlertDialog.Builder builder = new Builder(context);
		      builder.setMessage("确定要退出吗?");
		      builder.setTitle("提示");
		      builder.setIcon(android.R.drawable.ic_dialog_alert);
		      builder.setPositiveButton("确认",
		              new DialogInterface.OnClickListener() {
		                  public void onClick(DialogInterface dialog, int which) {
		                      dialog.dismiss();
		                      android.os.Process.killProcess(android.os.Process
		                              .myPid());
		                  }
		              });
		     
		      builder.setNegativeButton("取消",
		              new android.content.DialogInterface.OnClickListener() {
		                  public void onClick(DialogInterface dialog, int which) {
		                      dialog.dismiss();
		                  }
		              });
		     
		      builder.create().show();
		}
	
}

