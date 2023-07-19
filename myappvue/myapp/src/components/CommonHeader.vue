<template>
  <div class="header-container">
    <!-- 左侧部分 -->
    <div class="l-content">
        <!-- 图标 -->
        <el-button @click="handleMenu" icon="el-icon-menu" size="mini" style="margin-right:20px;"></el-button>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item v-for="item in tags" :key="item.name"  :to="{ path: item.path}">{{item.label}}</el-breadcrumb-item>
        </el-breadcrumb>
    </div>
    <!-- 右侧部分 -->
    <div class="r-content">
        <el-dropdown>
            <span class="el-dropdown-link">
                <img src="../assets/logo.png" alt="加载失败" class="user">
            </span>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>个人中心</el-dropdown-item>
                <el-dropdown-item>退出登录</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>

    </div>
  </div>
</template>

<script>

// 引入vuex的辅助函数
import { mapState, mapMutations} from "vuex"


export default {
    name: 'CommonHeader',
    data(){
        return {

        }
    },
    methods: {
        handleMenu(){
            // 调用挂载在Vue上的 store 
            this.$store.commit('tab/collapseMenu');
        },
        // itemPath(item){
        //     console.log(item.path)
        //     if(this.$route.path !== item.path && !(this.$route.path === '/home' && item.path === '/')){
        //         this.$router.push(item.path);
        //     }
        // }
    },
    computed: {
        ...mapState({
            tags: state => state.tab.tabsList
        })
    },
    mounted(){
        // console.log("我是tags:",this.tags)
    }

}
</script>

<style lang='less' scoped>
    .l-content {
        display: flex;
        align-items: center;
        /deep/.el-breadcrumb__item {
            .el-breadcrumb__inner {
                font-weight: normal;
                &.is-link {
                    color:#666;
                }
            }
            &:last-child {
                .el-breadcrumb__inner{
                     color: #fff;
                }
            }
        }
    }

    .header-container {
        padding: 0 20px;
        background-color: #333;
        height:60px;
        display: flex;
        justify-content: space-between; //主轴两端对齐
        align-items:center;   // 纵轴居中

        .text {
            color: #fff;
            font-size:14px;
            margin-left:10px;
        }
        .r-content {
            .user {
                width: 40px;
                height: 40px;
                border-radius: 50%;
            }
        }
    }
    .el-dropdown-link {
        cursor: pointer;
        color: #409EFF;
    }
    .el-icon-arrow-down {
        font-size: 12px;
    }
</style>