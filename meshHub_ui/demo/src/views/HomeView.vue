<template>
  <el-container class="home-container"> <!-- 设置全屏 height 100% -->

    <!-- 页面主体区域 -->
    <el-container>
      <!-- 侧边栏 -->
      <!-- 根据是否折叠决定宽度（折叠起来和menu折叠起来宽度一样） -->
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <div class="toggle-button" @click="toggleCollapse">|||</div>
        <!--侧边栏菜单区域-->
        <el-menu background-color="white" text-color="drakgray"
                 active-text-color="#409EEF" :unique-opened="true" :collapse="isCollapse"
                 collapse-transition="false" :router="true">
          <!--一级菜单-->
          <el-submenu :index="item.id+''" v-for="item in menulist" :key="item.id">
            <!--一级菜单的模板区域-->
            <template slot="title">
              <!--图标--><!--:class="iconsObj[item.id]"可设置一级菜单前的图标不同-->
              <i class="el-icon-user"></i>
              <!--文本-->
              <span>{{ item.name }}</span>
            </template>
            <!--二级菜单-->
              <el-menu-item :index="'/'+subItem.id" v-for="subItem in item.tags" :key="subItem.id">
                <template slot="title">
                  <!--图标-->
                  <i class="el-icon-menu"></i> <!-- el-icon-menu是element图标 -->
                  <!--文本-->
                  <span>{{subItem.name}}</span>
                </template>
              </el-menu-item>

            </el-submenu>

        </el-menu>
      </el-aside>

      <!-- 右侧内容主体 -->
      <el-main>

          <el-carousel height="400px">
            <el-carousel-item v-for="item in 4" :key="item">
               <!--模型区-->
                <img src="../assets/img/logo4.png">
            </el-carousel-item>
          </el-carousel>
      </el-main>
    </el-container>

    <!--主页第二部分-->
      <el-header>
          <div id="title"><span>预售展区</span></div>
          <div class="wrapper">
            <ul id="left">
              <li @mouseover="to1">
                <a href="#">模型一</a>
              </li>
              <li @mouseover="to2"><a href="#">模型二</a></li>
              <li @mouseover="to3"><a href="#">模型三</a></li>
              <li @mouseover="to4"><a href="#">模型四</a></li>
            </ul>
            <ul id="center">
              <li v-show="isShow1"><a href="#"><img src="../assets/img/logo4.png" alt=""/></a></li>
              <li v-show="isShow2"><a href="#"><img src="../assets/img/logo4.png" alt=""/></a></li>
              <li v-show="isShow3"><a href="#"><img src="../assets/img/logo4.png" alt=""/></a></li>
              <li v-show="isShow4"><a href="#"><img src="../assets/img/logo4.png" alt=""/></a></li>
              <li v-show="isShow5"><a href="#"><img src="../assets/img/head.jpg" alt=""/></a></li>
              <li v-show="isShow6"><a href="#"><img src="../assets/img/head.jpg" alt=""/></a></li>
              <li v-show="isShow7"><a href="#"><img src="../assets/img/head.jpg" alt=""/></a></li>
              <li v-show="isShow8"><a href="#"><img src="../assets/img/head.jpg" alt=""/></a></li>
            </ul>
            <ul id="right">
              <li @mouseover="to5"><a href="#">模型五</a></li>
              <li @mouseover="to6"><a href="#">模型六</a></li>
              <li @mouseover="to7"><a href="#">模型七</a></li>
              <li @mouseover="to8"><a href="#">模型八</a></li>
            </ul>
          </div>
      </el-header>



  </el-container>
</template>

