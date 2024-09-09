import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static java.time.Month.*;

public class Variables {
    static int year = 2024;     //год
    static Month startMonth = SEPTEMBER;
    static int startDay = 2;    //с понедельника
    static Month endMonth = OCTOBER;
    static int endDay = 26;  //по субботу

    //static int startWeekNumber = 3; //номер первой недели в полугодии. 2024год 3-я неделя жёлтая
    static int startWeekNumber = 36; //номер первой недели после каникул весенних.
    static String startWeekColor = "green";   //2024год 36-я неделя зелёная

    static String pathToWriteFile = "/home/forever/sch.csv";    //расположение файла в который идёт запись
}
