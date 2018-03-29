package com.autohome.ahkit;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by 一个人的暗 on 2018/3/29.
 *
 * @author: 一个人的暗
 * @Emial:532245792@qq.com
 */

public class TaoActivity extends Activity {

    Button btn1, btn2;
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao);
        initView();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        webView = (WebView) findViewById(R.id.webview);
        //下面这句可以让Android端调用html的方法
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/web.html");
        //这一句，可以让html界面调用Android端的方法
        webView.addJavascriptInterface(TaoActivity.this,"android");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:javacalljs()");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:javacalljswith(" + "'禽兽，为什么还是这个女孩！你就不能放过她？'" + ")");
            }
        });
    }


    @JavascriptInterface
    public void startFunction() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TaoActivity.this, "禽兽！放开那个女孩！有种的冲阿诺德施瓦辛格去啊！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @JavascriptInterface
    public void startFunction(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(TaoActivity.this).setMessage(text).show();
            }
        });


    }

}
