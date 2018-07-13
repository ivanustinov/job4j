package vocancyparcer;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 10.07.2018
 */
public class ParsingRepeat implements Job {
    SQLRUParser parser = new SQLRUParser("/vocansyparser.properties");

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        parser.parse();
    }
}
