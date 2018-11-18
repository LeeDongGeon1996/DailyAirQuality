package matth.kw.hw2.hw2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import VO.AirQualityVO;

public class MainActivity extends AppCompatActivity {

    public static final int MY_REQUEST_INTERNET = 0;
    public static final String REQUEST_DAILY = "REQUEST_DAILY";
    public static final String REQUEST_TODAY = "REQUEST_TODAY";
    String dataURL = "http://openAPI.seoul.go.kr:8088/6856584f4b6d61743131366f654b5a4f/json/DailyAverageAirQuality/1/5";
    EditText mAirQualityDate;
    EditText mAirQualityArea;
    EditText mFineDustArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("서울 대기질 정보");

        mAirQualityDate = findViewById(R.id.input_date);
        mAirQualityArea = findViewById(R.id.input_area);
        mFineDustArea = findViewById(R.id.input_fine_dust_area);

        if(checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "인터넷 권한을 요청합니다.", Toast.LENGTH_LONG);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, MY_REQUEST_INTERNET);
        }
    }

    public void onClickDailyAirQuality(View view){

        //Use network.
        new DailyAirQualitySearchTask().execute(REQUEST_DAILY, mAirQualityDate.getText().toString(), mAirQualityArea.getText().toString());
    }

    public void onClickFIneDust(View view){

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMMdd", Locale.KOREA );
        Date date = new Date();
        String today = mSimpleDateFormat.format (date);

        //Use network.
        new DailyAirQualitySearchTask().execute(REQUEST_TODAY, today, mFineDustArea.getText().toString());
    }

    public void onClickMyWebBrowser(View view){
        startActivity(new Intent(this, MyWebBrowserActivity.class));
    }

    private class DailyAirQualitySearchTask extends AsyncTask<String, Integer, Boolean>{

        List<AirQualityVO> airQuality = null;
        String requestMode=null;

        @Override
        protected Boolean doInBackground(String... strings) {

            requestMode = strings[0];

            String requestURL = dataURL + "/" +  strings[1] + "/" + strings[2];

            try {

                InputStream inputStream = new URL(requestURL).openStream();
                airQuality = readJsonStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {

            Intent intent = null;

            if (result == true) {
                if(!airQuality.isEmpty()){
                    if(requestMode.equals(REQUEST_DAILY)) {
                        intent = new Intent(getApplicationContext(), AirQualityActivity.class);
                    }
                    else if(requestMode.equals(REQUEST_TODAY)){
                        intent = new Intent(getApplicationContext(), FineDustActivity.class);
                    }
                    intent.putExtra("airQuality", airQuality.get(0));

                    startActivity(intent);
                }
                else{
                    //정보가 없는 경우.
                    Toast.makeText(getApplicationContext(), "해당하는 정보가 없습니다.", Toast.LENGTH_LONG);
                }
            }
            else{
                //요청이 실패한 경우.
                Toast.makeText(getApplicationContext(), "요청에 실패하였습니다.", Toast.LENGTH_LONG);
            }
            super.onPostExecute(result);
        }
    }

    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {

        switch (requestCode) {
            case MY_REQUEST_INTERNET: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "권한이 승인되었습니다.", Toast.LENGTH_LONG);

                } else {
                    Toast.makeText(this, "권한이 필요합니다.", Toast.LENGTH_LONG);
                    finish();
                }
            }
            return;
        }
    }


    public List<AirQualityVO> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readAirQualityArray(reader);
        } finally {
            reader.close();
        }
    }

    public List<AirQualityVO> readAirQualityArray(JsonReader reader) throws IOException {
        List<AirQualityVO> data = new ArrayList<AirQualityVO>();

        reader.beginObject();
        while(reader.hasNext()){
            if(reader.nextName().equals("DailyAverageAirQuality")){
                reader.beginObject();
                while(reader.hasNext()) {
                    if (reader.nextName().equals("row")) {
                        reader.beginArray();
                        while (reader.hasNext()) {
                            data.add(readAirQuality(reader));
                        }
                        reader.endArray();
                    } else {
                        reader.skipValue();
                    }
                }
                reader.endObject();
            }
            else{
                reader.skipValue();
            }
        }
        reader.endObject();

        return data;
    }

    public AirQualityVO readAirQuality(JsonReader reader) throws IOException {

        AirQualityVO data = new AirQualityVO();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("MSRDT_DE")) {
                data.setMSRDT_DE(reader.nextString());
            } else if (name.equals("MSRSTE_NM")) {
                data.setMSRSTE_NM(reader.nextString());
            } else if (name.equals("NO2")) {
                data.setNO2(reader.nextDouble());
            } else if (name.equals("O3")) {
                data.setO3(reader.nextDouble());
            } else if (name.equals("CO")) {
                data.setCO(reader.nextDouble());
            } else if (name.equals("SO2")) {
                data.setSO2(reader.nextDouble());
            } else if (name.equals("PM10")) {
                data.setPM10(reader.nextDouble());
            } else if (name.equals("PM25")) {
                data.setPM25(reader.nextDouble());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();

        return data;
    }


}
