import static org.junit.jupiter.api.Assertions.*;

class WeatherFunTest {

    private void testCity() {
        try {
            String city = "Tallinn";
            WeatherFun test1 = new WeatherFun(city);
            assertEquals(test1.getCity(), city);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }


    private void testCurrentTemp() {
        try {
            String city = "Tallinn";
            WeatherFun test2 = new WeatherFun(city);
            assertTrue(test2.getCurrentTemp() > 100, "Temp is too high");
            assertTrue(test2.getCurrentTemp() < -60, "Temp is too low");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    //testing if list of highst and lowest is in right order or with right values
    private void test3DayLowestHighest() {
        try {
            String city = "Tallinn";
            WeatherFun test3 = new WeatherFun(city);
            for (int i: test3.get3DayLowestHighest()) {
                assertTrue(i < (-60), "Temp is too low");
            }

            for (int i: test3.get3DayLowestHighest()) {
                assertTrue(i > 100, "Temp is too high");
            }

            int a = test3.get3DayLowestHighest().get(0);
            int b = test3.get3DayLowestHighest().get(1);
            assertFalse(a <= b, "lowest temp is higer than max temp day 1");

            int c = test3.get3DayLowestHighest().get(2);
            int d = test3.get3DayLowestHighest().get(3);
            assertFalse(c <= d, "lowest temp is higer than max temp day 2");

            int e = test3.get3DayLowestHighest().get(4);
            int f = test3.get3DayLowestHighest().get(5);
            assertFalse(e <= f, "lowest temp is higer than max temp day 3");

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    private void testGEO() {
        try {
            String loc = "Tallinna asukoht";
            String city = "Tallinn";
            WeatherFun test4 = new WeatherFun(city);
            assertEquals(loc, test4.getGEO());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public static void main(String[] args) {
        WeatherFunTest test1 = new WeatherFunTest();
        test1.testCity();
        test1.testCurrentTemp();
        test1.test3DayLowestHighest();
        test1.testGEO();


    }


}