//引入Vue核心库
import Vue from 'vue'
//引入Vuex
import Vuex from 'vuex'
// 引入标签的数据控制状态仓库
import tab from './tab'
//应用Vuex插件
Vue.use(Vuex)

// 创建vuex的实例
export default new Vuex.Store({
    modules: {
        tab
    }
})