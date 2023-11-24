const getDefaultState = () => {
  return {
    pagenum: 1,
    devices: [],
    curdevices: []
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_PAGENUM: (state, pagenum) => {
    state.pagenum = pagenum
  },
  SET_DEVICE: (state, devices) => {
    state.devices = devices
  },
  SET_CURDEVICE: (state, curdevices) => {
    state.curdevices = curdevices
  }
}

const actions = {
  // remove token
  setnum({ commit }, pagenum) {
    return new Promise(resolve => {
      commit('SET_PAGENUM', pagenum)
      resolve()
    })
  },
  setdevices({ commit }, devices) {
    return new Promise(resolve => {
      commit('SET_DEVICE', devices)
      resolve()
    })
  },
  setcurdevices({ commit }, curdevices) {
    return new Promise(resolve => {
      commit('SET_CURDEVICE', curdevices)
      resolve()
    })
  },
  reset({ commit }) {
    return new Promise(resolve => {
      commit('RESET_STATE')
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

