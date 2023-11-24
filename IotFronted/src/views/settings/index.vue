<template>
  <div style="margin-left: 1rem; margin-top: 1rem;">
    <!-- <el-descriptions
      title="定时任务设置信息"
      direction="vertical"
      :column="3"
      border
      style="width: 50%; margin-top: 1rem"
    >
      <el-descriptions-item
        label="当前定时时间"
      >{{ timer }}s</el-descriptions-item>
      <el-descriptions-item label="输入设置值">
        <el-input-number
          v-model="setvalue"
          :min="1"
          :max="60"
          @change="handleChange"
        />
      </el-descriptions-item>
      <el-descriptions-item label="修改">
        <el-button type="success" @click="update">更新</el-button>
      </el-descriptions-item>
    </el-descriptions> -->
    <el-table
      :data="tableData"
      style="width: 60%"
      :row-class-name="tableRowClassName"
    >
      <el-table-column
        align="center"
        prop="task"
        label="任务"
        width="180"
      />
      <el-table-column
        align="center"
        prop="setting"
        label="设置值"
        width="250"
      >
        <el-input-number
          v-model="setvalue"
          :min="1"
          @change="handleChange"
        />
      </el-table-column>
      <el-table-column label="操作" align="center" width="300">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            @click="handleEdit(scope.row.did)"
          >更新</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<style>
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>

<script>
import { get, update } from '@/api/task.js'

export default {
  data() {
    return {
      timer: 10,
      setvalue: 10,
      tableData: [{
        task: '温湿度传感器温度设置',
        settingvalue: 'UPTEMPERATURE'
      }, {
        task: '温湿度传感器湿度设置',
        settingvalue: 'UPHUMIDITY'
      }, {
        task: '关照强度阈值设置',
        settingvalue: 'UPLIGHT'
      }, {
        task: '延时打开设置',
        settingvalue: 'DELAYOPEN'
      }]
    }
  },
  created() {
    this.getinfo()
  },
  methods: {
    update() {
      const taskparams = {
        timer: this.setvalue,
        corn: ''
      }
      update(taskparams).then((res) => {
        this.timer = this.setvalue
        this.$notify({
          title: 'Success',
          message: 'Set Successfully',
          type: 'success',
          duration: 2000
        })
      })
    },
    getinfo() {
      get().then((res) => {
        this.corn = res.data.corn
        this.timer = res.data.timer / 1000
      })
    },
    handleChange(value) {
      this.setvalue = value
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 1) {
        return 'warning-row'
      } else if (rowIndex === 3) {
        return 'success-row'
      }
      return ''
    },
    handleEdit(id) {
      console.log('test')
      // this.$router.push("/device/edit/" + id);
    }
  }
}
</script>
