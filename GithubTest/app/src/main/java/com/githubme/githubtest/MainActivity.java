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
				AlertDialog.Builder b = new AlertDialog.Builder(this);
				b.setTitle("About App");
				b.setMessage("\nProject name : Github\n\nApp name : [GH]first\n\nApp version : Demo");
				b.setPositiveButton("Close",new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface c,int i)
					{
						c.dismiss();
					}
				});
				b.setCancelable(false);
				b.show();
				break;
				
			case R.id.item_logout:
				SharedPreferences sh = getSharedPreferences(JavLogin.MyPREFS,Context.MODE_PRIVATE);
				SharedPreferences.Editor e = sh.edit();
				e.remove("passKey");
				e.commit();
				AlertDialog.Builder a = new AlertDialog.Builder(this);
				a.setMessage("Do you want logout?");
				a.setNegativeButton("No",new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface n,int i)
					{
						n.cancel();
					}
				});
				
				a.setPositiveButton("Yes",new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface y,int i)
					{
						Intent out = new Intent(MainActivity.this,JavLogin.class);
						startActivity(out);
						finish();
					}
				});
				a.setCancelable(false);
				a.show();
				break;
		}
		return super.onOptionsItemSelected(item);
	} 
}
