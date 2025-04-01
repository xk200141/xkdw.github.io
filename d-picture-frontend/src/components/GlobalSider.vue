<template>
  <div id="globalSider">
    <a-layout-sider class="sider" width="200" breakpoint="lg" collapsed-width="0">
      <a-menu
        mode="inline"
        v-model:selectedKeys="current"
        :items="menuItems"
        @click="doMenuClick"
      />
    </a-layout-sider>
  </div>
</template>

<script lang="ts" setup>
import { PictureOutlined, UserOutlined } from '@ant-design/icons-vue'
import { useLoginUserStore } from '@/stores/user'
import { useRouter } from 'vue-router'
import { ref } from 'vue'

const menuItems = [
  {
    key: '/',
    // icon: () => h(PictureOutlined),
    label: '公共图库',
  },
  {
    key: '/my_space',
    label: '我的空间',
    // icon: () => h(UserOutlined),
  },
]
const loginUserStore = useLoginUserStore()

const router = useRouter()

// 当前选中菜单
const current = ref<string[]>([])
// 监听路由变化，更新当前选中菜单
router.afterEach((to, from, failure) => {
  current.value = [to.path]
})

// 路由跳转事件
const doMenuClick = ({ key }: { key: string }) => {
  router.push({
    path: key,
  })
}
</script>

<style scoped>
#globalSider .ant-layout-sider {
  background: none;
}

.title-bar {
  display: flex;
  align-items: center;
}

.title {
  color: black;
  font-size: 18px;
  margin-left: 16px;
}

.logo {
  height: 48px;
}
</style>
