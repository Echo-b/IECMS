import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    uid: '',
    name: '',
    avatar: '',
    roles: '',
    groupid: 0,
    email: ''
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_GROUP_ID: (state, groupid) => {
    state.groupid = groupid
  },
  SET_EMAIL: (state, email) => {
    state.email = email
  },
  SET_UID: (state, uid) => {
    state.uid = uid
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password_hash } = userInfo
    return new Promise((resolve, reject) => {
      // 调用login方法发送网络请求
      login({ username: username.trim(), password_hash: password_hash }).then(response => {
        const { data } = response
        // vuex
        commit('SET_TOKEN', data.token)
        console.log(data.token)
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response

        if (!data) {
          return reject('Verification failed, please Login again.')
        }

        console.log(data)

        const { uid, name, avatar, roles, groupid, email } = data

        commit('SET_UID', uid)
        commit('SET_NAME', name)
        commit('SET_AVATAR', avatar)
        commit('SET_ROLES', roles)
        commit('SET_GROUP_ID', groupid)
        commit('SET_EMAIL', email)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  },

  // set avatar
  setAvatar({ commit }, avatar) {
    return new Promise((resolve) => {
      commit('SET_AVATAR', avatar)
      resolve()
    })
  },

  setEmail({ commit }, email) {
    return new Promise((resolve) => {
      commit('SET_EMAIL', email)
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

