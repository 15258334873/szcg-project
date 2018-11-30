package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tx.bxp.entity.AppVersion;
import tx.bxp.mapper.VersionMapper;
import tx.bxp.service.IVersionService;

/**
 * @program: szcg-project
 * @description: 版本管理
 * @author:pck
 * @create: 2018-08-20 09:13
 **/
@Service
@Transactional
public class VersionServiceImpl  implements IVersionService {

    @Autowired
    private VersionMapper versionMapper;

    @Override
    public AppVersion getAppVersion() {
        return versionMapper.SelectVserion();
    }

}
