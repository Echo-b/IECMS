<template>
  <div class="dashboard-editor-container">
    <github-corner class="github-corner" />

    <panel-group @handleSetLineChartData="handleSetLineChartData" />

    <el-row style="background:#fff;padding:16px 16px 0;">
      <line-chart :chart-data="lineChartData" />
    </el-row>

    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <raddar-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <bar-chart />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import PanelGroup from './components/PanelGroup'
import LineChart from './components/LineChart'
import RaddarChart from './components/RaddarChart'
import PieChart from './components/PieChart'
import BarChart from './components/BarChart'
import GithubCorner from '@/components/GithubCorner'

import { getChartData } from '@/api/data.js'

export default {
  name: 'DashboardAdmin',
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart,
    GithubCorner
  },
  data() {
    return {
      lineChartData: {
        expectedData: [],
        actualData: []
      },
      ChartData: {
        groupdatas: {
          expectedData: [],
          actualData: []
        },
        normaldatas: {
          expectedData: [],
          actualData: []
        },
        devicedatas: {
          expectedData: [],
          actualData: []
        },
        warningdatas: {
          expectedData: [],
          actualData: []
        }
      }
    }
  },
  created: function() {
    this.fetchChartData()
  },
  methods: {
    handleSetLineChartData(type) {
      this.lineChartData = this.chartData[type]
    },
    fetchChartData() {
      getChartData(this.$store.getters.name).then((res) => {
        console.log(res)
        this.chartData = res.data.datas
        this.lineChartData = this.chartData.groupdatas
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .github-corner {
    position: absolute;
    top: 0px;
    border: 0;
    right: 0;
  }

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
