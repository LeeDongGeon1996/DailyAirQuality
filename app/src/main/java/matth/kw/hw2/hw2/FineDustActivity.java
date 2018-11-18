package matth.kw.hw2.hw2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import VO.AirQualityVO;

public class FineDustActivity extends AppCompatActivity {

    TextView mTextFindDust;
    TextView mTextUtraFindDust;

    AirQualityVO airQuality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine_dust);
        setTitle("서울 대기질 정보");

        mTextFindDust = findViewById(R.id.textView_today_fine_dust);
        mTextUtraFindDust = findViewById(R.id.textView_today_ultra_fine_dust);

        Intent intent = getIntent();
        airQuality = (AirQualityVO) intent.getSerializableExtra("airQuality");
        mTextFindDust.setText(airQuality.getPM10().toString());
        mTextUtraFindDust.setText(airQuality.getPM25().toString());

    }
}
