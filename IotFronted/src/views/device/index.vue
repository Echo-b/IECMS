<template>
  <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
    <el-tab-pane label="设备列表" name="devicelist">
      <device-list />
    </el-tab-pane>
    <el-tab-pane label="设备位置" name="devicelocation">
      <device-location />
    </el-tab-pane>
  </el-tabs>
</template>
<script>
import DeviceList from './deviceList.vue'
import DeviceLocation from './map.vue'

export default {
  components: {
    DeviceList,
    DeviceLocation
  },
  data() {
    return {
      activeName: 'devicelist',
      devices: []
    }
  },

  methods: {
    handleClick(tab, event) {

    }
  }
}
</script>
