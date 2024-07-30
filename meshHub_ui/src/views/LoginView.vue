<template>
  <el-container>
    <!--模型区-->
    <ModelShow model-url="Ram.obj" v-if="false" style="height: 400px"></ModelShow>
<!--    <div id="model_login">-->
<!--      -->
<!--    </div>-->
    <!--登录-->
    <div id="loginDiv">
      <!--添加ref，则loginRef属性值表示表单的一个引用对象-->
      <el-form  id="login_form" ref="loginRef" :model="loginForm" :rules="loginRules">
        <div class="title">LOGIN</div>
        <el-form-item prop="username" id="loginuser">
          <div class="word">账号:
            <el-input v-model="loginForm.username"  prefix-icon="el-icon-user-solid" type="text" placeholder="请输入账号"></el-input>
          </div>
        </el-form-item>
        <el-form-item prop="password" id="loginpsw">
          <div class="word">密码:
            <el-input v-model="loginForm.password" prefix-icon="el-icon-user-solid" type="password" placeholder="请输入登录密码"></el-input>
          </div>
        </el-form-item>
        <div id="login_btn">
          <el-button type="primary" @click="login">登录</el-button>
        </div>
        <div id="clear">
          <el-button type="info" @click="restLogin">重置</el-button>
        </div>
        <div class="text">
						<span class="txt1">
							没有账号|
						</span>
          <a class="txt2" @click="toRegister">
            注册
          </a>
        </div>
      </el-form>
    </div>
  </el-container>
</template>

<script>
import axios from "axios";
import ModelShow from "@/components/ModelShow.vue";
export default {
  name: "LoginView",
  components:{
    ModelShow,
  },
  data(){
    return{
      //这是登录表单的数据绑定对象
      loginForm:{
        username:'123',
        password:'123456',
      },
      userInfo:{},
      //这是表单验证规则对象
      loginRules:{
        //验证用户名
        username:[
          {required: true,message:"请输入登录名称",trigger: "blur"},
          {min:3,max:10,message: "长度在3到10个字符之间",trigger: "blur"}
        ],
        //验证密码
        password: [
          {required: true,message:"请输入登录密码",trigger: "blur"},
          {min:6,max:15,message: "长度在6到15个字符之间",trigger: "blur"}
        ]
      },
    }
  },
  methods:{
    //重置功能
    restLogin(){
      this.$refs.loginRef.resetFields();
    },
    login(){
      this.$refs.loginRef.validate(async valid=>{
        if (!valid)return;
        const  res=await axios.get('/api/login/'+this.loginForm.username);
        console.log(res);
        //将登录成功后的token，保存到客户端sessionStorage中
        window.sessionStorage.setItem("token",res.data.token);
        // if (res.status!==200) return this.$message.error('登录失败!');
        // this.$message.success('登录成功');

        // const data={name: window.sessionStorage.token};
        // sessionStorage.setItem("name",JSON.stringify(data));
        //登录成功跳转页面

        this.$store.commit("changeLoggedState");
        this.$store.commit("addUserId",res.data.data.id)

        this.$router.push({
          path: '/userinfo'
        });
      });
      // axios.get('/api/users/13').then(req=>{
      //   this.userInfo = req.data
      //   if (this.userInfo.password===this.loginForm.password){
      //     console.log("1")
      //   }
      // })
    },
    toRegister(){
      this.$router.push('/register');
    }
  }
}
</script>

<style scoped>
@import "@/assets/Login.css";
#model_login{
  width: 500px;
  height: 400px;
  /*background-color: #42b983;*/
  margin-left: 200px;
  margin-top: 100px;
}
</style>