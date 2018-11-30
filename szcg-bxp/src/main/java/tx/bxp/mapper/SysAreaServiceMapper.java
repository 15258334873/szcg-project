package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-09-27 15:46
 **/
@Mapper
public interface SysAreaServiceMapper {

    public ArrayList<HashMap<String, Object>> selectarea();

    public ArrayList<HashMap<String, Object>> selectway(Integer id);

    public Integer insertevaluation(HashMap<String, Object> map);

}
