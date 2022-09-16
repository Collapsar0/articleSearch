<template>
	<div class="main">
		<div class="login-content">
			<div class="left-part">
				<div class="head">
					<div>
<!--						<img src="../assets/logo2.png" class="loge">-->
					</div>
<!--					<div>-->
<!--						<h2 class="word1">"二货多多"</h2>-->
<!--						<p class="word2">城院二手商品信息交流网</p>-->
<!--					</div>-->
				</div>
				<div class="main-text">
					<h2 class="logoword1">上市公司年报主题分析系统</h2>
<!--					<p class="logoword2">专属城院的二手商品信息交流网站</p>-->
				</div>
			</div>
			<div class="right-part">
				<div class="box">
					<div class="login">
						<el-form size="medium" :model="ruleForm" status-icon :rules="rules" ref="ruleForm"
							label-width="90px" class="demo-ruleForm">
							<el-form-item label="账号" prop="username">
								<el-input type="text" v-model="ruleForm.username" autocomplete="off" placeholder="请输城院学生学号或教工号"></el-input>
							</el-form-item>
							<el-form-item label="密码" prop="password">
								<el-input type="password" v-model="ruleForm.password" autocomplete="off" @keyup.enter.native="submitForm('ruleForm')"></el-input>
							</el-form-item>
							<el-form-item>
								<el-button type="primary" @click="submitForm('ruleForm')" style="width: 100%">登录</el-button>
<!--								<el-button @click="turnToRegister">注册</el-button>-->
							</el-form-item>
						</el-form>
					</div>
				</div>
			</div>
		</div>
    <div class="Bottom">
      <div class="Bottom-head">
      </div>

    </div>
	</div>
</template>

<script>

  import qs from 'qs'
	export default {
		data() {
			//验证账号
			var validateLoginId = (rule, value, callback) => {
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

			return {
        // 账号后缀
        emailTail: '@stu.zucc.edu.cn',
				//表单数据
				ruleForm: {
          username: '', //登录名
          password: '', //登陆密码
				},
				radio: '1',
				//表单验证数据
				rules: {
					//验证账号
          username: [{
						validator: validateLoginId,
						trigger: 'blur'
					}],
					//验证密码
          password: [{
						validator: validateLoginPwd,
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
            const params = this.ruleForm
            // params.username = params.username  + this.emailTail
            this.$axios.post('/login?'+ qs.stringify(params)).then(res => {

              console.log(res)

              const jwt = res.headers['authorization']

              this.$store.commit('SET_TOKEN', jwt)
              // if(this.radio === '1'){
              //   this.$router.push("/user/customHome")
              // } else if (this.radio === '2'){
              //   this.$router.push("/admin/adminHome")
              // }
              this.$router.push("/mainPage")
            })
          }  else {
						console.log('error submit!!');
						return false;
					}
				});
			},
			//表单重置方法
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
      //返回登陆界面
			turnToRegister() {
				// window.location.href = "/register";
        this.$router.push('/register')
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
	.word1{
		color: white;
	}
	.word2{
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
	.box{
		width: 450px;
		height: 300px;
		background-color: rgba(255,255,255,0.6);
		margin: 25% auto;
		border-radius: 25px ;
	}

	.login {
		width: 300px;
		height: 100px;

		flex-direction: row;
		grid-area: right;
    display: flex;

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

	.login-content {
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
	.logoword2{
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

  .Bottom-head{
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

  .Bottom-tail{
    display: flex;
    align-items: center;
    margin: auto;
  }

  .Bottom-tail-text{
    margin-left: 10px;
  }

  .Bottom-tail-text p{
    color: white;
    font-size: 10px;
  }
</style>
