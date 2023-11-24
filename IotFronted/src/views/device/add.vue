<template>
  <table-template
    :form="form"
    @changeShow="cancelShow"
    @editTable="onSubmit"
  />
</template>
<script>
import { addDevice, getDeviceById } from '@/api/device'

import tableTemplate from './components/tableTemplate.vue'

export default {

  components: {
    tableTemplate
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
      editShow: true
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
    }
  }
}
</script>

<style>
</style>
