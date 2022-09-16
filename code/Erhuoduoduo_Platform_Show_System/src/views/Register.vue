<template>
  <div class="main">
    <div class="register-content">
      <div class="left-part">
        <div class="head">
          <div>
            <img src="../assets/logo2.png" class="loge">
          </div>
          <div>
            <h2 class="word1">"二货多多"</h2>
            <p class="word2">城院二手商品信息交流网</p>
          </div>
        </div>
        <div class="main-text">
          <h2 class="logoword1">闲置物品轻松交流</h2>
          <p class="logoword2">专属城院的二手商品信息交流网站</p>
        </div>
      </div>
      <div class="right-part">
        <div class="box">
          <!-- <img src="../assets/logo.png" class="loge"> -->
          <div class="login">
            <el-form size="medium" :model="ruleForm" status-icon :rules="rules" ref="ruleForm"
                     label-width="90px" class="demo-ruleForm">
              <el-form-item label="账号" prop="userName" >
                <el-input type="text" v-model="ruleForm.userName" autocomplete="off"
                          placeholder="请输城院学生学号或教工号" ></el-input>
              </el-form-item>
              <el-form-item label="账号后缀"  >
                <el-dropdown @command="handleCommand">
                <span class="el-dropdown-link">
                  {{ this.emailTail }}<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="@stu.zucc.edu.cn">@stu.zucc.edu.cn</el-dropdown-item>
                    <el-dropdown-item command="@zucc.edu.cn">@zucc.edu.cn</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </el-form-item>
              <el-form-item label="验证码" prop="verCode">
                <el-input type="text" v-model="ruleForm.verCode" autocomplete="off"
                          placeholder="验证码将发送到学校邮箱中"></el-input>
                <el-button type="primary" @click="sendVerCode">获取验证码</el-button>
              </el-form-item>
              <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="昵称" prop="nickName">
                <el-input type="text" v-model="ruleForm.nickName" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="手机号" prop="telePhone">
                <el-input type="text" v-model="ruleForm.telePhone" autocomplete="off"
                          @keyup.enter.native="submitForm('ruleForm')"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">确定</el-button>
                <el-button @click="turnToLogin">返回</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>
    </div>
    <div class="Bottom">
      <div class="Bottom-head">
<!--        <div class="Bottom-link">-->
<!--          <router-link :to="{name:'goShopping',query:{type: '全部商品'}}">-->
<!--            全部商品-->
<!--          </router-link>-->
<!--        </div>-->
<!--        <div class="separator">|</div>-->
<!--        <div class="Bottom-link">-->
<!--          <router-link :to="{name:'goShopping',query:{type: '闲置数码'}}">-->
<!--            闲置数码-->
<!--          </router-link>-->
<!--        </div>-->
<!--        <div class="separator">|</div>-->
<!--        <div class="Bottom-link">-->
<!--          <router-link :to="{name:'goShopping',query:{type: '校园代步'}}">-->
<!--            校园代步-->
<!--          </router-link>-->
<!--        </div>-->
<!--        <div class="separator">|</div>-->
<!--        <div class="Bottom-link">-->
<!--          <router-link :to="{name:'goShopping',query:{type: '电器日用'}}">-->
<!--            电器日用-->
<!--          </router-link>-->
<!--        </div>-->
<!--        <div class="separator">|</div>-->
<!--        <div class="Bottom-link">-->
<!--          <router-link :to="{name:'goShopping',query:{type: '图书教材'}}">-->
<!--            图书教材-->
<!--          </router-link>-->
<!--        </div>-->
      </div>
      <div class="Bottom-tail">
        <img src="../assets/logo2.png" width="35px" height="35px">
        <div class="Bottom-tail-text">
          <p>二货多多——城院二手商品交易网</p>
          <p>Second-hand commodity information trading network in ZUCC</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//导入qs
import qs from 'qs'
//导入发送验证码方法
// import {sendVerCodeByEmail} from '@/api/system'
import Element from "element-ui";

