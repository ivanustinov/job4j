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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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

    public String getCronExpression() {
        return cronExpression;
    }

    public void parse(String url) {
        try (Connection connection = DriverManager.getConnection(this.url, user, password);
             Statement stm = connection.createStatement();
             PreparedStatement insert = connection.prepareStatement("INSERT INTO javadevelopers (url, vocancy)"
                     + "VALUES (?, ?)");
             PreparedStatement select = connection.prepareStatement("SELECT url, vocancy FROM javadevelopers")) {
            Document doc = Jsoup.connect(url).get();
            Elements eelements = doc.getElementsByAttributeValue("class", "postslisttopic");
            stm.execute("CREATE TABLE IF NOT EXISTS javadevelopers(id SERIAL, url text primary key, vocancy text)");
            List<String> urls = new ArrayList<>();
            ResultSet rs = select.executeQuery();
            while (rs.next()) {
                urls.add(rs.getString("url"));
                LOGGER.info(rs.getString("vocancy"));
            }
            for (Element eelement : eelements) {
                Element element = eelement.child(0);
                String ur_l = element.attr("href");
                String job = element.text();
                if ((job.contains("Java") || job.contains("java")) && !urls.contains(ur_l)) {
                    LOGGER.info(job);
                    insert.setString(1, ur_l);
                    insert.setString(2, job);
                    insert.executeUpdate();
                } else {
                    continue;
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws SchedulerException {
        BasicConfigurator.configure();
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        JobDetail job = JobBuilder.newJob(Job.class).build();
        Trigger trigger = newTrigger()
                .withSchedule(cronSchedule("0 0 12 * * ?"))
                .build();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
