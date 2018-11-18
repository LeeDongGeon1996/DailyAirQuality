package matth.kw.hw2.hw2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MyMemoActivity extends AppCompatActivity {

    TextView mMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_text);
        setTitle("MyMemo");

        mMemo = findViewById(R.id.textView_memo);
        mMemo.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
    }

}