export default {
  data() {
    //验证账号
    var validateUserName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入账号'));
      } else {
        callback();
      }
    };
    //验证密码
    var validateLoginPwd = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };
    //验证验证码
    var validateVerCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'));
      } else if (value.toString().length !== 6) {
        callback(new Error('验证码长度为 6 个字符'));
      } else {
        callback();
      }
    };
    //验证昵称
    var validateNickName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入昵称'));
      } else {
        callback();
      }
    };
    //验证手机号
    var validateTelePhone = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入手机号'));
      } else {
        callback();
      }
    };

    return {
      // 邮箱后缀
      emailTail: '@stu.zucc.edu.cn',
      //表单数据
      ruleForm: {
        userName: '', //登录名
        password: '', //登陆密码
        verCode: '', //验证码
        nickName: '', //昵称
        telePhone: '', //手机号码
      },
      //表单验证数据
      rules: {
        //验证账号
        userName: [{
          validator: validateUserName,
          trigger: 'blur'
        }],
        //验证密码
        password: [{
          validator: validateLoginPwd,
          trigger: 'blur'
        }],
        //验证验证码
        verCode: [{
          validator: validateVerCode,
          trigger: 'blur'
        }],
        //验证昵称
        nickName: [{
          validator: validateNickName,
          trigger: 'blur'
        }],
        //验证手机号
        telePhone: [{
          validator: validateTelePhone,
          trigger: 'blur'
        }],
      }
    };
  },
  methods: {
    //表单提交方法
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        //判断表单数据是否验证成功
        if (valid) {
          const Users = {};
          Users.userAccount = this.ruleForm.userName + this.emailTail;
          Users.userPassword = this.ruleForm.password;
          Users.verCode = this.ruleForm.verCode;
          Users.userNickname = this.ruleForm.nickName;
          Users.userPhone = this.ruleForm.telePhone;

          this.$axios.post('/users/regist?' + qs.stringify(Users)).then(res => {

            console.log(res)

            const jwt = res.headers['authorization']

            this.$store.commit('SET_TOKEN', jwt)
            Element.Message.success("注册成功！")
            //需要先去发送一下登陆的请求,不然没有登陆的状态
            this.$router.push("/login")
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    //表单重置方法
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    turnToLogin() {
      this.$router.push('/login')
    },
    //发送邮箱验证码
    sendVerCode() {
      localStorage.clear()
      sessionStorage.clear()

      if (this.ruleForm.userName.length === 0) {
        Element.Message.error("账号输入不正确,请重新输入")
      } else {
        // const {data} = sendVerCodeByEmail(this.ruleForm.userName)
        const params = {
          email: this.ruleForm.userName + this.emailTail
        }

        this.$axios.post('/users/sendCode?' + qs.stringify(params)).then(res => {
          console.log(res)
          Element.Message.success(res.data.message)
        })
      }
    },
    // 下拉框选择
    handleCommand(command){
      this.emailTail =command
    },
  }
}
</script>

<style scoped Lang="scss">
.left-part {
  display: flex;
  flex-direction: column;
  width: 50%;
  height: 90vh;
}

.head {
  display: flex;
  flex-direction: row;
  width: 300px;
  height: 100px;
  align-items: center;
  margin-top: 40px;
  margin-left: 40px;
}

.word1 {
  color: white;
}

.word2 {
  color: white
}

.loge {
  width: 110px;
  height: 100px;
}

.main-text {
  margin: 25% auto;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.right-part {
  width: 50%;
  /* height: 100VH; */
  height: 80VH;
}

.box {
  width: 450px;
  height: 500px;
  background-color: rgba(255, 255, 255, 0.6);
  margin: 13% auto;
  border-radius: 25px;
}

.login {
  width: 300px;
  height: 100px;

  flex-direction: row;
  grid-area: right;
  padding: 50px;
}

.main {
  background: url(../assets/img/back.jpg);
  background-size: 100% 100%;
  background-position: center center;
  overflow: auto;
  align-items: center;
  height: 100vh;
}

.register-content {
  display: flex;
  flex-direction: row;
}

.word {
  color: #303133;
}

.logoword1 {
  color: white;
  font-size: 50px;
}

.logoword2 {
  color: white;
  font-size: 20px;
}

.Bottom {
  margin: 0px auto;
  width: 450px;
  height: 10vh;
  display: flex;
  flex-direction: column;
}

.Bottom-head {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}

.Bottom-link a, .separator {
  text-decoration: none;
  font-size: 18px;
  color: white;
}

.Bottom-link a:hover {
  color: #31cef9;
}

.Bottom-tail {
  display: flex;
  align-items: center;
  margin: auto;
}

.Bottom-tail-text {
  margin-left: 10px;
}

.Bottom-tail-text p {
  color: white;
  font-size: 5px;
}
</style>
