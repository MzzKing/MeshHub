<template>
  <el-container class="user-container"> <!-- 设置全屏 height 100% -->

    <!-- 页面主体区域 -->
    <el-container>
      <!-- 侧边栏 -->
      <el-aside width="200px">
        <!--侧边栏菜单区域-->
          <div id="info" @click="showInfo" @mouseover="enter($event)" @mouseout="out($event)">
            个人信息
          </div>
          <div id="collect" @click="showCollect" @mouseover="enter($event)" @mouseout="out($event)">
            我的收藏
          </div>
          <div id="other" @click="showOther" @mouseover="enter($event)" @mouseout="out($event)">
            其他
          </div>
      </el-aside>

      <!-- 右侧内容主体 -->
      <el-main id="user_main">
        <div class="info" v-show="info">
          个人信息
        </div>
        <div class="collect" v-show="collect">
          <el-card>
            <div id="all">
              <div id="model">
                模型
              </div>
              <span>
                文字介绍
              </span>
            </div>
          </el-card>
        </div>
        <div class="other" v-show="other">
          其他
        </div>
      </el-main>
    </el-container>


  </el-container>
</template>

<script>
export default {
  name:"UserTest",
  data() {
    return {
      // 左侧菜单数据
      menulist: [],
      info: true,
      collect: false,
      other: false,
      iconsObj: {
        '1': 'iconfont icon-user',
        '103': 'iconfont icon-tijikongjian',
        '101': 'iconfont icon-shangpin',
        '102': 'iconfont icon-danju',
        '145': 'iconfont icon-baobiao'
      },
      // 被激活的链接地址
      activePath: '',
    }
  },
  created() { /* 一打开页面就获取左侧菜单的激活状态 */
    this.getMenuList()
    this.activePath = window.sessionStorage.getItem('activePath')
  },

  methods: {
    enter($event){
      $event.currentTarget.className='active';
    },
    out($event){
      $event.currentTarget.className='noactive';
    },
    showInfo(){
      this.info=true;
      this.collect=false;
      this.other=false;
    },
    showCollect(){
      this.info=false;
      this.collect=true;
      this.other=false;
    },
    showOther(){
      this.info=false;
      this.collect=false;
      this.other=true;
    }
  }
}
</script>

<style scoped>
@import "../assets/User.css";
</style>
