<template>
  <el-tree :data="data" :props="defaultProps" show-checkbox node-key="catId" :expand-on-click-node="false"
    :default-expanded-keys="expendedKeys">
    <span class="custom-tree-node" slot-scope="{ node, data }">
      <span>{{ node.label }}</span>
      <span>
        <el-button v-if="data.catLevel <= 2" type="text" size="mini" @click="() => append(data)">
          Append
        </el-button>
        <el-button v-if="data.children.length == 0" type="text" size="mini" @click="() => remove(node, data)">
          Delete
        </el-button>
      </span>
    </span>
  </el-tree>
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
      },
      expendedKeys: [],
    };
  },
  methods: {
    append(data) {
      // const newChild = { id: id++, label: 'testtest', children: [] };
      // if (!data.children) {
      //   this.$set(data, 'children', []);
      // }
      // data.children.push(newChild);
    },

    remove(node, data) {
      this.$confirm(`此操作将删除【${data.name}】菜单, 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.$http({
          url: this.$http.adornUrl('/product/category/delete'),
          method: 'post',
          data: [data.catId]
        }).then(({ data }) => {
          this.data = this.getDataList();
          this.expendedKeys = [node.parent.data.catId];
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/product/category/list/tree'),
        method: 'get',
      }).then(({ data }) => {
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
<style lang='scss' scoped></style>