<template>
  <div class="block">
    <el-timeline>
      <el-timeline-item v-for="(item,index) of timeline" :key="index" :timestamp="item.date" placement="top" type="info">
        <el-card>
          <h4>{{ item.topic }}</h4>
          <p>{{ item.operator }}  {{ item.command }} {{ item.deviceName }} </p>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
import { getRecords } from '@/api/record.js'

export default {
  data() {
    return {
      timeline: []
    }
  },
  created() {
    this.getRecordsData()
  },
  methods: {
    getRecordsData() {
      getRecords(this.$store.getters.name).then(response => {
        this.timeline = response.data.records
      })
    }
  }
}
</script>
