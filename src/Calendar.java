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
        /*greenWeek.add(LocalDate.now());
        blueWeek.add(LocalDate.now().plusDays(2));
        yellowWeek.add(LocalDate.now().plusDays(1));
        redWeek.add(LocalDate.now().plusDays(3));*/
        Deque<ArrayList<LocalDate>> weeksList = new LinkedList<>(); //все цвета недель
        weeksList.add(greenWeek);   //0
        weeksList.add(yellowWeek);  //1
        weeksList.add(blueWeek);    //2
        weeksList.add(redWeek);     //3

        List<LocalDate> days = HelpClass.dateList(); //общий массив дат

        int startWeekNumber = Variables.startWeekNumber;

        //Перемещения недель
        //меняем последовательность недель, с какой недели начинается та и первая в списке
        switch (Variables.startWeekColor){
            case "green" : moveWeekToFront(weeksList, greenWeek); break;
            case "yellow" : moveWeekToFront(weeksList, yellowWeek); break;
            case "blue" : moveWeekToFront(weeksList, blueWeek); break;
            case "red" : moveWeekToFront(weeksList, redWeek); break;
        }
        //moveWeekToFront(weeksList, blueWeek); //меняем последовательность недель, с какой недели начинается та и первая в списке
        //System.out.println(weeksList);
        //назначаем каждой неделе свои даты по очереди
        for(LocalDate date:days){

            if ((date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber)
                    || (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 4)
                    || (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 8)){
                getElementAtIndex(weeksList, 0).add(date);
                //System.out.println(date);
            }
        }

        for(LocalDate date:days){

            if ((date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 1)
                    || (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 5)
                    || (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 9)){
                getElementAtIndex(weeksList, 1).add(date);
                //System.out.println(date);
            }
        }

        for(LocalDate date:days){

            if ((date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 2)
                    || (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 6)
                    || (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 10)){
                getElementAtIndex(weeksList, 2).add(date);
                //System.out.println(date);
            }
        }

        for(LocalDate date:days){

            if ((date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 3)
                    || (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 7)
                    || (date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == startWeekNumber + 11)){
                getElementAtIndex(weeksList, 3).add(date);
                //System.out.println(date);
            }
        }

        //формируем массив уроков по неделям и датам
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
        FileWriter fw = new FileWriter(Variables.pathToWriteFile);
        fw.write("Subject,Start Date,Start Time,End Date,End Time,Description" + System.lineSeparator());

        for(String sch:scheduleLessonsList){
            fw.write(sch);
        }

        fw.close();
    }

    //сортируем недели смещаяя по очереди
    public static void moveWeekToFront(Deque<ArrayList<LocalDate>> weeksList, ArrayList<LocalDate> week) {
        if (weeksList.contains(week)) {
            while (!weeksList.peekFirst().equals(week)) {
                weeksList.addLast(weeksList.pollFirst());
            }
        }
    }

    //поиск элемента в очереди по индексу
    public static ArrayList<LocalDate> getElementAtIndex(Deque<ArrayList<LocalDate>> weeksList, int index) {
        if (index < 0 || index >= weeksList.size()) {
            throw new IndexOutOfBoundsException("Индекс находится за пределами диапазона");
        }

        int currentIndex = 0;
        for (ArrayList<LocalDate> week : weeksList) {
            if (currentIndex == index) {
                return week;
            }
            currentIndex++;
        }

        throw new IndexOutOfBoundsException("Элемент с указанным индексом не найден");
    }
}