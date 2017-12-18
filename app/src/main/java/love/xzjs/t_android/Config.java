package love.xzjs.t_android;

/**
 * Created by Administrator on 2017/12/18.
 */

public class Config {
    private int id,time,date,week,firstDay,secondDay,thirdDay,num;
    private String location;

    public Config(int id, int time, int date, int week, int firstDay, int secondDay, int thirdDay, int num, String location) {
        this.id = id;
        this.time = time;
        this.date = date;
        this.week = week;
        this.firstDay = firstDay;
        this.secondDay = secondDay;
        this.thirdDay = thirdDay;
        this.num = num;
        this.location = location;
    }

    public Config(){
        this.id = 0;
        this.time = 0;
        this.date = 0;
        this.week = 0;
        this.firstDay = 0;
        this.secondDay = 0;
        this.thirdDay = 0;
        this.num = 3;
        this.location = "";

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(int firstDay) {
        this.firstDay = firstDay;
    }

    public int getSecondDay() {
        return secondDay;
    }

    public void setSecondDay(int secondDay) {
        this.secondDay = secondDay;
    }

    public int getThirdDay() {
        return thirdDay;
    }

    public void setThirdDay(int thirdDay) {
        this.thirdDay = thirdDay;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
