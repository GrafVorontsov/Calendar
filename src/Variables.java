import java.time.Month;

import static java.time.Month.*;

public class Variables {
    static int year = 2024;     //год
    static Month startMonth = APRIL;
    static int startDay = 1;    //с понедельника
    static Month endMonth = JUNE;
    static int endDay = 1;  //по субботу

    //static int startWeekNumber = 3; //номер первой недели в полугодии. 2024год 3-я неделя жёлтая
    static int startWeekNumber = 14; //номер первой недели после каникул весенних.
    static String startWeekColor = "red";   //2024год 14-я неделя красная

    static String pathToWriteFile = "/home/forever/sch.csv";    //расположение файла в который идёт запись
}
