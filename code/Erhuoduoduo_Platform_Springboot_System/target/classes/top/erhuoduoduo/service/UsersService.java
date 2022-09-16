package top.erhuoduoduo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.erhuoduoduo.entity.Users;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Collapsar
 * @since 2021-12-01
 */
public interface UsersService extends IService<Users> {

    //用户信息查询
    Users GetByUserName(String username);

    //根据用户信息模糊查询分页
    Page<Users> getUserByUserInfo(Integer current,Integer size,String userInfo);

    //返回用户列表分页
    Page<Users> getAllUser(Integer current, Integer size);

    //用户注册
    Users UsersRegister(Users users);

    //查询账号是否存在
    Boolean isExist(String email);

    //根据用户id查询用户
    Users getUserByID(Integer userId);

    Users getUserByAccount(String userAccount);


    //更新用户信息
    Users updateUser(String major, String userName, String picUrl, Integer userSex, Integer admissionTime, String userAccount);
}
