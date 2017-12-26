package c352.pack.namespace;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.tianditu.android.maps.GeoPoint;
import com.zcs.demo.volley.adapter.VolleyListAdapter;
import com.zcs.demo.volley.entity.VolleyItem;

public class MyspaceActivity extends Activity{ 
		

	int LIST_SIZE =100;
	int m=0;

	private Spinner distence = null;
	private ArrayAdapter<String> adapter = null;
	private static String[] l={"1千米"," 2千米","3千米","5千米"};
	private ListView mListView;
	private VolleyListAdapter mAdapter;
	private RequestQueue mQueue;
	private ImageView back;
	
	int x0;//圆心x坐标
	int y0;//圆心y坐标
	int r=8983;//圆的半径
	int i,j;
//	private com.example.sharesdk.SharePopupWindow share;
//
//	private String text = "这是我的分享数据";
//	private final String imageurl = "http://ssp352.hicp.net/images/xieq1.jpg";
//	private String title = "污染随手拍";
//	//private String url = "www.baidu.com";
	String[] a = new String[LIST_SIZE];
	String[] b = new String[LIST_SIZE];
	String[] c = new String[LIST_SIZE];
	String[] d = new String[LIST_SIZE];
	String[] t = new String[LIST_SIZE];
	String[] a1 = new String[LIST_SIZE];
	String[] b1 = new String[LIST_SIZE];
	String[] c1 = new String[LIST_SIZE];
	String[] d1 = new String[LIST_SIZE];
	String[] t1 = new String[LIST_SIZE];
	String[] x1 = new String[LIST_SIZE];
	String[] y1 = new String[LIST_SIZE];
	int[] x = new int[LIST_SIZE];
	int[] y = new int[LIST_SIZE];
	int[] x2 = new int[LIST_SIZE];
	int[] y2 = new int[LIST_SIZE];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewactivity_main);

		x0=Quanjubianliang.point1.getLatitudeE6();
		y0=Quanjubianliang.point1.getLongitudeE6();
		String str = Web.selectAllLocation();
		// String[] str2 = new String[10];
		String[] str2 = str.split("-");
		LIST_SIZE=(str.split("-").length)/7;
		// int i,j;

		for(i=0,j=6;i<LIST_SIZE;i++){
			x1[i]=str2[j];
			j+=7;
		}
		
		for(i=0,j=7;i<LIST_SIZE;i++){
			y1[i]=str2[j];
			j+=7;
		}
		for (i = 0; i <LIST_SIZE; i++) {
			x[i]=Integer.parseInt(x1[i]);
		} 
		
		for (i = 0; i <LIST_SIZE; i++) {
			y[i]=Integer.parseInt(y1[i]);
		}
		for (int i = 0, j = 5; i <LIST_SIZE; i++) {
			a1[i] = str2[j];
			j += 7;
		}
		for (int i = 0, j = 2; i <LIST_SIZE; i++) {
			b1[i] = str2[j];
			j += 7;
		}
		for (int i = 0, j = 4; i <LIST_SIZE; i++) {
			c1[i] = str2[j];
			j += 7;
		}
		for (int i = 0, j = 3; i <LIST_SIZE; i++) {
			d1[i] = str2[j];
			j += 7;
		}
		for (int i = 0, j = 1; i <LIST_SIZE; i++) {
			t1[i] = str2[j];
			j += 7;
		}
		for (i = 0; i <LIST_SIZE; i++) 
		{
			if((x[i]>=x0-r&&x[i]<=x0+r)&&(y[i]>=y0-r&&y[i]<=y0+r))
			{
				a[m]=a1[i];
				b[m]=b1[i];
				c[m]=c1[i];
				d[m]=d1[i];
				t[m]=t1[i];
				x2[m]=x[i];
				y2[m]=y[i];
				m++;
			}
			
		}
		
		mQueue = Volley.newRequestQueue(this);

		if(m!=0)
		{
    		initList(); 
		}
    	else {
    		Toast.makeText(MyspaceActivity.this,"此范围内附近没有", Toast.LENGTH_LONG).show();
		}
		//initList();
		//ShareSDK.initSDK(this);

		mListView = (ListView) findViewById(R.id.listview);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Quanjubianliang.point0=new GeoPoint(x2[position], y2[position]);
				Intent intent =new Intent(MyspaceActivity.this,Singles.class);
				intent.putExtra("c352.pack.namespace.username", b[position]);
				intent.putExtra("c352.pack.namespace.tex", a[position]);
				intent.putExtra("c352.pack.namespace.id", t[position]);
				intent.putExtra("c352.pack.namespace.cou", c[position]);
				startActivity(intent);
			}
		});
		mListView.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				switch (scrollState) {
	            case OnScrollListener.SCROLL_STATE_IDLE:
	                Log.v("已经停止：SCROLL_STATE_IDLE", "");
	                break;
	            case OnScrollListener.SCROLL_STATE_FLING:
	                Log.v("开始滚动：SCROLL_STATE_FLING","");
	                break;
	            case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
	   Log.v("正在滚动：SCROLL_STATE_TOUCH_SCROLL","");
	                break;
	            }
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				
			}
		});
		back = (ImageView) findViewById(R.id.backview);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MyspaceActivity.this.finish();
			}
		});

		distence = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,l);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distence.setAdapter(adapter);
        distence.setOnItemSelectedListener(new SpinnerSelectedListener());
        distence.setVisibility(View.VISIBLE);
    }
    class SpinnerSelectedListener implements OnItemSelectedListener{
 
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
        	int h=0;
        	String dis = distence.getItemAtPosition(arg2).toString();
        	if(dis.equals("1千米")) r=8983;
        	else if (dis.equals("2千米")) r=17966;
        	else if (dis.equals("3千米")) r=26949;
			else r=44915;
        	
        	for (i = 0; i <LIST_SIZE; i++) 
    		{
    			if((x[i]>=x0-r&&x[i]<=x0+r)&&(y[i]>=y0-r&&y[i]<=y0+r))
    			{
    				a[h]=a1[i];
    				b[h]=b1[i];
    				c[h]=c1[i];
    				d[h]=d1[i];
    				t[h]=t1[i];
    				x2[h]=x[i];
    				y2[h]=y[i];
    				h++;
    			}
    			
    		}
        	if(h!=0)
    		{
        		m=h;
        		initList(); 
    		}
        	else {
        		Toast.makeText(MyspaceActivity.this,"此范围内附近没有", Toast.LENGTH_LONG).show();
			}
    
    		 
        }
 
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

	/**
	 * 初始化List
	 */
	private void initList() {
		mListView = (ListView) findViewById(R.id.listview);
		// TODO 初始化数据
		ArrayList<VolleyItem> items = new ArrayList<VolleyItem>(m);
		//String[] imgUrl = t;
		for (int i = 0; i<m; i++) {
			VolleyItem item = new VolleyItem();
			item.setName(a[i]);
			item.setUser(b[i]);
			item.setTime(d[i]);
			item.setCount(c[i]);
			// TODO 为图片地址添加一个尾数,是为了多次访问图片,而不是读取第一张图片的缓存
			item.setImgUrl(t[i]);
			items.add(item);
		}
		// TODO 绑定数据
		mAdapter = new VolleyListAdapter(this, mQueue, items);
		mListView.setAdapter((ListAdapter) mAdapter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// TODO 取消所有未执行完的网络请求
		mQueue.cancelAll(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
}