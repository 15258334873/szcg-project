package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import tx.bxp.dto.BxpProjectList;
import tx.bxp.dto.BxpProject;
import tx.bxp.dto.MyBxpProject;
import tx.bxp.entity.Userinfo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-07-12 10:02
 **/
@Mapper
public interface BxpProjectMapper {

    public Integer InsertBxpProject(tx.bxp.entity.BxpProject bxpProject);

    public ArrayList<BxpProjectList> SelectBxpProjectList();

    public BxpProject SelectBxpProjectById(HashMap<String,Object> map);

    public Integer InsertBxpProjectaward(HashMap<String,Object> map);

    public Integer InsertBxpProjectpub(Integer id);

    public ArrayList<MyBxpProject> SelectMyBxpProjectList(HashMap<String,Object> map);

    public ArrayList<HashMap<String,Object>> selectSUM();

    public Integer selectbyUseridCount(Integer userId);

    public Userinfo SelectBxpProjectById1(Integer id);




}
