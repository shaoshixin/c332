package c352.pack.namespace;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import c352.pack.namespace.Text4Activity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author zhiwei.zhao
 * @project PhotoUtils
 * @Description 拍照、从系统相册选择上传
 */
public class PhotoUtilsActivity extends  Activity{
	private static final int MAX_COUNT = 100;
	private Button mback;
	@SuppressWarnings("unused")
	private Button mcomit;

	private ImageButton mcamera;

	@SuppressWarnings("unused")
	private TextView mtitle, mTextView, msign;

	private EditText mSuggest;

	@SuppressWarnings("unused")
	private CheckBox mtencent, msina, mqq, mrenren, mqzoon, mdouban;

	private Bitmap bitmap;
	String Name;
	int k=0;

	private LinearLayout ll_imgs;

	@Override
	public  void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sos);
		ll_imgs = (LinearLayout) findViewById(R.id.ll_imgs);

//		Intent intent=getIntent();
//		String name=intent.getStringExtra("c352.pack.namespace.username");
		
		mback = (Button) findViewById(R.id.back);
		mcomit = (Button) findViewById(R.id.comit);
		mtitle = (TextView) findViewById(R.id.title);

		mSuggest = (EditText) findViewById(R.id.suggest);
		mSuggest.setSingleLine(false);
		mSuggest.addTextChangedListener(mTextWatcher);
		mSuggest.setSelection(mSuggest.length());
		mTextView = (TextView) findViewById(R.id.count);
		setLeftCount();
		
		mcamera = (ImageButton) findViewById(R.id.camera);

		mback.setOnClickListener(new OnClickListener() { 

			@Override
			public void onClick(View v) {
				PhotoUtilsActivity.this.finish();
			}
		});

		mcamera.setOnClickListener(new OnClickListener() { 

			@Override
			public void onClick(View v) {

				if(Quanjubianliang.Zusername!=13011800)
				showCustomAlertDialog();
				else {
					Toast.makeText(PhotoUtilsActivity.this,"要先登录才可以上传哟！", Toast.LENGTH_LONG).show();
				}
			}

		});
