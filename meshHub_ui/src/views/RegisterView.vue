<template>
  <el-container>
    <!--模型区-->
    <div id="model_Register"><img src="../assets/img/logo4.png"></div>
    <!--注册-->
    <div id="RegisterDiv">
      <form  id="form">
        <div class="title">Register</div>
        <p>账号: <input id="email" type="text" placeholder="请输入账号">
        <p>密码: <input id="password" type="password" placeholder="请输入注册密码">

        <div >
          <input type="submit" class="button" value="注册" onclick="check()">
        </div>
        <div class="text">
						<span class="txt1">
							已有账号|
						</span>
          <a class="txt2" href="#">
            登录
          </a>
        </div>
      </form>
    </div>
  </el-container>
</template>

<script>
export default {
  name: "RegisterView",
  data(){
    return{
    }
  },
  methods:{

  }
}
</script>

<style scoped>
@import "@/assets/Register.css";

</style>