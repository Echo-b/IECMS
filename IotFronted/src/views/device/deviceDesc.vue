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

        <el-dropdown style="margin-right:1rem" size="small" @command="handleOpenCommand">
          <el-button
            size="mini"
            type="primary"
          >打开<i class="el-icon-arrow-down el-icon--right" /></el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="opennow">立即打开</el-dropdown-item>
            <el-dropdown-item command="delayopen">延时打开</el-dropdown-item>

          </el-dropdown-menu>
        </el-dropdown>

        <el-dropdown size="small" @command="handleCloseCommand">
          <el-button
            size="mini"
            type="danger"
          >关闭<i class="el-icon-arrow-down el-icon--right" /></el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="closenow">立即关闭</el-dropdown-item>
            <el-dropdown-item command="delayclose">延时关闭</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

      </el-descriptions-item>
    </el-descriptions>
    <model-vue :did="device.did" />

  </div>

</template>

<script>
import { getDeviceById, DeviceAction, DeviceControll } from '@/api/device'

import modelVue from './model.vue'

export default {
  components: {
    modelVue
  },
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
    handleOpenCommand(c) {
      const form = {
        deviceName: this.device.deviceName,
        did: this.device.did,
        topic: '/topics/led/sub',
        command: 'LEDON'
      }
      DeviceAction(form).then((res) => {
        this.$notify({
          title: '设置完毕',
          message: res.msg,
          type: res.type,
          duration: 2000
        })
        console.log(res)
      })
    },
    handleCloseCommand(c) {
      const form = {
        deviceName: this.device.deviceName,
        did: this.device.did,
        topic: '/topics/led/sub',
        command: 'LEDOFF'
      }
      DeviceAction(form).then((res) => {
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
