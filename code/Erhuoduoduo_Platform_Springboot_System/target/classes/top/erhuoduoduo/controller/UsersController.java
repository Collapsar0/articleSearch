package top.erhuoduoduo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import top.erhuoduoduo.entity.Users;
import top.erhuoduoduo.response.Result;
import top.erhuoduoduo.service.UsersService;
import top.erhuoduoduo.utils.RedisUtil;
import top.erhuoduoduo.utils.VerCodeGenerateUtil;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Collapsar
 * @since 2021-12-01
 */
@RestController
@RequestMapping("/users")
@Api(value = "提供用户登陆与注册的接口",tags = "用户管理")
public class UsersController {
    //定义用户业务对象
    @Resource
    private UsersService usersService;


    @Autowired
    RedisUtil redisUtil;


    //用这个注释可以做接口权限管理,记得数据库里得ROLE_ 开始存字段
    //@PreAuthorize("hasRole('测试者')")
    @ApiOperation("用户信息查询")
    @GetMapping("/userInfo")
    public Result userInfo(Principal principal){
        //用户信息查询
        Users u = usersService.GetByUserName(principal.getName());

        //如果账号不存在
        if(u==null){
            return Result.error().message("账号不存在").data("users",null);
        }else {
            return Result.ok().data("users",u);
        }
    }

    @PreAuthorize("hasRole('admin')")
    @ApiOperation("管理员用户信息查询")
    @GetMapping("/adminGetUserInfo")
    public Result adminGetUserInfo(Principal principal){
        //用户信息查询
        Users u = usersService.GetByUserName(principal.getName());

        //如果账号不存在
        if(u==null){
            return Result.error().message("账号不存在").data("users",null);
        }else {
            return Result.ok().data("users",u);
        }
    }

    @ApiOperation("根据用户账号查询用户信息")
    @GetMapping("/userInfoByAccount")
    public Result userInfoByAccount(String userAccount){
        //用户信息查询
        Users u = usersService.GetByUserName(userAccount);

        //如果账号不存在
        if(u==null){
            return Result.error().message("账号不存在").data("users",null);
        }else {
            return Result.ok().data("users",u);
        }
    }

    @ApiOperation("用户根据用户名或昵称查询")
    @GetMapping("/getUserByUserInfo")
    public Result getUserByUserInfo(String userInfo, Integer current,Integer size){
        Page<Users> usersPage = usersService.getUserByUserInfo(current,size,userInfo);
        return Result.ok().data("users",usersPage);
    }

    @ApiOperation("用户注册接口")
    @PostMapping("/regist")

    public Result regist(Users users,
                         @RequestParam("verCode") String verCode){

        if (StringUtils.isBlank(verCode)){
            return Result.error().message("验证码为空").data(null);
        }

        if (!verCode.equals(redisUtil.hget("captcha", users.getUserAccount()))){
            return Result.error().message("验证码错误或已过期").data(null);
        }
        // 一次性使用
        redisUtil.hdel("captcha", users.getUserAccount());

        return Result.ok().data("users",usersService.UsersRegister(users));
    }



    @ApiOperation("用户按ID精准查询")
    @GetMapping("/getUserByID")
    Result getUserByID(Integer userId){
        Users users = usersService.getUserByID(userId);
        if(users == null){
            return Result.error().message("该用户不存在!");
        }
        else {
            return Result.ok().data("users",users);
        }
    }



    @ApiOperation("修改用户信息")
    @GetMapping("/updateUser")
    public Result  updateUser(String major, String userNickName, String picUrl, Integer userSex, Integer admissionTime,Principal principal){
        Users users = usersService.updateUser(major,userNickName,picUrl,userSex,admissionTime,principal.getName());

        if(users==null){
            return Result.error().message("用户名不存在");
        }else {
            return Result.ok().data("users",users);
        }
    }

    @ApiOperation("根据用户id修改用户信息")
    @GetMapping("/updateUserById")
    public Result  updateUserById(String major, String userNickName, String picUrl, Integer userSex, Integer admissionTime,Integer userId){
        Users users1 = usersService.getUserByID(userId);
        Users users = usersService.updateUser(major,userNickName,picUrl,userSex,admissionTime,users1.getUserAccount());

        if(users==null){
            return Result.error().message("用户不存在");
        }else {
            return Result.ok().data("users",users);
        }
    }

    @ApiOperation("返回用户列表分页")
    @GetMapping("/getAllUser")
    public Result getAllUsers(Integer current,Integer size){
        Page<Users> usersPage = usersService.getAllUser(current,size);
        return Result.ok().data("users",usersPage);
    }

}

