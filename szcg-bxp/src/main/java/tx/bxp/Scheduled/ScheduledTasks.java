package tx.bxp.Scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tx.bxp.entity.PTask;
import tx.bxp.service.ITaskService;

import java.util.List;


@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    private ITaskService taskService;

    //每15分钟执行一次
    @Scheduled(cron = "0 */15 *  * * * ")
    public void reportCurrentByCron() {

        try {
            //已经发布任务但没人抢的
            List<PTask> PastPTask = taskService.pastPTask();
            if (PastPTask != null && PastPTask.size() > 0) {
                for (PTask task : PastPTask) {
                    System.out.println(task.getId());
                    Integer Id = task.getId();
                    Integer Projcode = task.getProjcode();
                    taskService.editpastPTask(Id, Projcode);

                }
            }
            //抢了没人做
            List<PTask> NOuserPTask = taskService.NOuserPTask();
            if (NOuserPTask != null && NOuserPTask.size() > 0) {
                for (PTask task : NOuserPTask) {
                    System.out.println(task.getId());
                    Integer Id = task.getId();
                    Integer Projcode = task.getProjcode();
                    taskService.editpastPTask1(Id, Projcode);
                }
            }
        } catch (Exception ex) {
        }

    }
}