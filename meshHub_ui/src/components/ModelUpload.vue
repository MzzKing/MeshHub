<template>
<el-container>
  <el-main>
    <el-row class="model-attribute model-name">
      <el-col :span="3" >名称</el-col>
      <el-col :span="6"><el-input class="inputModelName"
          placeholder="请输入内容"
          v-model="postData.model.name"
          clearable>
      </el-input>
      </el-col>
    </el-row>
    <el-row class="model-attribute model-description">
      <el-col :span="3" >描述</el-col>
      <el-col :span="10"><el-input class="inputTextarea"
          type="textarea"
          :rows="2"
          placeholder="请输入内容"
      v-model="postData.model.description">
      </el-input></el-col>
    </el-row>
      <el-row class="model-attribute model-type">
      <el-col :span="3" >类别</el-col>
      <el-col :span="6">
        <el-select v-model="postData.model.type" placeholder="请选择">
        <el-option-group
            v-for="group in options"
            :key="group.label"
            :label="group.label">
          <el-option
              v-for="item in group.options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-option-group>
      </el-select>
      </el-col>
    </el-row>

      <el-form>
        <el-row class="model-attribute model-name">
        <el-col :span="3" >文件</el-col>
        <el-col :span="6">
          <input type="file" name="file" class="fileUpload" @change="getFiles($event)">
        </el-col>
        </el-row>
        <el-row class="model-attribute post_btn">
          <!--      <el-col :span="5"><button :class="btnActive" @click="changeBtnClass('btnClick')" @mouseenter="changeBtnClass('btnHover')" @mouseleave="changeBtnClass('uploadBtn')">上传</button></el-col>-->
          <el-col :span="5">  <el-button type="primary" @click="uploadModel">上传</el-button></el-col>
        </el-row>
      </el-form>



  </el-main>

</el-container>
</template>

<script>
import axios from 'axios'
import {mapState} from "vuex";
export default {
  name: "ModelUpload",
  data(){
    return{
      btnActive:"uploadBtn",
      //提交数据
      postData:{
        inputFile:null,
        model:{
          name:"",
          description:"",
          type:"",
        },

      },

      options: [
        {
        label: '所属类别',
        options: [{
          value: '人物',
          label: '人物'
        }, {
          value: '建筑',
          label: '建筑'
        }, {
          value: '家具',
          label: '家具'
        }, {
          value: '自然',
          label: '自然'
        }]
      }],
      value: ''

    }
  },
  computed:{
    ...mapState(['userId'])
  },
  methods:{
      uploadModel(){
        const formData = new FormData()
        console.log(this.postData.inputFile)
        formData.append('file',this.postData.inputFile)
        axios.post("api/upload"+"?name="+this.postData.model.name+"&description="+this.postData.model.description
            +"&type="+this.postData.model.type+"&userId="+this.userId,formData,{
          headers:{
            'Content-Type':'multipart/form-data'
          },
        }).then(res=>{
          if(res.data.data.flag=="false") this.$message.error("上传失败")
          else{
            this.$message.success("上传成功")
            this.resetData()
          }
        })
      },
      getFiles(e){
        // console.log(e)
        this.postData.inputFile = e.target.files[0]

      },
    //重置提交数据
      resetData(){
        this.postData = {}
      }
  }
}
</script>

<style scoped>
.el-button{
  width: 150px;
}
.model-attribute{
    width: 100%;
    height: 40px;
  margin: 10px;
    line-height: 40px;
  }
.model-name{
  margin: 10px 10px;
}
.model-attribute .el-col:first-child{
  box-sizing: border-box;
  padding: 0px 0 0 10px;
}
.model-description{
  height: 80px;
}
.inputModelName{
  size:20px 30px;

  outline: none;

}
.inputTextarea{
  width: 300px;
  height: 70px;
}
.btnClick{
  width: 150px;
  height: 40px;
  border-radius: 5px;
  background-color: rgb(37, 101, 171);
}
.btnHover{
  width: 150px;
  height: 40px;
  border-radius: 5px;
  background-color: rgb(138, 165, 194);
}
.uploadBtn{
  width: 150px;
  height: 40px;
  border-radius: 5px;
  background-color: rgba(116,172,234,1);
}
.post_btn .el-col{
  margin: 0 0 0 380px;
}
</style>
