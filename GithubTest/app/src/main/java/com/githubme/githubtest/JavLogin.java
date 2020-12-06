package com.githubme.githubtest;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.graphics.*;
import android.view.View.*;
import android.view.*;
import android.content.*;
import android.text.*;

public class JavLogin extends Activity
{
	DatabaseLogin dbLogin;
	private EditText eUser;
	private EditText ePass;
	private Button bLogin;
	private TextView tRegis;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		dbLogin = new DatabaseLogin(this);
		
		eUser = (EditText) findViewById(R.id.edit_username);
		ePass = (EditText) findViewById(R.id.edit_password);
		bLogin = (Button) findViewById(R.id.button_login);
		tRegis = (TextView) findViewById(R.id.text_register);
		
		tRegis.setPaintFlags(tRegis.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);
		tRegis.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent r = new Intent(JavLogin.this,JavRegister.class);
				startActivity(r);
				finish();
			}
		});
		
		bLogin.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String username = eUser.getText().toString();
				String password = ePass.getText().toString();
				if(TextUtils.isEmpty(username) && TextUtils.isEmpty(password))
				{
					Toast.makeText(JavLogin.this,"Login Error!!",Toast.LENGTH_SHORT).show();
				}
				if(!TextUtils.isEmpty(username) && TextUtils.isEmpty(password))
				{
					ePass.setError("Pls,input your Password");
					ePass.requestFocus();
					return;
				}
				if(TextUtils.isEmpty(username) && !TextUtils.isEmpty(password))
				{
					eUser.setError("Pls,input your Username");
					eUser.requestFocus();
					return;
				}
				if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password))
				{
					boolean chkLogin = dbLogin.CheckLogin(username,password);
					if(chkLogin == true)
					{
						Intent i = new Intent(JavLogin.this,MainActivity.class);
						startActivity(i);
						finish();
					}
					else
					{
						Toast.makeText(JavLogin.this,"Wrong!! Pls,Check your Username or Password again",Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	} 
}
