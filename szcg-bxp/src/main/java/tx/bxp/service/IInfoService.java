package tx.bxp.service;

import org.springframework.web.multipart.MultipartFile;
import tx.bxp.dto.BxpProject;
import tx.bxp.dto.BxpProjectList;
import tx.bxp.dto.Infodetail;
import tx.bxp.entity.File;
import tx.bxp.entity.Info;
import tx.bxp.dto.Infolist;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description:
 * @author:fyz
 * @create: 2018-06-28 16:44
 **/

public interface IInfoService {

        /**
         * 新增资讯
         * @author chenmc
         * @date 2018/8/17 10:24
         * @param
         * @return
         */
        void insertInfo(Info info,MultipartFile[] files);

        /**
         * 资讯列表
         * @author FYZ
         * @date 2018/7/24 14:25
         * @param
         * @return
         */
        public ArrayList<BxpProjectList> searchInfoList4(Integer type);
        public ArrayList<BxpProjectList> searchInfoList5(Integer type);
        public ArrayList<BxpProjectList> searchInfoList7(Integer type);
        public ArrayList<BxpProjectList> searchInfoList6(Integer type);

        /**
         * 查看资讯详情
         * @author FYZ
         * @date 2018/7/25 14:25
         * @param
         * @return
         */
        public BxpProject searchInfoDetail(Integer id,Integer type,Integer userId);


}

