<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="设备ID" prop="did" :rules="didRules">
        <el-input v-model="form.did" type="number" @blur="validateDid" />
      </el-form-item>
      <el-form-item label="设备名称" prop="deviceName" :rules="deviceNameRules">
        <el-input v-model="form.deviceName" @blur="validateDName" />
      </el-form-item>
      <el-form-item label="设备状态">
        <el-select v-model="form.status" placeholder="请选择设备状态">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="设备经度" prop="longitude" :rules="longitudeRules">
        <el-input v-model="form.longitude" @blur="validateLongitude" />
      </el-form-item>
      <el-form-item label="设备纬度" prop="latitude" :rules="latitudeRules">
        <el-input v-model="form.latitude" @blur="validateLatitude" />
      </el-form-item>
      <el-form-item label="设备类型">
        <el-select v-model="form.type" placeholder="请选择设备类型">
          <el-option
            v-for="item in options1"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
    </el-form>
    <el-button @click="cancelShow">重 置</el-button>
    <el-button
      type="primary"
      :disabled="submitButtonDisabled"
      @click="onConfirm"
    >下一步</el-button>
  </div>
</template>

<script>
export default {
  name: 'tableTemplate',
  props: {
    form: {
      deviceName: '',
      longitude: 0.0,
      latitude: 0.0,
      status: "off",
      did: 0,
      type: ''
    }
  },
  data() {
    return {
      options: [
        { value: 'on', label: 'ON' },
        { value: 'off', label: 'OFF' }
      ],
      options1: [
        { value: 'sensor', label: 'sensor' },
        { value: 'light', label: 'light' },
        { value: 'other', label: 'other' }
      ],
      didRules: [
        { required: true, message: '请输入设备ID', trigger: 'blur' }
      ],
      deviceNameRules: [
        { required: true, message: '请输入设备名称', trigger: 'blur' }
      ],
      longitudeRules: [
        { required: true, message: '请输入设备经度', trigger: 'blur' },
        { pattern: /^-?\d+(\.\d+)?$/, message: '请输入数字或小数', trigger: 'blur' },
        { validator: this.validateLongitudeRange, trigger: 'blur' }
      ],
      latitudeRules: [
        { required: true, message: '请输入设备纬度', trigger: 'blur' },
        { pattern: /^-?\d+(\.\d+)?$/, message: '请输入数字或小数', trigger: 'blur' },
        { validator: this.validateLatitudeRange, trigger: 'blur' }
      ],
      submitButtonDisabled: true
    }
  },
  watch: {
    // 监听表单数据的变化，一旦变化就执行验证方法
    form: {
      handler() {
        this.validateForm()
      },
      deep: true
    }
  },
  methods: {
    onSubmit() {
      this.$emit('editTable')
    },
    onConfirm() {
      this.$emit('confirm')
    },
    cancelShow() {
      this.editShow = false
      this.$refs.form.resetFields()
      // this.$emit('changeShow');
    },
    validateDid() {
      // 验证设备ID的方法，可在失去焦点时调用
      this.$refs.form.validateField('did')
    },
    validateDName() {
      // 验证设备ID的方法，可在失去焦点时调用
      this.$refs.form.validateField('deviceName')
    },
    validateLongitude() {
      this.$refs.form.validateField('longitude')
    },

    validateLatitude() {
      this.$refs.form.validateField('latitude')
    },

    validateLongitudeRange(rule, value, callback) {
      // 自定义验证经度范围
      const longitude = parseFloat(value)
      if (isNaN(longitude) || longitude < -180 || longitude > 180) {
        callback(new Error('经度范围必须在-180到180之间'))
      } else {
        callback()
      }
    },

    validateLatitudeRange(rule, value, callback) {
      // 自定义验证纬度范围
      const latitude = parseFloat(value)
      if (isNaN(latitude) || latitude < -90 || latitude > 90) {
        callback(new Error('纬度范围必须在-90到90之间'))
      } else {
        callback()
      }
    },
    validateForm() {
      // 执行表单验证
      this.$refs.form.validate(valid => {
        this.submitButtonDisabled = !valid // 更新按钮的禁用状态
      })
    }
  }
}
</script>

<style scoped>
.dialog-footer{
  margin-left: 8rem;
}
</style>
