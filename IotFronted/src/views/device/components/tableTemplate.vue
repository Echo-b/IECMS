<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="设备ID" prop="did" :rules="didRules">
        <el-input v-model="form.did" type="number" @blur="validateDid"/>
      </el-form-item>
      <el-form-item label="设备名称" prop="deviceName" :rules="deviceNameRules">
        <el-input v-model="form.deviceName" @blur="validateDName"/>
      </el-form-item>
      <el-form-item label="设备状态">
        <el-input v-model="form.status" />
      </el-form-item>
      <el-form-item label="设备经度" prop="longitude" :rules="longitudeRules">
        <el-input v-model="form.longitude" @blur="validateLongitude"/>
      </el-form-item>
      <el-form-item label="设备纬度" prop="latitude" :rules="latitudeRules">
        <el-input v-model="form.latitude" @blur="validateLatitude"/>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="cancelShow">重 置</el-button>
      <el-button 
        type="primary" 
        @click="onConfirm"
        :disabled="submitButtonDisabled">下一步</el-button>
    </div>
  </div>
</template>

<script>
export default {
  watch: {
    // 监听表单数据的变化，一旦变化就执行验证方法
    form: {
      handler() {
        this.validateForm();
      },
      deep: true,
    },
  },
  name: 'tableTemplate',
  props: {
    form: {
      deviceName: '',
      longitude: 0.0,
      latitude: 0.0,
      status: "off",
      did: 0
    },
    editShow: false
  },
  methods: {
    onSubmit() {
      this.$emit('editTable')
    },
    cancelShow() {
      this.$refs.form.resetFields();
      this.$emit('changeShow');
    },
    onConfirm() {
      this.dialogVisible = true;
    },
    cancelDialog() {
      this.dialogVisible = false;
    },
    validateDid() {
      // 验证设备ID的方法，可在失去焦点时调用
      this.$refs.form.validateField('did');
    },
    validateDName() {
      // 验证设备ID的方法，可在失去焦点时调用
      this.$refs.form.validateField('deviceName');
    },
    validateLongitude() {
      this.$refs.form.validateField('longitude');
    },

    validateLatitude() {
      this.$refs.form.validateField('latitude');
    },

    validateLongitudeRange(rule, value, callback) {
      // 自定义验证经度范围
      const longitude = parseFloat(value);
      if (isNaN(longitude) || longitude < -180 || longitude > 180) {
        callback(new Error('经度范围必须在-180到180之间'));
      } else {
        callback();
      }
    },

    validateLatitudeRange(rule, value, callback) {
      // 自定义验证纬度范围
      const latitude = parseFloat(value);
      if (isNaN(latitude) || latitude < -90 || latitude > 90) {
        callback(new Error('纬度范围必须在-90到90之间'));
      } else {
        callback();
      }
    },
    validateForm() {
      // 执行表单验证
      this.$refs.form.validate(valid => {
        this.submitButtonDisabled = !valid; // 更新按钮的禁用状态
      });
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
      didRules: [
        { required: true, message: '请输入设备ID', trigger: 'blur' },
      ],
      deviceNameRules: [
        { required: true, message: '请输入设备名称', trigger: 'blur' },
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
      dialogVisible: false,
      submitButtonDisabled: true,
    };
  }
}
</script>

<style scoped>
.dialog-footer{
  margin-left: 8rem;
}
</style>
