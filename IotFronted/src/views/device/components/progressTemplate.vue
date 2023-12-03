<template>
  <div>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="date" label="申请日期" width="180" />
      <el-table-column prop="apply" label="申请人" width="180" />
      <el-table-column prop="deviceName" label="设备名称" width="180" />
      <el-table-column prop="status" label="审核进度" width="380">
        <template slot-scope="scope">
          <!-- 在这里添加 el-steps 组件 -->
          <el-steps :active="scope.row.status < 3 ? scope.row.status : 3" finish-status="success" space="400px">
            <el-step title="已申请"/>
            <el-step title="审核中" />
            <el-step :title="getStepTitle(scope.row.status)" :status="getStepIcon(scope.row.status)"/>
            <!-- <el-step :title="getStepTitle(scope.row.status)" icon="el-icon-error" /> -->
          </el-steps>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="400">
        <template slot-scope="scope">
          <el-button type="primary" @click="showDetail(scope.row)">详情</el-button>
          <el-button type="success" @click="cancelapply(scope.row)" :disabled="isCancelDisabled(scope.row)">撤销</el-button>
          <el-button type="danger" @click="deteleapply(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :visible="dialogVisible" title="申请记录详情" @close="closeDetailDialog">
        <el-descriptions :bordered="true" size="medium" border>
          <el-descriptions-item label="申请日期">{{ this.detailData["申请日期"] }}</el-descriptions-item>
          <el-descriptions-item label="设备id">{{ this.detailData["设备id"] }}</el-descriptions-item>
          <el-descriptions-item label="设备名称">{{ this.detailData["设备名称"] }}</el-descriptions-item>
          <el-descriptions-item label="申请人">{{ this.detailData["申请人"] }}</el-descriptions-item>
          <el-descriptions-item label="经度">{{ this.detailData["经度"] }}</el-descriptions-item>
          <el-descriptions-item label="纬度">{{ this.detailData["纬度"] }}</el-descriptions-item>
          <el-descriptions-item label="设备状态">{{ this.detailData["设备状态"] }}</el-descriptions-item>
          <el-descriptions-item label="设备类型">{{ this.detailData["设备类型"] }}</el-descriptions-item>
          <el-descriptions-item label="审核状态">{{ this.detailData["审核状态"] }}</el-descriptions-item>
        </el-descriptions>
      </el-dialog>
  </div>
</template>

<script>
import {getAllTodoListTask,updateTodoListTask,getTodoListTaskById,deleteTaskById} from "@/api/task.js"
import {getDeviceById} from "@/api/device.js"
export default {
  methods: {
    showDetail(row) {
      this.dialogVisible = true,
      getTodoListTaskById(row.tid).then((response) => {
        // response.data.task.did
        getDeviceById(1).then((response1) => {
          this.detailData["申请日期"] = response.data.task.date.split(" ")[0]
          this.detailData["设备id"] = response1.data.item.did
          this.detailData["设备名称"] = response1.data.item.deviceName
          this.detailData["申请人"] = response.data.task.apply
          this.detailData["经度"] = response1.data.item.latitude
          this.detailData["纬度"] = response1.data.item.longitude
          this.detailData["设备状态"] = response1.data.item.status
          this.detailData["设备类型"] = response1.data.item.type
          this.detailData["审核状态"] = this.statusmap[response.data.task.status]
        })
      })
      console.log(11111)
      console.log(this.detailData)
    },
    isCancelDisabled(row) {
    // 如果当前行的 status 是 3（审核通过）, 4（审核未通过）, 5（已撤销），则禁用按钮
      return [3, 4, 5].includes(row.status);
    },
    closeDetailDialog() {
      // 关闭对话框时，重置当前行数据
      this.dialogVisible = false;
    },
    deteleapply(row) {
  // 使用 confirm 函数显示确认框
      if (confirm('确定要删除吗？')) {
        // 用户点击了确认按钮
        deleteTaskById(row.tid);
        this.$message({
          message: '删除成功！',
          type: 'success'
        });
      } else {
        // 用户点击了取消按钮，不执行删除操作
        this.$message({
          message: '取消删除',
          type: 'info'
        });
      }
    },
    cancelapply(row) {
      const t = {
        tid: row.tid,
        status: 5
      }
      updateTodoListTask(t).then((response) => {
        console.log(response)
      })
      this.$message({
        message: '撤销成功',
        type: 'success'
      })
    }, 
    getStepIcon(status) {
      switch(status) {
        case 1:
          return "wait"
        case 3:
          return "success"
        case 4:
          return "error"
        case 5:
          return "finish"
      }
    },
    getStepTitle(status) {
      switch(status) {
        case 1:
          return "审核结果"
        case 3:
          return "审核通过"
        case 4:
          return "审核未通过"
        case 5:
          return "已撤销"
      }
    }
  },
  data() {
    return {
      tableData: [],
      dialogVisible: false,
      currentRow: {},
      detailData: {},
      statusmap: {
        1: "审核中",
        3: "审核通过",
        4: "审核未通过",
        5: "审核已撤销",
      }
    }
  },
  created: function(){
    getAllTodoListTask().then((response) =>{
      this.tableData = response.data.tasks.map(item => {
        return {
          date: item.date.split(" ")[0],  // 提取日期部分
          apply: item.apply,
          deviceName: item.deviceName,
          status: item.status,
          tid: item.tid
        }
      })
    })
  }
}
</script>
