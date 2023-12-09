<template>
  <div>
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
            type="success"
            @click="showDetail(scope.row)"
          >详细</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :visible="dialogVisible" title="用户详情" @close="closeDetailDialog">
      <el-descriptions :bordered="true" size="medium" border>
        <el-descriptions-item label="用户UID">{{ user.uid }}</el-descriptions-item>
        <el-descriptions-item label="用户名称">{{ user.username }}</el-descriptions-item>
        <el-descriptions-item label="小组ID">{{ user.group_id }}</el-descriptions-item>
        <el-descriptions-item label="邮箱">{{ user.email }}</el-descriptions-item>
        <el-descriptions-item label="角色">{{ user.roles }}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>
  </div>
</template>

<script>
import { getAllGroupUser } from '@/api/user.js'

export default {
  data() {
    return {
      groupmembers: [],
      user: {
        uid: 0,
        username: '',
        group_id: 0,
        email: '',
        roles: '',
        avatar: '',
        password_hash: ''
      },
      dialogVisible: false
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      getAllGroupUser(this.$store.getters.groupid).then(response => {
        console.log(response.data.groups)
        this.groupmembers = response.data.groups.slice(0, 8)
      })
    },
    showDetail(row) {
      console.log(row)
      this.user = row
      this.dialogVisible = true
    },
    closeDetailDialog() {
      // 关闭对话框时，重置当前行数据
      this.dialogVisible = false
    },
    handleDelete(did) {
      console.log(did)
    }
  }
}
</script>
