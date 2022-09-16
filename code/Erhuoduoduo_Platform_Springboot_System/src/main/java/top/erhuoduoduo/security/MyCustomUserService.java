package top.erhuoduoduo.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import top.erhuoduoduo.entity.Users;
import top.erhuoduoduo.mapper.UsersMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 登陆专用类自定义实现方法
 * @author: collapsar
 * @create: 2021/12/05 23:11
 */
@Component
public class MyCustomUserService implements UserDetailsService {

    @Resource
    private UsersMapper usersMapper;

    /**
     * 登陆验证时，通过username获取用户的所有权限信息
     * 并返回UserDetails放到spring的全局缓存SecurityContextHolder中，以供授权器使用
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //在这里可以自己调用数据库，对username进行查询，看看在数据库中是否存在
        MyUserDetails myUserDetail = new MyUserDetails();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_account",username);
        Users users = usersMapper.selectOne(queryWrapper);

        if(users==null){
            throw new UsernameNotFoundException("用户名未找到");
        }else{
            myUserDetail.setUsername(users.getUserAccount());
            myUserDetail.setPassword(users.getUserPassword());
            myUserDetail.setUserId(users.getUserId());
            myUserDetail.setUserNickname(users.getUserNickname());
            myUserDetail.setUserPicture(users.getUserPicture());
            myUserDetail.setUserPhone(users.getUserPhone());
            myUserDetail.setUserState(users.getUserState());
            myUserDetail.setReportCnt(users.getReportCnt());
            myUserDetail.setUserCategory(users.getUserCategory());

            return myUserDetail;
        }
    }

    /**
     * 获取用户权限信息（角色、菜单权限）
     * @param userAccount
     * @return
     */
    public List<GrantedAuthority> getUserAuthority(String userAccount){

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_account",userAccount);
        Users users = usersMapper.selectOne(queryWrapper);

        return AuthorityUtils.commaSeparatedStringToAuthorityList(users.getUserCategory());
    }
}
