<template>
  <el-container id="aboutContainer">
    <el-main>

      <el-row class="typeTop">
        <el-col :span="24">
          <el-row class="typeSelect">
            <el-col :span="2" class="titleText">搜索词</el-col>
            <el-col :span="5"><input type="text" class="in_search" v-model="postData.searchInfo" @change="getAll"></el-col><br>
          </el-row>
          <el-row  class="typeSelect" :class="typeClick">
            <el-col :span="2" class="titleText">类别</el-col>
            <el-col :span="2" v-for="(item,index) in typeData" :key="item" :class="item" @click.native="changeType(item,SelectedData.type[index],)">{{SelectedData.type[index]}}</el-col><br>
          </el-row>
          <el-row  class="typeSelect" :class="typeClicks">
            <el-col :span="2" class="titleText">格式</el-col>
            <el-col :span="2" v-for="(item,index) in sizeData" :key="item" :class="item" @click.native="changeFileForm(item,SelectedData.fileFrom[index])">{{SelectedData.fileFrom[index]}}</el-col>
          </el-row>
        </el-col>
      </el-row>
      <el-row class="order">
        <el-col :span="6" class="line"><hr></el-col>
        <el-col :span="3" class="txt">模型直达</el-col>
        <el-col :span="6" class="line"><hr></el-col>
      </el-row>
      <el-row  class="goodsBody">
        <el-col :span="24" >
          <el-row id="goods">
            <el-col :span="11" class="allModel" v-for="item in formData.records" :key="item.id" :style="{background:'url('+item.imageUrl+')',backgroundSize: 'cover'}" @click.native="toModelShow(item.id)">
              <el-row class="allInfo">
                <el-col class="left" :span="15">
                  <el-row>
                    <el-col class="modelName">{{item.name}}</el-col>
                  </el-row>
                  <el-row>
                    <el-col class="introduce">{{ item.description }}</el-col>
                  </el-row>
                </el-col>
                <el-col class="right" :span="5">
                  <el-row>
                    <el-col class="author">{{ item.fileForm }}</el-col>
                  </el-row>
                  <el-row>
                    <el-col class="el-icon-download"></el-col>
                    <el-col class="download">{{ item.downloadCount }}次</el-col>
                  </el-row>
                </el-col>

              </el-row>
            </el-col>
          </el-row>
          <el-pagination
              layout="prev, pager, next"
              :page-size="pagination.pageSize"
              :current-page="pagination.currentPage"
              @current-change="handleCurrentChange"
              :total="pagination.total">
          </el-pagination>
        </el-col>
      </el-row>

    </el-main>
  </el-container>
</template>
<script>
import axios from "axios"
import {mapState} from "vuex";


export default {
  computed:{
    ...mapState(['userId']),
    ...mapState(["searchValue"])
  },
  data(){
    return{
      typeData:["one","two","three","four","five","six"],
      sizeData:["one","two","three","four","five","six"],
      SelectedData:{
        type:["全部","人物","家具","建筑","汽车","自然"],
        fileFrom:["全部","STL","FBX","OBJ","DAE","3DS"]
      },
      postData:{ //发送到后端的数据
        searchInfo:"",
        type:"全部",
        fileForm: "全部",
      },
      formData:{}, //接收后端发送来的数据

      typeClick:"one",
      typeClicks:"one",
      pagination: {//分页相关模型数据
        currentPage: 1,//当前页码
        pageSize: 6,//每页显示的记录数
        total: 0,//总记录数
        type:"",
        name:"",
        description:"",
      },
    }
  },
  methods:{
    changeType(item,type){
      this.typeClick=item
      this.postData.type = type
      this.getAll();
    },
    changeFileForm(item,fileForm){
      this.typeClicks=item
      this.postData.fileForm = fileForm;
      this.getAll();
    },
    getAll(){
      let param = "?type="+this.postData.type+
          "&fileForm="+this.postData.fileForm+
          "&searchInfo="+this.postData.searchInfo;
      axios.get("api/models/"+this.pagination.currentPage+"/"+this.pagination.pageSize+param).then((res)=>{
        this.formData = res.data.data
        this.pagination.total = res.data.data.total
      })
    },
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage;
      this.getAll();
    },
    toModelShow(id){
      this.$router.push({
        path:"/OneModelView",
      })
      this.$store.commit("addModelId",id)
    }
  },
  created(){
    this.postData.searchInfo = this.searchValue
    this.getAll();

  },


}

