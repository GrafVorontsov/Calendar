
import org.xml.sax.ext.DeclHandler;

import java.io.IOException;
import java.io.FileWriter;
import java.util.*;
import java.time.*;
import java.util.stream.*;
import java.time.temporal.*;

public class Calendar {
    public enum WeekColor {
        GREEN("Green Week"),
        YELLOW("Yellow Week"),
        BLUE("Blue Week"),
        RED("Red Week");

        private final String colorName;

        WeekColor(String colorName) {
            this.colorName = colorName;
        }

        public String getColorName() {
            return colorName;
        }

        public static WeekColor fromString(String colorName) {
            for (WeekColor color : values()) {
                if (color.colorName.equals(colorName)) {
                    return color;
                }
            }
            throw new IllegalArgumentException("No week color with name " + colorName);
        }
    }

    public static void main(String[] args) throws IOException {
        // Инициализация необходимых объектов
        Title title = new Title();
        Timing time = new Timing();
        Link link = new Link();

        // Создание недель с использованием Enum
        List<Week> weeksList = createWeeksList();

        // Перемещение стартовой недели в начало списка
        weeksList = reorderWeeksList(weeksList, Variables.startWeekColor.getColorName());

        // Получение списка дат
        List<LocalDate> days = HelpClass.dateList();

        // Распределение дат по неделям
        assignDatesToWeeks(weeksList, days, Variables.startWeekNumber);

        // Формирование расписания
        List<String> scheduleLessonsList = generateSchedule(weeksList, title, time, link);

        // Запись расписания в файл
        writeScheduleToFile(scheduleLessonsList, Variables.pathToWriteFile);
    }

    // Создание списка недель с использованием Enum
    private static List<Week> createWeeksList() {
        return List.of(
                new Week(WeekColor.GREEN.getColorName()),
                new Week(WeekColor.YELLOW.getColorName()),
                new Week(WeekColor.BLUE.getColorName()),
                new Week(WeekColor.RED.getColorName())
        );
    }

    // Улучшенный метод перемещения недель с использованием Enum
    // Сохраняем точно такой же алгоритм перемещения, как в оригинальном коде
    /*private static List<Week> reorderWeeksList(List<Week> weeksList, String startWeekColor) {
        // Определяем точный порядок недель
        List<String> colorOrder = List.of("Green Week", "Yellow Week", "Blue Week", "Red Week");

        // Находим индекс стартовой недели
        int startIndex = colorOrder.indexOf(startWeekColor);

        // Создаем новый список с правильным порядком
        List<Week> reorderedList = new ArrayList<>();
        for (int i = 0; i < colorOrder.size(); i++) {
            String currentColor = colorOrder.get((startIndex + i) % colorOrder.size());
            for (Week week : weeksList) {
                if (week.getName().equals(currentColor)) {
                    reorderedList.add(week);
                    break;
                }
            }
        }

        return reorderedList;
    }*/
    public static List<Week> reorderWeeksList(List<Week> weeksList, String startWeekColor) {
        Deque<Week> deque = new LinkedList<>(weeksList);

        for (Week week : deque) {
            if (week.getName().equals(startWeekColor)) {
                while (!deque.peekFirst().equals(week)) {
                    deque.addLast(deque.pollFirst());
                }
                break;
            }
        }

        return new ArrayList<>(deque);
    }

    // Метод распределения дат по неделям
    private static void assignDatesToWeeks(List<Week> weeksList, List<LocalDate> days, int startWeekNumber) {
        for (LocalDate date : days) {
            int weekOfYear = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
            int weekIndex = calculateWeekIndex(weekOfYear, startWeekNumber);

            if (weekIndex != -1) {
                weeksList.get(weekIndex).addDate(date);
            }
        }
    }

    // Улучшенный метод определения индекса недели
    private static int calculateWeekIndex(int weekOfYear, int startWeekNumber) {
        for (int i = 0; i < 4; i++) {
            if (weekOfYear == startWeekNumber + i ||
                    weekOfYear == startWeekNumber + i + 4 ||
                    weekOfYear == startWeekNumber + i + 8) {
                return i;
            }
        }
        return -1;
    }

