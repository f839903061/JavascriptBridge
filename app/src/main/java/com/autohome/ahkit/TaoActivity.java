package com.autohome.ahkit;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

        //由于通过当你使用btn来执行js操作的时候，你的网页是已经加载完毕了的，
        // 但是如果你的打开网页直接又去执行js操作的时候可能因为异步的原因而导致callJS()没有反应
        //解决办法如下新建WebViewClient，当网页加载完毕了之后再去执行就可以了
        webView.setWebViewClient(new MyWebViewClient());

    }

    public class MyWebViewClient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            webView.loadUrl("javascript:javacalljs()");//这个是自动出发的，而btn是手动触发的
        }
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
