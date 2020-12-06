package com.githubme.githubtest;
import android.database.sqlite.*;
import android.content.*;
import android.database.*;

public class DatabaseLogin extends SQLiteOpenHelper
{
	public static final String DATABASE_NAME ="login.db";
	
	public DatabaseLogin(Context ct)
	{
		super(ct,DATABASE_NAME,null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE user_tb(id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,password TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXISTS user_tb");
		onCreate(db);
	}
	
	public boolean Insert(String username,String password)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues val = new ContentValues();
		val.put("username",username);
		val.put("password",password);
		long result = db.insert("user_tb",null,val);
		if(result == -1)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean CheckUsername(String username)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cs = db.rawQuery("SELECT * FROM user_tb WHERE username =?",new String[]{username});
		if(cs.getCount() > 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean CheckLogin(String username,String password)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cs = db.rawQuery("SELECT * FROM user_tb WHERE username =? AND password =?",new String[]{username,password});
		if(cs.getCount() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
