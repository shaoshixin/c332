package c352.pack.namespace;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

	public class huanbaoActivity extends Activity{
		private static int[] images = {R.drawable.hb1,
			R.drawable.hb2,R.drawable.hb3,R.drawable.catoon,R.drawable.hb4,R.drawable.hb5,R.drawable.hb6};
		Gallery gallery;
		
		
		
		
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.huanbaozhishi);
			gallery = (Gallery) findViewById(R.id.gallery1);
	        gallery.setAdapter(new ImageAdapter(this));
	        gallery.setSelection(images.length / 2);
			
	  }
		
		  private class ImageAdapter extends BaseAdapter {
		        private Context context;
		 
		        public ImageAdapter(Context context) {
		            this.context = context;
		        }
		 
		        // 可以return images.lenght(),在这里返回Integer.MAX_VALUE
		        // 是为了使图片循环显示
		        public int getCount() {
		            return Integer.MAX_VALUE;
		        }
		 
		        public Object getItem(int position) {
		            return null;
		        }
		 
		        public long getItemId(int position) {
		            return 0;
		        }
		 
		        public View getView(int position, View convertView, ViewGroup parent) {
		            ImageView iv = new ImageView(context);
		            //设置具体要显示的图片
		            iv.setImageResource(images[position % images.length]);
		            //设置ImageView的高度和宽度
		            iv.setLayoutParams(new Gallery.LayoutParams(
		                    LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		            iv.setAdjustViewBounds(true);
		            return iv;
		        }
		    }
		}