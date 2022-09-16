<template>
  <div class="app">
   <router-view></router-view>
  </div>
</template>

<style lang="scss">
*{//全局样式
	margin: 0;
	padding: 0;
	list-style: none;
	outline: none;
	text-decoration: none;
}
.app{
	width: 100%;
}
.flex{
	display: flex;
}
.j-c{
	justify-content: center;
}
.j-s{
	justify-content: space-between;
}
.a-c{
	align-items: center;
}
</style>
