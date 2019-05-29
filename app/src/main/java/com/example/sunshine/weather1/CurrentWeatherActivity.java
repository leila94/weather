package com.example.sunshine.weather1;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class CurrentWeatherActivity extends AppCompatActivity {

    private ImageView logo0;
    Toolbar toolbar;
    private TextView des0,min,max,temp,win,hum,pres, date, clock,sunset,sunrise;
    public static final String extra_message = "com.example.sunshine.weather1.MESSAGE";
    public static final String extra_messageId = "com.example.sunshine.weather1.MESSAGE2";
    private int cityMainId;
    private LinearLayout layout;
    private Entity1 entity1;
    private WeatherViewModel weatherViewModel;
    private int primaryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CustomPager viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.tablayout);

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new One(), "Next 24Hours");
        adapter.addFragment(new Two(), "Later");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        layout = findViewById(R.id.layout);

        Intent intent = getIntent();
        cityMainId = intent.getIntExtra(MainActivity.extra_message,-1);
        Log.i("id from activity", cityMainId + "");
        primaryId = intent.getIntExtra(MainActivity.extra_messageId, -1);
        Log.i("primary from activity", primaryId + "");

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

         weatherViewModel.fetchWeather(cityMainId,primaryId).subscribe(new SingleObserver<WeatherResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(WeatherResponse weatherResponse) {
                Log.i("observe new",weatherResponse.weather.get(0).main);


                setUi(weatherResponse);

                int resid = getResources().getIdentifier( "i" + weatherResponse.weather.get(0).icon, "drawable", getPackageName());
                logo0.setImageResource(resid);

                Log.i("observe new","done");

                     entity1 = new Entity1(
                            weatherResponse.weather.get(0).icon,
                            String.valueOf(weatherResponse.name),
                            cityMainId,
                            weatherResponse.weather.get(0).main,
                            String.valueOf(weatherResponse.main.temp) + " \u2103",
                            String.valueOf(weatherResponse.wind.speed) + " m/s",
                            String.valueOf(weatherResponse.main.humidity) + " %",
                             getTime((long)weatherResponse.dt, "dd MMM - HH:mm")
                    );
                if (primaryId != -1) {
                    entity1.setId(primaryId);
                    weatherViewModel.updateEntity(entity1);
                }



            }


            @Override
            public void onError(Throwable e){
                Log.i("observe new",e.getMessage());
            }


        });


        logo0 = findViewById(R.id.logo3);
        des0 = findViewById(R.id.descrip);
        temp = findViewById(R.id.temp1);
        max = findViewById(R.id.max);
        min = findViewById(R.id.min);
        win = findViewById(R.id.wind);
        hum = findViewById(R.id.hum);
        pres = findViewById(R.id.press);
        date = findViewById(R.id.date);
        clock = findViewById(R.id.clock);
        sunset = findViewById(R.id.sunset);
        sunrise = findViewById(R.id.sunrise);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
         if(id==R.id.save1){
             Log.i("primary",primaryId+"");
             if(primaryId == -1){

                 if(entity1 != null) {
                     weatherViewModel.insertEntity(entity1);
                     Log.i("insert", "saved");
                     Toast.makeText(this, "City Saved", Toast.LENGTH_LONG).show();
                 }

             } else {
                 Log.i("insert","already exist");
                 Toast.makeText(this, "Already Exists", Toast.LENGTH_LONG).show();

             }

        }
        return super.onOptionsItemSelected(item);
    }

    private String getTime(long time,String format){
        Date date = new Date(time * 1000L);
        DateFormat format1 = new SimpleDateFormat(format);
        format1.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));
        return format1.format(date);
    }

    @SuppressLint("SetTextI18n")
    private void setUi(WeatherResponse weatherResponse){

        if(weatherResponse.dt > weatherResponse.sys.sunrise && weatherResponse.dt < weatherResponse.sys.sunset){

            layout.setBackground(getDrawable(R.mipmap.day));
        } else {

            layout.setBackground(getDrawable(R.mipmap.night));
        }

        des0.setText(weatherResponse.weather.get(0).description);
        temp.setText(String.valueOf(weatherResponse.main.temp) + " \u2103");
        max.setText(String.valueOf(weatherResponse.main.temp_max) + " \u2103");
        min.setText(String.valueOf(weatherResponse.main.temp_min) + " \u2103");
        date.setText(getTime((long)weatherResponse.dt, "dd MMM yyyy"));
        clock.setText(getTime((long)weatherResponse.dt, "HH:mm"));
        pres.setText(String.valueOf(weatherResponse.main.pressure) + " hPa");
        win.setText(String.valueOf(weatherResponse.wind.speed) + " m/s");
        hum.setText(String.valueOf(weatherResponse.main.humidity) + " %");
        sunrise.setText(getTime(weatherResponse.sys.sunrise, "HH:mm"));
        sunset.setText(getTime(weatherResponse.sys.sunset, "HH:mm"));

        toolbar.setTitle(weatherResponse.name);


    }

}
