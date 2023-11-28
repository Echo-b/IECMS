<template>
  <div>
    <el-form>
      <el-form-item label="Name">
        <el-input v-model.trim="user.name" />
      </el-form-item>
      <el-form-item label="Email">
        <el-input v-model.trim="user.email" />
      </el-form-item>
      <el-form-item label="Avatar">
        <el-input v-model.trim="avatarUrl" />
      </el-form-item>
      <el-form-item>
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8080/file/upload"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img v-if="require('@/assets/images/' + avatarUrl)" :src="require('@/assets/images/' + avatarUrl)" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon" />
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateUserInfo()">Update</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>

<script>
// import 'babel-polyfill'
import { updateUserInfo } from '@/api/user.js'
export default {
  inject: ['reload'],
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          name: '',
          email: '',
          avatar: '',
          uid: ''
        }
      }
    }
  },
  data() {
    return {
      avatarUrl: this.user.avatar
    }
  },
  methods: {
    updateUserInfo() {
      const u = {
        uid: this.user.uid,
        username: this.user.name,
        avatar: this.avatarUrl,
        email: this.user.email
      }
      updateUserInfo(u).then((res) => {
        if (res.success) {
          this.user.avatar = this.avatarUrl
          this.$store.dispatch('user/setAvatar', this.user.avatar).then(() => {
            console.log(this.user.avatar)
          })
          this.$store.dispatch('user/setEmail', this.user.email)
          this.$message({
            message: res.msg,
            type: res.type,
            duration: 2 * 1000
          })
        } else {
          this.$message({
            message: res.msg,
            type: 'warning',
            duration: 2 * 1000
          })
        }
      })
    },
    handleAvatarSuccess(res, file) {
      console.log(res)
      this.avatarUrl = res.data.filename
      if (res.success) {
        // this.reload()
        this.$message({
          message: res.msg,
          type: res.type,
          duration: 2 * 1000
        })
      }
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  }
}
</script>