<script>
export default {
  name:"HomeView",
  data() {
    return {
      // 左侧菜单数据
      menulist: [],
      modelMenuList:[],
      iconsObj: {
        '1': 'iconfont icon-user',
        '103': 'iconfont icon-tijikongjian',
        '101': 'iconfont icon-shangpin',
        '102': 'iconfont icon-danju',
        '145': 'iconfont icon-baobiao'
      },
      // 是否折叠
      isCollapse: false,
      // 被激活的链接地址
      activePath: '',
      searchValue: "",
      //遮罩层显示状态
      isShow: false,
      //图片显示第几张
      isShow1: true,
      isShow2: false,
      isShow3: false,
      isShow4: false,
      isShow5: false,
      isShow6: false,
      isShow7: false,
      isShow8: false,
    }
  },
  created() { /* 一打开页面就获取左侧菜单的激活状态 */
    this.getMenuList()
    this.activePath = window.sessionStorage.getItem('activePath')
  },

  methods: {
    logout() {
      window.sessionStorage.clear() /* 清空token */
      this.$router.push('/login') /* 重定向到登录页 要用replace push还是能点返回回到这个home页 */
    },
    // 获取左侧的菜单
    async getMenuList() {
      const  res = await this.$http.get('pet/1')
      // 获取失败：
      if (res.status !== 200) return this.$message.error('获取数据失败')
      // 获取成功：存放到data里面
      this.menulist = res.data
      console.log(res) /* 5个一级菜单 children为二级菜单 */
    },
    // 点击按钮，切换菜单的折叠与展开 同时aside的宽度变小 即切换menu的collapse属性 用isCollapse存储数据实现切换
    toggleCollapse() {
      this.isCollapse = !this.isCollapse
    },
    // 保存链接的激活状态
    saveNavState(activePath) {
      window.sessionStorage.setItem('activePath', activePath)
      this.activePath = activePath
    },
    // 获取左侧的菜单
    async getModelMenu() {
      const  res = await this.$http.get('pet/1')
      // 获取失败：
      if (res.status !== 200) return this.$message.error('获取数据失败')
      // 获取成功：存放到data里面
      this.menulist = res.data
      console.log(res)
    },
    SearchModel() {
      this.isShow = true
    },
    hidden() {
      this.isShow = false
    },
    to1(){
      this.isShow1=true;
      this.isShow2=false;
      this.isShow3=false;
      this.isShow4=false;
      this.isShow5=false;
      this.isShow6=false;
      this.isShow7=false;
      this.isShow8=false;
    },
    to2(){
      this.isShow2=true;
      this.isShow1=false;
      this.isShow3=false;
      this.isShow4=false;
      this.isShow5=false;
      this.isShow6=false;
      this.isShow7=false;
      this.isShow8=false;
    },
    to3(){
      this.isShow3=true;
      this.isShow1=false;
      this.isShow2=false;
      this.isShow4=false;
      this.isShow5=false;
      this.isShow6=false;
      this.isShow7=false;
      this.isShow8=false;
    },
    to4(){
      this.isShow4=true;
      this.isShow1=false;
      this.isShow2=false;
      this.isShow3=false;
      this.isShow5=false;
      this.isShow6=false;
      this.isShow7=false;
      this.isShow8=false;
    },
    to5(){
      this.isShow5=true;
      this.isShow1=false;
      this.isShow2=false;
      this.isShow3=false;
      this.isShow4=false;
      this.isShow6=false;
      this.isShow7=false;
      this.isShow8=false;
    },
    to6(){
      this.isShow6=true;
      this.isShow1=false;
      this.isShow2=false;
      this.isShow3=false;
      this.isShow4=false;
      this.isShow5=false;
      this.isShow7=false;
      this.isShow8=false;
    },
    to7(){
      this.isShow7=true;
      this.isShow1=false;
      this.isShow2=false;
      this.isShow3=false;
      this.isShow4=false;
      this.isShow5=false;
      this.isShow6=false;
      this.isShow8=false;
    },
    to8(){
      this.isShow8=true;
      this.isShow1=false;
      this.isShow2=false;
      this.isShow3=false;
      this.isShow4=false;
      this.isShow5=false;
      this.isShow6=false;
      this.isShow7=false;
    },
  }
}
</script>

<style scoped>
@import "../assets/Home.css";

</style>
