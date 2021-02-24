package com.example.sunshine.weather1;

import android.os.Bundle;
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
import java.util.TimeZone;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;


public class Two extends Fragment {

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    private ArrayList<Entity1> results = new ArrayList<Entity1>();

    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "2e65127e909e178d0af311a81f39948c";
    private WeatherViewModel weatherViewModel;
    private Response2 response2;

    private int cityMainId;
    public static final String extra_message = "com.example.sunshine.weather1.MESSAGE";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   // private OnFragmentInteractionListener mListener;

    public Two() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Two.
     */
    // TODO: Rename and change types and number of parameters
    public static Two newInstance(String param1, String param2) {
        Two fragment = new Two();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frgmentView = inflater.inflate(R.layout.fragment_two, container, false);

        cityMainId = getActivity().getIntent().getExtras().getInt(extra_message);
        Log.i("city id",cityMainId + "");
        // Inflate the layout for this fragment

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

                for (int index = 8; index <40; index++) {
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
