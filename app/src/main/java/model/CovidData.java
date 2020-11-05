package model;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "covid_data_table")
public class CovidData {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "continent")
    private String continent;

    @NonNull
    @ColumnInfo(name = "recovered")
    private int recovered;

    @NonNull
    @ColumnInfo(name = "active")
    private int active;


    public CovidData(int id, @NonNull String continent, int recovered, int active) {
        this.id = id;
        this.continent = continent;
        this.recovered = recovered;
        this.active = active;
    }

    public CovidData(CovidData covidData) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }


    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }


}
