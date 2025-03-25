import java.time.Month;

import static java.time.Month.*;
//расписание ТОЛЬКО на одну четверть!!!
public class Variables {
    static int year = 2025;     //год
    static Month startMonth = MARCH;
    static int startDay = 31;    //с понедельника
    static Month endMonth = MAY;
    static int endDay = 30;  //по субботу
    static int startWeekNumber = 14; //номер первой недели в четверти.
    static Calendar.WeekColor startWeekColor = Calendar.WeekColor.BLUE;  //2024год 36-я неделя зелёная (Green Week, Blue Week, Yellow Week, Red Week)
    static String pathToWriteFile = "/home/forever/sch.csv";    //расположение файла в который идёт запись
}