<template>
<div v-bind:id="testID">
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "TestView",
  data(){
    return{
      testID:"hhh",
      reqData:""
    }
  },
  mounted(){
    //get请求方式
    axios.get("http://127.0.0.1:4523/m1/2525284-0-default/pet/1")
        .then(res=>{
      console.log(res.data);
    })
    // axios({
    //   method:"get",
    //   url:"http://127.0.0.1:4523/m1/2525284-0-default/pet/1"
    // }).then(res=>{
    //   console.log(res.data);
    // })
    //post请求方式
    axios.post("http://127.0.0.1:4523/m1/2525284-0-default/pet",{
      name:"HelloKitty",
      status:"sold"
    }).then(res=>{
      console.log(res.data);
    })
    // axios({
    //   method:"post",
    //   url:"http://127.0.0.1:4523/m1/2525284-0-default/pet",
    //   data:{
    //     name:"HelloKitty",
    //     status:"sold"
    //   }
    // }).then(res=>{
    //   console.log(res.data);
    // })
  }
}
</script>

<style scoped>

</style>