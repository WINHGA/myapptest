<template>
  <el-row>
    <!-- 左侧内容 -->
    <el-col :span="8" style="padding-right: 10px">
      <!-- 左上角卡片 -->
      <el-card class="box-card">
        <!-- 用户信息 -->
        <div class="user">
          <img src="../assets/logo.png" alt="头像" />
          <div class="userinfo">
            <p class="name">Admin</p>
            <p class="access">超级管理员</p>
          </div>
        </div>
        <!-- 登录信息 -->
        <div class="login-info">
          <!-- 上次登录的时间 -->
          <p>上次登录的时间：<span>2023-7-16</span></p>
          <!-- 上次登录的时间 -->
          <p>上次登录的地点：<span>大连</span></p>
        </div>
      </el-card>
      <!-- 左下角表格 -->
      <el-card class="box-card" style="margin-top: 20px; height: 400px">
        <!-- 表格 -->
        <el-table :data="tableData" stripe style="width: 100%">
          <!-- 表头 -->
          <el-table-column
            v-for="(val, key) in tableLabel"
            :key="key"
            :prop="key"
            :label="val"
          />
        </el-table>
      </el-card>
    </el-col>
    <!-- 右侧内容 -->
    <el-col :span="16" style="padding-left: 10px">
      <!--  上边卡片 -->
      <div class="num">
        <el-card
          class="el-card"
          v-for="item in countData"
          :key="item.name"
          :body-style="{ display: 'flex', padding: 0 }"
        >
          <i
            class="icon"
            :class="`el-icon-${item.icon}`"
            :style="{ background: item.color }"
          ></i>
          <div class="detail">
            <p class="price">￥{{ item.value }}</p>
            <p class="desc">{{ item.name }}</p>
          </div>
        </el-card>
      </div>
      <!-- 图表 -->
      <el-card style="height: 270px">
        <!-- 折线图 -->
        <div ref="echarts1" style="height:270px"></div>
      </el-card>
      <div class="graph">
        <el-card style="height: 250px">
            <div ref="echarts2" style="height: 250px"></div>
        </el-card>
        <el-card style="height: 250px">
            <div ref="echarts3" style="height: 230px"></div>
        </el-card>
      </div>
    </el-col>
  </el-row>
</template>

<script>
// 引入 请求
import { getData } from "../api";
// 引入图标
import * as echarts from "echarts";

export default {
  name: "Home",
  data() {
    return {
      tableData: [],
      tableLabel: {
        name: "品牌",
        todayBuy: "日销量",
        monthBuy: "月销量",
        totalBuy: "总销量",
      },
      countData: [
        {
          name: "今日支付订单",
          value: 1234,
          icon: "success",
          color: "#2ec7c9",
        },
        {
          name: "今日支付订单1",
          value: 1234,
          icon: "success",
          color: "#2ec7c9",
        },
        {
          name: "今日支付订单2",
          value: 1234,
          icon: "success",
          color: "#2ec7c9",
        },
        {
          name: "今日支付订单3",
          value: 1234,
          icon: "success",
          color: "#2ec7c9",
        },
        {
          name: "今日支付订单4",
          value: 1234,
          icon: "success",
          color: "#2ec7c9",
        },
        {
          name: "今日支付订单5",
          value: 1234,
          icon: "success",
          color: "#2ec7c9",
        },
      ],
    };
  },
  mounted() {
    getData().then(({ data }) => {
      const { tableData } = data.data;
      this.tableData = tableData;


    // 折线图
      // 基于准备好的dom，初始化echarts实例
      const echarts1 = echarts.init(this.$refs.echarts1);

      // 指定图表的配置项和数据
      var echarts1Option = {};

      // 处理数据xAxis
      const {orderData, userData,videoData} = data.data
      const xAxis = Object.keys(orderData.data[0])
      echarts1Option.xAxis  = {data:xAxis}
      echarts1Option.legend = {data:xAxis}
      echarts1Option.yAxis = {}
      echarts1Option.series = []
      // 获取
      xAxis.forEach(key => {
        echarts1Option.series.push({
            name: key,
            data: orderData.data.map(item=>item[key]),
            type: 'line'
        })
      })
      echarts1.setOption(echarts1Option);


    // 柱状图
    const echarts2 = echarts.init(this.$refs.echarts2)
    const echarts2Option = {
        // 提示框
        tooltip: {
            trigger: "axis",
        },
        // 上方图例
        legend: {
          // 图例文字颜色
          textStyle: {
            color:"#333",
          },
        },
        grid: {
            left:'20%',
        },
        xAxis: {
            type: "category",  // 类目轴
            data: userData.map(item => item.date),
            axisLine: {
                lineStyle: {
                    color: "#17b3a3",
                },
            },
            axisLabel: {
                interval: 0,
                color: "#333",
            }
        },
        yAxis: [
            {
                type: "value",
                axisLine: {
                    lineStyle: {
                        color: "#17b3a3",
                    },
                },
            },
        ],
        color: ["#2ec7c9","#b6a2de"],
        series: [
            {
                name: '新增用户',
                data: userData.map(item => item.new),
                type: 'bar'
            },
            {
                name: '活跃用户',
                data: userData.map(item => item.active),
                type: 'bar'
            }
        ]
    };
    echarts2.setOption(echarts2Option)

    // 饼状图
    const echarts3 = echarts.init(this.$refs.echarts3)
    const echarts3Option = {
        tooltip: {
            trigger:"item",
        },
        color: [
            "#0f78f4",
            "#dd536b",
            "#9462e5",
            "#a6a6a6",
            "#e1bb22",
            "#39c362",
            "#3ed1cf",
        ],
        series: [
            {
                data: videoData,
                type:'pie'
            }
        ],
    }
     echarts3.setOption(echarts3Option)
    });
  },
};
</script>

<style lang="less" scoped>
.user {
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #ccc;
  display: flex;
  align-items: center; // 内容水平居中
  img {
    margin-right: 40px;
    width: 150px;
    height: 150px;
    border-radius: 50%;
  }
  .userinfo {
    .name {
      font-size: 32px;
      margin-bottom: 10px;
    }
    .access {
      color: #999999;
    }
  }
}
.login-info {
  p {
    line-height: 28px;
    font-size: 14px;
    color: #999999;
    span {
      color: #666;
      margin-left: 60px;
    }
  }
}
.num {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  .icon {
    width: 80px;
    height: 80px;
    font-size: 30px;
    color: #fff;
    text-align: center;
    line-height: 80px;
  }
  .detail {
    display: flex;
    margin-left: 15px;
    flex-direction: column;
    justify-content: center;
    .price {
      font-size: 30px;
      height: 30px;
      margin-bottom: 10px;
      line-height: 30px;
    }
    .desc {
      font-size: 14px;
      color: #999;
      text-align: center;
    }
  }
  .el-card {
    width: 32%;
    margin-bottom: 20px;
  }
}
.graph {
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
  .el-card {
    width: 48%;
  }
}
</style>