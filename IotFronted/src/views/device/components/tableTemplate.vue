<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="设备ID">
        <el-input v-model="form.did" type="number" />
      </el-form-item>
      <el-form-item label="设备名称">
        <el-input v-model="form.deviceName" />
      </el-form-item>
      <el-form-item label="设备状态">
        <el-select v-model="form.status" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="设备经度">
        <el-input v-model="form.longitude" />
      </el-form-item>
      <el-form-item label="设备纬度">
        <el-input v-model="form.latitude" />
      </el-form-item>
      <el-form-item label="设备类型">
        <el-select v-model="form.type" placeholder="请选择">
          <el-option
            v-for="item in options1"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="cancelShow">取 消</el-button>
      <el-button type="primary" @click="onConfirm">确 定</el-button>
    </div>
    <el-dialog :visible.sync="dialogVisible" title="确认信息" width="30%">
      <el-descriptions :bordered="true" column="1">
        <el-descriptions-item label="设备ID">{{ form.did }}</el-descriptions-item>
        <el-descriptions-item label="设备名称">{{ form.deviceName }}</el-descriptions-item>
        <el-descriptions-item label="设备状态">{{ form.status }}</el-descriptions-item>
        <el-descriptions-item label="设备经度">{{ form.longitude }}</el-descriptions-item>
        <el-descriptions-item label="设备纬度">{{ form.latitude }}</el-descriptions-item>
        <el-descriptions-item label="设备类型">{{ form.type }}</el-descriptions-item>
      </el-descriptions>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">上一步</el-button>
        <el-button type="primary" @click="onSubmit">确认添加</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'tableTemplate',
  props: {
    form: {
      type: Object,
      default: () => ({
        deviceName: '',
        longitude: 0.0,
        latitude: 0.0,
        status: "off",
        did: 0,
        type: ''
      })
    },
    editShow: Boolean
  },
  methods: {
    onSubmit() {
      this.$emit('editTable');
    },
    cancelShow() {
      this.$emit('changeShow');
    },
    onConfirm() {
      this.dialogVisible = true;
    },
    cancelDialog() {
      this.dialogVisible = false;
    },
  },
  data() {
    return {
      options: [
        { value: 'on', label: 'ON' },
        { value: 'off', label: 'OFF' }
      ],
      options1: [
        { value: 'public', label: 'PUBLIC' },
        { value: 'private', label: 'PRIVATE' }
      ],
      dialogVisible: false
    };
  }
}
</script>

<style scoped>
.dialog-footer {
  margin-left: 8rem;
}
</style>
