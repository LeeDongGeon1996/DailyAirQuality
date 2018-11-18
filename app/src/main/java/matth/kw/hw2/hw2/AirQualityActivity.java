package matth.kw.hw2.hw2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import VO.AirQualityVO;

public class AirQualityActivity extends AppCompatActivity {

    TextView mTextIsanhwa;
    TextView mTextOzon;
    TextView mTextIlsanhwa;
    TextView mTextAhwangsan;
    TextView mTextFindDust;
    TextView mTextUtraFindDust;

    AirQualityVO airQuality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_quality);
        setTitle("서울 대기질 정보");

        mTextIsanhwa = findViewById(R.id.textView_isanhwa);
        mTextOzon = findViewById(R.id.textView_ozon);
        mTextIlsanhwa = findViewById(R.id.textView_ilsanhwa);
        mTextAhwangsan = findViewById(R.id.textView_ahwangsan);
        mTextFindDust = findViewById(R.id.textView_fineDust);
        mTextUtraFindDust = findViewById(R.id.textView_ultraFineDust);

        Intent intent = getIntent();
        airQuality = (AirQualityVO) intent.getSerializableExtra("airQuality");
        mTextIsanhwa.setText(airQuality.getNO2().toString());
        mTextOzon.setText(airQuality.getO3().toString());
        mTextIlsanhwa.setText(airQuality.getCO().toString());
        mTextAhwangsan.setText(airQuality.getSO2().toString());
        mTextFindDust.setText(airQuality.getPM10().toString());
        mTextUtraFindDust.setText(airQuality.getPM25().toString());

    }
}
