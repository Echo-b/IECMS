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
            <el-step title="已申请" />
            <el-step title="审核中" />
            <el-step :title="getStepTitle(scope.row.status)" :status="getStepIcon(scope.row.status)" />
            <!-- <el-step :title="getStepTitle(scope.row.status)" icon="el-icon-error" /> -->
          </el-steps>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="400">
        <template slot-scope="scope">
          <el-button type="primary" @click="showDetail(scope.row)">详情</el-button>
          <el-button type="success" :disabled="isCancelDisabled(scope.row)" @click="cancelapply(scope.row)">撤销</el-button>
          <el-button type="danger" @click="deteleapply(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :visible="dialogVisible" title="申请记录详情" @close="closeDetailDialog">
      <el-descriptions :bordered="true" size="medium" border>
        <el-descriptions-item label="申请日期">{{ detailData.date }}</el-descriptions-item>
        <el-descriptions-item label="设备id">{{ detailData.did }}</el-descriptions-item>
        <el-descriptions-item label="设备名称">{{ detailData.deviceName }}</el-descriptions-item>
        <el-descriptions-item label="申请人">{{ detailData.creator }}</el-descriptions-item>
        <el-descriptions-item label="经度">{{ detailData.latitude }}</el-descriptions-item>
        <el-descriptions-item label="纬度">{{ detailData.longitude }}</el-descriptions-item>
        <el-descriptions-item label="设备状态">{{ detailData.status }}</el-descriptions-item>
        <el-descriptions-item label="设备类型">{{ detailData.type }}</el-descriptions-item>
        <el-descriptions-item label="审核状态">{{ statusmap[taskstatus] }}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>
  </div>
</template>

<script>
import { getAllTodoListTask, updateTodoListTask, getTodoListTaskById, deleteTaskById } from '@/api/task.js'
import { getDeviceById } from '@/api/device.js'
export default {
  inject: ['reload'],
  data() {
    return {
      tableData: [],
      dialogVisible: false,
      currentRow: {},
      detailData: {
        creator: '',
        did: 0,
        deviceName: '',
        latitude: 0.0,
        longitude: 0.0,
        status: '',
        type: '',
        date: '',
        insert_flag: 0,
        group_id: 0
      },
      taskstatus: 0,
      statusmap: {
        1: '审核中',
        3: '审核通过',
        4: '审核未通过',
        5: '审核已撤销'
      }
    }
  },
  created: function() {
    getAllTodoListTask().then((response) => {
      this.tableData = response.data.tasks.map(item => {
        return {
          date: item.date.split(' ')[0], // 提取日期部分
          apply: item.apply,
          deviceName: item.deviceName,
          status: item.status,
          tid: item.tid
        }
      })
    })
  },
  methods: {
    showDetail(row) {
      getTodoListTaskById(row.tid).then((res) => {
        this.date = res.data.task.date.split(' ')[0]
        this.taskstatus = res.data.task.status
        // response.data.task.did
        getDeviceById(res.data.task.did).then((res1) => {
          this.detailData = res1.data.item
        })
      })
      this.dialogVisible = true
      console.log(11111)
      console.log(this.detailData)
    },
    isCancelDisabled(row) {
    // 如果当前行的 status 是 3（审核通过）, 4（审核未通过）, 5（已撤销），则禁用按钮
      return [3, 4, 5].includes(row.status)
    },
    closeDetailDialog() {
      // 关闭对话框时，重置当前行数据
      this.dialogVisible = false
    },
    deteleapply(row) {
    // 使用 confirm 函数显示确认框
      this.$confirm('此操作将删除该次申请, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteTaskById(row.tid).then((res) => {
          if (res.success) {
            this.reload()
            this.$message({
              message: '删除成功！',
              type: 'success'
            })
          } else {
            this.$message({
              message: '删除失败！',
              type: 'error'
            })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    cancelapply(row) {
      const t = {
        tid: row.tid,
        status: 5
      }
      updateTodoListTask(t).then((response) => {
        if (response.success) {
          this.$message({
            message: '撤销成功',
            type: 'success'
          })
        } else {
          this.$message({
            message: '撤销失败',
            type: 'error'
          })
        }
      })
    },
    getStepIcon(status) {
      switch (status) {
        case 1:
          return 'wait'
        case 3:
          return 'success'
        case 4:
          return 'error'
        case 5:
          return 'finish'
      }
    },
    getStepTitle(status) {
      switch (status) {
        case 1:
          return '审核结果'
        case 3:
          return '审核通过'
        case 4:
          return '审核未通过'
        case 5:
          return '已撤销'
      }
    }
  }
}
</script>
