import java.time.Month;

import static java.time.Month.*;
//расписание ТОЛЬКО на одну четверть!!!
public class Variables {
    static int year = 2025;     //год
    static Month startMonth = SEPTEMBER;
    static int startDay = 8;    //с понедельника
    static Month endMonth = OCTOBER;
    static int endDay = 25;  //по субботу
    static int startWeekNumber = 37; //номер первой недели в четверти.
    static Calendar.WeekColor startWeekColor = Calendar.WeekColor.YELLOW;  //2024год 36-я неделя зелёная (Green Week, Blue Week, Yellow Week, Red Week)
    static String pathToWriteFile = "/home/forever/sch.csv";    //расположение файла в который идёт запись
}