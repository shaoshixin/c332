package com.zcs.demo.volley.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import c352.pack.namespace.MyspaceActivity;
import c352.pack.namespace.Quanjubianliang;
import c352.pack.namespace.R;
import c352.pack.namespace.ShezhiActivity;
import c352.pack.namespace.Text4Activity;
import c352.pack.namespace.Web;
import c352.pack.namespace.zhuceActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.example.sharesdk.ShareModel;
import com.example.sharesdk.SharePopupWindow;
import com.zcs.demo.volley.entity.VolleyItem;
import com.zcs.demo.volley.utils.BitmapCache;

public class VolleyListAdapter extends BaseAdapter {
	private final String TAG = getClass().getSimpleName();

	private RequestQueue mQueue;
	private ImageLoader mImageLoader;

	private LayoutInflater mInflater;
	private List<VolleyItem> items;
	String[]countc = null ;

	private com.example.sharesdk.SharePopupWindow share;
	public VolleyListAdapter(Context context, RequestQueue queue, List<VolleyItem> itemList) {
		super();
		Log.d(TAG, "new VolleyListAdapter");
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.items = itemList;

		mQueue = queue;
		// ��ʼ��VolleyͼƬLoader
		mImageLoader = new ImageLoader(mQueue, new BitmapCache());
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).getId();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.activity_ashamed_detail, null);

			holder = new ViewHolder();
			holder.img = (ImageView) convertView.findViewById(R.id.Detail_MainImg);
			holder.text = (TextView) convertView.findViewById(R.id.Detail_MainText);
			holder.user = (TextView) convertView.findViewById(R.id.Detail_UserName);
			holder.time = (TextView) convertView.findViewById(R.id.time);
			holder.count = (TextView) convertView.findViewById(R.id.Detail_Down_text);
			

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final VolleyItem item = items.get(position);
		holder.text.setText(item.getName());
		holder.user.setText(item.getUser());
		holder.time.setText(item.getTime());
		holder.count.setText(item.getCount());
		
		// ����Volley����ͼƬ
		ImageListener listener = ImageLoader.getImageListener(holder.img, 0, R.drawable.jiazaishibai);
		mImageLoader.get(item.getImgUrl()+".jpg"+ "?rank=" + position, listener);

		return convertView;
	}

	
	private static class ViewHolder {
		ImageView img;
		TextView user;
		TextView time;
		TextView text;
		TextView count;
		
	}

	/**
	 * ˢ���б�
	 * 
	 * @param newList
	 */
	public void notifyDataSetChanged(List<VolleyItem> newList) {
		this.items = newList;
		notifyDataSetChanged();
	}

}
