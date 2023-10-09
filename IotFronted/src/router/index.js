import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [{
  path: '/login',
  component: () =>
    import ('@/views/login/index'),
  hidden: true
},

{
  path: '/404',
  component: () =>
    import ('@/views/404'),
  hidden: true
},

{
  path: '/',
  component: Layout,
  redirect: '/dashboard',
  children: [{
    path: 'dashboard',
    name: 'Dashboard',
    component: () =>
      import ('@/views/dashboard/index'),
    meta: { title: '首页', icon: 'dashboard' }
  }]
},
{
  path: '/device',
  component: Layout,
  redirect: '/device/list',
  name: 'Example',
  meta: { title: '设备管理', icon: 'el-icon-s-help' },
  children: [{
    path: 'list',
    name: 'list',
    component: () =>
      import ('@/views/device/index'),
    meta: { title: '设备列表', icon: 'table' }
  },
  {
    path: 'add',
    name: 'add',
    component: () =>
      import ('@/views/device/add'),
    meta: { title: '添加设备', icon: 'tree' }
  },
  // 此路由用于编辑品牌信息，:id用于接收需要被修改的品牌id
  {
    // /device/edit/123
    path: 'edit/:id',
    name: 'edit',
    hidden: true,
    component: () =>
      import ('@/views/device/add'),
    meta: { title: '编辑设备', icon: 'tree' }
  },
  {
    // /device/edit/123
    path: 'more/:id',
    name: 'more',
    // 此路由不需要在页面上显示
    hidden: true,
    component: () =>
      import ('@/views/device/deviceDesc'),
    meta: { title: '详细信息', icon: 'tree' }
  }
  ]
},
{
  path: '/data',
  component: Layout,
  redirect: '/data/detail',
  name: 'data',
  meta: { title: '告警管理', icon: 'el-icon-s-data' },
  children: [
    {
      path: 'detail',
      name: 'detail',
      component: () =>
        import ('@/views/data/info'),
      meta: { title: '告警信息', icon: 'table' }
    },
    {
      path: 'graph/:id',
      name: 'graph',
      hidden: true,
      component: () =>
        import ('@/views/data/graph'),
      meta: { title: '告警温度图', icon: 'el-icon-pie-chart' }
    }
  ]
},
{
  path: '/profile',
  component: Layout,
  redirect: '/profile/index',
  hidden: true,
  children: [
    {
      path: 'index',
      component: () => import('@/views/profile/index'),
      name: 'Profile',
      meta: { title: 'Profile', icon: 'user', noCache: true }
    }
  ]
},
{
  path: '/settings',
  component: Layout,
  redirect: '/settings/index',
  children: [
    {
      path: 'index',
      component: () => import('@/views/settings/index'),
      name: 'Settings',
      meta: { title: '参数设置', icon: 'el-icon-s-tools', noCache: true }
    }
  ]
},

{
  path: '/video',
  component: Layout,
  redirect: '/video/index',
  children: [
    {
      path: 'index',
      component: () => import('@/views/video/index'),
      name: 'Video',
      meta: { title: '实时监控', icon: 'el-icon-video-play', noCache: true }
    }
  ]
},

// 404 page must be placed at the end !!!
{ path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
