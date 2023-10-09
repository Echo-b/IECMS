<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="设备ID">
        <el-input v-model="form.did"></el-input>
      </el-form-item>
      <el-form-item label="设备名称">
        <el-input v-model="form.deviceName"></el-input>
      </el-form-item>
      <el-form-item label="设备状态">
        <el-input v-model="form.status"></el-input>
      </el-form-item>
      <el-form-item label="设备经度">
        <el-input v-model="form.longitude"></el-input>
      </el-form-item>
      <el-form-item label="设备纬度">
        <el-input v-model="form.latitude"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="cancelShow" >取 消</el-button>
      <el-button type="primary" @click="onSubmit" >确 定</el-button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    form: {
      deviceName: "",
      longitude: 0.0,
      latitude: 0.0,
      status: "off",
      did: 0,
    },
    editShow: false,
  },
  name: "tableTemplate",
  methods: {
    onSubmit() {
      this.$emit("editTable");
    },
    cancelShow(){
      this.$emit("changeShow")
    }
  },
};
</script>

<style scoped>
.dialog-footer{
  margin-left: 8rem;
}
</style>