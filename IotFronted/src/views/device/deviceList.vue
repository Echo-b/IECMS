<template>
  <div class="app-container">
    <el-table
      :data="tables"
      border
      fit
      highlight-current-row
      style="width: 100%"
    >
      <el-table-column label="ID" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span style="margin-left: 10px">{{ scope.row.did }}</span>
        </template>
      </el-table-column>

      <el-table-column label="名称" prop="deviceName" width="200">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>设备名称: {{ scope.row.deviceName }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.deviceName }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="300">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"
          />
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleEdit(scope.row.did)"
          >编辑</el-button>
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
    <el-pagination
      v-if="isShow"
      background
      layout="prev, pager, next"
      :total="total"
      :page-size="pageSize"
      @current-change="changePage"
    />
    <el-dialog class="app-container" :visible.sync="editShow">
      <table-template
        :form="form"
        @changeShow="cancelShow"
        @editTable="onSubmit"
      />
    </el-dialog>
  </div>
</template>

<script>
import {
  deleteDevice,
  getAppointPageDevice,
  getAllGroupDevice,
  editDevice
} from '@/api/device.js'

import tableTemplate from './components/tableTemplate.vue'

export default {
  components: {
    tableTemplate
  },
  inject: ['reload'],
  data() {
    return {
      total: 1,
      pageSize: 1,
      // 默认不显示分页
      isShow: false,
      tableData: [],
      allDevices: [],
      editShow: false,
      editStatus: false,
      form: {
        deviceName: '',
        longitude: 0.0,
        latitude: 0.0,
        status: 'off',
        did: 1,
        type: '',
        date: '',
        insert_flag: 0,
        creator: '',
        group_id: 0
      },
      search: ''
    }
  },
  computed: {
    // 模糊搜索
    tables() {
      const search = this.search
      if (search) {
        return this.tableData.filter(data => {
          // some() 方法会依次执行数组的每个元素：如果有一个元素满足条件，则表达式返回true , 剩余的元素不会再执行检测;
          // 如果没有满足条件的元素，则返回false。
          return Object.keys(data).some(key => {
            // indexOf() 返回某个指定的字符在某个字符串中首次出现的位置，如果没有找到就返回-1
            return String(data[key]).toLowerCase().indexOf(search) > -1
          })
        })
      }
      return this.tableData
    }
  },
  created: function() {
    getAllGroupDevice(this.$store.getters.groupid).then((response) => {
      this.allDevices = response.data.items.devices
      this.$store.dispatch('page/setdevices', this.allDevices).then(() => {
        console.log('devices init')
        console.log(this.allDevices)
      })
      // 总记录数
      this.total = response.data.total
      // 每页显示的条数
      this.pageSize = 10

      this.tableData = this.allDevices.slice(0, this.pageSize)

      this.$store.dispatch('page/setcurdevices', this.tableData).then(() => {
        console.log('curdevices init')
      })
      // 网络请求成功后，显示分页
      this.isShow = true
    })
  },
  methods: {
    handleEdit(id) {
      this.editShow = true
      const did = this.tableData.findIndex((item) => item.did === id)
      this.form = this.tableData[did]

      // this.$router.push("/device/edit/" + id);
    },
    handleExpand(id) {
      this.$router.push('/device/more/' + id)
    },
    handleDelete(did) {
      console.log(did)
      deleteDevice(did).then((response) => {
        // remove the target element from cur tabledata
        this.tableData.splice(
          this.tableData.findIndex((item) => item.did === did),
          1
        )
        // remove the target element from all devices
        this.allDevices.splice(
          this.allDevices.findIndex((item) => item.did === did),
          1
        )
        // update total data
        this.$store.dispatch('page/setdevices', this.allDevices).then(() => {
          console.log('devices init')
          console.log(this.allDevices)
        })

        // judge
        --this.total
        if (this.tableData.length === 0) {
          getAppointPageDevice(this.total / 10).then((response) => {
            this.tableData = response.data.items.devices
          })
        }

        this.reload()
        this.$notify({
          title: 'Success',
          message: 'Delete Successfully',
          type: 'success',
          duration: 2000
        })
      })
    },
    changePage(pageNum) {
      this.$store.dispatch('page/setnum', pageNum).then(() => {
        getAppointPageDevice(pageNum).then((response) => {
          this.tableData = response.data.items.devices
          this.$store.dispatch('page/setcurdevices', this.tableData).then(() => {
            console.log('curdevice data update')
          })
        })
      })
    },

    onSubmit() {
      console.log('edit')
      editDevice(this.form).then((response) => {
        this.editShow = false
        this.tableData.splice(
          this.tableData.findIndex((item) => item.did === this.form.did),
          1,
          this.form
        )
        this.$notify({
          title: 'Success',
          message: 'edit Successfully',
          type: 'success',
          duration: 2000
        })
      })
    },
    cancelShow() {
      this.editShow = false
    }
  }
}
</script>
