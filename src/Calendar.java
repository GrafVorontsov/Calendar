import org.xml.sax.ext.DeclHandler;

import java.io.FileWriter;
import java.util.*;
import java.time.*;
import java.util.stream.*;
import java.time.temporal.*;

public class Calendar {
    public static void main(String[] args) throws java.io.IOException{

        //идентификаторы для доступа к урокам
        String restLessons = "\"Идентификатор: 577 583 8429" + System.lineSeparator() +
                "Код доступа: v7GXT0" + System.lineSeparator() +
                "Ссылка: https://us05web.zoom.us/j/5775838429?pwd=RnhMd284QnBIUDRvY2JaWUJTUURadz09\"";
        String english1 = "\"Идентификатор: 459 034 5208" + System.lineSeparator() +
                "Код доступа: JREb6H" + System.lineSeparator() +
                "Ссылка: https://us04web.zoom.us/j/4590345208?pwd=NktlMklsVG91SlVuS2U0MVpudTlmZz09\"";
        String english2 = "\"Идентификатор: 851 555 5808" + System.lineSeparator() +
                "Код доступа: 5byWJD" + System.lineSeparator() +
                "Ссылка: https://us04web.zoom.us/j/8515555808?pwd=otiuojpn2DQahElXxjgoMRS2Hs3vDB.1\"";
        String informatika = "\"Идентификатор: 716 6067 5364" + System.lineSeparator() +
                "Код доступа: Tv35UM" + System.lineSeparator() +
                "Ссылка: https://us04web.zoom.us/j/71660675364?pwd=42GXT37QbQVQ4WN1xmWDljDy10bJbJ.1\"";
        String fizra = "";

        //название уроков
        String nameMatematika = "Математика";
        String nameUkrMova = "Українська мова";
        String nameLiteratura = "Літературне читання";
        String nameJDC = "ЯДС";
        String nameEnglish1 = "English 1гр.";
        String nameEnglish2 = "English";
        String nameInformatika = "Iнформатика";
        String nameFizra = "Фiз-ра";

        //массивы дат по дням недели
        ArrayList<LocalDate> greenBlueWeek = new ArrayList<>();    //нечётная неделя
        ArrayList<LocalDate> yellowRedWeek = new ArrayList<>();    //чётная неделя

        List<LocalDate> days = dateList(); //общий массив дат

        for(LocalDate date:days){

            if (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) % 2 != 0){
                yellowRedWeek.add(date);
                //System.out.println(date);
            } else {
                greenBlueWeek.add(date);
            }
        }

        //расписание звонков
        String les1Start = "9:15:00";
        String les1Finish = "10:00:00";

        String les2Start = "10:10:00";
        String les2Finish = "10:55:00";

        String les3Start = "11:00:00";
        String les3Finish = "11:45:00";

        String les4Start = "12:00:00";
        String les4Finish = "12:45:00";

        String les5Start = "13:00:00";
        String les5Finish = "13:45:00";

        ArrayList<String> scheduleLessonsList = new ArrayList<>();

        //Українська мова
        scheduleLessonsList.add(oneTime3Days(nameUkrMova,greenBlueWeek,les1Start,les1Finish,restLessons,1,4,0));
        scheduleLessonsList.add(oneTime3Days(nameUkrMova,greenBlueWeek,les3Start,les3Finish,restLessons,2,0,0));

        scheduleLessonsList.add(oneTime3Days(nameUkrMova,yellowRedWeek,les1Start,les1Finish,restLessons,1,4,0));
        //scheduleLessonsList.add(oneTime3Days(nameUkrMova,yellowRedWeek,les2Start,les2Finish,restLessons,4,0,0));
        scheduleLessonsList.add(oneTime3Days(nameUkrMova,yellowRedWeek,les3Start,les3Finish,restLessons,2,0,0));

        //Літературне читання
        scheduleLessonsList.add(oneTime3Days(nameLiteratura,greenBlueWeek,les4Start,les4Finish,restLessons,2,0,0));
        scheduleLessonsList.add(oneTime3Days(nameLiteratura,greenBlueWeek,les3Start,les3Finish,restLessons,3,5,0));

        scheduleLessonsList.add(oneTime3Days(nameLiteratura,yellowRedWeek,les4Start,les4Finish,restLessons,2,0,0));
        scheduleLessonsList.add(oneTime3Days(nameLiteratura,yellowRedWeek,les3Start,les3Finish,restLessons,3,0,0));
        scheduleLessonsList.add(oneTime3Days(nameLiteratura,yellowRedWeek,les1Start,les1Finish,restLessons,5,0,0));
        scheduleLessonsList.add(oneTime3Days(nameLiteratura,yellowRedWeek,les2Start,les2Finish,restLessons,4,0,0));

        //математика
        scheduleLessonsList.add(oneTime3Days(nameMatematika,greenBlueWeek,les2Start,les2Finish,restLessons,3,0,0));
        scheduleLessonsList.add(oneTime3Days(nameMatematika,greenBlueWeek,les2Start,les2Finish,restLessons,2,4,5));
        scheduleLessonsList.add(oneTime3Days(nameMatematika,greenBlueWeek,les1Start,les1Finish,restLessons,5,0,0));

        scheduleLessonsList.add(oneTime3Days(nameMatematika,yellowRedWeek,les2Start,les2Finish,restLessons,1,2,3));
        scheduleLessonsList.add(oneTime3Days(nameMatematika,yellowRedWeek,les2Start,les2Finish,restLessons,5,0,0));

