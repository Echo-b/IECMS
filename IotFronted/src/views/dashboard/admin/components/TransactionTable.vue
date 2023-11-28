<template>
  <el-table :data="groupmembers" style="width: 100%;padding-top: 15px;">
    <el-table-column label="Uid" min-width="200">
      <template slot-scope="scope">
        {{ scope.row.uid }}
      </template>
    </el-table-column>
    <el-table-column label="UserName" width="195" align="center">
      <template slot-scope="scope">
        {{ scope.row.username }}
      </template>
    </el-table-column>
    <el-table-column label="roles" width="100" align="center">
      <template slot-scope="scope">
        {{ scope.row.roles }}
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { getAllGroupUser } from '@/api/user.js'

export default {
  data() {
    return {
      groupmembers: []
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      getAllGroupUser(this.$store.getters.groupid).then(response => {
        this.groupmembers = response.data.groups
      })
    }
  }
}
</script>
