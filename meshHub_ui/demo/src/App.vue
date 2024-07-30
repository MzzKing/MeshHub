<template>
  <div id="app_bg">
    <el-container id="app">
      <el-header id="header">
        <el-row>
          <el-col :span="24">
            <nav>
              <div class="logo"><img src="./assets/img/logo4.png" alt=""></div>
              <div class="home"><router-link to="/" id="home">Home</router-link></div>
              <div class="mall"><router-link to="/mall" id="mall">模型</router-link></div>
              <div id="part1">
                <form>
                  <div class="el-icon-search" ></div>
                  <input type="text" id="searchInput" v-model="searchValue" placeholder="家居机器人">
                </form>
              </div>
              <router-link to="/login" id="login" v-if="!isLogged">登录</router-link>
              <router-link to="/register" id="register" v-if="!isLogged">注册</router-link>
              <router-link to="/userinfo" id="user" v-if="isLogged">我的</router-link>
            </nav>
          </el-col>
          <el-button id="logout" type="info" @click="logout">退出</el-button>
        </el-row>
      </el-header>
      <el-main>
        <el-row>
          <el-col :span="24">
            <!--路由的显示入口-->
            <router-view/>
          </el-col>
        </el-row>
      </el-main>
  </el-container>
  </div>
</template>

<style>
@import "@/assets/Nav.css";
</style>
<script>
export default {
  name:"App",
  data(){
    return{
      isLogged: false,
    }
  },
  methods: {
    logout() {
      window.sessionStorage.clear();
      this.$router.push('/login');
    },
  }
}
</script>
