import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class WeatherFun {
    private String city;
    private String lat;
    private String lon;
    String day1temp = "";
    String day1tempmin = "";
    String day1tempmax = "";

    String day2tempmin = "";
    String day2tempmax = "";

    String day3tempmin = "";
    String day3tempmax = "";


    WeatherFun(String city) throws IOException {
        this.city = city;

        System.out.println("Enter city name:");
        Scanner scanner = new Scanner(System.in);
        String holder = scanner.nextLine();
        if (!holder.equals("")) {
            this.city = holder;
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("input.txt"));
        writer.write(this.city);
        writer.close();

        String path = "http://api.openweathermap.org/data/2.5/forecast?id=588409&APPID=12eb7563cf461f80e54a415effa8de38";

        URL oracle = new URL(path);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        //System.out.println(in.readLine());
        String[] splited = in.readLine().split("main");

        String[] day1split = splited[1].split(",");
        String[] day2split = splited[3].split(",");
        String[] day3split = splited[5].split(",");
        for (String i: splited) {
            if (i.contains("city")) {
                String[] citysplit = i.split(",");
                for (String ii: citysplit) {
                    if (ii.contains("name")) {
                        this.city = ii.split("name")[1];
                        this.city = this.city.replace(":", "");
                        this.city = this.city.replace("\"", "");
                    }
                    if (ii.contains("lat")) {
                        this.lat = ii.split("lat")[1];
                        this.lat = this.lat.replace(":", "");
                        this.lat = this.lat.replace("}", "");
                        this.lat = this.lat.replace("\"", "");
                    }
                    if (ii.contains("lon")) {
                        this.lon = ii.split("lon")[1];
                        this.lon = this.lon.replace(":", "");
                        this.lon = this.lon.replace("}", "");
                        this.lon = this.lon.replace("\"", "");
                    }

                }
            }

        }

        for (String i: day1split) {
            if (i.contains("\"temp\"")) {
                day1temp = (i.split(":"))[2];
            }
            if (i.contains("\"temp_min\"")) {
                day1tempmin = (i.split(":"))[1];
            }
            if (i.contains("\"temp_max\"")) {
                day1tempmax = (i.split(":"))[1];
            }
        }

        for (String i: day2split) {
            if (i.contains("\"temp_min\"")) {
                day2tempmin = (i.split(":"))[1];
            }
            if (i.contains("\"temp_max\"")) {
                day2tempmax = (i.split(":"))[1];
            }
        }

        for (String i: day3split) {
            if (i.contains("\"temp_min\"")) {
                day3tempmin = (i.split(":"))[1];
            }
            if (i.contains("\"temp_max\"")) {
                day3tempmax = (i.split(":"))[1];
            }
        }

        in.close();

        BufferedWriter writer1 = new BufferedWriter(new FileWriter("output.txt"));
        writer1.write("This is first day temp: " + day1temp);
        writer1.write("This is first day min temp: " + day1tempmin);
        writer1.write("This is first day max temp: " + day1tempmax);
        writer1.write("This is second day min temp: " + day2tempmin);
        writer1.write("This is second day max temp: " + day2tempmax);
        writer1.write("This is third day min temp: " + day3tempmin);
        writer1.write("This is third day max temp: " + day3tempmax);
        writer1.close();

    }

    String getCity() {
        return city;
    }

    int getCurrentTemp() {
        return Integer.parseInt(day1temp);
    }

    List<Integer> get3DayLowestHighest() {
        List<Integer> day3list = new ArrayList<>();

        day3list.add(Integer.parseInt(day1tempmin));
        day3list.add(Integer.parseInt(day1tempmax));
        day3list.add(Integer.parseInt(day2tempmin));
        day3list.add(Integer.parseInt(day2tempmax));
        day3list.add(Integer.parseInt(day3tempmin));
        day3list.add(Integer.parseInt(day3tempmax));

        return day3list;
    }
    String getGEO() {
        return lon + ":" + lat;
    }

    public static void main(String[] args) {
        try {
            WeatherFun fun1 = new WeatherFun("Tallinn");
            System.out.println(fun1.getGEO());
            System.out.println(fun1.getCity());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
