<template>
  <div>
    <el-descriptions
      class="margin-top"
      :column="2"
      :size="size"
      border
    >
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user" />
          设备ID
        </template>
        {{ this.device.did }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-mobile-phone" />
          设备名称
        </template>
        {{ this.device.deviceName }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-location-outline" />
          设备状态
        </template>
        <el-tag size="small">{{ this.device.status }}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets" />
          设备经度
        </template>
        {{ this.device.longitude }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-office-building" />
          设备纬度
        </template>
        {{ this.device.latitude }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-video-play" />
          动作
        </template>
        <el-button
          size="mini"
          type="primary"
          @click="handleOpenCommand"
        >打开</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleCloseCommand"
        >关闭</el-button>

      </el-descriptions-item>
    </el-descriptions>
    <!-- <model-vue :did="device.did" /> -->

  </div>

</template>

<script>
import { getDeviceById, DeviceControll } from '@/api/device'

// import modelVue from './model.vue'

export default {
  // components: {
  //   modelVue
  // },
  data() {
    return {
      device: {
        deviceName: '',
        longitude: 0.0,
        latitude: 0.0,
        status: 'off',
        did: 1
      },
      size: ''
    }
  },
  mounted() {
    if (this.$route.params.id) {
      const did = this.$route.params.id
      getDeviceById(did).then((res) => {
        this.device = res.data.item
      })
    }
  },
  methods: {
    handleOpenCommand() {
      const form = {
        did: this.device.did,
        command: 'LEDON',
        deviceName: this.device.deviceName,
        topic: '/topics/led/sub',
        date: ''
      }
      DeviceControll(form).then((res) => {
        this.$notify({
          title: '设置完毕',
          message: res.msg,
          type: res.type,
          duration: 2000
        })
        console.log(res)
      })
    },
    handleCloseCommand() {
      const form = {
        did: this.device.did,
        command: 'LEDOFF',
        deviceName: this.device.deviceName,
        topic: '/topics/led/sub'
      }
      DeviceControll(form).then((res) => {
        this.$notify({
          title: '设置完毕',
          message: res.msg,
          type: res.type,
          duration: 2000
        })
        console.log(res)
      })
    }
  }
}
</script>
