<template>
    <div class="tags">
        <el-tag
            v-for="(item,index) in tags"
            :key="item.label"
            :closable="item.name !== 'home'"
            :effect="$route.name === item.name ? 'dark':'plain'"
            @click="changeMenu(item)"
            @close="handleClose(item,index)"
            size="small">
            {{ item.label }}
        </el-tag>
    </div>
</template>

<script>

import {mapState,mapMutations} from 'vuex'

export default {
    name: 'CommonTag',
    data() {
        return {

        }
    },
    computed: {
        ...mapState({
            tags: state => state.tab.tabsList
        })
    },
    methods:{

        ...mapMutations("tab",['closeTag']),
        // 点击tag跳转的功能
        changeMenu(item){
            this.$router.push({name:item.name})
        },
        // 关闭tag
        handleClose(item,index){
            // 关闭tag
            this.closeTag(item)
            // 获取当前的长度
            const length = this.tags.length;
            // 删除的不是当前的路由
            if(item.name !== this.$route.name){
                return 
            }
            // 删除的是最后一项
            if(index === length){
                this.$router.push({
                    // 设置当前路由为前一个路由
                    name: this.tags[index-1].name
                })
            }else {
                this.$router.push({
                    name: this.tags[index].name
                })
            }

        }
    }
}
</script>

<style lang="less" scoped>
.tags {
    padding:10px;
    .el-tag {
        margin-right: 15px;
        cursor: pointer;
    }
}

</style>