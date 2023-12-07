<template>
  <el-table
    :data="tableData"
    style="width: 100%"
    :row-class-name="tableRowClassName"
  >
    <el-table-column
      align="center"
      prop="task"
      label="任务"
      width="200"
    />
    <el-table-column
      align="center"
      label="设置值"
      width="250"
      prop="setvalue"
    >
      <template slot-scope="scope">
        <el-input
          v-model="scope.row.setvalue"
          placeholder="请输入设置值"
          clearable
          @blur="validateSetValue(scope.row)"
        />
      </template>
      <!-- <el-input-number
        v-model="setvalue"
        :min="1"
        @change="handleChange"
      /> -->
    </el-table-column>
    <el-table-column
      align="center"
      label="选择设备"
      prop="value"
      width="250"
    >
      <template slot-scope="scope">
        <el-select v-model.number="scope.row.value" placeholder="请选择设备" @change="handleChange(scope.row)">
          <el-option-group
            v-for="group in options"
            :key="group.label"
            :label="group.label"
          >
            <el-option
              v-for="item in group.options"
              :key="item.did"
              :label="item.deviceName"
              :value="{value: item.did, deviceName: item.deviceName}"
            />
          </el-option-group>
        </el-select>
      </template>
    </el-table-column>
    <el-table-column label="操作" align="center" width="250">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="success"
          @click="handleEdit(scope.row)"
        >设置</el-button>
      </template>
    </el-table-column>
  </el-table>
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
import { insertRecord } from '@/api/record.js'
import { setDeviceHumiThreshold, setDeviceLightThreshold, setDeviceTempThreshold } from '@/api/threshold.js'

export default {
  data() {
    return {
      numberInput: '',
      setvalue: 10,
      options: [],
      tableData: [{
        task: '温湿度传感器温度设置',
        command: 'UPTEMPERATURE',
        value: ''
      }, {
        task: '温湿度传感器湿度设置',
        command: 'UPHUMIDITY',
        value: ''
      }, {
        task: '关照强度阈值设置',
        command: 'UPLIGHT',
        value: ''
      }, {
        task: '延时打开',
        command: 'DELAYOPEN',
        value: ''
      }, {
        task: '延时关闭',
        command: 'DELAYCLOSE',
        value: ''
      }]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    validateSetValue(row) {
      const regNumber1 = /^(0|[1-9]\d*)(\.\d{0,2})?$/;
      const regInteger = /^[1-9]\d*$/;
      if (row.rowIndex < 3) {
        // 对前三行进行整数或小数验证
        if (!regNumber1.test(row.setvalue)) {
          this.$message.error('请输入有效的数字，可以是整数或最多两位小数');
          row.setvalue = '';  // 清空不符合规则的值
      }else{
        if(row.setvalue <= 0 || row.setvalue >= 9999) {
          this.$message.error('请输入0-9999范围的数值');
          row.setvalue = ''; }
      }
      } else{
          if (!regInteger.test(row.setvalue)) {
            this.$message.error('请输入大于0的整数');
            row.setvalue = '';  }
      }
    },
    fetchData() {
      getThresholdDevices(this.$store.getters.name).then((res) => {
        console.log(res)
        this.options = res.data.options
        console.log(this.options)
      })
    },
    handleChange(row) {
      this.value = row.value
    },
    tableRowClassName({ row, rowIndex }) {
      row.rowIndex = rowIndex
      if (rowIndex === 1) {
        return 'warning-row'
      } else if (rowIndex === 3) {
        return 'success-row'
      }
      return ''
    },
    handleEdit(row) {
      console.log(row)
      switch (row.rowIndex) {
        case 0: {
          // set temperature
          const t = {
            did: row.value.value,
            temp_max: row.setvalue
          }
          setDeviceTempThreshold(t).then((res) => {
            console.log(res)
            if (res.success) {
              const r = {
                did: row.value.value,
                deviceName: row.value.deviceName,
                command: row.command,
                operator: this.$store.getters.name
              }
              insertRecord(r).then((res) => {
                console.log(res)
                if (res.success) {
                  this.$notify({
                    title: 'Success',
                    message: res.msg,
                    type: 'success'
                  })
                } else {
                  this.$notify({
                    title: 'Success',
                    message: res.msg,
                    type: 'error'
                  })
                }
              })
            } else {
              this.$notify({
                title: 'Success',
                message: res.msg,
                type: 'error'
              })
            }
          })
          break
        }
        case 1: {
          // set humidity
          const t = {
            did: row.value.value,
            humi_max: row.setvalue
          }
          setDeviceHumiThreshold(t).then((res) => {
            console.log(res)
            if (res.success) {
              const r = {
                did: row.value.value,
                deviceName: row.value.deviceName,
                command: row.command,
                operator: this.$store.getters.name
              }
              insertRecord(r).then((res) => {
                console.log(res)
                if (res.success) {
                  this.$notify({
                    title: 'Success',
                    message: res.msg,
                    type: 'success'
                  })
                } else {
                  this.$notify({
                    title: 'Success',
                    message: res.msg,
                    type: 'error'
                  })
                }
              })
            } else {
              this.$notify({
                title: 'Success',
                message: res.msg,
                type: 'error'
              })
            }
          })
          break
        }
        case 2: {
          // set light
          const t = {
            did: row.value.value,
            light: row.setvalue
          }
          setDeviceLightThreshold(t).then((res) => {
            console.log(res)
            if (res.success) {
              const r = {
                did: row.value.value,
                deviceName: row.value.deviceName,
                command: row.command,
                operator: this.$store.getters.name
              }
              insertRecord(r).then((res) => {
                console.log(res)
                if (res.success) {
                  this.$notify({
                    title: 'Success',
                    message: res.msg,
                    type: 'success'
                  })
                } else {
                  this.$notify({
                    title: 'Success',
                    message: res.msg,
                    type: 'error'
                  })
                }
              })
            } else {
              this.$notify({
                title: 'Success',
                message: res.msg,
                type: 'error'
              })
            }
          })
          break
        }
        case 3: {
          // set delay open task
          const r = {
            did: row.value.value,
            deviceName: row.value.deviceName,
            command: row.command,
            operator: this.$store.getters.name,
            topic: `/topics/sub/${row.value.deviceName}`,
            param: row.setvalue
          }
          insertRecord(r).then((res) => {
            console.log(res)
            if (res.success) {
              this.$notify({
                title: 'Success',
                message: res.msg,
                type: 'success'
              })
            } else {
              this.$notify({
                title: 'Success',
                message: res.msg,
                type: 'error'
              })
            }
          })
          break
        }
        case 4: {
          // set delay close task
          const r = {
            did: row.value.value,
            deviceName: row.value.deviceName,
            command: row.command,
            operator: this.$store.getters.name,
            topic: `/topics/sub/${row.value.deviceName}`,
            param: row.setvalue
          }
          insertRecord(r).then((res) => {
            console.log(res)
            if (res.success) {
              this.$notify({
                title: 'Success',
                message: res.msg,
                type: 'success'
              })
            } else {
              this.$notify({
                title: 'Success',
                message: res.msg,
                type: 'error'
              })
            }
          })
          break
        }
      }
    }
  }
}
</script>
