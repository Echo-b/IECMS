<template>
  <div class="app-container">
    <el-table
      :data="tableData"
      border
      fit
      highlight-current-row
      style="width: 100%"
    >
      <el-table-column label="DNO" width="180">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span style="margin-left: 10px">{{ scope.row.dno }}</span>
        </template>
      </el-table-column>

      <el-table-column label="温度" width="180">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium" type="warning" effect="dark">{{ scope.row.temperature }}</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column label="发送者" width="200">
        <template slot-scope="scope">
          <i class="el-icon-s-promotion" />
          <span style="margin-left: 10px">{{ scope.row.sender }}</span>
        </template>
      </el-table-column>

      <el-table-column label="日期" width="200">
        <template slot-scope="scope">
          <i class="el-icon-date" />
          <span style="margin-left: 10px">{{ scope.row.date }}</span>
        </template>
      </el-table-column>

      <el-table-column label="设备ID" width="180">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span style="margin-left: 10px">{{ scope.row.did }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" width="200">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row.dno)"
          >删除</el-button>
          <el-button
            size="mini"
            type="success"
            @click="handleExpand(scope.row.did)"
          >查看数据图</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      v-if="isShow"
      background
      layout="prev, pager, next"
      :total="total"
      :page-size="pageSize"
      @current-change="changePage"
    />
  </div>
</template>

<script>
import { getAlertData, deleteAlertlData } from '@/api/data.js'

export default {
  data() {
    return {
      total: 1,
      pageSize: 1,
      // 默认不显示分页
      isShow: false,
      tableData: [],
      form: {
        dno: 0,
        temperature: 0.0,
        sender: '',
        date: '',
        did: 1
      }
    }
  },
  created: function() {
    getAlertData().then((response) => {
      this.tableData = response.data.items
      // 总记录数
      this.total = response.data.total
      // 每页显示的条数
      this.pageSize = response.data.size
      // 网络请求成功后，显示分页
      this.isShow = true
    })
  },

  methods: {
    handleDelete(dno) {
      console.log(dno)
      deleteAlertlData(dno).then((response) => {
        // remove the target element
        this.tableData.splice(
          this.tableData.findIndex((item) => item.dno === dno),
          1
        )

        // judge
        --this.total
        if (this.tableData.length == 0) {
          getAlertData(this.total / 10).then((response) => {
            this.tableData = response.data.items
          })
        }

        this.$notify({
          title: 'Success',
          message: 'Delete Successfully',
          type: 'success',
          duration: 2000
        })
      })
    },
    changePage(pageNum) {
      getAlertData(pageNum).then((response) => {
        this.tableData = response.data.items
      })
    },
    handleExpand(id) {
      this.$router.push('/data/graph/' + id)
    }
  }
}
</script>
