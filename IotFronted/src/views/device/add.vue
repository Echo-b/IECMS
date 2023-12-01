<template>
  <el-tabs v-model="activeName" type="border-card">
    <el-tab-pane label="用户管理" name="first">
      <table-template
        :form="form"
        @changeShow="cancelShow"
        @editTable="onSubmit"
      />

      <el-button @click="cancelShow">重 置</el-button>
      <el-button 
        type="primary" 
        @click="onConfirm"
        :disabled="submitButtonDisabled">下一步</el-button>
 
    </el-tab-pane>
    <el-tab-pane label="提交进度" name="second">
      <progressTemplate />
    </el-tab-pane>
    <el-dialog :visible.sync="dialogVisible" title="请确认添加信息" width="40%" @close="cancelDialog">
      <!-- el-descriptions 显示设备信息 -->
      <el-descriptions :bordered="true" size="small" column="1">
        <el-descriptions-item label="设备ID">{{ form.did }}</el-descriptions-item>
        <el-descriptions-item label="设备名称">{{ form.deviceName }}</el-descriptions-item>
        <el-descriptions-item label="设备状态">{{ form.status }}</el-descriptions-item>
        <el-descriptions-item label="设备经度">{{ form.longitude }}</el-descriptions-item>
        <el-descriptions-item label="设备纬度">{{ form.latitude }}</el-descriptions-item>
      </el-descriptions>

      <!-- 操作按钮 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancelDialog">上一步</el-button>
        <el-button type="primary" @click="addTask()">确定</el-button>
      </span>
    </el-dialog>
  </el-tabs>
</template>
<script>
import { addDevice, getDeviceById } from '@/api/device'
import { addTodoListTask } from '@/api/task'
import tableTemplate from './components/tableTemplate.vue'
import progressTemplate from './components/progressTemplate.vue'

export default {

  components: {
    tableTemplate,
    progressTemplate
  },
  data() {
    return {
      form: {
        deviceName: '',
        longitude: 0.0,
        latitude: 0.0,
        status: 'off',
        did: 1
      },
      editShow: true,
      activeName: 'first',
      dialogVisible: false,
      submitButtonDisabled: false
    }
  },

  created() {
    if (this.$route.params.id) {
      const did = this.$route.params.id
      getDeviceById(did).then((res) => {
        this.form = res.data.item
      })
    }
  },

  methods: {
    onSubmit() {
      console.log(this.form)
      var devices = this.$store.getters.devices
      if (devices.findIndex((item) => item.did === Number(this.form.did)) === -1) {
        this.form.status = 'off'
        addDevice(this.form).then((res) => {
          this.$message({
            message: '添加成功',
            type: 'success'
          })
        })
        devices.push(this.form)
        this.$store.dispatch('page/setdevices', devices).then(() => {
          console.log(devices)
        })
        this.$router.push({ path: '/device/list' })
      } else {
        this.$message({
          message: '设备ID已存在',
          type: 'warning'
        })
      }
    },
    cancelShow() {
      this.editShow = false
    },
    // cancelShow() {
    //   this.editShow = false
    //   this.$refs.form.resetFields();
    //   this.$emit('changeShow');
    // },
    onConfirm() {
      this.dialogVisible = true;
    },
    cancelDialog() {
      this.dialogVisible = false;
    },
    addTask(){
      const t = {
        apply: this.$store.getters.name,
        deviceName: this.form.deviceName,
        did: this.form.did,
        status: 0
      };
      console.log(t);
      addTodoListTask(t).then(res => {
        console.log(res)
      })
    }
  }
}
</script>

<style>
</style>
