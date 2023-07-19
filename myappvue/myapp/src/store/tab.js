export default {
    namespaced:true, // 开启命名空间
    state: {
        isCollapse: false,  // 控制菜单的展开和收起
        tabsList: [
            {
                path: '/',
                name: "home",
                label: "首页",
                icon: "s-home",
                url:"Home/Home",
            }
        ]        // 面包屑数据
    },
    mutations: {
        // 修改菜单展开收起的方法（有两个参数，第一个：state中的数据，第二个：传的数据）参数可写可不写
        collapseMenu(state){
            state.isCollapse = !state.isCollapse;
        },
        // 更新面包屑数据
        selectMenu(state,val){
            // 更新数据逻辑
            // 1. 判断面包屑中是否存在数据，存在就不添加，不存在就加入
            // 不等于首页
            if(val.name !== 'home'){
                // 查找当前面包屑数组中该数据存在的位置
                const index = state.tabsList.findIndex(item => item.name === val.name)
                // 如果不存在
                if(index === -1){
                    state.tabsList.push(val)
                }
            }
        },
        // 删除指定的tag数据
        closeTag(state,item){
            // 删除对应的面包屑数据
            state.tabsList.splice(state.tabsList.findIndex(val => val.name === item.name),1)
        }
    }
}