package com.example.qdemotest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.PublicKey;

import org.json.JSONException;
import org.json.JSONObject;

import com.highk.qdemo.json.JsonUtil;
import com.highk.qdemo.views.EventInjectUtil;
import com.highk.qdemo.views.ViewInjectUtils;
import com.highk.qdemo.views.annotation.ContentView;
import com.highk.qdemo.views.annotation.ViewInject;
import com.highk.qdemo.views.annotation.event.OnClick;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


@ContentView(R.layout.activity_main)
public class MainActivity extends Activity {
	
	@ViewInject(R.id.button1)
	Button button;
	
	@ViewInject(R.id.button2)
	Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.injectContentView(this);
        ViewInjectUtils.injectViews(this);
        EventInjectUtil.eventInject(this);
       
    }
    
    @OnClick(R.id.button1)
	public void testButton(View view) {
		Log.i("test", "testButton");
	}
    
    @OnClick(R.id.button2) 
    public void testJson(View view) throws JSONException{
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("name", "gaofeng");
    	jsonObject.put("age", 11);
    	jsonObject.put("userName", "userName");
    	//jsonObject.put("sex", new Character('A'));
    	jsonObject.put("small", 123);
    	BigDecimal bigDecimal = new BigDecimal("12345.5431");
    	jsonObject.put("decimal", bigDecimal);
    	Log.i("json", jsonObject.toString());
    	String data = jsonObject.toString();
    	JSONObject jsonObject2 = new JSONObject(data);
    	User user = JsonUtil.parseObject(jsonObject, User.class);
    	Log.i("jsontest", user.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
