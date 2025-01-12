import java.time.Month;

import static java.time.Month.*;
//расписание ТОЛЬКО на одну четверть!!!
public class Variables {
    static int year = 2025;     //год
    static Month startMonth = JANUARY;
    static int startDay = 13;    //с понедельника
    static Month endMonth = MARCH;
    static int endDay = 22;  //по субботу
    static int startWeekNumber = 3; //номер первой недели после каникул весенних.
    static String startWeekColor = "Green Week";   //2024год 36-я неделя зелёная (Green Week, Blue Week, Yellow Week, Red Week)
    static String pathToWriteFile = "/home/forever/sch.csv";    //расположение файла в который идёт запись
}