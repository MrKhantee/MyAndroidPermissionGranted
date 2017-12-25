package com.tromin.myapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{
    private Context mContext=null;
    protected Button myButton1 ;
    protected Button myButton2 ;
    protected Button myButton3 ;
    //------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this ;
        myButton1 = (Button)findViewById(R.id.button1) ;
        myButton2 = (Button)findViewById(R.id.button2) ;
        myButton3 = (Button)findViewById(R.id.button3) ;
        //------------------------------------
        myButton1.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TakePhoto("takePhoto");
            }
        });
        myButton2.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TakePhoto("openAlbum");
            }
        });
        myButton3.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AndroidQuit();
            }
        });
    }

    // 定義一個顯示 一按鍵對話盒 的方法，在Unity中調用此方法
    public void ShowDialog(final String _title, final String _content , final String _buttonName)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(_title).setMessage(_content).setPositiveButton(_buttonName, null);
                builder.show();
            }
        });
    }
    // 定義一個顯示Toast的方法，在Unity中調用此方法
    public void ShowToast(final String StringToast)
    {
        // 同樣需要在UI線程下執行
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Toast.makeText(getApplicationContext(),StringToast, Toast.LENGTH_LONG).show();
            }
        });
    }
    //  定義一個手機振動的方法，在Unity中調用此方法
    public void SetVibrator()
    {
        Vibrator mVibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        //mVibrator.vibrate(new long[]{200, 2000, 2000, 200, 200, 200}, -1); //-1：表示不重復 0：循環的震動
        mVibrator.vibrate(2000);
    }
    //呼叫Android的Quit
    public void AndroidQuit()
    {
        finish() ;
    }
    // 定義一個打開Activity的方法，Unity中會調用這個方法，用於區分打開攝像機 開始本地相冊
    public void TakePhoto(String stringType)
    {
        Intent intent = new Intent(mContext,WebViewActivity.class);
        intent.putExtra("type", stringType);
        this.startActivity(intent);
    }
    //  定義一個呼叫Unity的方法
    // 第一個參數是unity中的對象名字，記住是對象名字，不是腳本類名
    // 第二個參數是函數名
    // 第三個參數是傳給函數的參數，目前只看到一個參數，並且是string的，自己傳進去轉吧
    public void AndroidCallUnity(String _objectName , String _functionName, String _content)
    {
//          UnityPlayer.UnitySendMessage(_objectName, _functionName, _content);
    }
}
