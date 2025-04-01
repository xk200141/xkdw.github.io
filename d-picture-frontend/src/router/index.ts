import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import HomePage from '@/page/HomePage.vue'
import UserLoginUser from '@/page/user/UserLoginUser.vue'
import UserRegisterPage from '@/page/user/UserRegisterPage.vue'
import AddPicturePage from '@/page/AddPicturePage.vue'
import UserManagePage from '@/page/admin/UserManagePage.vue'
import PictureManagePage from '@/page/admin/PictureManagePage.vue'
import PictureDetailPage from '@/page/PictureDetailPage.vue'
import AddPictureBatchPage from '@/page/addPictureBatchPage.vue'
import SpaceManagePage from '@/page/admin/SpaceManagePage.vue'
import AddSpacePage from '@/page/AddSpacePage.vue'
import MySpacePage from '@/page/MySpacePage.vue'
import SpaceDetailPage from '@/page/SpaceDetailPage.vue'
import SearchPicturePage from '@/page/SearchPicturePage.vue'
import SpaceAnalyzePage from '@/page/SpaceAnalyzePage.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomePage,
    },
    {
      path: '/user/login',
      name: '用户登录',
      component: UserLoginUser,
    },
    {
      path: '/user/register',
      name: '用户注册',
      component: UserRegisterPage,
    },
    {
      path: '/admin/userManage',
      name: '用户管理',
      component: UserManagePage,
    },
    {
      path: '/add_picture',
      name: '创建图片',
      component: AddPicturePage,
    },
    {
      path: '/add_space',
      name: '创建空间',
      component: AddSpacePage,
    },


    {
      path: '/add_picture/batch',
      name: '批量创建图片',
      component: AddPictureBatchPage,
    },
    {
      path: '/admin/pictureManage',
      name: '图片管理',
      component: PictureManagePage,
    },
    {
      path: '/admin/spaceManage',
      name: '空间管理',
      component: SpaceManagePage,
    },
    {
      path: '/my_space',
      name: '我的空间',
      component: MySpacePage,
    },
    {
      path: '/space/:id',
      name: '空间详情',
      component: SpaceDetailPage,
      props: true,
    },

    {
      path: '/picture/:id',
      name: '图片详情',
      component: PictureDetailPage,
      props: true,
    },
    {
      path: '/search_picture',
      name: '图片搜索',
      component: SearchPicturePage,
    }
,
    {
      path: '/space_analyze',
      name: '空间分析',
      component: SpaceAnalyzePage,
    },



    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue'),
    },
  ],
})

export default router
