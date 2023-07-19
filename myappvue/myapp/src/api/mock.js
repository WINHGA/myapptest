import Mock from 'mockjs'
import homeApi from './mockServeData/home'

// 拦截请求（‘路径’，‘请求方法’，‘回调函数’）
Mock.mock('/api/home/getData',homeApi.getStatisticalData)


