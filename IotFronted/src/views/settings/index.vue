<template>
  <el-descriptions
    title="定时任务设置信息"
    direction="vertical"
    :column="3"
    border
    style="width: 50%; margin-top: 1rem"
  >
    <el-descriptions-item label="当前定时时间"
      >{{ this.timer }}s</el-descriptions-item
    >
    <el-descriptions-item label="输入设置值">
      <el-input-number
        v-model="setvalue"
        @change="handleChange"
        :min="1"
        :max="60"
      ></el-input-number>
    </el-descriptions-item>
    <el-descriptions-item label="修改">
      <el-button @click="update" type="success">更新</el-button>
    </el-descriptions-item>
  </el-descriptions>
</template>

<script>
import { get, update} from "@/api/task.js";

export default {
  data() {
    return {
      timer: 10,
      setvalue: 10,
    };
  },
  created() {
    this.getinfo();
  },
  methods: {
    update() {
      const taskparams = {
        timer: this.setvalue,
        corn: ""
      }
      update(taskparams).then((res) => {
        this.timer = this.setvalue;
        this.$notify({
          title: 'Success',
          message: 'Set Successfully',
          type: 'success',
          duration: 2000
        })
      });
    },
    getinfo() {
      get().then((res) => {
        this.corn = res.data.corn;
        this.timer = res.data.timer / 1000;
      });
    },
    handleChange(value) {
      this.setvalue = value;
    },
  },
};
</script>