    // Генерация расписания с использованием Enum
    private static List<String> generateSchedule(List<Week> weeksList, Title title, Timing time, Link link) {
        List<String> scheduleLessonsList = new ArrayList<>();

        for (Week week : weeksList) {
            System.out.println(week.getName() + "  " + week.getDates());

            WeekColor weekColor = WeekColor.fromString(week.getName());
            switch (weekColor) {
                case GREEN:
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getPriroda(), week.getDates(), time._1Start900, time._1Finish, link.priroda, 1, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week.getDates(), time._2Start1100, time._2Finish, link.matematika, 1, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week.getDates(), time._1Start915, time._1Finish, link.matematika, 2, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week.getDates(), time._2Start1100, time._2Finish, link.matematika, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week.getDates(), time._3Start1250, time._3Finish, link.ukrmova, 1, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week.getDates(), time._2Start1050, time._2Finish, link.ukrmova, 2, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week.getDates(), time._4Start1450, time._4Finish, link.english, 1, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week.getDates(), time._3Start1250, time._3Finish, link.english, 2, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish2(), week.getDates(), time._1900, time._2030, link.english, 5, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week.getDates(), time._1Start900, time._1Finish945, link.fizra, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHistory(), week.getDates(), time._1Start950, time._1Finish, link.history, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrLit(), week.getDates(), time._3Start1250, time._3Finish, link.ukrlit, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getTechnology(), week.getDates(), time._4Start1450, time._4Finish, link.technology, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMusic(), week.getDates(), time._2Start1045, time._2Finish, link.music, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGeografya(), week.getDates(), time._3Start1250, time._3Finish, link.geografya, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMystectvo(), week.getDates(), time._1Start900, time._1Finish, link.mystectvo, 5, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika(), week.getDates(), time._2Start1100, time._2Finish, link.informatika, 5, 0, 0));
                    break;
                case YELLOW:
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getPriroda(), week.getDates(), time._1Start900, time._1Finish, link.priroda, 1, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week.getDates(), time._2Start1100, time._2Finish, link.matematika, 1, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week.getDates(), time._2Start1100, time._2Finish, link.matematika, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week.getDates(), time._3Start1250, time._3Finish, link.ukrmova, 1, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week.getDates(), time._2Start1050, time._2Finish, link.ukrmova, 2, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week.getDates(), time._4Start1450, time._4Finish, link.english, 1, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week.getDates(), time._3Start1250, time._3Finish, link.english, 2, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish2(), week.getDates(), time._1900, time._2030, link.english, 5, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week.getDates(), time._1Start900, time._1Finish, link.fizra, 2, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week.getDates(), time._1Start900, time._1Finish945, link.fizra, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHistory(), week.getDates(), time._1Start950, time._1Finish, link.history, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrLit(), week.getDates(), time._3Start1250, time._3Finish, link.ukrlit, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getTechnology(), week.getDates(), time._4Start1450, time._4Finish, link.technology, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGeografya(), week.getDates(), time._3Start1250, time._3Finish, link.geografya, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getZarubizhnaLiteratura(), week.getDates(), time._2Start1050, time._2Finish, link.zarubizhnaliteratura, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHealthy(), week.getDates(), time._2Start1050, time._2Finish, link.healthy, 5, 0, 0));
                    break;
                case BLUE:
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getPriroda(), week.getDates(), time._1Start900, time._1Finish, link.priroda, 1, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week.getDates(), time._2Start1100, time._2Finish, link.matematika, 1, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week.getDates(), time._1Start915, time._1Finish, link.matematika, 2, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week.getDates(), time._2Start1100, time._2Finish, link.matematika, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week.getDates(), time._3Start1250, time._3Finish, link.ukrmova, 1, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week.getDates(), time._2Start1050, time._2Finish, link.ukrmova, 2, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week.getDates(), time._3Start1250, time._3Finish, link.english, 2, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish2(), week.getDates(), time._1900, time._2030, link.english, 5, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHealthy(), week.getDates(), time._4Start1450, time._4Finish, link.healthy, 2, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week.getDates(), time._1Start900, time._1Finish945, link.fizra, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHistory(), week.getDates(), time._1Start950, time._1Finish, link.history, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrLit(), week.getDates(), time._3Start1250, time._3Finish, link.ukrlit, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getTechnology(), week.getDates(), time._4Start1450, time._4Finish, link.technology, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMusic(), week.getDates(), time._1Start900, time._1Finish, link.music, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGeografya(), week.getDates(), time._3Start1250, time._3Finish, link.geografya, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getZarubizhnaLiteratura(), week.getDates(), time._2Start1050, time._2Finish, link.zarubizhnaliteratura, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMystectvo(), week.getDates(), time._1Start900, time._1Finish, link.mystectvo, 5, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika(), week.getDates(), time._2Start1100, time._2Finish, link.informatika, 5, 0, 0));
                    break;
                case RED:
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getPriroda(), week.getDates(), time._1Start900, time._1Finish, link.priroda, 1, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week.getDates(), time._2Start1100, time._2Finish, link.matematika, 1, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMatematika(), week.getDates(), time._2Start1100, time._2Finish, link.matematika, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week.getDates(), time._3Start1250, time._3Finish, link.ukrmova, 1, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week.getDates(), time._2Start1050, time._2Finish, link.ukrmova, 2, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week.getDates(), time._4Start1450, time._4Finish, link.english, 1, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week.getDates(), time._3Start1250, time._3Finish, link.english, 2, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish2(), week.getDates(), time._1900, time._2030, link.english, 5, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week.getDates(), time._1Start900, time._1Finish, link.fizra, 2, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week.getDates(), time._1Start900, time._1Finish945, link.fizra, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHistory(), week.getDates(), time._1Start950, time._1Finish, link.history, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrLit(), week.getDates(), time._3Start1250, time._3Finish, link.ukrlit, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getTechnology(), week.getDates(), time._4Start1450, time._4Finish, link.technology, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGeografya(), week.getDates(), time._3Start1250, time._3Finish, link.geografya, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getZarubizhnaLiteratura(), week.getDates(), time._2Start1050, time._2Finish, link.zarubizhnaliteratura, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHealthy(), week.getDates(), time._1Start900, time._1Finish, link.healthy, 5, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika(), week.getDates(), time._2Start1100, time._2Finish, link.informatika, 5, 0, 0));
                    break;
            }
        }
        return scheduleLessonsList;
    }

    // Запись расписания в файл
    private static void writeScheduleToFile(List<String> scheduleLessonsList, String filePath) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write("Subject,Start Date,Start Time,End Date,End Time,Description" + System.lineSeparator());

            for (String schedule : scheduleLessonsList) {
                fw.write(schedule);
            }
        }
    }

}