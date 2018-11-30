package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import tx.bxp.service.IUserinfoService;
import tx.framework.security.support.*;

import java.io.Serializable;
import java.util.Collection;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-07-06 11:05
 **/
@Component
@Primary
public class AuthcService implements AuthenticationProvider, AuthorizationProvider<Integer, Integer> {

    @Autowired
    private IUserinfoService userinfoService;

    @Override
    public UserBean findByUsername(String username) {
       return userinfoService.selectUserinfoByID(0);
       // return null;
    }

    @Override
    public Collection<? extends RoleBean> findEffectiveRolesByUserId(Integer userId) {
        return null;
    }

    @Override
    public Collection<? extends RoleBean> findEffectiveRolesByRouteId(Integer routeId) {
        return null;
    }

    @Override
    public Collection<? extends RouteBean> findEffectiveRoutes() {
        return null;
    }
}
