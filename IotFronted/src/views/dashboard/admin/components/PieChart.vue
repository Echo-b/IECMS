<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

import { getPieData } from '@/api/data.js'

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    }
  },
  data() {
    return {
      chart: null,
      legend: [],
      datavalue: [],
      piedata: [
        { value: 0, name: 'Mon' },
        { value: 0, name: 'Tue' },
        { value: 0, name: 'Wed' },
        { value: 0, name: 'Thu' },
        { value: 0, name: 'Fri' },
        { value: 0, name: 'Sat' },
        { value: 0, name: 'Sun' }
      ]
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')
      console.log(this.piedata)
      getPieData().then((res) => {
        console.log(res)
        this.legend = res.data.chart.legend.data
        this.datavalue = res.data.chart.series.data
        for (let i = 0; i < this.piedata.length; ++i) {
          this.piedata[i].value = this.datavalue[i]
        }
        this.chart.setOption({
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          legend: {
            left: 'center',
            bottom: '10',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          },
          series: [
            {
              name: 'WEEKLY WRITE ARTICLES',
              type: 'pie',
              roseType: 'radius',
              radius: [15, 95],
              center: ['50%', '38%'],
              data: this.piedata,
              animationEasing: 'cubicInOut',
              animationDuration: 2600
            }
          ]
        })
      })
    }
  }
}
</script>