</script>

<style>
.el-pagination{
  padding: 20px 40px 20px 430px;
}
*{
  padding: 0px;
  margin: 0px;
}
.typeTop{
  height: 200px;
  padding:20px 20px;
  border-radius: 5px;

  background-color: rgba(231, 225, 225, 0.7);
  opacity: 1;
  font-family: 方正粗黑宋简体;
  font-weight: lighter;
  font-size: 17px;
}
.goodsBody{
  margin-top: 70px;
  /*height: 1300px;*/
  border-radius: 5px;
  background-color: #c0bea8;
  background-color: rgba(231, 225, 225, 0.7);
}
.el-main{
  height: 660px;
  width: 1000px;
  padding: 0px;
  margin: 0px auto;
  border-radius: 10px;
}
.allModel{
  height: 300px;
  text-align: center;
  border-radius: 10px;
  box-sizing: border-box;
  padding: 10px;
  margin: 66px 20px;
  background-color: rgba(255, 255, 255,1);
}
.allModel:hover{
  border-radius: 10px;
  border: 2px solid palegoldenrod;
}

.in_search{
  background-color: rgba(119, 161, 155, 0.7);
  outline: none;
  color: black;
  width: 100%;
  height: 20px;
  padding: 3px 8px;
  border-radius: 2px;
  margin-left: 12px;
}
.order {
  height: 20px;
  line-height: 20px;
  left: 10px;
}
.order .line {
  display: inline-block;
  padding-top : 3px;
  width: 40%;
  border-top: 3px dashed grey;
  margin-top: 43px;
}
 .order .txt {
   color: #7cb7a9;
   font-size: 20px;
   font-family: 方正姚体;
   vertical-align: middle;
   margin-top: 33px;
   margin-left: 50px;
 }

.selectValue .el-col{
  margin:5px 0;
}
.el-aside::-webkit-scrollbar{
  display: none;
}
::-webkit-scrollbar{
  width: 0 !important;
}

.typeSelect{
  width: 650px;
  height: 40px;
  line-height: 30px;
  margin: 11px 11px;
  border-bottom: 2px solid gray;
}
.typeSelect>.el-col{
  text-align: center;
}
.titleText{
  font-weight: normal;
  font-family: Sans-serif;
}
.one .one,
.two .two,
.three .three,
.four .four,
.five .five,
.six .six{
  background-color: #928DAB;
  border-radius: 5px;
  height: 24px;
  margin: 5px 0;
  line-height: 24px;
  color: white;
}
.allInfo{
  height: 100px;
  margin-top: 100px;
  margin-left: -10px;
}
.img{
  margin-top: -100px;
  margin-left: 0px;
}
.left{
  text-align: left;
  height: 100px;
  margin-left: 16px;
  margin-top: 70px;
}
.modelName{
  font-family: 华文彩云;
  font-weight: bolder;
  font-size: 35px;
  font-style: italic;
  color: #000000;
}
.introduce{
  font-family: "Hiragino Sans GB";
  font-weight: lighter;
  font-size: 15px;
  height: 60px;
  word-wrap: break-word;
  word-break: break-all;
  margin-top: 16px;
  padding-bottom: 10px;
  line-height: 23px;
  clear: both;
  color: #3e3e3e;
}
.right{
  text-align: left;
  margin-left: 370px;
  margin-top: -60px;
}
.el-icon-download{
  margin-left: -22px;
  height: 10px;
  margin-top: 0px;
}
.download{
  font-size: 15px;
  font-weight: lighter;
  margin-top: -12px;
}
.author{
  font-family: 华文楷体;
  font-weight: bolder;
  font-size: 30px;
  margin-bottom: 10px;
;
}
.Pagination{
  margin-top: 70px;
  float: right;
}
</style>

