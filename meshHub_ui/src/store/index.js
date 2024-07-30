import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLogged: false,
    userId:"",
    modelId:"",
    searchValue:""
  },
  getters: {
  },
  mutations: {
    changeLoggedState(state){
      state.isLogged = !state.isLogged
    },
    addUserId(state,id){
      state.userId = id
    },
    addModelId(state,id){
      state.modelId = id
    },
    setSearchValue(state,value){
      state.searchValue=value
    }
  },
  actions: {
  },
  modules: {
  }
})
