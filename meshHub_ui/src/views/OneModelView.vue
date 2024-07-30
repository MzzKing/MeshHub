<template>
  <el-container>
    <el-main>
      <el-row class="model-show">
        <el-col :span="24">
          <model-show style="height: 400px" v-if="mountedLater" :model-url="modelInfo.url"></model-show>
        </el-col>
      </el-row>
      <el-row class="modelBottom"></el-row>
      <el-row class="modelName">
        <el-col :span="2">名称</el-col>
        <el-col :span="12">{{ modelInfo.name }}</el-col>

      </el-row>
      <el-row class="modelDescription">
        <el-col :span="2">描述</el-col>
        <el-col :span="20"> {{ modelInfo.description}}</el-col>
      </el-row>
      <el-row class="modelType">
        <el-col :span="2">类别</el-col>
        <el-col :span="20">{{ modelInfo.type}}</el-col>
      </el-row>
      <el-row class="modelFileForm">
        <el-col :span="2">格式</el-col>
        <el-col :span="20">{{ modelInfo.fileForm}}</el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import {mapState} from "vuex";
import axios from "axios";
import ModelShow from "@/components/ModelShow.vue";

export default {
  name: "ModelView",
  components:{
    ModelShow,
  },
  data(){
    return{
      modelInfo:{},
      mountedLater:false
    }
  },
  computed:{
    ...mapState(["modelId"])
  },
  created() {
    axios.get("api/models/"+this.modelId).then((res)=>{
      this.modelInfo = res.data.data
    }).finally(()=>{
      this.mountedLater=true
    })

  }
}

</script>

<style scoped>
  .el-container{
    width: 1000px !important;
    margin: 0 auto;
    /*height: 900px;*/
  }
  .model-show{
    width: 60%;
    margin: 0 auto;
    height: 400px;
    /*background-color: #42b983;*/
  }
  .modelBottom{
    width: 30%;
    border-radius: 15px 15px 5px 5px;
    margin: 0 auto;
    height: 30px;
    background-color: #74acea;
  }
  .modelName{
    width:60%;
    height:50px;
    background-color: #f3f1f1;
    margin: 20px auto 0 auto;
    box-sizing: border-box;
    /*padding: 10px 30px;*/
  }
  .modelName .el-col:nth-child(2){
    font-size: 30px;
    font-weight: 500;
    font-family: "华文新魏";
    box-sizing: border-box;
    padding: 9px 0 0 14px;
  }
  .modelName .el-col:nth-child(4){
    box-sizing: border-box;
    padding: 15px 0 0 0;
    font-size: 25px;
    font-family: "华文新魏";
  }
  .modelName .el-col:nth-child(1){
    font-size: 13px;
    box-sizing: border-box;
    text-align: right;
    padding: 20px 0 0 0;
    color: #383737;
  }
  .modelName .el-col:nth-child(3){
    box-sizing: border-box;
    font-size: 13px;
    text-align: right;
    padding: 22px 0 0 0;
    color: #383737;
  }
  .modelDescription{
    width:60%;
    height:50px;
    background-color: #f3f1f1;
    margin: 0 auto;
    box-sizing: border-box;
  }
  .modelDescription .el-col:nth-child(1){
    font-size: 13px;
    color: gray;
    box-sizing: border-box;
    text-align: right;
    padding: 20px 0 0 0;
    color: #383737;
  }
  .modelDescription .el-col:nth-child(2){
    box-sizing: border-box;
    height: 50px;
    padding: 10px 0 0 5px;
    font-family: "宋体";
  }
  .modelType{
    width:60%;
    height:50px;
    background-color: #f3f1f1;
    margin: 0 auto;
    box-sizing: border-box;
  }
  .modelType .el-col:nth-child(1){
    font-size: 13px;
    color: gray;
    box-sizing: border-box;
    text-align: right;
    padding: 20px 0 0 0;
    color: #383737;
  }
  .modelType .el-col:nth-child(2){
    box-sizing: border-box;

    padding: 20px 0 0 5px;
    font-family: "宋体";
  }
  .modelFileForm{
    width:60%;
    height:50px;
    background-color: #f3f1f1;
    margin: 0 auto;
    box-sizing: border-box;
  }
  .modelFileForm .el-col:nth-child(1){
    font-size: 13px;
    color: gray;
    box-sizing: border-box;
    text-align: right;
    padding: 20px 0 0 0;
    color: #383737;
  }
  .modelFileForm .el-col:nth-child(2){
    box-sizing: border-box;

    padding: 20px 0 0 5px;
    font-family: "宋体";
  }
  .modelImage{
    width:60%;
    height:50px;
    background-color: #f3f1f1;
    margin: 0 auto;
    box-sizing: border-box; 
  }
</style>