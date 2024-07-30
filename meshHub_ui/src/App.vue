<template>
    <el-container id="app">
      <el-header id="header">
        <el-row>
          <el-col :span="24">
            <nav>
              <router-link to="/" id="home">Home</router-link>
<!--              <router-link to="/about" id="about">About</router-link>-->
              <router-link to="/mall" id="mall">模型</router-link>
              <router-link to="/login" id="login" v-if="!isLogged">登录</router-link>
              <router-link to="/userInfo" id="user" v-if="isLogged">我的</router-link>

            </nav>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
<!--        <el-row>-->
<!--          <el-col :span="24">-->
            <router-view style="width: 100%"/>
<!--            <UserInfoView v-if="!isLogged" @loginEnter="changeState"></UserInfoView>-->
<!--          </el-col>-->
<!--        </el-row>-->
      </el-main>
    </el-container>
</template>

<style>
/*全局默认样式*/
*{
  border: 0px;
  padding: 0px;
  margin: 0px;
}

/*a标签基础样式*/
a{text-decoration: none;}
a:link {color: #f3f1f1
} /* 未被访问的链接*/
a:visited {color: #fcf8f8
} /* 已被访问过的链接*/
a:hover {
  color: #f8f7f7
} /* 鼠标悬浮在上的链接*/
/*点击之后样式*/
nav a.router-link-exact-active {
  color: #74acea;
  font-weight: bold;
  text-decoration: none;
}
#app{
  height: 745px;
  background: url("@/assets/start_sky_1920.jpg");  /* fallback for old browsers */
  background-size: cover;
  /*background: -webkit-linear-gradient(to bottom, #153c4b, #4e707c);  !* Chrome 10-25, Safari 5.1-6 *!*/
  /*background: linear-gradient(to bottom, #153c4b, #4e707c); !* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ *!*/

}
.el-main{
  height: 660px;
  width: 1000px;
  padding: 0px;
  margin: 0px auto;
  border-radius: 10px;
}
/*导航栏样式*/
nav {
  box-sizing: border-box;
  width:100%;
  height: 45px;
  background-color: rgba(0,0,0,0);
  padding: 10px 100px;
  position: relative;
}
/*设置header的默认样式*/
.el-header{
  height: 45px !important;
}

/*设置导航之间的距离*/
nav a {
  margin: 0 20px;
}

#login,#user{
  position: absolute;
  right: 20%;
}
</style>
<script>
// import UserInfoView from "@/views/UserInfoView.vue";
import {mapState} from "vuex";

export default {
  data(){
    return{

    }

  },

  methods:{

  },
  computed:{
    ...mapState(["isLogged"])
  },
created() {
    this.$router.push("/")
}
}
</script>