////////////图片提交
		mcomit.setOnClickListener(new OnClickListener() { 

			@Override
			public void onClick(View v) {

				if(Quanjubianliang.Zusername!=13011800)
				{comitpicture();
				k++;}
				else {
					Toast.makeText(PhotoUtilsActivity.this,"要先登录才可以上传哟！", Toast.LENGTH_LONG).show();
				}
			}

		});

	}

	////////////////////实现图片上传的函数
	public void comitpicture(){
		Calendar c = Calendar.getInstance();  

		     
        int year = c.get(Calendar.YEAR);      
        int month = c.get(Calendar.MONTH)+1;
        if(month==13)month=1;
        int day = c.get(Calendar.DAY_OF_MONTH);      
        int minute = c.get(Calendar.MINUTE) ;      
        int hour = c.get(Calendar.HOUR_OF_DAY);       
        int sec =c.get(Calendar.SECOND);
        String tm = "";
		tm +=year+"/"+month+"/"+day+" "+hour+":"+minute+":"+sec;
		String image=bitmaptoString(bitmap);
		String ImagefileName=Name+"c"+k;
		Web.FileUploadImage(ImagefileName, image);
		String text=mSuggest.getText().toString();
		if(text.contains("-")) text=text.replaceAll("-", "_");
		String lx=String.valueOf(Quanjubianliang.point1.getLatitudeE6());
		String ly=String.valueOf(Quanjubianliang.point1.getLongitudeE6());
		String s=Web.insertInfo("http://qianxiang.vicp.cc/images/"+ImagefileName, Quanjubianliang.Zusername, 0, tm, text,lx,ly);
		if(s.equals("上传失败")){Toast.makeText(PhotoUtilsActivity.this,s, Toast.LENGTH_LONG).show();}
		else {
			Intent intent =new Intent(PhotoUtilsActivity.this,MyspaceActivity.class);
			startActivity(intent);
			PhotoUtilsActivity.this.finish();
		}
	};
	
	
	/////////////////
	public void showCustomAlertDialog() {
		final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.show();
		Window win = alertDialog.getWindow();

		WindowManager.LayoutParams lp = win.getAttributes();
		win.setGravity(Gravity.LEFT | Gravity.BOTTOM);
		lp.alpha = 0.7f;
		win.setAttributes(lp);
		win.setContentView(R.layout.dialog);

		Button cancelBtn = (Button) win.findViewById(R.id.camera_cancel);
		cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialog.cancel();
			}
		});
		Button camera_phone = (Button) win.findViewById(R.id.camera_phone);
		camera_phone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				systemPhoto();
				alertDialog.cancel();
			}

		});
		Button camera_camera = (Button) win.findViewById(R.id.camera_camera);
		camera_camera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cameraPhoto();
				alertDialog.cancel();
			}

		});

	}

	private final int SYS_INTENT_REQUEST = 0XFF01;
	private final int CAMERA_INTENT_REQUEST = 0XFF02;

	/**
	 * 打开系统相册
	 */
	private void systemPhoto() {

		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent, SYS_INTENT_REQUEST);

	}

	/**
	 * 调用相机拍照
	 */
	private void cameraPhoto() {
		String sdStatus = Environment.getExternalStorageState();
		/* 检测sd是否可用 */
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
			Toast.makeText(this, "SD卡不可用！", Toast.LENGTH_SHORT).show();
			return;
		}
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		startActivityForResult(intent, CAMERA_INTENT_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == SYS_INTENT_REQUEST && resultCode == RESULT_OK
				&& data != null) {
			try {
				Uri uri = data.getData();
				Cursor cursor = getContentResolver().query(uri, null, null,
						null, null);
				cursor.moveToFirst();

				String imageFilePath = cursor.getString(1);
				System.out.println("File path is----->" + imageFilePath);

				FileInputStream fis = new FileInputStream(imageFilePath);
				bitmap = BitmapFactory.decodeStream(fis);

				int width = bitmap.getWidth();
				int height = bitmap.getHeight();
				System.out.println("压缩前的宽高----> width: " + width + " height:"
						+ height);

				/* 压缩获取的图像 */
				showImgs(bitmap, false);

				fis.close();
				cursor.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (requestCode == CAMERA_INTENT_REQUEST
				&& resultCode == RESULT_OK && data != null) {
			cameraCamera(data);
		}
		super.onActivityResult(requestCode, resultCode, data);

	}

	/**
	 * @param bitmap
	 * @return 压缩后的bitmap
	 */
	private Bitmap compressionBigBitmap(Bitmap bitmap, boolean isSysUp) {
		Bitmap destBitmap = null;
		/* 图片宽度调整，大于这个比例的，按一定比例缩放到宽度 */
		if (bitmap.getWidth() > 500) {
			float scaleValue = (float) (500f / bitmap.getWidth());
			System.out.println("缩放比例---->" + scaleValue);

			Matrix matrix = new Matrix();
			/* 针对系统拍照，旋转90° */
			if (isSysUp)
				matrix.setRotate(90);
			matrix.postScale(scaleValue, scaleValue);

			destBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);
			int widthTemp = destBitmap.getWidth();
			int heightTemp = destBitmap.getHeight();
			Log.i("zhiwei.zhao", "压缩前的宽高----> width: " + heightTemp
					+ " height:" + widthTemp);
		} else {
			return bitmap;
		}
		return destBitmap;

	}

	/**
	 * @param data
	 *            拍照后获取照片
	 */
	private void cameraCamera(Intent data) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String name = formatter.format(System.currentTimeMillis()) + ".jpg";
		Log.i("zhiwei.zhao", "image name:" + name);
		Toast.makeText(this, name, Toast.LENGTH_LONG).show();
		Bundle bundle = data.getExtras();
		/* 获取相机返回的数据，并转换为Bitmap图片格式 */
		//Bitmap bitmap = (Bitmap) bundle.get("data");
		bitmap = (Bitmap) bundle.get("data");
		FileOutputStream b = null;
		Name=formatter.format(System.currentTimeMillis());

		String path = Environment.getExternalStorageDirectory().getPath();
		File file = new File(path + "/myImage/");
		/** 检测文件夹是否存在，不存在则创建文件夹 **/
		if (!file.exists() && !file.isDirectory())
			file.mkdirs();
		String fileName = file.getPath() + "/" + name;
		Log.i("zhiwei.zhao", "camera file path:" + fileName);
		try {
			b = new FileOutputStream(fileName);
			/* 把数据写入文件 */
			bitmap.compress(Bitmap.CompressFormat.JPEG, 30, b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (b == null)
					return;
				b.flush();
				b.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		showImgs(bitmap, true);
	}

	/**
	 * 展示选择的图片
	 * 
	 * @param bitmap
	 * @param isSysUp
	 */
	private void showImgs(Bitmap bitmap, boolean isSysUp) {
		if (ll_imgs.getChildCount() > 0) {
			Toast.makeText(this, "最多上传一张图片哦，可点击删除已选择的图片", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		Bitmap _bitmap = compressionBigBitmap(bitmap, isSysUp);
		final ImageView im = new ImageView(this);
		im.setPadding(10, 0, 0, 0);
		im.setImageBitmap(_bitmap);
		ll_imgs.addView(im);
		im.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				BitmapDrawable bitmapDrawable = (BitmapDrawable) im
						.getDrawable();
				if (bitmapDrawable != null
						&& !bitmapDrawable.getBitmap().isRecycled()) {
					bitmapDrawable.getBitmap().recycle();
				}
				ll_imgs.removeView(im);
			}
		});
	}

	private TextWatcher mTextWatcher = new TextWatcher() {

		private int editStart;

		private int editEnd;

		public void afterTextChanged(Editable s) {
			editStart = mSuggest.getSelectionStart();
			editEnd = mSuggest.getSelectionEnd();

			/* 先去掉监听器，否则会出现栈溢出 */
			mSuggest.removeTextChangedListener(mTextWatcher);

			/*
			 * 注意这里只能每次都对整个EditText的内容求长度，不能对删除的单个字符求长度
			 * 因为是中英文混合，单个字符而言，calculateLength函数都会返回1 当输入字符个数超过限制的大小时，进行截断操作
			 */
			while (calculateLength(s.toString()) > MAX_COUNT) {
				s.delete(editStart - 1, editEnd);
				editStart--;
				editEnd--;
			}

			/* 将这行代码注释掉就不会出现后面所说的输入法在数字界面自动跳转回主界面的问题了 */
			//mEditText.setText(s);
			mSuggest.setSelection(editStart);

			/* 恢复监听器 */
			mSuggest.addTextChangedListener(mTextWatcher);

			setLeftCount();
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

		}

	};

	private long calculateLength(CharSequence c) {
		double len = 0;
		for (int i = 0; i < c.length(); i++) {
			int tmp = (int) c.charAt(i);
			if (tmp > 0 && tmp < 127) {
				len += 0.5;
			} else {
				len++;
			}
		}
		return Math.round(len);
	}

	private void setLeftCount() {
		mTextView.setText("还可以输入"
				+ String.valueOf((MAX_COUNT - getInputCount())) + "个字符哦~");
	}

	private long getInputCount() {
		return calculateLength(mSuggest.getText().toString());
	}

	public String bitmaptoString(Bitmap bitmap) {  
		  
		  
		  
        // 将Bitmap转换成字符串  
  
        String string = null;  
  
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();  
  
        bitmap.compress(CompressFormat.PNG, 100, bStream);  
  
        byte[] bytes = bStream.toByteArray();  
  
        string = Base64.encodeToString(bytes, Base64.DEFAULT);  
  
        return string;  
  
}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
	} 
	
}
