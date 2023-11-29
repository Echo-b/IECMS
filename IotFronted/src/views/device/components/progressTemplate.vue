<template>
  <div>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="date" label="申请日期" width="180" />
      <el-table-column prop="apply" label="申请人" width="180" />
      <el-table-column prop="deviceName" label="设备名称" width="180" />
      <el-table-column prop="status" label="审核进度" width="380">
        <template slot-scope="scope">
          <!-- 在这里添加 el-steps 组件 -->
          <el-steps :active="scope.row.status" finish-status="success" space="400px">
            <el-step title="提交申请" />
            <el-step title="审核中" />
            <el-step title="添加成功" />
          </el-steps>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="400">
        <template slot-scope="scope">
          <el-button type="primary" @click="showDetail">详情</el-button>
          <el-button type="success" @click="handleButton2(scope.row)">撤销</el-button>
          <el-button type="danger" @click="handleButton3(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :visible="dialogVisible" title="详情" @close="closeDetailDialog">
        <el-descriptions :bordered="true" size="small" column="1">
          <el-descriptions-item label="申请日期">{{ currentRow.date }}</el-descriptions-item>
          <el-descriptions-item label="申请人">{{ currentRow.apply }}</el-descriptions-item>
          <el-descriptions-item label="设备名称">{{ currentRow.deviceName }}</el-descriptions-item>
          <el-descriptions-item label="审核进度">{{ currentRow.status }}</el-descriptions-item>
        </el-descriptions>
      </el-dialog>
  </div>
</template>

<script>
import {getAllTodoListTask} from "@/api/task.js"
export default {
  methods: {
    showDetail() {
      this.dialogVisible = true
    },
    closeDetailDialog() {
      // 关闭对话框时，重置当前行数据
      this.dialogVisible = false;
      this.currentRow = {};
    },
  },
  data() {
    return {
      tableData: [],
        // {
        //   date: '2016-05-02',
        //   name: '王小虎',
        //   dname: '测试机1',
        //   progress: 0 // 添加 progress 属性
        // },
      dialogVisible: false,
      currentRow: {}
    }
  },
  created: function(){
    getAllTodoListTask().then((response) =>{
      console.log(response.data.tasks)
      this.tableData = response.data.tasks.map(item => {
        return {
          date: item.date.split(" ")[0],  // 提取日期部分
          apply: item.apply,
          deviceName: item.deviceName,
          status: item.status + 1
        }
      })
      // this.$store.dispatch('page/setdevices', this.allDevices).then(() => {
      //   console.log('devices init')
      //   console.log(this.allDevices)
      // })
      // // 总记录数
      // this.total = response.data.total
      // // 每页显示的条数
      // this.pageSize = 10

      // this.tableData = this.allDevices.slice(0, this.pageSize)

      // this.$store.dispatch('page/setcurdevices', this.tableData).then(() => {
      //   console.log('curdevices init')
      // })
      // // 网络请求成功后，显示分页
      // this.isShow = true
    })
  }
}
</script>
