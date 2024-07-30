<template>
<el-container>
  <el-row class="modelList">
    <el-col :span="24">
      <el-row style="background-color: rgba(255,255,255,0.3)" class="topTitle">
        <el-col :span="6">
          123
        </el-col>
        <el-col :span="8">
          描述
        </el-col>
        <el-col :span="3">
          文件
        </el-col>
        <el-col :span="4">
          下载次数
        </el-col>
        <el-col :span="3">
          操作
        </el-col>

      </el-row>
      <el-row class="oneModel" v-for="(item,index) in modelInfo.records" :key="item.id"
              @click.native="toModelShow(item.id)" @mouseenter.native="changeStyle(index)"
              @mouseleave.native = "changeStyle(index)"
              :class="{'clicked':isClicked[index]}">
        <el-col :span="5">
          <img :src="item.imageUrl" height="140" width="100%">
        </el-col>
        <el-col :span="8">
          {{item.description}}
        </el-col>
        <el-col :span="3">
          {{item.fileForm}}
        </el-col>
        <el-col :span="3">
          {{item.downloadCount}}
        </el-col>
        <el-col :span="3">

          <el-row>
            <el-col :span="11" id="downloadBtn" @click.stop.prevent.native="download(item.url,item.name,item.fileForm)">下载</el-col>
            <el-col :span="11" id="deleteBtn" @click.stop.prevent.native="deleteModel(userId,item.id)">删除</el-col>
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
</el-container>
</template>

<script>
import axios from "axios";
import {mapState} from "vuex";
import { saveAs } from "file-saver";

export default {
  name: "UserFavorite",
  data(){
    return{
      modelInfo:{},
      pagination: {//分页相关模型数据
        currentPage: 1,//当前页码
        pageSize: 2,//每页显示的记录数
        total: 0,//总记录数
        type:"",
        name:"",
        description:"",
      },
      isClicked:[false,false],
      hoverColor:"",
      modelIdList:[],
    }
  },
  created() {
    axios.get("api/userCollection/"+this.userId+"/"+this.pagination.currentPage+"/"+this.pagination.pageSize).then((res)=>{
      this.modelInfo = res.data.data
      this.pagination.total = res.data.data.total
    })
  },
  computed:{
    ...mapState(['userId'])
  },
  methods:{
    getAll(){
      axios.get("api/userCollection/"+this.userId+"/"+this.pagination.currentPage+"/"+this.pagination.pageSize).then((res)=>{
        this.modelInfo = res.data.data
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
    },
    changeStyle(index){
      this.isClicked[index] = !this.isClicked[index]
    },
    download(url,name,fileForm){
      axios.get(url,{
        responseType:'blob',
      }).then(res=>{
        saveAs(res.data,name+"."+fileForm.toLowerCase())
      })
    },
    deleteModel(userId,modelId){
      axios.delete("api/userCollection/"+userId+"/"+modelId).then((res)=>{
        if(res.data.data.flag=="false") this.$message.error("删除失败")
        else this.$message.success("删除成功")
      }).finally(()=>{
        this.getAll();
      })
    }
  }
}
</script>

<style scoped>
#downloadBtn{
  height: 20px;
  margin: 0px;
}
#downloadBtn:hover{
  color: #42b983;
}
#deleteBtn:hover{
  color: #bb0000;
}
#deleteBtn{
  height: 20px;
  padding: 0px;
  margin: 0px;
  font-size: 12px;
}
.modelList{
  width:100%;
  height: 200px;
}
.topTitle .el-col{
  text-align: center;
}
.oneModel{
  width: 98%;
  height: 150px;
  margin: 5px auto;
  border-radius: 5px;
  background-color: rgba(255,255,255,0.7);
}
.clicked{
  background-color: rgba(215, 190, 190, 0.7) !important;
}

.oneModel .el-col:first-child{
  height: 140px;
  margin: 6px 10px;
  /*background-color: aqua;*/
}
.oneModel .el-col:nth-child(2){
  font-size: 14px;
  height: 140px;
  box-sizing: border-box;
  padding: 30px 18px;
  margin: 6px 5px;
  /*background-color: aqua;*/
}
.oneModel .el-col:nth-child(3){
  text-align: center;
  font-size: 10px;
  height: 140px;
  box-sizing: border-box;
  padding: 50px 2px;
  margin: 6px 5px;
  /*background-color: aqua;*/
}
.oneModel .el-col:nth-child(4){
  text-align: center;
  font-size: 10px;
  height: 140px;
  box-sizing: border-box;
  padding: 50px 2px;
  margin: 6px 5px;
  /*background-color: aqua;*/
}
.oneModel .el-col:nth-child(5){
  text-align: center;
  font-size: 10px;
  height: 140px;
  box-sizing: border-box;
  padding: 50px 2px;
  margin: 6px 0;
  /*background-color: aqua;*/
}

</style>
