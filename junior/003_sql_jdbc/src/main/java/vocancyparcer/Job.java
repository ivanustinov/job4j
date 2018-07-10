package vocancyparcer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 10.07.2018
 */
public class Job implements org.quartz.Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SQLRUParser parser = new SQLRUParser("/vocansyparser.properties");
        parser.parse("http://www.sql.ru/forum/job-offers");
    }
}
