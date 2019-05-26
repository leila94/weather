package com.example.sunshine.weather1;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import java.util.List;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {

    public String date = "2019-04-08 21:00:00";
    public GetTime time;
    Toolbar toolbar1;
    public static final String extra_message = "com.example.sunshine.weather1.MESSAGE";
    public static final String extra_messageId = "com.example.sunshine.weather1.MESSAGE2";
    private Single<List<String>> iranCities;
    private AutoCompleteTextView findCity;
    private ArrayAdapter arrayAdapter;
    private WeatherViewModel weatherViewModel;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar1 = findViewById(R.id.toolbar);
        toolbar1.setTitle("");
        setSupportActionBar(toolbar1);

        RecyclerView mRecyclerView = findViewById(R.id.my_recycler_view2);

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        final MyRecyclerViewAdapter mAdapter = new MyRecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);



        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        weatherViewModel.getAllWeather().observe(this, new Observer<List<Entity1>>() {
            @Override
            public void onChanged(@Nullable List<Entity1> entity1s) {

                mAdapter.submitList(entity1s);


            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                weatherViewModel.deleteEntity(mAdapter.getEntityAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(mRecyclerView);

        mAdapter.SetOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Entity1 entity1) {
                Log.i("database size", entity1.getCityId() + "");
                Log.i("database size2", "ccccc");


                Intent intent = new Intent(MainActivity.this, CurrentWeatherActivity.class);
                intent.putExtra(CurrentWeatherActivity.extra_message, entity1.getCityId());
                intent.putExtra(CurrentWeatherActivity.extra_messageId, entity1.getId());
                startActivity(intent);


            }
        });


        findCity = findViewById(R.id.autoCompleteTextView);

findCity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View view, boolean b) {
        if(b) {

            iranCities = weatherViewModel.getIranNames();
            iranCities.subscribe(new SingleObserver<List<String>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onSuccess(List<String> strings) {
                    Log.i("test", "fail");

                    if (strings != null) {
                        Log.i("test", strings.get(0));
                        arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, strings);
                        findCity.setAdapter(arrayAdapter);
                        findCity.setThreshold(1);
                    }


                }

                @Override
                public void onError(Throwable e) {
                    Log.i("test", e.getMessage());

                }
            });

        }

    }
});

        findCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                weatherViewModel.getIranids(findCity.getText().toString()).subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integers) {

                        Log.i("city feature", integers + " " + findCity.getText());

                        Intent intent = new Intent(MainActivity.this, CurrentWeatherActivity.class);
                        intent.putExtra(CurrentWeatherActivity.extra_message, integers);
                        //intent.putExtra(CurrentWeatherActivity.extra_messageId, entity1.getId());
                        startActivity(intent);
                        findCity.getText().clear();
                        //findCity.clearFocus();



                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.delete_all) {

            new AlertDialog.Builder(this).setMessage("Delete All Cities?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            weatherViewModel.deleteAll();
                        }
                    }).setNegativeButton("No", null).create().show();

            }


        return true;
    }


}

//AIzaSyBYgtpEKKMaFJJ36M9em9QbCDPY67h93nU