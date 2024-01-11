import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HelpClass {
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
                            .append(date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear()).append(",")
                            .append(startTime).append(",")
                            .append(date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear()).append(",")
                            .append(endTime).append(",")
                            .append(info).append(System.getProperty("line.separator"));
                }
            }
        }

        return str.toString();
    }

    //делает массив дат определённого периода
    public static List<LocalDate> dateList(){
        LocalDate startDate = LocalDate.of(2024, Month.JANUARY, 15);  //с понедельника
        LocalDate endDate = LocalDate.of(2024, Month.MARCH, 23);   //по субботу
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
