<template>
  <el-table :data="groupmembers" style="width: 100%;padding-top: 15px;">
    <el-table-column label="Uid" min-width="150">
      <template slot-scope="scope">
        {{ scope.row.uid }}
      </template>
    </el-table-column>
    <el-table-column label="UserName" width="150" align="center">
      <template slot-scope="scope">
        {{ scope.row.username }}
      </template>
    </el-table-column>
    <el-table-column label="roles" width="150" align="center">
      <template slot-scope="scope">
        {{ scope.row.roles }}
      </template>
    </el-table-column>
    <el-table-column label="operater" width="150" align="center">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.row.did)"
        >删除</el-button>
        <el-button
          size="mini"
          type="success"
          @click="handleExpand(scope.row.did)"
        >详细</el-button>
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
        this.groupmembers = response.data.groups.slice(0, 8)
      })
    },
    handleExpand(id) {
      this.$router.push('/user/detail/' + id)
    },
    handleDelete(did) {
      console.log(did)
    }
  }
}
</script>
