package vocancyparcer;

import org.apache.log4j.BasicConfigurator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 09.07.2018
 */
public class SQLRUParser {

    private static LocalDateTime lastDayParsing = LocalDateTime.now();
    private static boolean isFirstParsing = true;
    private static DataBase base = new DataBase("/vocansyparser.properties");



    public void parse() {
        System.out.println(lastDayParsing);
        ArrayList<String> vocancies = new ArrayList<>();
        LocalDateTime firstDay = LocalDateTime.now().with(firstDayOfYear());
        LocalDateTime date = LocalDateTime.now();
        int i = 0;
        while (firstDay.isBefore(date)) {
            try {
                Document doc = Jsoup.connect(String.format("http://www.sql.ru/forum/job-offers/%s", ++i)).get();
                Elements eelements = doc.getElementsByTag("tr");
                Elements el = new Elements(eelements.subList(7, eelements.size() - 3));
                for (Element element : el) {
                    String vocancy = element.child(1).child(0).text();
                    String ddata = element.children().get(5).text().split(",")[0];
                    String time = element.children().get(5).text().split(",")[1];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yy k:mm");
                    date = checkDate(ddata, time, formatter);
                    if (!isFirstParsing) {
                        firstDay = lastDayParsing;
                        if (lastDayParsing.isBefore(date)) {
                            vocancies.add(vocancy);
                        } else break;
                    } else {
                        if (firstDay.isBefore(date)) {
                            vocancies.add(vocancy);
                        } else {
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        lastDayParsing = LocalDateTime.now();
        isFirstParsing = false;
        base.insert(vocancies);
    }

    public LocalDateTime checkDate(String date, String time, DateTimeFormatter formatter) {
        LocalDateTime localDate;
        if (date.contains("сегодня")) {
            localDate = LocalDateTime.now();
        } else if (date.contains("вчера")) {
            String[] t = time.split(":");
            t[0] = t[0].trim();
            LocalDate da = LocalDate.now().minusDays(1);
            LocalTime ta = LocalTime.of(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
            localDate = LocalDateTime.of(da, ta);
        } else if (date.contains("май")) {
            String[] d = date.split(" ");
            String[] t = time.split(":");
            t[0] = t[0].trim();
            localDate = LocalDateTime.of(Integer.parseInt(d[2]) + 2000, Month.MAY, Integer.parseInt(d[0]),
                    Integer.parseInt(t[0]), Integer.parseInt(t[1]));
        } else {
            localDate = LocalDateTime.parse(String.format("%s%s", date, time), formatter);
        }
        return localDate;
    }


    public static void main(String[] args) throws SchedulerException {
        BasicConfigurator.configure();
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        JobDetail job = JobBuilder.newJob(ParsingRepeat.class).build();
        Trigger trigger = newTrigger()
                .withSchedule(cronSchedule("0 0 12 * * ?"))
                .build();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
