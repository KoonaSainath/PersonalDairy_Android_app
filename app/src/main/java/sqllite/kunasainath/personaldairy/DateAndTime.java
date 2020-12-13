package sqllite.kunasainath.personaldairy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTime {
    public static String[] getDateAndTime(){

        Date date = new Date();

        DateFormat pattern = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        String result = pattern.format(date);

        String[] array = result.split(" ");

        return array;
    }
}
