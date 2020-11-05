package ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.auie.covid.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import model.CovidData;

public class RecyclerAdapterSaved extends RecyclerView.Adapter<RecyclerAdapterSaved.UserViewHolder> {

    Context context;
    List<CovidData> covidDataList;

    public RecyclerAdapterSaved(Context context, List<CovidData> covidDataList) {
        this.context = context;
        this.covidDataList = covidDataList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_adapter_saved, parent, false);
        RecyclerAdapterSaved.UserViewHolder ViewHolder = new UserViewHolder(view);
        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.savedContinent.setText("Continent: " + covidDataList.get(position).getContinent());
        holder.savedActive.setText("Active cases: " + covidDataList.get(position).getActive());
        holder.savedRecovered.setText("Recovered: " + covidDataList.get(position).getRecovered());
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        holder.savedDate.setText("Saved at: " + date);
    }

    public void setCovidDataRoomList (List<CovidData> covidData){
        covidDataList = covidData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return covidDataList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView savedContinent;
        TextView savedActive;
        TextView savedRecovered;
        TextView savedDate;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            savedContinent = itemView.findViewById(R.id.scontinent);
            savedActive = itemView.findViewById(R.id.sactive);
            savedRecovered = itemView.findViewById(R.id.srecovered);
            savedDate = itemView.findViewById(R.id.date_saved);
        }
    }
}
