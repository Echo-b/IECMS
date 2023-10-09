<template>
  <table-template
    :form="this.form"
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
      this.form.status = 'off'
      addDevice(this.form).then((res) => {
        this.$message({
          message: '添加成功',
          type: 'success'
        })
      })
      this.$router.push({ path: '/device/list' })
    },
    cancelShow() {
      this.editShow = false
    }
  }
}
</script>

<style>
</style>
