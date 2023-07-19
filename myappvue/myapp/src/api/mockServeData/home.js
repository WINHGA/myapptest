// mock 数据模拟
import Mock from 'mockjs'

// 图标数据
let List = []

export default {
    getStatisticalData:() => {
        // Mock。Random。float 产生随机数100到8000之间的数据 保留小数 最小0位 最大0位
        for (let i = 0; i < 7; i++){
            List.push(
                Mock.mock({
                    苹果: Mock.Random.float(100,8000,0,0),
                    vivo: Mock.Random.float(100,8000,0,0),
                    oppo: Mock.Random.float(100,8000,0,0),
                    魅族: Mock.Random.float(100,8000,0,0),
                    三星: Mock.Random.float(100,8000,0,0),
                    小米: Mock.Random.float(100,8000,0,0),
                })
            )
        }
        return {
            code: 20000,
            data: {
                // 饼图
                videoData: [
                    {
                        name: '苹果',
                        value: 2999
                    },
                    {
                        name: 'vivo',
                        value: 3999
                    },
                    {
                        name: 'oppo',
                        value: 5999
                    },
                    {
                        name: '三星',
                        value: 4999
                    },
                    {
                        name: '魅族',
                        value: 3999
                    },
                    {
                        name: '小米',
                        value: 2999
                    },
                ],
                // 柱状图
                userData: [
                    {
                        date:'周一',
                        new: 5,
                        active:200
                    },
                    {
                        date: '周二',
                        new: 10,
                        active: 500
                    },
                    {
                        date:'周三',
                        new:12,
                        active:550
                    },
                    {
                        date:'周四',
                        new: 17,
                        active: 650
                    },
                    {
                        date: '周五',
                        new: 24,
                        active:680,
                    },
                    {
                        date: '周六',
                        new: 28,
                        active: 700
                    },
                    {
                        date:'周日',
                        new: 30,
                        active:750
                    }
                ],
                // 折线图
                orderData: {
                    date: ['20191001','20191002','20191003','20191004','20191005','20191006','20191007'],
                    data: List
                },
                tableData: [
                    {
                        name: 'oppo',
                        todayBuy: 100,
                        monthBuy: 300,
                        totalBuy: 8030
                    },
                      {
                        name: '小米',
                        todayBuy: 1200,
                        monthBuy: 3100,
                        totalBuy: 1800
                    },
                      {
                        name: '华为',
                        todayBuy: 1002,
                        monthBuy: 3200,
                        totalBuy: 8300
                    },
                      {
                        name: '魅族',
                        todayBuy: 1020,
                        monthBuy: 3001,
                        totalBuy: 1800
                    },
                      {
                        name: '苹果',
                        todayBuy: 1200,
                        monthBuy: 3300,
                        totalBuy: 8500
                    },
                    {
                        name: '三星',
                        todayBuy: 1220,
                        monthBuy: 7300,
                        totalBuy: 4500
                    },
                ],
            }
        }
    }
    
}