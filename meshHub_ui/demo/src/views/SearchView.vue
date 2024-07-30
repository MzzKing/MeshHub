<template>
<div id="search_body">
<!--  搜索框-->

  <div>
    <div class="el-icon-search1" ></div>
    <input type="text" id="searchInput1" v-model="searchValue" @keyup.enter="searchModel" placeholder="请输入需要搜索的模型">
  </div>
  <div id="menu">
    <div id="menu_left">
    <ul>
      <a href=""><li>搜索词</li></a>
      <a href=""><li>领域</li></a>
      <a href=""><li>大小</li></a>
    </ul>
    </div>
    <div id="menu_right">
    <ul>
      <a href=""><li>词条词条词条词条词条</li></a>
        <li>
          <ul id="option">
            <a href=""><li>机器人</li></a>
            <a href=""><li>星球</li></a>
            <a href=""><li>文物</li></a>
            <a href=""><li>其他</li></a>
          </ul>
        </li>
      <a href=""><li>选项1选项1选项1选项1选项1选项1选项1选项1选项1选项1</li></a>
  </ul>
    </div>
  </div>
  <div id="answer">
    <ul>
      <li v-for="item in modelList">
        <div class="picture3">
        <a href="#"><img src="../assets/img/logo4.png" class="pic"></a><br>
          <div><span style="font-family: 微软雅黑;color:#F8AE19;">{{ item.id }}</span></div>
          <div><span style="font-family: 微软雅黑;color:#F8AE19;">{{ item.name }}</span></div>
      </div>
      </li>
    </ul>

    </div>
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "SearchView",
  data(){
    return{
      searchValue: "",
      //使用数组存放多个搜索结果
      modelList:[],
      modelUrl:"",
    }
  },
  methods:{
    searchModel(){
      //回调函数，this会改变，需用that保存
      var that=this;
      //调用接口
      axios.get('http://127.0.0.1:4523/m1/2525284-0-default/pet/1?searchValue='+this.searchValue)
          .then(function (response){
            // console.log(response);
             console.log(response.data.data.name);
            that.modelList=response.data.data.id+response.data.data.name;
          })
      .catch((function (err){}))
    }
  }
}
</script>

<style scoped>
@import "../assets/Search.css";
</style>