package sqllite.kunasainath.personaldairy;

import java.io.Serializable;

public class Entry implements Serializable {
    private int id;
    private String date, time, title, description;

    public Entry(int id, String date, String time, String title, String description) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return Integer.toString(getId()) + "\n" + getDate() + "\n" + getTime() + "\n" + getTitle() + "\n" + getDescription() ;
    }
}
