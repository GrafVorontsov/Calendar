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
        ArrayList<LocalDate> greenWeek = new ArrayList<>();     //зелёная неделя
        ArrayList<LocalDate> blueWeek = new ArrayList<>();      //синяя неделя
        ArrayList<LocalDate> yellowWeek = new ArrayList<>();    //жёлтая неделя
        ArrayList<LocalDate> redWeek = new ArrayList<>();       //красная неделя
        ArrayList<LocalDate> week = new ArrayList<>();          //неделя


        List<LocalDate> days = HelpClass.dateList(); //общий массив дат

        int startWeekNumber = 3; //номер первой недели в полугодии. 2024год 3-я неделя жёлтая

        System.out.println("Жёлтая неделя");

        for(LocalDate date:days){

            if ((date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber)
                    || (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 4)
                    || (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 8)){
                yellowWeek.add(date);
                System.out.println(date);
            }
        }

        System.out.println("Синяя неделя");

        for(LocalDate date:days){

            if ((date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 1)
                    || (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 5)
                    || (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 9)){
                blueWeek.add(date);
                System.out.println(date);
            }
        }

        System.out.println("Красная неделя");

        for(LocalDate date:days){

            if ((date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 2)
                    || (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 6)
                    || (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 10)){
                redWeek.add(date);
                System.out.println(date);
            }
        }

        System.out.println("Зелёная неделя");

        for(LocalDate date:days){

            if ((date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 3)
                    || (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 7)
                    || (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 11)){
                greenWeek.add(date);
                System.out.println(date);
            }
        }

        ArrayList<String> scheduleLessonsList = new ArrayList<>();

        String weekColor = "Жёлтая неделя";

        if (weekColor == "Жёлтая неделя") {

            week = yellowWeek;

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week, time._1Start900, time._1Finish, link.ukrmova, 1, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week, time._2Start1050, time._2Finish, link.ukrmova, 3, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrLit(), week, time._1Start900, time._1Finish, link.ukrlit, 5, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week, time._2Start1050, time._2Finish, link.english, 1, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week, time._1Start900, time._1Finish, link.english, 4, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getPriroda(), week, time._3Start1250, time._3Finish, link.priroda, 1, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getLiteratura(), week, time._4Start1445, time._4Finish, link.literatura, 1, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._1Start915, time._1Finish, link.matematika, 2, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._2Start1100, time._2Finish, link.matematika, 4, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getTechnology(), week, time._2Start, time._2Finish, link.technology, 2, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week, time._1Start900, time._1Finish, link.fizra, 3, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHealthy(), week, time._3Start1250, time._3Finish, link.healthy, 4, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika(), week, time._3Start, time._3Finish, link.informatika, 5, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMusic(), week, time._2Start, time._2Finish, link.music, 5, 0, 0));
        }

        weekColor = "Синяя неделя";

        if (weekColor == "Синяя неделя") {

            week = blueWeek;

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week, time._1Start900, time._1Finish, link.ukrmova, 1, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week, time._2Start1050, time._2Finish, link.ukrmova, 3, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrLit(), week, time._1Start900, time._1Finish, link.ukrlit, 5, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week, time._2Start1050, time._2Finish, link.english, 1, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week, time._2Start1050, time._2Finish, link.english, 5, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getPriroda(), week, time._3Start1250, time._3Finish, link.priroda, 1, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getLiteratura(), week, time._4Start1445, time._4Finish, link.literatura, 1, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._1Start915, time._1Finish, link.matematika, 2, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._3Start, time._3Finish, link.matematika, 4, 5, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getTechnology(), week, time._2Start, time._2Finish, link.technology, 2, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week, time._1Start900, time._1Finish, link.fizra, 3, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week, time._1Start900, time._1Finish, link.fizra, 4, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHealthy(), week, time._4Start1450, time._4Finish, link.healthy, 4, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMystectvo(), week, time._2Start, time._2Finish, link.mystectvo, 4, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHistory(), week, time._3Start, time._3Finish, link.history, 3, 0, 0));
        }


        weekColor = "Красная неделя";

        if (weekColor == "Красная неделя") {

            week = redWeek;

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week, time._1Start900, time._1Finish, link.ukrmova, 1, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week, time._2Start1050, time._2Finish, link.ukrmova, 3, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrLit(), week, time._1Start900, time._1Finish, link.ukrlit, 5, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week, time._2Start1050, time._2Finish, link.english, 1, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getPriroda(), week, time._3Start1250, time._3Finish, link.priroda, 1, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getLiteratura(), week, time._4Start1445, time._4Finish, link.literatura, 1, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._1Start915, time._1Finish, link.matematika, 2, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._2Start1100, time._2Finish, link.matematika, 4, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getTechnology(), week, time._2Start, time._2Finish, link.technology, 2, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week, time._1Start900, time._1Finish, link.fizra, 3, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHealthy(), week, time._3Start1250, time._3Finish, link.healthy, 4, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika(), week, time._3Start, time._3Finish, link.informatika, 5, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMusic(), week, time._2Start, time._2Finish, link.music, 5, 0, 0));
        }


        weekColor = "Зелёная неделя";

        if (weekColor == "Зелёная неделя") {

            week = greenWeek;

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week, time._1Start900, time._1Finish, link.ukrmova, 1, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week, time._2Start1050, time._2Finish, link.ukrmova, 3, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrLit(), week, time._1Start900, time._1Finish, link.ukrlit, 5, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week, time._2Start1050, time._2Finish, link.english, 1, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week, time._2Start1050, time._2Finish, link.english, 5, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getPriroda(), week, time._3Start1250, time._3Finish, link.priroda, 1, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._1Start915, time._1Finish, link.matematika, 2, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._2Start1100, time._2Finish, link.matematika, 4, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week, time._4Start, time._4Finish, link.matematika, 3, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getTechnology(), week, time._2Start, time._2Finish, link.technology, 2, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week, time._1Start900, time._1Finish, link.fizra, 3, 0, 0));
            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week, time._1Start900, time._1Finish, link.fizra, 4, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika(), week, time._3Start, time._3Finish, link.informatika, 4, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMystectvo(), week, time._3Start1245, time._3Finish, link.mystectvo, 2, 0, 0));

            scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHistory(), week, time._3Start, time._3Finish, link.history, 3, 0, 0));
        }

        //Пишем рассписание в файл .csv
        FileWriter fw = new FileWriter("/home/forever/sch.csv");
        fw.write("Subject,Start Date,Start Time,End Date,End Time,Description" + System.lineSeparator());

        for(String sch:scheduleLessonsList){
            fw.write(sch);
        }

        fw.close();
    }
}