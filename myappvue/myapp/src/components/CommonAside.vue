<template>
  <el-menu
    default-active="1-4-1"
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose"
    :collapse="isCollapse"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
  >
    <!--  通用h3标签 -->
    <h3 v-show="!isCollapse">通用后台管理系统</h3>
    <!-- 一级菜单 -->
    <el-menu-item @click="clickMenu(item)" v-for="item in noChildren" :key="item.name" :index="item.name">
      <i :class="`el-icon-${item.icon}`"></i>
      <span slot="title">{{item.label}}</span>
    </el-menu-item>
    <!-- 二级菜单 -->
    <el-submenu v-for="item in hasChildren" :key="item.label" index="item.label">
      <template slot="title">
        <i :class="`el-icon-${item.icon}`"></i>
        <span slot="title">{{item.label}}</span>
      </template>
      <el-menu-item-group v-for="subItem in item.children" :key="subItem.path">
        <el-menu-item @click="clickMenu(subItem)" :index="subItem.path">{{subItem.label}}</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>


<style lang="less" scope>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.el-menu {
  height: 100vh;
  border-right: none;
  h3 {
    color: #fff;
    text-align: center;
    line-height: 48px;
  }
}
</style>

<script>
export default {
  data() {
    return {
      // // 是否展开
      // isCollapse: false,
      // 菜单数据
      menuData: [
        {
          path: "/",
          name: "home",
          label: "首页",
          icon: "s-home",
          url: "Home/Home",
        },
        {
          path: "/mall",
          name: "mall",
          label: "商品管理",
          icon: "video-play",
          url: "MallManage/MallManage",
        },
        {
          path: "/user",
          name: "user",
          label: "用户管理",
          icon: "user",
          url: "UserManage/UserManage",
        },
        {
          label: "其他",
          icon: "location",
          children: [
            {
              path: "/pageone",
              name: "pageone",
              label: "页面1",
              icon: "setting",
              url: "Other/PageOne",
            },
            {
              path: "/pagetwo",
              name: "pagetwo",
              label: "页面2",
              icon: "setting",
              url: "Other/PageTwo",
            },
          ],
        },
      ],
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    clickMenu(item){
      // alert(item.path)
      // 跳转到指定的路由
      // 如果点击的就是当前的页面，不进行跳转
      if(this.$route.path !== item.path && !(this.$route.path === '/home' && item.path === '/')){
        this.$router.push(item.path)
      }

      // 修改面包屑中数据
      this.$store.commit('tab/selectMenu',item);
    },

  },
  computed: {
    // 计算属性
    // 没有子菜单
    noChildren(){
      return this.menuData.filter(item => !item.children)
    },
    // 有子菜单
    hasChildren(){
      return this.menuData.filter(item => item.children)
    },
    // 计算isCollapse属性是否修改
    isCollapse(){
      return this.$store.state.tab.isCollapse;
    }
  }
};
</script>