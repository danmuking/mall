<template>
  <div>
    <el-tree :data="data" :props="defaultProps" show-checkbox node-key="catId" :expand-on-click-node="false"
      :default-expanded-keys="expendedKeys"
      >
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button v-if="data.catLevel <= 2" type="text" size="mini" @click="() => append(data)">
            Append
          </el-button>
          <el-button v-if="data.children.length == 0" type="text" size="mini" @click="() => remove(node, data)">
            Delete
          </el-button>
          <el-button type="text" size="mini" @click="() => edit(data)">
            Edit
          </el-button>
        </span>
      </span>
    </el-tree>

    <el-dialog :title="title" :visible.sync="dialogVisible" width="30%"
    :close-on-click-modal="false"
    >
      <el-form :model="category">
        <el-form-item label="菜单名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="菜单图标">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计数单位">
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="() => submitData()">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      dialogType: '',
      data: [],
      title: "",
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      category: {
        catId: null,
        name: '',
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        sort: 0,
        icon: '',
        productUnit: ''
      },
      dialogVisible: false,
      expendedKeys: [],
    };
  },
  methods: {
    submitData() {
      if (this.dialogType == 'edit') {
        this.editCategory();
      } else if (this.dialogType == 'add') {
        this.addCategory();
      }
    },
    edit(data) {
      console.log("要修改的数据", data);
      this.dialogType = 'edit';
      this.title = '修改分类';
      this.dialogVisible = true;

      // 获取当前节点的最新数据
      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: 'get',
      }).then(({ data }) => {
        console.log(data.data);
        this.category.name = data.data.name;
        this.category.catId = data.data.catId;
        this.category.icon = data.data.icon;
        this.category.productUnit = data.data.productUnit;
        this.category.parentCid = data.data.parentCid;
        this.category.catLevel = data.data.catLevel;
        this.category.sort = data.data.sort;
        this.category.showStatus = data.data.showStatus;
      })
    },
    editCategory() {
      let { catId, name, icon, productUnit } = this.category;
      this.$http({
        url: this.$http.adornUrl("/product/category/update"),
        method: "post",
        data: this.$http.adornData({ catId, name, icon, productUnit }, false)
      }).then(({ data }) => {
        this.$message({
          message: "菜单修改成功",
          type: "success"
        });
        //关闭对话框
        this.dialogVisible = false;
        //刷新出新的菜单
        this.getDataList();
        //设置需要默认展开的菜单
        this.expandedKey = [this.category.parentCid];
      });
    },
    append(data) {
      // console.log(data);
      this.dialogType='add';
      this.title = '添加菜单';
      this.dialogVisible = true;
      this.category.parentCid = data.catId;
      this.category.catLevel = data.catLevel * 1 + 1;
      this.category.parentCid = null;
      this.category.name = '';
      this.category.icon = '';
      this.category.productUnit = '';
      this.category.sort = 0;
      this.category.showStatus = 1;
    },
    addCategory() {
      this.$http({
        url: this.$http.adornUrl('/product/category/save'),
        method: 'post',
        data: this.$http.adornData(this.category, false)
      }).then(({ data }) => {
        this.$message({
          type: 'success',
          message: `添加【${this.category.name}】成功!`
        });
        this.data = this.getDataList();
        this.expendedKeys = [this.category.parentCid];
        this.dialogVisible = false;
      });
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