/*Group--AJ

File Name : SlideShow.java
Members : Satyanarayana 800844992
          Sumanth Krishna 800808182
*/
package com.example.imagesgetting;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

public class SlideShow extends Activity {

	int i;
	ImageSwitcher Switch;
	ImageView images;
	int mFlipping = 0 ;
	float initialX;
	Bitmap bitmap;
	int imgid[]={R.drawable.ic_launcher,R.drawable.background_logo,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,
			R.drawable.ic_launcher,R.drawable.ic_launcher};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Switch = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
		images = (ImageView) findViewById(R.id.img1);
		try{
			updateUI();
		} catch(OutOfMemoryError e) {

		} finally {
		}
	}

	RefreshHandler refreshHandler=new RefreshHandler();
	class RefreshHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			SlideShow.this.updateUI();
		}
		public void sleep(long delayMillis){
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	};
	@SuppressLint("NewApi")
	public void updateUI(){
		refreshHandler.sleep(2000);
		if(i<MainActivity.imageslist.size()){
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

			StrictMode.setThreadPolicy(policy); 
			
			String getImage = MainActivity.imageslist.get(i);
			try {
				bitmap = BitmapFactory.decodeStream((InputStream)new URL(getImage).getContent());
				images.setImageBitmap(bitmap);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
			i++;
		}

		//		if(i<imgid.length){
		//			images.setImageResource(imgid[i]);
		//			i++;
		//			}
	}
	

}




