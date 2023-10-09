<template>
  <div class="app-container">
    <el-table
      :data="tableData"
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

      <el-table-column label="名称" width="200">
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
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleEdit(scope.row.did)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row.did)"
            >删除</el-button
          >
          <el-button
            size="mini"
            type="success"
            @click="handleExpand(scope.row.did)"
            >详细</el-button
          >
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
        :form="this.form"
        @changeShow="cancelShow"
        @editTable="onSubmit"
      />
    </el-dialog>
  </div>
</template>

<script>
import {
  deleteDevice,
  getDevice,
  getDeviceById,
  editDevice,
} from "@/api/device.js";

import tableTemplate from "./components/tableTemplate.vue";

export default {
  components: {
    tableTemplate,
  },
  data() {
    return {
      total: 1,
      pageSize: 1,
      // 默认不显示分页
      isShow: false,
      tableData: [],
      editShow: false,
      editStatus: false,
      form: {
        deviceName: "",
        longitude: 0.0,
        latitude: 0.0,
        status: "off",
        did: 1,
      },
    };
  },
  created: function () {
    getDevice().then((response) => {
      this.tableData = response.data.items.devices;
      this.$store.dispatch("page/setdevices", this.tableData).then(() => {
        console.log("devices init");
      });
      // 总记录数
      this.total = response.data.total;
      // 每页显示的条数
      this.pageSize = response.data.size;
      // 网络请求成功后，显示分页
      this.isShow = true;
    });
  },

  methods: {
    handleEdit(id) {
      this.editShow = true;
      const did = this.tableData.findIndex((item) => item.did === id);
      this.form = this.tableData[did];

      // this.$router.push("/device/edit/" + id);
    },
    handleExpand(id) {
      this.$router.push("/device/more/" + id);
    },
    handleDelete(did) {
      console.log(did);
      deleteDevice(did).then((response) => {
        // remove the target element
        this.tableData.splice(
          this.tableData.findIndex((item) => item.did === did),
          1
        );

        // judge
        --this.total;
        if (this.tableData.length == 0) {
          getDevice(this.total / 10).then((response) => {
            this.tableData = response.data.items.devices;
          });
        }

        this.$notify({
          title: "Success",
          message: "Delete Successfully",
          type: "success",
          duration: 2000,
        });
      });
    },
    changePage(pageNum) {
      this.$store.dispatch("page/setnum", pageNum).then(() => {
        getDevice(pageNum).then((response) => {
          this.tableData = response.data.items.devices;
          this.$store.dispatch("page/setdevices", this.tableData).then(() => {
            console.log("device data update");
          });
        });
      });
    },

    onSubmit() {
      console.log("edit");
      editDevice(this.form).then((response) => {
        this.editShow = false;
        this.tableData.splice(
          this.tableData.findIndex((item) => item.did === this.form.did),
          1,
          this.form
        );
        this.$notify({
          title: "Success",
          message: "edit Successfully",
          type: "success",
          duration: 2000,
        });
      });
    },
    cancelShow() {
      this.editShow = false;
    },
  },
};
</script>
