package ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.auie.covid.R;
import java.util.List;
import data.CovidRoomDatabase;
import model.CovidData;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.UserViewHolder> {
    Context context;
    List<CovidData> covidDataList;
    CovidRoomDatabase covidRoomDatabase;

    public RecyclerAdapter(Context context, List<CovidData> covidDataList) {
        this.context = context;
        this.covidDataList = covidDataList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_adapter, parent, false);
        UserViewHolder ViewHolder = new UserViewHolder(view);
        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        holder.continent.setText("Continent: " + covidDataList.get(position).getContinent());
        holder.active.setText("Active cases: " + covidDataList.get(position).getActive());
        holder.recovered.setText("Recovered: " + covidDataList.get(position).getRecovered());
        holder.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CovidData covidData = new CovidData(covidDataList.get(position).getId(),
                        covidDataList.get(position).getContinent(),
                        covidDataList.get(position).getActive(), covidDataList.get(position).getRecovered());
               covidRoomDatabase.getDatabase(context).covidDao().insert(covidData);
            }
        });
    }

    public void setCovidData(List<CovidData> covidData){
        covidDataList = covidData;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return covidDataList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView continent;
        TextView active;
        TextView recovered;
        Button saveButton;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            continent = itemView.findViewById(R.id.continent);
            active = itemView.findViewById(R.id.active);
            recovered = itemView.findViewById(R.id.recovered);
            saveButton = itemView.findViewById(R.id.save_button);
        }
    }
}
