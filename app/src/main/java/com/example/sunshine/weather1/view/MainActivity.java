package com.example.sunshine.weather1.view;

import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunshine.weather1.Entity1;
import com.example.sunshine.weather1.GetTime;
import com.example.sunshine.weather1.MyRecyclerViewAdapter;
import com.example.sunshine.weather1.R;
import com.example.sunshine.weather1.WeatherViewModel;
import com.example.sunshine.weather1.databinding.ActivityMainBinding;
import com.example.sunshine.weather1.viewModel.MainViewModel;
import com.example.sunshine.weather1.viewModel.ViewModelFactory;

import java.util.List;


import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;


public class MainActivity extends DaggerAppCompatActivity {

    public String date = "2019-04-08 21:00:00";
    public GetTime time;
    Toolbar toolbar1;
    public static final String extra_message = "com.example.sunshine.weather1.MESSAGE";
    public static final String extra_messageId = "com.example.sunshine.weather1.MESSAGE2";
    private Single<List<String>> iranCities;
    private AutoCompleteTextView findCity;
    private ArrayAdapter arrayAdapter;
    private MainViewModel mainViewModel;
    private WeatherViewModel weatherViewModel;
    private SimpleCursorAdapter m1Adapter;

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);

       // setContentView(R.layout.activity_main);


        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setMainViewModel(mainViewModel);
        activityMainBinding.setLifecycleOwner(this);
        activityMainBinding.executePendingBindings();

        mainViewModel.checkDB();

        mainViewModel.getCities().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                activityMainBinding.autoCompleteTextView.setAdapter(
                        new ArrayAdapter<String >(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item,
                                strings)
                );
                activityMainBinding.autoCompleteTextView.setThreshold(3);

                Log.i("db", "set");
            }
        });

        activityMainBinding.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, CurrentWeatherActivity.class);
                intent.putExtra(CurrentWeatherActivity.extra_message, position);
                startActivity(intent);

            }
        });

        toolbar1 = findViewById(R.id.toolbar);
        toolbar1.setTitle("");
        setSupportActionBar(toolbar1);

        RecyclerView mRecyclerView = findViewById(R.id.my_recycler_view2);

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        final MyRecyclerViewAdapter mAdapter = new MyRecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);



        /*weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        weatherViewModel.getAllWeather().observe(this, new Observer<List<Entity1>>() {
            @Override
            public void onChanged(@Nullable List<Entity1> entity1s) {

                mAdapter.submitList(entity1s);


            }
        });*/

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

                Intent intent = new Intent(MainActivity.this, CurrentWeatherActivity.class);
                intent.putExtra(CurrentWeatherActivity.extra_message, entity1.getCityId());
                intent.putExtra(CurrentWeatherActivity.extra_messageId, entity1.getId());
                startActivity(intent);


            }
        });


        findCity = findViewById(R.id.autoCompleteTextView);



/*findCity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

                    if (strings != null) {

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
});*/

        findCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

              /*  weatherViewModel.getIranids(findCity.getText().toString()).subscribe(new SingleObserver<Integer>() {
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
                });*/

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