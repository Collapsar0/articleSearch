package top.erhuoduoduo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.erhuoduoduo.entity.Users;
import top.erhuoduoduo.mapper.UsersMapper;
import top.erhuoduoduo.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.erhuoduoduo.utils.MyPasswordEncoder;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Collapsar
 * @since 2021-12-01
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Resource
    private UsersMapper usersMapper;




    //用户信息查询
    @Override
    public Users GetByUserName(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_account",username);
        Users users1 = usersMapper.selectOne(queryWrapper);

        return users1;
    }

    @Override
    public Page<Users> getUserByUserInfo(Integer current,Integer size,String userInfo){
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>()
                .like("user_nickname", userInfo)
                .or()
                .like("user_account", userInfo);
        Page<Users> usersPage = new Page<>(current,size);
        Page page = usersMapper.selectPage(usersPage,queryWrapper);
        return page;
    }

    //用户注册
    @Override
    public Users UsersRegister(Users users) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_account",users.getUserAccount());
        Users users1 = usersMapper.selectOne(queryWrapper);

        if(users1==null){
            String usersPwd = users.getUserPassword();
            users.setUserPassword(new MyPasswordEncoder().encode(usersPwd));
            usersMapper.insert(users);
            return users;
        }else {
            return null;
        }

    }

    //检查邮箱账号是否存在
    @Override
    public Boolean isExist(String email) {
        QueryWrapper queryWrapper  = new QueryWrapper();
        queryWrapper.eq("user_account",email);
        Users usersResult = usersMapper.selectOne(queryWrapper);

        if(usersResult == null){
            return false;
        }
        return true;
    }

    /**
     * 用户查找
     * @param userId
     * @return
     */
    @Override
    public Users getUserByID(Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select().eq("user_id",userId);
        Users users = usersMapper.selectOne(queryWrapper);
        return users;
    }

    /**
     * 用户查找
     * @param userAccount
     * @return
     */
    @Override
    public Users getUserByAccount(String userAccount) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select().eq("user_account",userAccount);
        Users users = usersMapper.selectOne(queryWrapper);
        return users;
    }



    /**
     * 修改用户信息
     * @param userAccount
     * @param userName
     * @param picUrl
     * @param userSex
     * @param admissionTime
     * @param major
     * @return
     */
    @Override
    public Users updateUser(String major, String userName, String picUrl, Integer userSex, Integer admissionTime, String userAccount){
        UpdateWrapper<Users> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_account",userAccount).set("user_nickname", userName);
        updateWrapper.eq("user_account",userAccount).set("user_picture", picUrl);
        updateWrapper.eq("user_account",userAccount).set("user_sex", userSex);
        updateWrapper.eq("user_account",userAccount).set("user_admission_time", admissionTime);
        updateWrapper.eq("user_account",userAccount).set("user_major", major);
        usersMapper.update(null, updateWrapper);
        Users users = getUserByAccount(userAccount);
        return users;
    }

    /**
     * 返回所有用户列表分页
     * @param current
     * @param size
     * @return
     */
    @Override
    public Page<Users> getAllUser(Integer current,Integer size){
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select().orderByAsc("user_id");
        Page<Users> usersPage = new Page<>(current,size);
        Page page = usersMapper.selectPage(usersPage,queryWrapper);
        return page;
    }
}
