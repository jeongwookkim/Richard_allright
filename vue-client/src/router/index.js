// Imports
import Vue from 'vue'
import Router from 'vue-router'
import store from '../store'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  scrollBehavior: (to, from, savedPosition) => {
    if (to.hash) return { selector: to.hash }
    if (savedPosition) return savedPosition
    return { x: 0, y: 0 }
  },

  routes: [
    {
      path: '/',
      component: () => import('@/layouts/home/Index.vue'),
      children: [
        {
          path: '',
          name: 'Home',
          component: () => import('@/views/home/Index.vue'),
        },
        {
          path: 'parkingLot',
          name: 'ParkingLot',
          component: () => import('@/views/parkingLot/Index.vue'),
          meta: { src: require('@/assets/parkingLot.jpg') },
        },
        {
          path: 'contact-us',
          name: 'Contact',
          component: () => import('@/views/contact-us/Index.vue'),
          meta: { src: require('@/assets/contact.jpg') },
        },
        {
          path: 'login',
          name: 'Login',
          component: () => import('@/views/login/Index.vue'),
          meta: { src: require('@/assets/login.jpg') },
        },
        {
          path: 'logout',
          name: 'Logout',
          component: () => import('@/views/logout/Index.vue'),
          meta: { src: require('@/assets/login.jpg') },
        },
        {
          path: 'mypage',
          name: 'Mypage',
          component: () => import('@/views/mypage/Index.vue'),
          meta: { src: require('@/assets/login.jpg') },
        },
        {
          path: 'payment',
          name: 'Payment',
          component: () => import('@/views/payment/Index.vue'),
          meta: { src: require('@/assets/payment.jpg') },
        },
        {
          path: '*',
          name: 'FourOhFour',
          component: () => import('@/views/404/Index.vue'),
        },
      ],
    },
  ],
})

  router.beforeEach((to, from, next) => {
    // 로그인 안하고 접근 불가 페이지로 가려고 한다면
    const authRequiredPages = ['Payment']
    const authRequired = authRequiredPages.includes(to.name)
    const { isLoggedIn } = store.getters

    if (authRequired && !isLoggedIn) {
      // 인증해야 하는데, 로그인 안 했을 때
      next('/')
    } else {
      // 인증해야하는데, 로그인 했을 때
      next()
    }
  })

export default router
