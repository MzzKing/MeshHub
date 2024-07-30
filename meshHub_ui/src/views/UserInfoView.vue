<template>
  <el-container>
    <el-main>
      <el-row style="height: 500px">
        <el-col :span="4" class="bodyLeft">
          <el-row class="demo-avatar demo-basic head-image">
            <el-col :span="24">
              <div class="demo-basic--circle">
                <div class="block">
                  <el-avatar :size="60" :src="userInfo.imageUrl"></el-avatar>
                </div>
              </div>
            </el-col>
          </el-row>

          <el-row class="user-name-sex">
            <el-col :span="24" style="text-align: center">
              {{ userInfo.username }}
            </el-col>

          </el-row>
          <el-row class="user-name-sex">
            <el-col :span="24" style="text-align: center;font-size: 10px">
              {{ userInfo.description }}
            </el-col>

          </el-row>
            <hr class="dashed">
          <el-row class="follower-fans">
            <el-col :span="11" style="text-align: center;font-size: 10px">
              关注:&nbsp;{{ userInfo.followerCount }}
            </el-col>
            <el-col :span="11" style="text-align: center;font-size: 10px">
              粉丝:&nbsp;{{ userInfo.fansCount }}
            </el-col>

          </el-row>
          <hr class="dashed">
        </el-col>
        <el-col :span="12" class="bodyRight">
          <el-row class="topSelected" :class="active">
            <el-col :span="4" class="collection" @click.native="topClick('collection')">
              我的收藏
            </el-col>
            <el-col :span="4" class="model" @click.native="topClick('model')">
              我的模型
            </el-col>
            <el-col :span="4" class="upload" @click.native="topClick('upload')">
              上传模型
            </el-col>
          </el-row>
          <user-collection style="width: 100%" v-if="selectComponent=='collection'"></user-collection>
          <user-model style="width: 100%" v-if="selectComponent=='model'"></user-model>
          <model-upload style="width: 100%" v-if="selectComponent=='upload'"></model-upload>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import UserCollection from "@/components/UserCollection.vue";
import UserModel from "@/components/UserModel.vue";
import ModelUpload from "@/components/ModelUpload.vue";
import {mapState} from "vuex";
import axios from "axios"



export default {
  name: "UserInfoView",
  components:{
    UserCollection,
    UserModel,
    ModelUpload,
  },
  data() {
    return {
      circleUrl: "static/images/logoTop.png",
      userInfo: {},

      selectComponent:"collection",
      active:"collection"
    }
  },
  methods:{
    topClick(param){
      this.active = param
      this.selectComponent = param
    }
  },
  mounted() {
    axios("api/users/"+this.userId).then((res)=>{
      this.userInfo=res.data.data
    })
  },
  computed:{
    ...mapState(['userId'])
  },


}

</script>


<style scoped>
/* 被选中样式*/
.collection .collection,
.model .model,
.upload .upload{
  height: 49px;
  margin-left: 5px;
  text-align: center;
  box-sizing: border-box;
  border-bottom: 5px solid cadetblue;
}
.dashed{
  border:1px dashed black;
  width: 90%;
  margin: 0 auto;
}
.image {
  width: 1200px;
  height: 500px;
  margin: 10px auto;
}

.el-main {
  height: 500px;
  width: 1000px !important;

}

.bodyLeft {
  height: 500px;
  width: 220px;
  padding: 10px 0;
  box-sizing: border-box;
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 0.7);
}

.head-image{
  height: 55px;
  line-height: 36px;
  text-align: center;
  margin-top: 10px;
  box-sizing: border-box;
  /*background-color: #42b983;*/
}
.user-name-sex{
  height: 30px;
  line-height: 15px;
  margin: 0px 0;
  /*background-color: palevioletred;*/
}
.follower-fans{
  height: 40px;
  line-height: 40px;
  margin: 0px 0;
  /*background-color: darkgreen;*/
}
.bodyRight {
  height: 500px;
  width: 700px;
  margin-left: 50px;
  box-sizing: border-box;
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 0.7);
}
.topSelected {
  width: 100%;
  height: 50px;
  line-height: 50px;
  box-sizing: border-box;
  border-radius: 10px 10px 0 0;
  padding: 0 0 0 30px;
  /*background-color: rgba(107, 34, 34, 0.5);*/
  border-bottom: 1px solid black;
}
.topSelected .el-col{
  height: 49px;
  margin-left: 5px;
  text-align: center;
  box-sizing: border-box;

}
.modelList{
  width:100%;
  height: 50px;
  background-color: #74acea;
}
</style>