<template>
  <div class="main">
    <div class="content">
      <customHead></customHead>
      <div class="contain">
        <div class="describeWord">
          <p>闲置物品轻松交流</p>
          <div class="icon-text-box">
            <i class="el-icon-s-shop"></i>
            <h2> 专属城院的二手商品信息交流网站</h2>
          </div>
        </div>
        <div class="choice">
          <div>
            <router-link :to="{name:'goShopping',query:{type: '全部商品'}}">
              <div class="box">
                <div class="box-content">
                  <i class="el-icon-shopping-cart-2"></i>
                  <p>我要逛逛</p>
                </div>
              </div>
            </router-link>
          </div>
          <div>
            <router-link :to="{name:'myOrders'}">
              <div class="box">
                <div class="box-content">
                  <i class="el-icon-document-copy"></i>
                  <p>我的订单</p>
                </div>
              </div>
            </router-link>
          </div>
          <div>
            <router-link :to="{name:'myRelease'}">
              <div class="box">
                <div class="box-content">
                  <i class="el-icon-thumb"></i>
                  <p>我的发布</p>
                </div>
              </div>
            </router-link>
          </div>
        </div>
        <bottom></bottom>
      </div>
    </div>
  </div>
</template>

<script>
import customHead from '../components/tabbar/CustomHead.vue'
import bottom from '../components/tabbar/Bottom.vue'

export default {
  components: {
    customHead,
    bottom
  }
}
</script>

<style scoped>
@import url("../assets/css/CustomBase.css");

h2 {
  color: #6ADBE4;
  font-weight: normal;
}

.describeWord {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 4%;
}

.describeWord p {
  color: #6ADBE4;
  font-size: 45px;
  font-weight: bold;
}

.icon-text-box{
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-top: 7%;
}

.el-icon-s-shop{
  font-size: 45px;
  color: #6ADBE4;
}

.choice {
  display: flex;
  flex-direction: row;
  width: 80%;
  height: 60%;
  margin-top: 5%;
  margin-left: 1%;
  margin-right: 1%;
  justify-content: space-around;
}

.box {
  width: 20VW;
  height: 75%;
  background-color: #6ADBE4;
  margin: 0 auto;
  align-items: center;
  border-radius: 5px;
  display: flex;
  align-items: center;
}

.box-content{
  width: 80%;
  height: 80%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: row;
  margin: auto auto;
}

.el-icon-shopping-cart-2,.el-icon-document-copy,.el-icon-thumb{
  color: white;
  font-size: 45px;
}

.box-content p {
  color: white;
  font-size: 30px;
}

h1 {
  height: 80%;
  width: 80%;
  color: white;
  margin: auto;
}
</style>
