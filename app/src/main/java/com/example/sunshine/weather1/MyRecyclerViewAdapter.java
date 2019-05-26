package com.example.sunshine.weather1;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Leila on 4/9/2019.
 */

public class MyRecyclerViewAdapter extends ListAdapter<Entity1,MyRecyclerViewAdapter.Entity1Holder> {

    private static String LOG_TAG = "MyRecyclerViewAdapter";
private String nameC;
    public static final String extra_message = "com.example.sunshine.weather1.MESSAGE";

    //private List<Entity1> mDataset = new ArrayList<>();
    private static final String database_name = "weatherBase";
    private AppDatabase appDatabase;
    //private Context context;
    private Entity1 entity1;
    private int p1;
   // private OnItemClickListener listener;
    private OnItemClickListener listener;

    public MyRecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Entity1> DIFF_CALLBACK = new DiffUtil.ItemCallback<Entity1>() {

        @Override
        public boolean areItemsTheSame(Entity1 oldItem, Entity1 newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Entity1 oldItem, Entity1 newItem) {
            return oldItem.getCityName().equals(newItem.getCityName()) &&
                    oldItem.getCityId() == newItem.getCityId();
        }

    };



    public class Entity1Holder extends RecyclerView.ViewHolder {


    TextView label;
    TextView temp;
    ImageView logo;
    TextView hum;
    TextView wind;
    TextView pres;
    TextView time;

    //public static MyClickListener myClickListener;


    //public View.OnClickListener onItemClickListener2;

    public Entity1Holder(final View itemView) {
        super(itemView);

        itemView.setTag(itemView);

        logo = itemView.findViewById(R.id.logo1);
        label = (TextView) itemView.findViewById(R.id.descript);
        temp = (TextView) itemView.findViewById(R.id.temp);
        hum = itemView.findViewById(R.id.hum);
        wind = itemView.findViewById(R.id.wind);
        pres = itemView.findViewById(R.id.pres);
        time = itemView.findViewById(R.id.time);
        Log.i(LOG_TAG, "Adding Listener");

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION){
                    listener.OnItemClick(getItem(position));
                }
            }
        });
    }


    }


    @Override
    public Entity1Holder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        Entity1Holder entity1Holder = new Entity1Holder(view);
        return entity1Holder;
    }


    @Override
    public void onBindViewHolder(final Entity1Holder holder, int position) {
Entity1 en1 = getItem(position);
       // String iconUrl = "http://openweathermap.org/img/w/" + en1.getIcon2() + ".png";
        //Picasso.get().load(iconUrl).into(holder.logo);
        String en2 = "i" + en1.getIcon2();
       // String en3 = holder.logo.getApplicationWindowToken().g
        int resid = holder.logo.getContext().getResources().getIdentifier( en2, "drawable", holder.logo.getContext().getPackageName());
        //holder.logo.setImageBitmap(BitmapFactory.decodeResource(holder.logo.getContext().getResources(),resid));
        holder.logo.setImageResource(resid);

        //holder.logo.setImageDrawable(mDataset.get(position).getLogo2());
        holder.label.setText(en1.getMain());
        holder.temp.setText(en1.getTemp());

        holder.hum.setText(en1.getHum());
        holder.wind.setText(en1.getWind());
        holder.pres.setText(en1.getCityName());
        holder.time.setText(en1.getDate());
    }

/*
        final SharedPreferences sharedPreferences = holder.logo.getContext().getSharedPreferences("weather", Context.MODE_PRIVATE);


        appDatabase = Room.databaseBuilder(holder.logo.getContext(),AppDatabase.class,database_name)
                .fallbackToDestructiveMigration().build();

        holder.setOnItemClickListener(new MyClickListener() {
            @Override
            public void onItemClick(int position, View v, boolean isLong) {
               // deleteItem(position);

Log.i("rec1",mDataset.get(position).getmText6());
Context context = v.getContext();

            if(isLong) {

nameC = mDataset.get(position).getmText6();
p1 = position;

sharedPreferences.edit().putInt("position",position).apply();

//thread4.start();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }else {

                Intent intent = new Intent(v.getContext(), CurrentWeatherActivity.class);
                intent.putExtra(extra_message, mDataset.get(position).getmText6());

                context.startActivity(intent);
            }

            }
        });



    } */

   /* public void addItem(DataObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    } */


public Entity1 getEntityAt(int position){
    return getItem(position);
}

    public interface MyClickListener {
        public void onItemClick(int position, View v, boolean isLong);
    }

    private void iii() {
        Thread thread4 = new Thread() {
            @Override
            public void run() {

                entity1 = appDatabase.wDao().findWeather(nameC);
                appDatabase.wDao().deleteCity(entity1);


            }


        };
        thread4.start();
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                   // iii();
                   // deleteItem(p1);
                  // notifyItemRemoved(p1);
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };

public interface OnItemClickListener{
    void OnItemClick(Entity1 entity1);
}

public void SetOnItemClickListener(OnItemClickListener listener){
    this.listener = listener;
}
}
