package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import tx.bxp.dto.MyPTask;
import tx.bxp.dto.NodeAction;
import tx.bxp.entity.PTask;
import tx.bxp.entity.ProjectTrace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
@Mapper
public interface TaskMapper {

    public List<MyPTask> selectPTask(HashMap <String,Object> map);

    public List<MyPTask> myselectPTask(HashMap <String,Object> map);

    public MyPTask MyPTask1(Integer projectId);

    public PTask selectPTaskById(Integer id);

    public PTask selectPTaskById1(Integer id);

    public NodeAction selectnodeaction(Integer actionId);

    public void  UpdateProject(HashMap<String,Object> map);

    public void UpdateTask (PTask task);

    public void UpdateTask1 (PTask task);

    public void saveprojecttrace(HashMap <String,Object> map);


    public List<PTask> SelectpastPTask();

    public void updatepastPTask(Integer id);
    public void  Updateproject(Integer Projcode);

    public List<PTask> NOuserPTask();

    public void updatepastPTask1(Integer id);

    public PTask NOuserPTask1(Integer Projcode);

    public void updatepastPTaskstate1(Integer id);
    public void updatepastPTaskstate2(Integer id);

    public void updatepastPW(Integer id);

    public void Inserprojecttrace( ProjectTrace projectTrace);


}
