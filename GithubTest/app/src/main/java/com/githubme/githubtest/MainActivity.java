package com.githubme.githubtest;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_main,menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.item_about:
				break;
				
			case R.id.item_logout:
				SharedPreferences sh = getSharedPreferences(JavLogin.MyPREFS,Context.MODE_PRIVATE);
				SharedPreferences.Editor e = sh.edit();
				e.remove("passKey");
				e.commit();
				Intent out = new Intent(MainActivity.this,JavLogin.class);
				startActivity(out);
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	} 
}
