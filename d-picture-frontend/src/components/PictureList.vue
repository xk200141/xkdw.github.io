<template>
  <div class="picture-list">
    <!-- 图片列表 -->
    <ShareModal ref="shareModalRef" :link="shareLink" />

    <a-list
      :grid="{ gutter: 16, xs: 1, sm: 2, md: 3, lg: 4, xl: 5, xxl: 6 }"
      :data-source="dataList"
      :loading="loading"
    >
      <template #renderItem="{ item: picture }">
        <a-list-item style="padding: 0">
          <!-- 单张图片 -->
          <a-card hoverable @click="doClickPicture(picture)">
            <template #cover>
              <img
                style="height: 180px; object-fit: cover"
                :alt="picture.name"
                :src="picture.thumbnailUrl ?? picture.url"
                loading="lazy"
              />
            </template>
            <a-card-meta :title="picture.name">
              <template #description>
                <a-flex>
                  <a-tag color="green">
                    {{ picture.category ?? '默认' }}
                  </a-tag>
                  <a-tag v-for="tag in picture.tags" :key="tag">
                    {{ tag }}
                  </a-tag>
                </a-flex>
              </template>
            </a-card-meta>
            <template v-if="showOp" #actions>

              <a-tooltip placement="topLeft">
                <template #title>
                  <span>搜索</span>
                </template>
                <search-outlined @click="(e) => doSearch(picture, e)" />
              </a-tooltip>
              <a-tooltip placement="topLeft">
                <template #title>
                  <span>分享</span>
                </template>
              <share-alt-outlined @click="(e) => doShare(picture, e)" />
              </a-tooltip><a-tooltip placement="topLeft">
                <template #title>
                  <span>编辑</span>
                </template>
              <edit-outlined @click="(e) => doEdit(picture, e)" />
              </a-tooltip>



              <delete-outlined @click="(e) => doDelete(picture, e)" />
            </template>

          </a-card>
        </a-list-item>
      </template>
    </a-list>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import { EditOutlined, DeleteOutlined,SearchOutlined ,ShareAltOutlined} from '@ant-design/icons-vue'
import { deletePictureUsingPost } from '@/api/pictureController'
import { message } from 'ant-design-vue'
import ShareModal from '@/components/ShareModal.vue'
import { ref } from 'vue'

interface Props {
  dataList?: API.PictureVO[]
  loading?: boolean
  showOp?: boolean
  onReload?: () => void
}

const props = withDefaults(defineProps<Props>(), {
  dataList: () => [],
  loading: false,
  showOp: false,
})

// 编辑
const doEdit = (picture, e) => {
  e.stopPropagation()
  router.push({
    path: '/add_picture',
    query: {
      id: picture.id,
      spaceId: picture.spaceId,
    },
  })
}
// 以图搜图搜索
const doSearch = (picture, e) => {
  e.stopPropagation()
  window.open(`/search_picture?pictureId=${picture.id}`)
}


// 删除
const doDelete = async (picture, e) => {
  e.stopPropagation()
  const id = picture.id

  if (!id) {
    return
  }
  const res = await deletePictureUsingPost({ id })
  if (res.data.code === 0) {
    message.success('删除成功')
    // 让外层刷新
    props?.onReload()
  } else {
    message.error('删除失败')
  }
}

// 跳转至图片详情
const router = useRouter()
const doClickPicture = (picture) => {
  router.push({
    path: `/picture/${picture.id}`,
  })
}


// 分享弹窗引用
const shareModalRef = ref()
// 分享链接
const shareLink = ref<string>()

// 分享
const doShare = (picture: API.PictureVO, e: Event) => {
  e.stopPropagation()
  shareLink.value = `${window.location.protocol}//${window.location.host}/picture/${picture.id}`
  if (shareModalRef.value) {
    shareModalRef.value.openModal()
  }
}

</script>

<style scoped></style>
