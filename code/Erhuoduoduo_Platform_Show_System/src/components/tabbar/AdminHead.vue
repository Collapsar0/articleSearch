<template>
  <div id="customHead" class="bottle">
    <div class="back">
      <div class="left-part">
        <div class="pic" href="/admin/adminHome">
          <router-link :to="{name: 'adminHome'}">
            <img src="../../assets/img/logo.png" class="logo">
          </router-link>
        </div>

        <a href="/admin/adminHome">
          <h2 class="word1">"二货多多"</h2>
          <p class="word2">城院二手商品信息交流网（管理员）</p>
        </a>
      </div>
      <div class="medium-part">
        <div class="head-menu">

          <div class="Bottom-link">
            <router-link :to="{name:'goodsManage'}">
              商品信息
            </router-link>
          </div>
          <div class="separator">|</div>
          <div class="Bottom-link">
            <router-link :to="{name:'usersManage'}">
              用户信息
            </router-link>
          </div>
          <div class="separator">|</div>
          <div class="Bottom-link">
            <router-link :to="{name:'ordersManage'}">
              订单信息
            </router-link>
          </div>
          <div class="separator">|</div>
          <div class="Bottom-link">
            <router-link :to="{name:'ComplaintsManage'}">
              申诉信息
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
      this.$axios.get("/users/adminGetUserInfo").then(res => {
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
  width: 380px;
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
