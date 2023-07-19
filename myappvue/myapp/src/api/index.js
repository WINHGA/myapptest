import http from '../utils/request'
import axios from 'axios'
import path from "./path"

// 请求首页数据
export const getData = () => {
    // 返回一个promise对象
    return http.get("/home/getData")
}

const api = {
    // 添加用户
    addUser(parm){
        return axios.post(path.baseUrl+"/addUser",parm)
    },
    // 查询所有的用户
    selectAll(size,current){
        return axios.get(path.baseUrl+"/getAll"+"?pageSize="+size+"&currentPage="+current)
    },
    // 更新用户信息
    update(parm){
        return axios.put(path.baseUrl+"/updateUser",parm)
    },
    // 根据id删除用户
    delete(id){
        return axios.delete(path.baseUrl+"/deleteUser/"+id)
    },
    // 根据条件查询
    queryList(keyword,size,current){
        return axios.get(path.baseUrl+"/queryList"+"?keyword="+keyword+"&pageSize="+size+"&currentPage="+current)
    }
}

export default api;