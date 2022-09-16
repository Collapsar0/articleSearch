<template>
  <div id="customHead" class="bottle">
    <div class="back">
      <div class="left-part">
        <div class="pic" href="/user/customHome">
          <router-link :to="{name: 'customHome'}">
            <img src="../../assets/img/logo.png" class="logo">
          </router-link>
        </div>

        <a href="/user/customHome">
          <h2 class="word1">"二货多多"</h2>
          <p class="word2">城院二手商品信息交流网</p>
        </a>
      </div>
      <div class="medium-part">
        <div class="head-menu">
          <div class="Bottom-link">
            <router-link :to="{name:'goShopping',query:{type: '全部商品'}}">
              全部商品
            </router-link>
          </div>
          <div class="separator">|</div>
          <div class="Bottom-link">
            <router-link :to="{name:'goShopping',query:{type: '闲置数码'}}">
              闲置数码
            </router-link>
          </div>
          <div class="separator">|</div>
          <div class="Bottom-link">
            <router-link :to="{name:'goShopping',query:{type: '校园代步'}}">
              校园代步
            </router-link>
          </div>
          <div class="separator">|</div>
          <div class="Bottom-link">
            <router-link :to="{name:'goShopping',query:{type: '电器日用'}}">
              电器日用
            </router-link>
          </div>
          <div class="separator">|</div>
          <div class="Bottom-link">
            <router-link :to="{name:'goShopping',query:{type: '图书教材'}}">
              图书教材
            </router-link>
          </div>
          <div class="separator">|</div>
          <div class="Bottom-link">
            <router-link :to="{name:'goShopping',query:{type: '美妆衣物'}}">
              美妆衣物
            </router-link>
          </div>
          <div class="separator">|</div>
          <div class="Bottom-link">
            <router-link :to="{name:'goShopping',query:{type: '运动棋牌'}}">
              运动棋牌
            </router-link>
          </div>
          <div class="separator">|</div>
          <div class="Bottom-link">
            <router-link :to="{name:'goShopping',query:{type: '票券小物'}}">
              票券小物
            </router-link>
          </div>
        </div>
      </div>
      <div class="right-part">
        <div class="user-menu">
        <p>{{ userInfo.userNickname }}</p>
          <el-dropdown>
          <span class="el-dropdown-link">
            <el-avatar :size="60" :src="userInfo.userPicture"></el-avatar>
          </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <router-link :to="{name: 'communication',query:{pid: 'myCommunication'}}" style="color: #606266">我的消息</router-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <router-link :to="{name: 'UserCenter'}" style="color: #606266">个人中心</router-link>
              </el-dropdown-item>
              <el-dropdown-item @click.native="logout">登出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userInfo: {
        userId: "",
        userAccount: "",
        // userPassword: "",
        userNickname: "",
        userPicture: "",
        userPhone: "",
        userState: "",
        reportCnt: "",
        userCategory: ""
      },
      name: 'customHead',
      goodsType: '闲置数码'
    }
  },
  created() {
    this.getUserInfo()
  },
  methods: {
    getUserInfo() {
      this.$axios.get("/users/userInfo").then(res => {
        console.log(res.data.data.users)
        this.userInfo = res.data.data.users
      })
    },
    logout() {
      this.$axios.post("/logout").then(res => {
        console.log(res)
        localStorage.clear()
        sessionStorage.clear()

        // this.$store.commit("resetState")

        this.$router.push("/login")
      })
    }
  }

}
</script>

<style scoped>
.bottle {
  height: 100px;
}

.back {
  background-color: white;
  display: flex;
  flex-direction: row;
  align-items: center;
  height: 100%;
}

.word1 {
  color: black;
}

.word2 {
  color: black;
}

.logo {
  width: 100%;
  height: 100%;
}

.left-part {
  display: flex;
  flex-direction: row;
  width: 25%;
  height: 80%;
  align-items: center;
  /*margin-top: 1%;*/
  /*margin-left: 1%;*/
}

.medium-part {
  display: flex;
  flex-direction: column;
  align-items: center;
  /*margin-top: 1%;*/
  margin: auto;
  width: 60%;
}

.head-menu {
  width: 700px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}

.Bottom-link a, .separator {
  text-decoration: none;
  font-size: 18px;
  color: black;
}

.Bottom-link a:hover {
  color: #31cef9;
}

.right-part {
  display: flex;
  flex-direction: row;
  width: 15%;
  align-items: center;
  /*margin-top: 1%;*/
  margin: auto;
}

.user-menu {
  display: flex;
  flex-direction: row;
  margin: auto;
  align-items: center;
  width: 80%;
  justify-content: space-around;
}

.user-menu p{
  color: #38b9e2;
}

/*头像下拉菜单属性*/
.el-dropdown-link {
  cursor: pointer;
}
.el-icon-arrow-down {
  font-size: 12px;
}

.pic {
  display: flex;
  flex-direction: column;
  width: 30%;
}

</style>
