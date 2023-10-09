<template>
  <div id="device-model" style="width:auto;height:auto">
    <div id="container" />
  </div>
</template>

<script>
import { getNewestNormalData } from '@/api/data.js'

import * as THREE from 'three'
import * as dat from 'dat.gui'
import { OBJLoader, MTLLoader } from 'three-obj-mtl-loader'
const OrbitControls = require('three-orbit-controls')(THREE)

export default {
  props: {
    did: {
      type: Number,
      default: 1
    }
  },
  data() {
    return {
      temperature: 0,
      date: ''
    }
  },

  mounted() {
    this.getinfo()
    this.init()
    this.loadPlant()
    this.animate()
    document.getElementsByTagName('canvas')[0].style.verticalAlign = 'bottom'
  },
  methods: {
    getinfo() {
      getNewestNormalData(this.did).then((res) => {
        this.temperature = res.data.item.temperature
        this.date = res.data.item.date
        this.guiset()
      })
    },
    guiset() {
      const gui = new dat.GUI({ autoPlace: false })
      gui.domElement.style = 'position:absolute;top:10rem;right:10px;'
      const guiContainer = document.getElementById('device-model')
      guiContainer.appendChild(gui.domElement)
      const effectController = {
        temperature: this.temperature,
        date: this.date
      }

      gui.add(effectController, 'temperature').name('设备最近工作温度')
      gui.add(effectController, 'date').name('最近工作日期')
    },

    initScene() {
      this.scene = new THREE.Scene()
      this.scene.background = new THREE.Color(0xaaaaaa)
      const ambientLight = new THREE.AmbientLight(0xcccccc, 0.4)
      this.scene.add(ambientLight)
      var axesHelper = new THREE.AxesHelper(15)
      this.scene.add(axesHelper)
    },
    initCamera() {
      const aspect = window.innerWidth / innerHeight
      this.camera = new THREE.PerspectiveCamera(45, aspect, 1, 1000)
      this.camera.position.set(15, 25, 20)
      this.camera.lookAt(new THREE.Vector3(0, 0, 0))

      const pointLight = new THREE.PointLight(0xffffff, 1, 100)
      pointLight.position.set(0, 50, 0)
      this.scene.add(pointLight)
      this.scene.add(this.camera)
    },
    initRenderer() {
      this.renderer = new THREE.WebGLRenderer({ antialias: true, alpha: true })
      this.renderer.setPixelRatio(window.devicePixelRatio)
      this.renderer.setSize(window.innerWidth, innerHeight)
      this.container = document.getElementById('container')
      this.container.appendChild(this.renderer.domElement)
    },
    // 初始化控制
    initOrbitControls() {
      const controls = new OrbitControls(this.camera, this.renderer.domElement)
      controls.enableDamping = true
      controls.enableZoom = true
      controls.autoRotate = false
      controls.autoRotateSpeed = 3
      controls.enablePan = true
      controls.enableKeys = true
      controls.keyPanSpeed = 7
      controls.keys = {
        LEFT: 37,
        UP: 38,
        RIGHT: 39,
        BOTTOM: 40
      }
      this.controls = controls
    },
    animate() {
      this.renderer.render(this.scene, this.camera)
      this.requestId = requestAnimationFrame(this.animate)
    },
    init() {
      this.initScene()
      this.initCamera()
      this.initRenderer()
      this.initOrbitControls()
    },
    loadPlant() {
      const that = this
      var mtlLoader = new MTLLoader() // mtl材加载器
      mtlLoader.load(`static/model/themoSensor.mtl`, (materials) => {
        materials.preload()
        var objLoader = new OBJLoader()
        objLoader.setMaterials(materials)
        objLoader.load(
          `static/model/themoSensor.obj`,
          (obj) => {
            console.log(obj)
            obj.position.set(0, 0, 0)
            obj.scale.set(0.5, 0.5, 0.5)
            that.scene.add(obj)
          },
          function(xhr) {
            console.log((xhr.loaded / xhr.total) * 100 + '% loaded')
          },
          function(error) {
            console.log('An error happened')
            console.log(error)
          }
        )
      })
    }
  }
}
</script>
<style>
#container {
  width: 100%;
  padding: 10px;
  z-index: 1;
}

body {
  margin: 0;
  font-family: Monospace;
  font-size: 13px;
}
</style>
