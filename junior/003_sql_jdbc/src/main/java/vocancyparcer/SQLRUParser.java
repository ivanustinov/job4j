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

    private static LocalDate lastDayParsing = LocalDate.now();
    private static boolean isFirstParsing = true;
    private static DataBase base = new DataBase("/vocansyparser.properties");



    public void parse() {
        System.out.println(lastDayParsing);
        ArrayList<String> vocancies = new ArrayList<>();
        LocalDate firstDay = LocalDate.now().with(firstDayOfYear());
        LocalDate date = LocalDate.now();
        int i = 0;
        while (firstDay.isBefore(date)) {
            try {
                Document doc = Jsoup.connect(String.format("http://www.sql.ru/forum/job-offers/%s", ++i)).get();
                Elements eelements = doc.getElementsByTag("tr");
                Elements el = new Elements(eelements.subList(7, eelements.size() - 3));
                for (Element element : el) {
                    String vocancy = element.child(1).child(0).text();
                    String ddata = element.children().get(5).text().split(",")[0];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yy");
                    date = checkDate(ddata, formatter);
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
        lastDayParsing = LocalDate.now();
        isFirstParsing = false;
        base.insert(vocancies);
    }

    public LocalDate checkDate(String date, DateTimeFormatter formatter) {
        LocalDate localDate;
        if (date.contains("сегодня")) {
            localDate = LocalDate.now();
        } else if (date.contains("вчера")) {
            localDate = LocalDate.now().minusDays(1);
        } else if (date.contains("май")) {
            String[] d = date.split(" ");
            localDate = LocalDate.of(Integer.parseInt(d[2]) + 2000, Month.MAY, Integer.parseInt(d[0]));
        } else {
            localDate = LocalDate.parse(date, formatter);
        }
        return localDate;
    }


    public static void main(String[] args) throws SchedulerException {
        BasicConfigurator.configure();
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        JobDetail job = JobBuilder.newJob(ParsingRepeat.class).build();
        Trigger trigger = newTrigger()
                .withSchedule(cronSchedule("0/7 * * * * ?"))
                .build();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
