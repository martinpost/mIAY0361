import java.util.List;

public class WeatherFun {
    private String city;

    WeatherFun(String city) {
        this.city = city;
    }

    String getCity() {
        return city;
    }

    int getCurrentTemp() {
        return 0;
    }

    List<Integer> get3DayLowestHighest() {
        return null;
    }
    String getGEO() {
        return null;
    }

    public static void main(String[] args) {
        System.out.println("test");
    }

}
