<template>
  <div class="manage">
    <!--  弹窗 -->
    <el-dialog
      :title="option === -1 ? '新增用户' : '更新用户'"
      :visible.sync="dialogVisible"
      :before-close="handleClose"
      width="50%">
      <!-- 表单数据 -->
      <el-form ref="form" :inline="true" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="姓名" prop="name">
              <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
              <el-input v-model.number="form.age" autocomplete="off" placeholder="请输入年龄"></el-input>
          </el-form-item>
           <el-form-item label="性别" prop="sex">
              <el-select v-model="form.sex" placeholder="请选择性别">
                <el-option label="男" value="男"></el-option>
                <el-option label="女" value="女"></el-option>
              </el-select>
          </el-form-item>
           <el-form-item label="出生日期" prop="birth">
              <el-date-picker type="date" placeholder="选择日期" v-model="form.birth" style="width: 100%;"></el-date-picker>
          </el-form-item>
           <el-form-item label="地址" prop="addr">
              <el-input v-model="form.addr" placeholder="请输入地址"></el-input>
          </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleClose">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 新增 -->
    <div class="manage-header">
      <!--  添加显示按扭 -->
      <el-button type="primary" @click="showDialog(-1)"> 新增</el-button>
       <!-- 搜索框 -->
       <div>
            <el-input
            placeholder="请输入内容"
            v-model="input"
            size="medium" 
            clearable
            @keydown.enter.native="query()">
            <i slot="prefix" class="el-input__icon el-icon-search"></i>
          </el-input>
          <!--  添加显示按扭 -->
          <el-button type="primary" @click="query" size="medium"> 搜索</el-button>
       </div>
      
    </div>
   
    <!-- 表格 -->
      <el-table
        :data="tableData"
        stripe
        height="550"
        style="width: 100%">
        <el-table-column
          prop="id"
          label="id"
        >
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          >
        </el-table-column>
        <el-table-column
          prop="sex"
          label="性别"
         >
        </el-table-column>
        <el-table-column
          prop="age"
          label="年龄"
          >
        </el-table-column>
        <el-table-column
          prop="birth"
          label="生日" 
          >
        </el-table-column>
        <el-table-column
          prop="addr"
          label="地址">
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
    </el-table>

    <!-- 分页栏 -->
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import api from "../api"

export default {
    name:'User',
    data(){
        return {
            // 搜索内容：
            input:'',
            // 弹窗
            dialogVisible: false,
            // 表单数据
            form: {
              name:"",
              age: '',
              sex:'',
              birth:'',
              addr:''
            },
            // 操作
            option: 1,
            // 校验规则
            rules: {
                name: [
                  { required: true, message: '请输入姓名', trigger: 'blur' },
                  { min: 2, max: 8, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                ],
                sex: [
                  { required: true, message: '请选择性别', trigger: 'change' }
                ],
                birth: [
                  { type: 'date', required: true, message: '请选择出生日期', trigger: 'change' }
                ],
                addr: [
                  { required: true, message: '请填写地址', trigger: 'blur' }
                ],
                age: [
                  { required: true, message: '年龄不能为空'},
                  { type: 'number', message: '年龄必须为数字值'}
                ]
              },
            // 表格数据
            tableData:[],
            // 分页栏数据
            total: 0,
            // 当前页
            currentPage: 1,
            // 每页多少条
            pageSize: 5,

        }
    },
    methods: {
      // 弹窗显示
      showDialog(option){
        this.option = option;
        this.dialogVisible = true;
      },
      // 确定：提交表单数进行校验
      submit(){
        
        this.$refs.form.validate((valid) => {
          console.log("是否通过校验：",valid);
          if(valid){
            console.log(this.form)
            if(this.option === -1){
              // 向后端发起请求
              api.addUser(this.form).then(({data})=> {
                if(data.code === 200 ){
                  this.$message({
                    type:"success",
                    message: data.data
                  })
                  this.handleClose()
                  this.show()
                }else {
                  this.$message({
                    type: "error",
                    message: data.msg
                  })
                }
              })
            }else {
              // 向后端发起请求
              api.update(this.form).then(({data})=> {
                if(data.code === 200 ){
                  this.$message({
                    type:"success",
                    message: data.data
                  })
                  this.handleClose()
                  this.show()
                }else {
                  this.$message({
                    type: "error",
                    message: data.msg
                  })
                }
              })
            }
          }
        })
      },
      // 关闭form表单
      handleClose(){
        // 重置表单数据
        this.$refs.form.resetFields()
        // 关闭弹窗
        this.dialogVisible = false
      },
      // 显示列表
      show(){
        api.selectAll(this.pageSize,this.currentPage).then(({data}) => {
          this.total = data.data.total;
          this.tableData = data.data.records.map((item) => {
            item.birth = item.birth.slice(0,10);
            return item;
          })
          // this.tableData = data.data
          // console.log(data.data)
        });
      },
      // 编辑
       handleEdit(index, row) {
        // console.log("编辑",row);
        // 不能直接把row数据给form，因为数据是对象，直接赋值就是把引用的地址赋值了，这样修改会出错的。
        // this.form = row;
        this.showDialog(row.id);
        // // 解决第一种方式:
        this.$nextTick(()=>{
            //赋值到表单
            this.form.id = row.id
            this.form.name = row.name
            this.form.age = row.age
            this.form.sex = row.sex
            this.form.addr = row.addr
            this.form.birth = new Date(row.birth)
        })
        // 解决第二种方式：
        // this.form = JSON.parse(JSON.stringify(row));
      },
      // 删除
      handleDelete(index, row) {
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
         // 向后端发起请求
          api.delete(row.id).then(({data})=> {
            if(data.code === 200 ){
              this.$message({
                type:"success",
                message: data.data
              })
              this.show()
            }else {
              this.$message({
                type: "error",
                message: data.msg
              })
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
        
      },
      // 搜索
      query(){
        if(this.input === ''){
          this.show()
          return ;
        }
        api.queryList(this.input,this.pageSize,this.currentPage).then(({data}) => {
          if(data.data.total !== 0){
            this.tableData =data.data.records.map((item) => {
              item.birth = item.birth.slice(0,10);
              return item;
            })
            this.tableData = data.data.records;
            this.total = data.data.total;
          }else {
            this.$message({
              type: 'info',
              message: data.msg || "为查询到用户"
            })
          }
        })
      },
      // 页面改变
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
        this.pageSize = val;
        this.query();
      },
      // 当前页
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.currentPage = val
        this.query();
      }
    },
    mounted(){
      // 页面加载完毕就执行
      this.show();
    }
}
</script>

<style lang="less" scoped>
.manage {
  height: 90%;
}
.manage-header {
  display:flex;
  justify-content:space-between;
  .el-input {
    width: 180px;
    margin-right:10px;
  }
  .el-button {
    margin-right: 100px;
  }
}

.block {
  text-align: center;
}
</style>