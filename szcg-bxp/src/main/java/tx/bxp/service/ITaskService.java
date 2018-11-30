package tx.bxp.service;

import org.springframework.web.multipart.MultipartFile;
import tx.bxp.dto.MyPTask;
import tx.bxp.entity.PTask;
import tx.bxp.entity.ProjectFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description: 任务功能
 * @author:pck
 * @create: 2018-06-07 16:44
 **/
public interface ITaskService {

    public List<MyPTask> ListPTask(HashMap <String,Object> map);

    public List<MyPTask> MyListPTask(HashMap <String,Object> map);

    public PTask GetPTask(Integer id);

    public PTask GetPTask1(Integer id);

    public void EditTask (PTask task);

    public void qTask(HashMap<String,Object> map, MultipartFile[] files);

    public MyPTask MyPTask1(Integer projectId);

    public List<PTask> pastPTask();

    public void editpastPTask(Integer Id,Integer Projcode);

    public List<PTask> NOuserPTask();
    public void editpastPTask1(Integer Id,Integer Projcode);

    public PTask GetPTask2(Integer Projcode);

    public void editpastPTaskstate1(Integer id);

    public void editpastPTaskstate2(Integer id);
}