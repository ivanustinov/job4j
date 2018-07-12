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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.Date;

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
    private ArrayList<String> vocancies;
    private static final Logger LOGGER = LogManager.getLogger(SQLRUParser.class.getName());

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
        vocancies = new ArrayList<>();
        java.util.Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date date = c.getTime();
        Date last = new Date();
        int i = 0;
        do {
            try {
                Document doci = Jsoup.connect(String.format("http://www.sql.ru/forum/job-offers/%s", ++i)).get();
                Elements eelements = doci.getElementsByTag("tr");
                Elements el = new Elements(eelements.subList(7, eelements.size() - 3));
                SimpleDateFormat formatForDate = new SimpleDateFormat("d MMM yy");
                for (Element element : el) {
                    try {
                        String ddata = element.children().get(5).text();
                        if (ddata.contains("сегодня") || ddata.contains("вчера")) {
                            vocancies.add(element.child(1).child(0).text());
                            continue;
                        }
                        last = formatForDate.parse(ddata);
                        if (date.before(last)) {
                            vocancies.add(element.child(1).child(0).text());
                        } else {
                            break;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (date.before(last));
        insert(vocancies);
    }

    public void insert(List<String> list) {
        try (Connection connection = DriverManager.getConnection(this.url, user, password);
             Statement stm = connection.createStatement();
             PreparedStatement insert = connection.prepareStatement("INSERT INTO javadevelopers2 (id, vocancy)"
                     + "VALUES (?, ?)")) {
            stm.execute("CREATE TABLE IF NOT EXISTS javadevelopers2(id integer PRIMARY KEY, vocancy text)");
            stm.execute("DELETE FROM javadevelopers2");
            int i = 1;
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
                .withSchedule(cronSchedule("0/5 * * * * ?"))
                .build();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
