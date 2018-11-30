package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-10-15 11:45
 **/
@Mapper
public interface PramMapper {

    public HashMap<String,Object> selectpram(String pram);
}
