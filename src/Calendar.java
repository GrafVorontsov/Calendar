
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
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getAlgebra(), week.getDates(), time._1Start830, time._1Finish1010, link.algebra, 1, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getAlgebra(), week.getDates(), time._4Start1420, time._4Finish1600, link.algebra, 2, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGeografya(), week.getDates(), time._2Start1020, time._2Finish1200, link.geografya, 1, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getZarubizhnaLiteratura(), week.getDates(), time._3Start1220, time._3Finish1400, link.zarubizhnaliteratura, 1, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week.getDates(), time._4Start1420, time._4Finish1600, link.english, 1, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week.getDates(), time._4Start1420, time._4Finish1600, link.english, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getBiologia(), week.getDates(), time._3Start1220, time._3Finish1400, link.biologia, 2, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getBiologia(), week.getDates(), time._2Start1020, time._2Finish1200, link.biologia, 5, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrainHistory(), week.getDates(), time._1Start830, time._1Finish1010, link.ukrainHistory, 2, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week.getDates(), time._1Start830, time._1Finish1010, link.ukrmova, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week.getDates(), time._2Start1020, time._2Finish1200, link.fizra, 2, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week.getDates(), time._2Start1020, time._2Finish1200, link.fizra, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGeometry(), week.getDates(), time._2Start1020, time._2Finish1200, link.geometria, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getPhizika(), week.getDates(), time._3Start1220, time._3Finish1400, link.phizika, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrLit(), week.getDates(), time._1Start830, time._1Finish1010, link.ukrlit, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getXimia(), week.getDates(), time._3Start1220, time._3Finish1400, link.ximia, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika(), week.getDates(), time._1Start830, time._1Finish1010, link.informatika, 5, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika2(), week.getDates(), time._4Start1420, time._4Finish1600, link.informatika, 5, 0, 0));

                    break;
                case YELLOW:
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getAlgebra(), week.getDates(), time._1Start830, time._1Finish1010, link.algebra, 1, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGeografya(), week.getDates(), time._2Start1020, time._2Finish1200, link.geografya, 1, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGromadOsvita(), week.getDates(), time._3Start1220, time._3Finish1400, link.gromadOsvita, 1, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week.getDates(), time._4Start1420, time._4Finish1600, link.english, 1, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week.getDates(), time._3Start1220, time._3Finish1400, link.english, 2, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getBiologia(), week.getDates(), time._4Start1420, time._4Finish1600, link.biologia, 2, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week.getDates(), time._1Start830, time._1Finish1010, link.ukrmova, 2, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week.getDates(), time._1Start830, time._1Finish1010, link.ukrmova, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week.getDates(), time._2Start1020, time._2Finish1200, link.fizra, 2, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGeometry(), week.getDates(), time._2Start1020, time._2Finish1200, link.geometria, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getPhizika(), week.getDates(), time._3Start1220, time._3Finish1400, link.phizika, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHealthy(), week.getDates(), time._4Start1420, time._4Finish1600, link.healthy, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getTechnology(), week.getDates(), time._4Start1420, time._4Finish1600, link.technology, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrLit(), week.getDates(), time._1Start830, time._1Finish1010, link.ukrlit, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getWorldHistory(), week.getDates(), time._2Start1020, time._2Finish1200, link.worldHistory, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getRisovanie(), week.getDates(), time._3Start1220, time._3Finish1400, link.risovanie, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika(), week.getDates(), time._1Start830, time._1Finish1010, link.informatika, 5, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika2(), week.getDates(), time._4Start1420, time._4Finish1600, link.informatika, 5, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMusic(), week.getDates(), time._2Start1020, time._2Finish1200, link.music, 5, 0, 0));

                    break;
                case BLUE:
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getAlgebra(), week.getDates(), time._1Start830, time._1Finish1010, link.algebra, 1, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getAlgebra(), week.getDates(), time._4Start1420, time._4Finish1600, link.algebra, 2, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGeografya(), week.getDates(), time._2Start1020, time._2Finish1200, link.geografya, 1, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getZarubizhnaLiteratura(), week.getDates(), time._3Start1220, time._3Finish1400, link.zarubizhnaliteratura, 1, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week.getDates(), time._4Start1420, time._4Finish1600, link.english, 1, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week.getDates(), time._4Start1420, time._4Finish1600, link.english, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getBiologia(), week.getDates(), time._3Start1220, time._3Finish1400, link.biologia, 2, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrainHistory(), week.getDates(), time._1Start830, time._1Finish1010, link.ukrainHistory, 2, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week.getDates(), time._1Start830, time._1Finish1010, link.ukrmova, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week.getDates(), time._2Start1020, time._2Finish1200, link.fizra, 2, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week.getDates(), time._2Start1020, time._2Finish1200, link.fizra, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGeometry(), week.getDates(), time._2Start1020, time._2Finish1200, link.geometria, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getPhizika(), week.getDates(), time._3Start1220, time._3Finish1400, link.phizika, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrLit(), week.getDates(), time._1Start830, time._1Finish1010, link.ukrlit, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getXimia(), week.getDates(), time._3Start1220, time._3Finish1400, link.ximia, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika(), week.getDates(), time._1Start830, time._1Finish1010, link.informatika, 5, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika2(), week.getDates(), time._4Start1420, time._4Finish1600, link.informatika, 5, 0, 0));

                    break;
                case RED:
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getAlgebra(), week.getDates(), time._1Start830, time._1Finish1010, link.algebra, 1, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGeografya(), week.getDates(), time._2Start1020, time._2Finish1200, link.geografya, 1, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getZarubizhnaLiteratura(), week.getDates(), time._3Start1220, time._3Finish1400, link.zarubizhnaliteratura, 1, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getEnglish(), week.getDates(), time._3Start1220, time._3Finish1400, link.english, 2, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getBiologia(), week.getDates(), time._4Start1420, time._4Finish1600, link.biologia, 1, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week.getDates(), time._1Start830, time._1Finish1010, link.ukrmova, 2, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrmova(), week.getDates(), time._1Start830, time._1Finish1010, link.ukrmova, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getFizra(), week.getDates(), time._2Start1020, time._2Finish1200, link.fizra, 2, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getGeometry(), week.getDates(), time._2Start1020, time._2Finish1200, link.geometria, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getPhizika(), week.getDates(), time._3Start1220, time._3Finish1400, link.phizika, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getHealthy(), week.getDates(), time._4Start1420, time._4Finish1600, link.healthy, 2, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getTechnology(), week.getDates(), time._4Start1420, time._4Finish1600, link.technology, 3, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getUkrLit(), week.getDates(), time._1Start830, time._1Finish1010, link.ukrlit, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getWorldHistory(), week.getDates(), time._2Start1020, time._2Finish1200, link.worldHistory, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getRisovanie(), week.getDates(), time._3Start1220, time._3Finish1400, link.risovanie, 4, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika(), week.getDates(), time._1Start830, time._1Finish1010, link.informatika, 5, 0, 0));
                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getInformatika2(), week.getDates(), time._4Start1420, time._4Finish1600, link.informatika, 5, 0, 0));

                    scheduleLessonsList.add(HelpClass.oneTime3Days(title.getMusic(), week.getDates(), time._2Start1020, time._2Finish1200, link.music, 5, 0, 0));

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