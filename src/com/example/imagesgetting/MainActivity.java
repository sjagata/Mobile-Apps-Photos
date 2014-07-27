/*Group--AJ

File Name : MainActivity.java
Members : Satyanarayana 800844992
          Sumanth Krishna 800808182
*/


package com.example.imagesgetting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	ProgressDialog pd;
	public String response_str;

	public static ArrayList<String> imageslist=new ArrayList<String>();
	private LruCache<String, Bitmap> mMemoryCache;

	int click=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

	    // Use 1/8th of the available memory for this memory cache.
	    final int cacheSize = maxMemory / 8;

	    mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
	        @SuppressLint("NewApi")
			@Override
	        protected int sizeOf(String key, Bitmap bitmap) {
	            // The cache size will be measured in kilobytes rather than
	            // number of items.
	            return bitmap.getByteCount() / 1024;
	        }
	    };
		
		setContentView(R.layout.activity_main);
		setTitle("UNCC Photos");
	}

	public void photosgetting(View v)
	{
		click=1;
		new imagesgetting().execute();
	}
	public void sliding(View v)
	{
		click=2;
		new imagesgetting().execute();
	}

	public class imagesgetting extends AsyncTask<Void, Void, Void>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd=new ProgressDialog(MainActivity.this);
			pd.setTitle("Retrieving image Urls......");
			pd.setCanceledOnTouchOutside(false);
			pd.show();
		}

		@SuppressLint("NewApi")
		@Override
		protected Void doInBackground(Void... args) {
			// TODO Auto-generated method stub

			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

			StrictMode.setThreadPolicy(policy); 
			URL textUrl;
			try {
				textUrl = new URL("http://liisp.uncc.edu/~mshehab/api/photos.txt");
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(textUrl.openStream()));
				String StringBuffer;

				while ((StringBuffer = bufferReader.readLine()) != null) {
					imageslist.add(""+StringBuffer);
				}
				bufferReader.close();
				Log.v("images url", ""+imageslist);

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			if(click==1)
			{
				Intent i=new Intent(MainActivity.this, ImagesActivity.class);
				startActivity(i);
			}
			if(click==2)
			{
				Intent i=new Intent(MainActivity.this, SlideShow.class);
				startActivity(i);

			}
		}
	}

}
