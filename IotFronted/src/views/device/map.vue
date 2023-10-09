<template>
  <div>
    <el-button size="mini" type="success" @click="showMarker">显示位置</el-button>
    <el-button size="mini" type="success" @click="removeMarker">删除位置</el-button>
    <div id="container" style="height: 800px; width: 100%" />
  </div>
</template>

<script>
import AMapLoader from '@amap/amap-jsapi-loader'

import { getDevice } from '@/api/device.js'

export default {
  name: 'DeviceLocation',
  data() {
    return {
      devices: [],
      markerList: []
    }
  },
  mounted() {
    // this.getCurrentDevice()
    this.initAMap()
  },
  methods: {
    initAMap() {
      AMapLoader.load({
        key: '21effbff713251548c41dc216cbe8b38', // 设置您的key
        version: '2.0',
        plugin: [
          'AMap.Autocomplete',
          'AMap.PlaceSearch',
          'AMap.Scale',
          'AMap.OverView',
          'AMap.ToolBar',
          'AMap.MapType',
          'AMap.PolyEditor',
          'AMap.CircleEditor'
        ],
        AMapUI: {
          version: '1.1'
        },
        Loca: {
          version: '2.0'
        }
      })
        .then((AMap) => {
          this.AMap = AMap
          this.map = new AMap.Map('container', {
            viewMode: '3D',
            zoom: 5,
            zooms: [2, 22],
            center: [105.602725, 37.076636] // 初始化地图中心点位置
          })
          // this.getCurrentDevice()
        })
        .catch((e) => {
          console.log(e)
        })
      console.log('map init')
    },
    initMarker() {
      this.devices = this.$store.getters.devices
      // console.log('map devices infor', this.devices)
      for (const device of this.devices) {
        const marker = new this.AMap.Marker({
          position: new this.AMap.LngLat(Number(device.longitude), Number(device.latitude)),
          title: device.deviceName
        })
        this.markerList.push(marker)
      }
      console.log(this.markerList)
    },
    showMarker() {
      this.initMarker()
      this.map.add(this.markerList)
    },
    removeMarker() {
      this.map.remove(this.markerList)
      this.markerList.splice(0, this.markerList.length)
    },
    getCurrentDevice() {
      getDevice().then((res) => {
        this.devices = res.data.items.devices
        this.$store.dispatch('page/setdevices', this.devices).then(() => {
          console.log('devices init')
        })
      })
    }
  }
}
</script>
