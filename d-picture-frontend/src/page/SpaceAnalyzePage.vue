<template>
  <div id="spaceAnalyzePage">
    <h2>
      空间图库分析 -
      <span v-if="queryAll"> 全部空间 </span>
      <span v-else-if="queryPublic"> 公共图库 </span>
      <span v-else>
        <a :href="`/space/${spaceId}`" target="_blank">id：{{ spaceId }}</a>
      </span>
    </h2>

    <a-row :gutter="[16, 16]">
      <!-- 空间使用分析 -->
      <a-col :xs="24" :md="12">
        <SpaceUsageAnalyz :spaceId="spaceId" :queryAll="queryAll" :queryPublic="queryPublic" />
      </a-col>
      <!-- 空间分类分析 -->
      <a-col :xs="24" :md="12">
        <SpaceCatageAnalyze :spaceId="spaceId" :queryAll="queryAll" :queryPublic="queryPublic" />
      </a-col>
      <!-- 标签分析 -->
      <a-col :xs="24" :md="12">
        <SpaceTagAnalyz :spaceId="spaceId" :queryAll="queryAll" :queryPublic="queryPublic" />
      </a-col>
      <!-- 图片大小分段分析 -->
      <a-col :xs="24" :md="12">
        <SpaceSizeAnalyz :spaceId="spaceId" :queryAll="queryAll" :queryPublic="queryPublic" />
      </a-col>
      <!-- 用户上传行为分析 -->
      <a-col :xs="24" :md="12">
        <SpaceUserAnalyz :spaceId="spaceId" :queryAll="queryAll" :queryPublic="queryPublic" />
      </a-col>
      <!-- 空间使用排行分析 -->
      <a-col :xs="24" :md="12">
        <SpaceRankAnalyz
          v-if="isAdmin"
          :spaceId="spaceId"
          :queryAll="queryAll"
          :queryPublic="queryPublic"
        />
      </a-col>
    </a-row>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useLoginUserStore } from '@/stores/user'
import { useRoute } from 'vue-router'
import SpaceCatageAnalyze from '@/components/analyze/SpaceCatageAnalyze.vue'
import SpaceTagAnalyz from '@/components/analyze/space-tag-analyz.vue'
import SpaceUserAnalyz from '@/components/analyze/space-user-analyz.vue'
import SpaceUsageAnalyz from '@/components/analyze/space-usage-analyz.vue'
import SpaceSizeAnalyz from '@/components/analyze/space-size-analyz.vue'
import SpaceRankAnalyz from '@/components/analyze/space-rank-analyz.vue'

const route = useRoute()

// 空间 id
const spaceId = computed(() => {
  return route.query?.spaceId as string
})

// 是否查询所有空间
const queryAll = computed(() => {
  return !!route.query?.queryAll
})

// 是否查询公共空间
const queryPublic = computed(() => {
  return !!route.query?.queryPublic
})

const loginUserStore = useLoginUserStore()
const loginUser = loginUserStore.loginUser

const isAdmin = computed(() => {
  return loginUser.userRole === 'admin'
})
</script>

<style scoped></style>
