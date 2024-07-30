<template>
  <el-container>
    <el-aside width="200px">
      <!--左侧固定菜单-->
      <el-menu-item :index="'/'+subItem.id" v-for="subItem in item.tags" :key="subItem.id">
        <template slot="title">
          <!--图标-->
          <i class="el-icon-menu"></i> <!-- el-icon-menu是element图标 -->
          <!--文本-->
          <span>{{subItem.name}}</span>
        </template>
      </el-menu-item>
    </el-aside>
    <el-main>Main</el-main>
  </el-container>
</template>

<script>

export default {
  name: "UserInfoView",
  data(){
    return{
      // 左侧菜单数据
      menulist: [],
    }
  },
  created() { /* 一打开页面就获取左侧菜单的激活状态 */
    this.getMenuList()
    this.activePath = window.sessionStorage.getItem('activePath')
  },
  methods:{
    // 获取左侧的菜单
    async getMenuList() {
      const  res = await this.$http.get('pet/1')
      // 获取失败：
      if (res.status !== 200) return this.$message.error('获取数据失败')
      // 获取成功：存放到data里面
      this.menulist = res.data
      console.log(res) /* 5个一级菜单 children为二级菜单 */
    },
  }
}
</script>

<style scoped>

</style>