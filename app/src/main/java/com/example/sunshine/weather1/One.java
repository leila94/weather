package com.example.sunshine.weather1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;


public class One extends Fragment {

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Entity1> results = new ArrayList<>();
    private WeatherViewModel weatherViewModel;
    private int cityMainId;
    public static final String extra_message = "com.example.sunshine.weather1.MESSAGE";


   // public ImageView mlogo;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters


   // private OnFragmentInteractionListener mListener;

    public One() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *

     * @return A new instance of fragment One.
     */
    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         //cityMainId = this.getArguments().getString("id");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // cityMainId = this.getArguments().getString(extra_message);
       // primaryId = intent.getIntExtra(MainActivity.extra_messageId, -1);
        cityMainId = getActivity().getIntent().getExtras().getInt(extra_message);
        Log.i("city id",cityMainId + "");
        // Inflate the layout for this fragment
        View frgmentView = inflater.inflate(R.layout.fragment_one, container, false);
        mRecyclerView = frgmentView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        ViewCompat.setNestedScrollingEnabled(mRecyclerView, false);

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
       // response2 = weatherViewModel.fetchForecast(cityMainId);
weatherViewModel.fetchForecast(cityMainId).subscribe(new SingleObserver<Response2>() {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(Response2 response2) {

        for (int index = 0; index <8; index++) {
            Entity1 obj = new Entity1(
                    response2.list.get(index).weather.get(0).icon,
                    getTime((long)response2.list.get(index).dt, "EEEE"),
                    cityMainId,
                    response2.list.get(index).weather.get(0).main,
                    String.valueOf(response2.list.get(index).main.temp) + " \u2103",
                    String.valueOf(response2.list.get(index).wind.speed) + " m/s",
                    String.valueOf(response2.list.get(index).main.humidity) + " %",
                    getTime((long)response2.list.get(index).dt, "HH:mm"));

            results.add(obj);
        }
        mAdapter = new MyRecyclerViewAdapter();
        mAdapter.submitList(results);
        mRecyclerView.setAdapter(mAdapter);
        Log.i("get forecast", results.size() + "");


    }

    @Override
    public void onError(Throwable e) {
        Log.i("get forecast","false");

    }
});


    return frgmentView;
    }


    private String getTime(long time,String format){
        Date date = new Date(time * 1000L);
        DateFormat format1 = new SimpleDateFormat(format);
        format1.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));
        return format1.format(date);
    }

}
