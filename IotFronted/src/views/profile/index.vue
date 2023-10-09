<template>
  <div>
    <el-card style="margin-bottom: 20px" class="user-card">
      <div slot="header" class="clearfix">
        <span>About me</span>
      </div>

      <div class="user-profile">
        <div class="box-center">
          <pan-thumb
            :image="user.avatar"
            :height="'100px'"
            :width="'100px'"
            :hoverable="false"
          >
            <div>Hello</div>
            {{ user.role }}
          </pan-thumb>
        </div>
        <div class="box-center">
          <div class="user-name text-center">{{ user.name }}</div>
        </div>
      </div>

      <el-button
        style="margin-left: 43%"
        type="text"
        @click="dialogFormVisible = true"
      >修改密码</el-button>
    </el-card>

    <el-dialog title="修改密码" :visible.sync="dialogFormVisible" width="30%">
      <el-form ref="upform" :model="upform" :rules="rules">
        <el-form-item label="旧密码" label-width="10" prop="oldpassword">
          <el-input
            v-model="upform.oldpassword"
            autocomplete="off"
            placeholder="请输入旧密码"
            show-password
          />
        </el-form-item>
        <el-form-item label="新密码" label-width="10" prop="newpassword">
          <el-input
            v-model="upform.newpassword"
            autocomplete="off"
            placeholder="请输入新密码"
            show-password
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="updatePassword"
        >确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import PanThumb from '@/components/PanThumb'
import { mapGetters } from 'vuex'
import { updatePassoword } from '@/api/user'

export default {
  components: { PanThumb },
  computed: {
    ...mapGetters(['name', 'avatar', 'roles'])
  },
  data() {
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('The password can not be less than 6 digits'))
      } else {
        callback()
      }
    }
    return {
      dialogFormVisible: false,
      user: {},
      upform: {
        oldpassword: '',
        newpassword: ''
      },
      rules: {
        oldpassword: [{ required: true, trigger: 'blur', validator: validatePassword }],
        newpassword: [{ required: true, trigger: 'blur', validator: validatePassword }]
      }
    }
  },
  created() {
    this.getUser()
  },
  methods: {
    updatePassword() {
      console.log(this.$refs)
      this.$refs.upform.validate(valid => {
        if (valid) {
          const form = {
            username: this.name,
            oldPassword: this.upform.oldpassword,
            newPassword: this.upform.newpassword
          }
          updatePassoword(form).then((res) => {
            console.log(res)
            this.dialogFormVisible = false
            this.$notify({
              title: '修改密码',
              message: res.msg,
              type: res.type,
              duration: 2000
            })
          })
        } else {
          return false
        }
      })
    },
    getUser() {
      this.user = {
        name: this.name,
        role: 'admin',
        email: 'admin@test.com',
        avatar: this.avatar
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.user-card {
  width: 480px;
}
.box-center {
  margin: 0 auto;
  display: table;
}

.text-muted {
  color: #777;
}

.user-profile {
  .user-name {
    font-weight: bold;
  }

  .box-center {
    padding-top: 10px;
  }

  .user-role {
    padding-top: 10px;
    font-weight: 400;
    font-size: 14px;
  }

  .box-social {
    padding-top: 30px;

    .el-table {
      border-top: 1px solid #dfe6ec;
    }
  }

  .user-follow {
    padding-top: 20px;
  }
}

.user-bio {
  margin-top: 20px;
  color: #606266;

  span {
    padding-left: 4px;
  }

  .user-bio-section {
    font-size: 14px;
    padding: 15px 0;

    .user-bio-section-header {
      border-bottom: 1px solid #dfe6ec;
      padding-bottom: 10px;
      margin-bottom: 10px;
      font-weight: bold;
    }
  }
}
</style>
