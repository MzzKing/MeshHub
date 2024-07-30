<template>
  <el-container>
    <el-main style="height: 660px;background-color: rgba(255,255,255,0.1)">
      <el-row>
        <el-col :span="24">
          <model-show style="height: 500px" v-if="true" model-url="/static/models/Ram.obj"></model-show>

        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <form>
            <div class="el-icon-search"></div>
            <input type="text" id="searchInput" v-model="tempSearchValue" @keydown.enter="toSearch">
          </form>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<style>

#searchInput{
  position: fixed;
  left: 40%;
  bottom: 10%;
  width: 300px;
  padding: 0 28px;
  height: 30px;
  border: black solid 1px;
  border-radius: 15px;
  background-color: rgba(255,255,255,0.3);
  outline: none;
}
.el-icon-search{
  position: fixed;
  left: 40.5%;
  bottom: 11%;
}
</style>
<script>

import ModelShow from "@/components/ModelShow.vue";
import {mapState} from "vuex";

export default {
  // eslint-disable-next-line vue/no-unused-components
  components: {ModelShow },
  name: 'HomeView',
  data(){
    return{
      tempSearchValue:"",
    }
  },
  computed:{
    ...mapState["searchValue"]
  },
  methods:{
    toSearch(){
      this.$store.commit("setSearchValue",this.tempSearchValue)
      this.$router.push("/mall")
    }
  }


}
</script>
