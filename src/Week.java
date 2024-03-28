import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Week {
    private String name;
    private ArrayList<LocalDate> dates;

    public Week(String name) {
        this.name = name;
        this.dates = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<LocalDate> getDates() {
        return dates;
    }

    public void addDate(LocalDate date) {
        dates.add(date);
    }

    @Override
    public String toString() {
        return name;
    }

    // Переопределение метода equals() для сравнения объектов по содержимому
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Week week = (Week) o;
        return Objects.equals(name, week.name) && Objects.equals(dates, week.dates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dates);
    }
}
