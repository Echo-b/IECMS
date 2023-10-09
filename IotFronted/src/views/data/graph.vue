<template>
  <div>
    <graph-template-vue :id="id1" :title="title" :date="date" :temperature="temperature" />
    <graph-template-vue :id="id2" :title="title2" :date="alertdate" :temperature="alerttemperature" />
    <graph-template-vue :id="id3" :title="title3" :date="averageD" :temperature="averageT" />
  </div>

</template>

<script>
import graphTemplateVue from './components/graphTemplate.vue'
import { getNormalDataById, getAlertDataById, getAverageTemperature } from '@/api/data'

export default {
  name: '',
  components: { graphTemplateVue },
  data() {
    return {
      title2: '告警温度图',
      title: '正常温度图',
      title3: '平均温度图',
      id1: 'chart1',
      id2: 'chart2',
      id3: 'chart3',
      date: [],
      temperature: [],
      alertdate: [],
      alerttemperature: [],
      averageT: [],
      averageD: []
    }
  },
  created() {
    this.getdata()
  },

  methods: {
    getdata() {
      if (this.$route.params.id) {
        const did = this.$route.params.id
        getNormalDataById(did).then((res) => {
          res.data.items.forEach((item) => {
            this.date.push(item.date)
          })
          res.data.items.forEach((item) => {
            this.temperature.push(item.temperature)
          })
        })
        getAlertDataById(did).then((res) => {
          res.data.items.forEach((item) => {
            this.alertdate.push(item.date)
          })
          res.data.items.forEach((item) => {
            this.alerttemperature.push(item.temperature)
          })
        })
        getAverageTemperature(did).then((res) => {
          res.data.items.forEach((item) => {
            this.averageD.push(item.date)
          })
          res.data.items.forEach((item) => {
            this.averageT.push(item.temperature)
          })
        })
      }
    }
  }
}
</script>

