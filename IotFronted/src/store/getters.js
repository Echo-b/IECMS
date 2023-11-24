const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  pagenum: state => state.page.pagenum,
  devices: state => state.page.devices,
  roles: state => state.user.roles,
  curdevices: state => state.page.curdevices
}
export default getters
