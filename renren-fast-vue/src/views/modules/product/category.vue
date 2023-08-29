<template>
    <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
</template>

<script>
export default {
    components: {},
    props: {},
    data() {
      return {
        data: [],
        defaultProps: {
          children: 'children',
          label: 'name'
        }
      };
    },
    methods: {
      handleNodeClick(data) {
        console.log(data);
      },
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/product/category/list/tree'),
          method: 'get',
        }).then(({data}) => {
          console.log(data.data);
          this.data = data.data;
        })
      },
    },
    computed: {},
    watch: {},
    created() {
        this.getDataList();
    }
}
</script>
<style lang='scss' scoped>
</style>