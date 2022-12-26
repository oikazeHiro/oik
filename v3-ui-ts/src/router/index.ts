import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import {useMeanStore} from '@/store/menus'
import {getMenus} from '@/api/request/login'

const routes: Array<RouteRecordRaw> = [
  // {
  //   path: '/',
  //   name: 'home',
  //   component: () =>
  //     import(/* webpackChunkName: "home" */ '@/views/HomeView.vue'),
  //   children: [
  //     {
  //       path: '/default',
  //       name: 'default',
  //       meta: {
  //         title: '首页',
  //       },
  //       component: () =>
  //         import(
  //           /* webpackChunkName: "dashboard" */ '@/views/home/DefaultView.vue'
  //         ),
  //     },
  //   ],
  // },
  {
    path: '/login',
    name: 'login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
        import(/* webpackChunkName: "login" */ '@/views/LoginView.vue'),
  },
]

const router = createRouter({
  //   history: createWebHistory(process.env.BASE_URL),
  history: createWebHistory(),
  routes,
})

// @router/routers.ts 中添加前置路由守卫
router.beforeEach(async (to, from, next) => {
  const token = localStorage.getItem('token')
  // 注意：在beforeEach中调用pinia存储的菜单状态是为了避免` Did you forget to install pinia?`这个bug
  const useMean = useMeanStore()
  console.log('hasRoute', useMean.hasRoute)
  if (to.path == '/login') {
    next()
  } else if (!token) {
    next({path: '/login'})
  } else if (!useMean.hasRoute) {
    const res = await getMenus()
    useMean.setMenuList(res.data)
    const home: any = {
      path: '/',
      name: 'home',
      component: () =>
          import(/* webpackChunkName: "home" */ '@/views/HomeView.vue'),
      children: [

      ],
    }
    useMean.menuList.forEach((e) => {
      if (e.children) {
        e.children.forEach((e: any) => {
          if (!e.component) {
            return
          }
          const route: any = {
            name: e.menuName,
            path: e.path,
            meta: {
              icon: e.icon,
              title: e.menuName,
            },
            component: () => import('@/views/' + e.component + '.vue'),
          }
          home.children.push(route)
        })
      }
    })
    router.addRoute(home)
    useMean.changeRouteStatus(true)
    console.log(useMean.menuList)
    next({path: to.path})
  } else {
    next()
  }
})

export default router