        //ЯДС
        scheduleLessonsList.add(oneTime3Days(nameJDC,greenBlueWeek,les3Start,les3Finish,restLessons,1,0,0));
        scheduleLessonsList.add(oneTime3Days(nameJDC,greenBlueWeek,les4Start,les4Finish,restLessons,3,0,0));
        scheduleLessonsList.add(oneTime3Days(nameJDC,greenBlueWeek,les2Start,les2Finish,restLessons,5,0,0));

        scheduleLessonsList.add(oneTime3Days(nameJDC,yellowRedWeek,les3Start,les3Finish,restLessons,1,5,0));

        //Информатика
        scheduleLessonsList.add(oneTime3Days(nameInformatika,yellowRedWeek,les3Start,les3Finish,informatika,4,0,0));

        //English
        scheduleLessonsList.add(oneTime3Days(nameEnglish2,greenBlueWeek,les2Start,les2Finish,english2,1,0,0));
        scheduleLessonsList.add(oneTime3Days(nameEnglish2,greenBlueWeek,les1Start,les1Finish,english2,3,0,0));

        scheduleLessonsList.add(oneTime3Days(nameEnglish2,yellowRedWeek,les1Start,les1Finish,english2,3,0,0));

        //Физ-ра
        scheduleLessonsList.add(oneTime3Days(nameFizra,greenBlueWeek,les1Start,les1Finish,fizra,2,0,0));
        //scheduleLessonsList.add(oneTime3Days(nameFizra,greenBlueWeek,les2Start,les2Finish,fizra,4,0,0));
        scheduleLessonsList.add(oneTime3Days(nameFizra,yellowRedWeek,les1Start,les1Finish,fizra,2,0,0));

        //Пишем рассписание в файл .csv
        FileWriter fw = new FileWriter("/home/forever/sch.csv");
        fw.write("Subject,Start Date,Start Time,End Date,End Time,Description" + System.lineSeparator());

        for(String sch:scheduleLessonsList){
            fw.write(sch);
        }

        fw.close();
    }

    //назначает уроки по нужным датам и дням недели
    public static String oneTime3Days(String lessons,
                                      ArrayList<LocalDate> list,
                                      String startTime,
                                      String endTime,
                                      String info,
                                      int firstDay,
                                      int secondDay,
                                      int thirdDay){

        StringBuilder str = new StringBuilder();

        ArrayList<String> days = new ArrayList<>();

        //проверка чтоб день недели не был равен нулю и добавляет их в массив
        if (firstDay != 0){
            days.add(dayOfWeek(firstDay));
        }
        if (secondDay !=0){
            days.add(dayOfWeek(secondDay));
        }
        if (thirdDay !=0){
            days.add(dayOfWeek(thirdDay));
        }

        //собирает нужную строку из дат, уроков, времени, дней для файла
        for(LocalDate date:list){
            for(String day:days){

                if (date.getDayOfWeek() == DayOfWeek.valueOf(day)){

                    str.append(lessons).append(",")
                            .append(date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear()).append(",")
                            .append(startTime).append(",")
                            .append(date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear()).append(",")
                            .append(endTime).append(",")
                            .append(info).append(System.getProperty("line.separator"));
                }
            }
        }

        return str.toString();
    }

    //делает массив дат определённого периода
    public static List<LocalDate> dateList(){
        LocalDate startDate = LocalDate.of(2023, Month.FEBRUARY, 27);  //с понедельника
        LocalDate endDate = LocalDate.of(2023, Month.MARCH, 25);   //по субботу
        Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

        //исключаем выходные

        return startDate.datesUntil(endDate)
                .filter(d -> !weekend.contains(d.getDayOfWeek()))
                .collect(Collectors.toList());
    }

    //перевод цифр в нормальное обозначение дня недели
    public static String dayOfWeek(int day){

        String dayOfWeek = "";

        switch (day) {
            case 1 -> dayOfWeek = "MONDAY";
            case 2 -> dayOfWeek = "TUESDAY";
            case 3 -> dayOfWeek = "WEDNESDAY";
            case 4 -> dayOfWeek = "THURSDAY";
            case 5 -> dayOfWeek = "FRIDAY";
            default -> {
            }
        }
        return dayOfWeek;
    }
}
//зел\син
 /*   Пн/пт
Українська мова (1)
9:15-9:45
9:55-10:25
16:00-16:30
16:40-17:10
--------------------
Вт/ср/пт
Математика
10:35-11:05
чт
09:55-10:25
ср
16:00-16:40
-------------------
вт/ср
Літературне читання
11:25-11:55
чт
10:35-11:05
--------------------
вт
ЯДС
12:05-12:35
чт
9:15-9:45
пт
11:25-11:55

англиский
Понеділок: 10:35-11:15
Середа: 09:15-09:55

*****************

    жёлт/красн
    пн/чт
Українська мова (1)
9:15-9:45
9:55-10:25
16:00-16:30
16:40-17:10

Математика
пн/вт/пт
10:35-11:05
ср
16:00-16:40
ср/пт
09:55-10:25

Літературне читання
пн/вт
11:25-11:55
ср/пт
09:15-09:45

ЯДС
вт
12:05-12:35
ср
10:35-11:05

англиский
Ср
12:15-12:55

информатика
чт.
10:35-11:05

Физра
каждый вторник
9:30-9:50
*/