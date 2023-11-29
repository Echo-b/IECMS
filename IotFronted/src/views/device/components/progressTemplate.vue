<template>
  <div>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="date" label="申请日期" width="180" />
      <el-table-column prop="name" label="申请人" width="180" />
      <el-table-column prop="dname" label="设备名称" width="180" />
      <el-table-column prop="progress" label="审核进度" width="380">
        <template slot-scope="scope">
          <!-- 在这里添加 el-steps 组件 -->
          <el-steps :active="scope.row.progress" finish-status="success" space="400px">
            <el-step title="提交申请" />
            <el-step title="审核中" />
            <el-step title="添加成功" />
          </el-steps>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="400">
        <template slot-scope="scope">
          <el-button type="primary" @click="showDetail">详情</el-button>
          <el-button type="success" @click="handleButton2(scope.row)">撤销</el-button>
          <el-button type="danger" @click="handleButton3(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :visible="dialogVisible" title="详情" @close="closeDetailDialog">
        <el-descriptions :bordered="true" size="small" column="1">
          <el-descriptions-item label="申请日期">{{ currentRow.date }}</el-descriptions-item>
          <el-descriptions-item label="申请人">{{ currentRow.name }}</el-descriptions-item>
          <el-descriptions-item label="设备名称">{{ currentRow.dname }}</el-descriptions-item>
          <el-descriptions-item label="审核进度">{{ currentRow.progress }}</el-descriptions-item>
        </el-descriptions>
      </el-dialog>
  </div>
</template>

<script>
export default {
  methods: {
    showDetail() {
      this.dialogVisible = true;
      this.currentRow = row;
    },
    closeDetailDialog() {
      // 关闭对话框时，重置当前行数据
      this.dialogVisible = false;
      this.currentRow = {};
    },
  },
  data() {
    return {
      tableData: [
        {
          date: '2016-05-02',
          name: '王小虎',
          dname: '测试机1',
          progress: 0 // 添加 progress 属性
        },
        {
          date: '2016-05-04',
          name: '王小虎',
          dname: '测试机2',
          progress: 1 // 添加 progress 属性
        },
        {
          date: '2016-05-01',
          name: '王小虎',
          dname: '测试机3',
          progress: 2 // 添加 progress 属性
        },
        {
          date: '2016-05-03',
          name: '王小虎',
          dname: '测试机4',
          progress: 0 // 添加 progress 属性
        }
      ],
      dialogVisible: false,
      currentRow: {}
    }
  }
}
</script>
