
import org.xml.sax.ext.DeclHandler;

import java.io.FileWriter;
import java.util.*;
import java.time.*;
import java.util.stream.*;
import java.time.temporal.*;

public class Calendar {
    public static void main(String[] args) throws java.io.IOException{
        Title title = new Title();
        Timing time = new Timing();
        Link link = new Link();

        //массивы дат по дням недели
        ArrayList<LocalDate> week = new ArrayList<>();          //неделя
        Week greenWeek = new Week("Green Week");        //зелёная неделя
        Week blueWeek = new Week("Blue Week");          //синяя неделя
        Week yellowWeek = new Week("Yellow Week");      //жёлтая неделя
        Week redWeek = new Week("Red Week");        //красная неделя

        LinkedList<Week> weeksList = new LinkedList<>();
        weeksList.add(greenWeek);
        weeksList.add(yellowWeek);
        weeksList.add(blueWeek);
        weeksList.add(redWeek);

        List<LocalDate> days = HelpClass.dateList(); //общий массив дат

        int startWeekNumber = Variables.startWeekNumber;

        //Перемещения недель
        //меняем последовательность недель, с какой недели начинается та и первая в списке
        for (Week weeek : weeksList) {
            if (weeek.getName().equals(Variables.startWeekColor)) {
                weeksList = moveWeekToFront(weeksList, weeek.getName());
                System.out.println(weeek.getName());
                break;
            }
        }

        //назначаем каждой неделе свои даты по очереди
        for (LocalDate date : days) {
            int weekOfYear = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

            // Определяем, в какую неделю нужно добавить дату
            int weekIndex;
            if (weekOfYear == startWeekNumber || weekOfYear == startWeekNumber + 4 || weekOfYear == startWeekNumber + 8) {
                weekIndex = 0;
            } else if (weekOfYear == startWeekNumber + 1 || weekOfYear == startWeekNumber + 5 || weekOfYear == startWeekNumber + 9) {
                weekIndex = 1;
            } else if (weekOfYear == startWeekNumber + 2 || weekOfYear == startWeekNumber + 6 || weekOfYear == startWeekNumber + 10) {
                weekIndex = 2;
            } else  if (weekOfYear == startWeekNumber + 3 || weekOfYear == startWeekNumber + 7 || weekOfYear == startWeekNumber + 11) {
                weekIndex = 3;
            } else {
                weekIndex = -1; // Или другое значение по умолчанию, если необходимо
            }

            // Добавляем дату в соответствующую неделю
            weeksList.get(weekIndex).addDate(date);
        }

        //формируем массив уроков по неделям и датам
        ArrayList<String> scheduleLessonsList = new ArrayList<>();

        week = greenWeek.getDates();

        System.out.println("Green Week  " + week);

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getPriroda(), week, time._1Start900, time._1Finish, link.priroda, 1, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._2Start1100, time._2Finish, link.matematika, 1, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._1Start915, time._1Finish, link.matematika, 2, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._2Start1100, time._2Finish, link.matematika, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week, time._3Start1250, time._3Finish, link.ukrmova, 1, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week, time._2Start1050, time._2Finish, link.ukrmova, 2, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week, time._4Start1450, time._4Finish, link.english, 1, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week, time._3Start1250, time._3Finish, link.english, 2, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish2(), week, time._1900, time._2030, link.english, 5, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week, time._1Start900, time._1Finish945, link.fizra, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHistory(), week, time._1Start950, time._1Finish, link.history, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrLit(), week, time._3Start1250, time._3Finish, link.ukrlit, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getTechnology(), week, time._4Start1450, time._4Finish, link.technology, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMusic(), week, time._2Start1045, time._2Finish, link.music, 4, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGeografya(), week, time._3Start1250, time._3Finish, link.geografya, 4, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMystectvo(), week, time._1Start900, time._1Finish, link.mystectvo, 5, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika(), week, time._2Start1100, time._2Finish, link.informatika, 5, 0, 0));

        week = yellowWeek.getDates();

        System.out.println("Yellow Week  " + week);

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getPriroda(), week, time._1Start900, time._1Finish, link.priroda, 1, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._2Start1100, time._2Finish, link.matematika, 1, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._2Start1100, time._2Finish, link.matematika, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week, time._3Start1250, time._3Finish, link.ukrmova, 1, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week, time._2Start1050, time._2Finish, link.ukrmova, 2, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week, time._4Start1450, time._4Finish, link.english, 1, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week, time._3Start1250, time._3Finish, link.english, 2, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish2(), week, time._1900, time._2030, link.english, 5, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week, time._1Start900, time._1Finish, link.fizra, 2, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week, time._1Start900, time._1Finish945, link.fizra, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHistory(), week, time._1Start950, time._1Finish, link.history, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrLit(), week, time._3Start1250, time._3Finish, link.ukrlit, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getTechnology(), week, time._4Start1450, time._4Finish, link.technology, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGeografya(), week, time._3Start1250, time._3Finish, link.geografya, 4, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getZarubizhnaLiteratura(), week, time._2Start1050, time._2Finish, link.zarubizhnaliteratura, 4, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHealthy(), week, time._2Start1050, time._2Finish, link.healthy, 5, 0, 0));

        week = blueWeek.getDates();

        System.out.println("Blue Week  " + week);

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getPriroda(), week, time._1Start900, time._1Finish, link.priroda, 1, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._2Start1100, time._2Finish, link.matematika, 1, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._1Start915, time._1Finish, link.matematika, 2, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._2Start1100, time._2Finish, link.matematika, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week, time._3Start1250, time._3Finish, link.ukrmova, 1, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week, time._2Start1050, time._2Finish, link.ukrmova, 2, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week, time._3Start1250, time._3Finish, link.english, 2, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish2(), week, time._1900, time._2030, link.english, 5, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHealthy(), week, time._4Start1450, time._4Finish, link.healthy, 2, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week, time._1Start900, time._1Finish945, link.fizra, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHistory(), week, time._1Start950, time._1Finish, link.history, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrLit(), week, time._3Start1250, time._3Finish, link.ukrlit, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getTechnology(), week, time._4Start1450, time._4Finish, link.technology, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMusic(), week, time._1Start900, time._1Finish, link.music, 4, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGeografya(), week, time._3Start1250, time._3Finish, link.geografya, 4, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getZarubizhnaLiteratura(), week, time._2Start1050, time._2Finish, link.zarubizhnaliteratura, 4, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMystectvo(), week, time._1Start900, time._1Finish, link.mystectvo, 5, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika(), week, time._2Start1100, time._2Finish, link.informatika, 5, 0, 0));

        week = redWeek.getDates();

        System.out.println("Red Week  " + week);

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getPriroda(), week, time._1Start900, time._1Finish, link.priroda, 1, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._2Start1100, time._2Finish, link.matematika, 1, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._2Start1100, time._2Finish, link.matematika, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week, time._3Start1250, time._3Finish, link.ukrmova, 1, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week, time._2Start1050, time._2Finish, link.ukrmova, 2, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week, time._4Start1450, time._4Finish, link.english, 1, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week, time._3Start1250, time._3Finish, link.english, 2, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish2(), week, time._1900, time._2030, link.english, 5, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week, time._1Start900, time._1Finish, link.fizra, 2, 0, 0));
        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week, time._1Start900, time._1Finish945, link.fizra, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHistory(), week, time._1Start950, time._1Finish, link.history, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrLit(), week, time._3Start1250, time._3Finish, link.ukrlit, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getTechnology(), week, time._4Start1450, time._4Finish, link.technology, 3, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGeografya(), week, time._3Start1250, time._3Finish, link.geografya, 4, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getZarubizhnaLiteratura(), week, time._2Start1050, time._2Finish, link.zarubizhnaliteratura, 4, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHealthy(), week, time._1Start900, time._1Finish, link.healthy, 5, 0, 0));

        scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika(), week, time._2Start1100, time._2Finish, link.informatika, 5, 0, 0));

        //Пишем рассписание в файл .csv
        FileWriter fw = new FileWriter(Variables.pathToWriteFile);
        fw.write("Subject,Start Date,Start Time,End Date,End Time,Description" + System.lineSeparator());

        for(String sch:scheduleLessonsList){
            fw.write(sch);
        }

        fw.close();
    }

    //сортируем недели смещаяя по очереди
    public static LinkedList<Week> moveWeekToFront(LinkedList<Week> weeksList, String weekName) {
        System.out.println("Исходный список недель: " + weeksList);

        // Создаем временный Deque для сортировки
        Deque<Week> deque = new LinkedList<>(weeksList);

        for (Week week : deque) {
            if (week.getName().equals(weekName)) {
                System.out.println("Неделя, которую мы хотим переместить: " + week.getName());
                // Перемещаем неделю в начало Deque
                while (!deque.peekFirst().equals(week)) {
                    deque.addLast(deque.pollFirst());
                }
                break;
            }
        }

        // Копируем содержимое Deque обратно в LinkedList
        LinkedList<Week> result = new LinkedList<>(deque);
        System.out.println("После перемещения: " + result);
        return result;
    }

}
