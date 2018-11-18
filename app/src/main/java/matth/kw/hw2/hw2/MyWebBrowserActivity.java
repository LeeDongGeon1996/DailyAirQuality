package matth.kw.hw2.hw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MyWebBrowserActivity extends AppCompatActivity {

    EditText mURL;
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);
        setTitle("MyWebBrowser");

        mURL = findViewById(R.id.editText_url);
        mWebView = findViewById(R.id.webView);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    public void onClickLoadURL(View view){
        String url = mURL.getText().toString();
        if(!(url.contains("http://") || url.contains("https://"))){
            url = "http://" + url;
        }
        Log.d("loadURL : ", url);
        mWebView.loadUrl(url);
    }
}
