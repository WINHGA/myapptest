<template>
    <div>
        <el-container>
            <!-- 导航区 -->
            <el-aside width="auto">
                <common-aside></common-aside>
            </el-aside>
            <el-container>
                <!-- 头部 -->
                <el-header class="el-header">
                    <common-header></common-header>
                </el-header>
                <!-- tag组件 -->
                <common-tag/>
                <!-- 内容 -->
                <el-main>
                    <!-- 路由出口 -->
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
  
</template>

<script>
// 导入侧边导航组件
import CommonAside from '../components/CommonAside.vue'
// 导入头部组件
import CommonHeader from '../components/CommonHeader.vue'
// 导入tag组件
import CommonTag from '../components/CommonTag.vue'

export default {
    name:'Main',
    data(){
        return {

        }
    },
    components:{
        CommonAside,
        CommonHeader,
        CommonTag
    },
    watch: {

    }
}
</script>

<style scoped>
    .el-header {
        padding:0;
    }

</style>