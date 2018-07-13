package vocancyparcer;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 09.07.2018
 */
public class SQLRUParser {
    private final Properties property = new Properties();
    private String url;
    private String user;
    private String password;
    private String cronExpression;
    private static final Logger LOGGER = LogManager.getLogger(SQLRUParser.class.getName());
    private static LocalDate lastDayParsing = LocalDate.now();
    private static int i = 1;

    public SQLRUParser(String fileProperties) {
        try (InputStream inputStream = getClass().getResourceAsStream(fileProperties)) {
            property.load(inputStream);
            url = property.getProperty("jdbc.url");
            user = property.getProperty("jdbc.user");
            password = property.getProperty("jdbc.password");
            cronExpression = property.getProperty("cron.time");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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
                    if (this.i != 1 && lastDayParsing.isBefore(date)) {
                        firstDay = lastDayParsing;
                        vocancies.add(vocancy);
                        continue;
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
        insert(vocancies);
    }

    public LocalDate checkDate(String date, DateTimeFormatter formatter) {
        if (date.contains("сегодня")) {
            return LocalDate.now();
        }
        if (date.contains("вчера")) {
            return LocalDate.now().minusDays(1);
        }
        if (date.contains("май")) {
            return LocalDate.now().minusDays(60);
        } else return LocalDate.parse(date, formatter);
    }


    public void insert(List<String> list) {
        try (Connection connection = DriverManager.getConnection(this.url, user, password);
             Statement stm = connection.createStatement();
             PreparedStatement insert = connection.prepareStatement("INSERT INTO javadevelopers2 (id, vocancy)"
                     + "VALUES (?, ?)")) {
            stm.execute("CREATE TABLE IF NOT EXISTS javadevelopers2(id integer PRIMARY KEY, vocancy text)");
//            stm.execute("DELETE FROM javadevelopers2");
            for (String job : list) {
                if ((job.contains("Java") || job.contains("java"))) {
                    LOGGER.info(job);
                    insert.setInt(1, i++);
                    insert.setString(2, job);
                    insert.executeUpdate();
                } else {
                    continue;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
