/*Group--AJ

File Name : ImagesActivity.java
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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;

public class ImagesActivity extends Activity{
	ImageSwitcher Switch;
	ImageView images;
	
	ProgressDialog pd;

	float initialX;
	int position = 0;
	Bitmap bitmap;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_images);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		Switch = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
		images = (ImageView) findViewById(R.id.imageView1);

		String getImage = MainActivity.imageslist.get(position);
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.images, menu);
		return true;
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			pd=new ProgressDialog(ImagesActivity.this);
			pd.setTitle("Loading  Image......");
			pd.setCanceledOnTouchOutside(false);
			pd.show();
			initialX = event.getX();
			break;
		case MotionEvent.ACTION_UP:
			float finalX = event.getX();

			try {		

				if (initialX > finalX)
				{
					position++;
					// cursor.moveToPosition(position);
					// int imageID = cursor.getInt(columnIndex);
					String getImage = MainActivity.imageslist.get(position);
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
					//images.setBackgroundResource(R.drawable.mb__messagebar_divider);
					Switch.showNext();
					pd.dismiss();
//					Toast.makeText(getApplicationContext(), "Next Image", Toast.LENGTH_LONG).show();
				}
				else
				{
					if(position > 0)
					{
						String getImage = MainActivity.imageslist.get(position);
						Bitmap bitmap;
						try {
							bitmap = BitmapFactory.decodeStream((InputStream)new URL(getImage).getContent());
							images.setImageBitmap(bitmap);

						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}                  //images.setBackgroundResource(R.drawable.ic_launcher);
//						Toast.makeText(getApplicationContext(), "previous Image", Toast.LENGTH_LONG).show();
						Switch.showPrevious();
						pd.dismiss();
						position= position-1;
					}
					else
					{
						Toast.makeText(getApplicationContext(), "No More Images To Swipe",
								Toast.LENGTH_LONG).show();
					}
				}
			} catch(OutOfMemoryError e) {
				
			} finally {			

			}
			break;
		}
		return false;
	}
	
	
}
