package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tx.bxp.entity.Push;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
@Mapper
public interface PushMapper {

    Push findByUserid(Integer userId);
    void save(Push push);
    void update(Push push);
    void delete(Integer userId);

}
