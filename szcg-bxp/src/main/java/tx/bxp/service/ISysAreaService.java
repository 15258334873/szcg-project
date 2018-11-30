package tx.bxp.service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-09-27 15:44
 **/
public interface ISysAreaService {

    public ArrayList<HashMap<String, Object>> selectarea();

    public ArrayList<HashMap<String, Object>> getway(Integer id);

    public Integer addevaluation(HashMap<String, Object> map);

}
