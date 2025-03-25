import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HelpClass {
    //назначает уроки по нужным датам и дням недели
    public static String oneTime3Days(String lessons,
                                      List<LocalDate> list,
                                      String startTime,
                                      String endTime,
                                      String info,
                                      int firstDay,
                                      int secondDay,
                                      int thirdDay) {
        // Используем StringJoiner для более эффективного concatenation
        var result = new StringBuilder();

        // Используем Stream API для фильтрации дней недели
        var days = new ArrayList<String>();
        var dayNums = new int[]{firstDay, secondDay, thirdDay};

        for (int day : dayNums) {
            if (day != 0) {
                days.add(dayOfWeek(day));
            }
        }

        // Оптимизируем циклы с помощью Stream API
        list.stream()
                .filter(date -> days.contains(date.getDayOfWeek().name()))
                .forEach(date -> {
                    result.append(String.format("%s,%d/%d/%d,%s,%d/%d/%d,%s,%s",
                                    lessons,
                                    date.getDayOfMonth(), date.getMonthValue(), date.getYear(),
                                    startTime,
                                    date.getDayOfMonth(), date.getMonthValue(), date.getYear(),
                                    endTime,
                                    info))
                            .append(System.lineSeparator());
                });

        return result.toString();
    }

    // Создаёт список дат определённого периода
    public static List<LocalDate> dateList() {
        LocalDate startDate = LocalDate.of(Variables.year, Variables.startMonth, Variables.startDay);
        LocalDate endDate = LocalDate.of(Variables.year, Variables.endMonth, Variables.endDay);

        // Используем EnumSet для выходных дней
        Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

        // Оптимизируем stream с помощью более компактного фильтра
        return startDate.datesUntil(endDate)
                .filter(date -> !weekend.contains(date.getDayOfWeek()))
                .collect(Collectors.toList());
    }

    // Перевод цифр в обозначение дня недели с использованием enhanced switch
    public static String dayOfWeek(int day) {
        return switch (day) {
            case 1 -> "MONDAY";
            case 2 -> "TUESDAY";
            case 3 -> "WEDNESDAY";
            case 4 -> "THURSDAY";
            case 5 -> "FRIDAY";
            default -> "";
        };
    }
}