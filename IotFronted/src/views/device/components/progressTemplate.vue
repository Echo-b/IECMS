<template>
  <el-table :data="tableData" stripe style="width: 100%">
    <el-table-column prop="date" label="申请日期" width="180"></el-table-column>
    <el-table-column prop="name" label="申请人" width="180"></el-table-column>
    <el-table-column prop="dname" label="设备名称" width="180"></el-table-column>
    <el-table-column prop="progress" label="审核进度" width="380">
      <template slot-scope="scope">
        <!-- 在这里添加 el-steps 组件 -->
        <el-steps :active="scope.row.progress" finish-status="success" space="400px">
          <el-step title="提交申请"></el-step>
          <el-step title="审核中"></el-step>
          <el-step title="添加成功"></el-step>
        </el-steps>
      </template>
    </el-table-column>
    <el-table-column label="操作" width="400">
      <template slot-scope="scope">
        <el-button type="primary" @click="handleButton1(scope.row)">详情</el-button>
        <el-button type="success" @click="handleButton2(scope.row)">撤销</el-button>
        <el-button type="danger" @click="handleButton3(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  data() {
    return {
      tableData: [
        {
          date: '2016-05-02',
          name: '王小虎',
          dname: '测试机1',
          progress: 0, // 添加 progress 属性
        },
        {
          date: '2016-05-04',
          name: '王小虎',
          dname: '测试机2',
          progress: 1, // 添加 progress 属性
        },
        {
          date: '2016-05-01',
          name: '王小虎',
          dname: '测试机3',
          progress: 2, // 添加 progress 属性
        },
        {
          date: '2016-05-03',
          name: '王小虎',
          dname: '测试机4',
          progress: 0, // 添加 progress 属性
        },
      ],
    };
  },
};
</script>
