package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tx.bxp.dto.MyPTask;
import tx.bxp.dto.NodeAction;
import tx.bxp.entity.*;
import tx.bxp.mapper.FileMapper;
import tx.bxp.mapper.TaskMapper;
import tx.bxp.mapper.bxpProjAwardMapper;
import tx.bxp.service.ITaskService;
import tx.commons.util.FileUploadUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-08 10:39
 **/
@Transactional
@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private bxpProjAwardMapper bxpProjAwardMapper;

    @Value("${bxp.imgurl}")
    private String imgurl;

    @Value("${bxp.Actionname}")
    private String Actionname;

    @Value("${bxp.Opinion}")
    private String Opinion;

    @Value("${bxp.Memo}")
    private String Memo;


    @Override
    public List<MyPTask> ListPTask(HashMap<String, Object> map) {
        return taskMapper.selectPTask(map);
    }

    @Override
    public PTask GetPTask(Integer id) {
        return taskMapper.selectPTaskById(id);
    }

    @Override
    public PTask GetPTask1(Integer id) {
        return taskMapper.selectPTaskById1(id);
    }


    @Override
    public void EditTask(PTask task) {
        taskMapper.UpdateTask(task);
        ProjectAward projectAward=new ProjectAward();

        projectAward.setTaskid(task.getId());
        projectAward.setFlag(0);
        projectAward.setState(0);
        projectAward.setUserid(task.getUserid());
        bxpProjAwardMapper.updateProjectAward(projectAward);

    }

    @Override
    public List<MyPTask> MyListPTask(HashMap<String, Object> map) {
        return taskMapper.myselectPTask(map);

    }

    @Override
    public void qTask(HashMap<String, Object> map, MultipartFile[] files) {

        NodeAction nodeAction=taskMapper.selectnodeaction(51);

        System.out.println(nodeAction.toString());

        HashMap<String, Object> Projectmap=new HashMap<>();
        Projectmap.put("pId",Integer.valueOf(map.get("pId").toString()));
        Projectmap.put("NextNodeId",nodeAction.getNextNodeId());
        taskMapper.UpdateProject(Projectmap);

        PTask task=new PTask();

        task.setId(Integer.valueOf(map.get("rId").toString()));
        task.setIsfinish(1);
        taskMapper.UpdateTask1(task);


        HashMap<String, Object> projecttrace =new HashMap<>();
        projecttrace.put("ProjCode",Integer.valueOf(map.get("pId").toString()));
        projecttrace.put("PreNodeId",nodeAction.getNodeId());
        projecttrace.put("CurrentNodeId",nodeAction.getNextNodeId());
        projecttrace.put("actionId",nodeAction.getActionId());
        projecttrace.put("ActionName",nodeAction.getActionName());
        projecttrace.put("Opinion",map.get("mome"));
        taskMapper.saveprojecttrace(projecttrace);

        //图片上传
        String flag = "";
        List<String> fileList = null;
        try {
            fileList = FileUploadUtils.uploadFile(imgurl, "/bxp/task", files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileList != null) {
            for (int i=0;i<fileList.size();i++) {
                String filename=fileList.get(i);
                int j=i+1;
                String suffix = filename.substring(filename.indexOf(".") + 1);
                if (suffix.equals("jpg") || suffix.equals("png")) {
                    flag = "1";
                }
                if (suffix.equals("mp3")) {
                    flag = "2";
                }
                if (suffix.equals("mp4")) {
                    flag = "3";
                }
                File file = new File();
                file.setPost_id(Integer.valueOf(map.get("pId").toString()));
                file.setFile_path(filename);//路径
                file.setFile_type(flag);
                file.setFile_status(2); // 文件类型（0：案件上报:1：案件回复:2：核查回复 3:资讯）
                file.setPost_status(0); // 帖子类型 （0：案件类，1：资讯类）
                file.setFile_num(j);
                fileMapper.insertFileInfo(file);
            }
        }
    }

    @Override
    public MyPTask MyPTask1(Integer projectId) {
        return taskMapper.MyPTask1(projectId);
    }


    @Override
    public List<PTask> pastPTask() {
        return taskMapper.SelectpastPTask();
    }

    @Override
    public void editpastPTask(Integer id,Integer Projcode) {
        try {
            taskMapper.updatepastPTask(id);
            taskMapper.Updateproject(Projcode);

            ProjectTrace projectTrace=new ProjectTrace();
            projectTrace.setProjcode(Projcode);
            projectTrace.setActionname(Actionname);
            projectTrace.setOpinion(Opinion);
            projectTrace.setMemo(Memo);
            taskMapper.Inserprojecttrace(projectTrace);
        }catch(Exception ex){
        }
    }

    @Override
    public List<PTask> NOuserPTask() {
        return taskMapper.NOuserPTask();
    }

    @Override
    public void editpastPTask1(Integer Id, Integer Projcode) {
        try {
            taskMapper.updatepastPTask1(Id);
            taskMapper.Updateproject(Projcode);
            ProjectTrace projectTrace=new ProjectTrace();
            projectTrace.setProjcode(Projcode);
            projectTrace.setActionname(Actionname);
            projectTrace.setOpinion(Opinion);
            projectTrace.setMemo(Memo);
            taskMapper.Inserprojecttrace(projectTrace);
        }catch(Exception ex){
        }
    }


    @Override
    public PTask GetPTask2(Integer Projcode) {
        return taskMapper.NOuserPTask1(Projcode);
    }


    @Override
    public void editpastPTaskstate1(Integer id) {
        taskMapper.updatepastPTaskstate1(id);
    }

    @Override
    public void editpastPTaskstate2(Integer id) {
        taskMapper.updatepastPTaskstate2(id);
        taskMapper.updatepastPW(id);

    }
}
