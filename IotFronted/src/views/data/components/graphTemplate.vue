<template>
  <div :id="id" :style="{ width: '100%', height: '300px' }" />
</template>

<script>

import * as echarts from 'echarts'

export default {
  name: '',
  props: ['date', 'temperature', 'id','title'],
  data() {
    return {
      chart: null
    }
  },
  watch: {
    date() {
      this.$nextTick(() => {
        if (this.date) { this.initChart() }
      })
    }
  },
  methods: {
    initChart() {
      this.echart = echarts.init(document.getElementById(this.id))
      this.echart.setOption({
        title: { text: this.title },
        xAxis: {
          data: this.date
        },
        tooltip: {
          trigger: 'axis'
        },
        yAxis: {},
        series: [
          {
            data: this.temperature,
            type: 'line',
            smooth: true
          }
        ]
      })
    }
  }
}
</script>

