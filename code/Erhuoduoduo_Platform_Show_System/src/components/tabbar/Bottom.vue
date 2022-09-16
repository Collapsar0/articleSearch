<template>
  <div class="Bottom">
    <div class="Bottom-head">
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
    </div>
    <div class="Bottom-tail">
      <img src="../../assets/logo2.png" width="35px" height="35px">
      <div class="Bottom-tail-text">
        <p>二货多多——城院二手商品交易网</p>
        <p>Second-hand commodity information trading network in ZUCC</p>
      </div>
    </div>
  </div>
</template>

<script>
	export default{
		data() {
			return{
				name: 'bottom'
			}
		}
	}
</script>

<style scoped>
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
  color: black;
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
  color: black;
  font-size: 10px;
}
</style>
