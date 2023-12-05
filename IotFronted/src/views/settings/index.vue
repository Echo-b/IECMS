<template>
  <div style="margin-left: 1rem; margin-top: 1rem;">
    <el-table
      :data="tableData"
      style="width: 100%"
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
            @click="handleEdit(scope.row)"
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
import { getThresholdDevices } from '@/api/device.js'

export default {
  data() {
    return {
      timer: 10,
      setvalue: 10,
      thresDevice: [],
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
    this.fetchData()
  },
  methods: {
    fetchData() {
      getThresholdDevices(this.$store.getters.name).then((res) => {
        console.log(res)
        this.thresDevice = res.data.thresDevice
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
    handleEdit(row) {
      // console.log(this.thresDevice)
      // this.$router.push("/device/edit/" + id);
    }
  }
}
</script>
