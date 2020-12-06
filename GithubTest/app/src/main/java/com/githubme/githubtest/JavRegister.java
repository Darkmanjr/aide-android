package com.githubme.githubtest;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.text.*;
import android.content.*;

public class JavRegister extends Activity
{
	DatabaseLogin dbLogin;
	private EditText rUser;
	private EditText rPass;
	private EditText rConfirm;
	private Button bRegis;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		
		dbLogin = new DatabaseLogin(this);
		
		rUser = (EditText) findViewById(R.id.regis_username);
		rPass = (EditText) findViewById(R.id.regis_password);
		rConfirm = (EditText) findViewById(R.id.regis_confirm_password);
		bRegis = (Button) findViewById(R.id.button_register);
		
		bRegis.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String username = rUser.getText().toString();
				String password = rPass.getText().toString();
				String confirm = rConfirm.getText().toString();
				if(TextUtils.isEmpty(username) && TextUtils.isEmpty(password) && TextUtils.isEmpty(confirm))
				{
					Toast.makeText(JavRegister.this,"REGISTER ERROR!!",Toast.LENGTH_SHORT).show();
				}
				if(TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) || (TextUtils.isEmpty(confirm) && !TextUtils.isEmpty(confirm)))
				{
					rUser.setError("No Empty Field");
					rUser.requestFocus();
					return;
				}
				if(!TextUtils.isEmpty(username) && TextUtils.isEmpty(password) || (TextUtils.isEmpty(confirm) && !TextUtils.isEmpty(confirm)))
				{
					rPass.setError("No Empty Field");
					rPass.requestFocus();
					return;
				}
				if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && confirm.equals(password))
				{
					boolean chkUser = dbLogin.CheckUsername(username);
					if(chkUser == true)
					{
						boolean insert = dbLogin.Insert(username,password);
						if(insert == true)
						{
							Toast.makeText(JavRegister.this,"REGISTER IS SUCCESS!!",Toast.LENGTH_SHORT).show();
							rUser.setText("");
							rPass.setText("");
							rConfirm.setText("");
						}
						else
						{
							Toast.makeText(JavRegister.this,"ERROR",Toast.LENGTH_SHORT).show();
						}
					}
					else
					{
						rUser.setError("Username has already");
						rUser.requestFocus();
						return;
					}
				}
				else
				{
					if(!confirm.equals(password) && !TextUtils.isEmpty(username))
					{
						rConfirm.setError("Password Don't Match");
						rConfirm.requestFocus();
						return;
					}

				}
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				Intent b = new Intent(JavRegister.this,JavLogin.class);
				startActivity(b);
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	} 
}
