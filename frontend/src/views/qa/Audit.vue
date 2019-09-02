<template>
  <a-card :bordered="false" class="card-area">
    <div :class="advanced ? 'search' : null">
      <!-- 搜索区域 -->
      <a-form layout="horizontal">
        <div :class="advanced ? null: 'fold'">
          <a-row >
            <a-col :md="12" :sm="24" >
              <a-form-item
                label="问题内容"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}">
                <a-input v-model="queryParams.question"/>
              </a-form-item>
            </a-col>
          </a-row>
        </div>
        <span style="float: right; margin-top: 3px;">
          <a-button type="primary" @click="search">查询</a-button>
          <a-button style="margin-left: 8px" @click="reset">重置</a-button>
        </span>
      </a-form>
    </div>
    <div>
      <!-- 表格区域 -->
      <a-table :columns="columns"
               :dataSource="dataSource"
               :pagination="pagination"
               :loading="loading"
               :scroll="{ x: 900 }"
               @change="handleTableChange">
        <template slot="operation" slot-scope="text, record">
          <a-icon v-hasPermission="'audit:update'" type="check" theme="twoTone" twoToneColor="#4a9ff5" @click="access(record)" title="通过审批"></a-icon>
          &nbsp;
          <a-icon v-hasPermission="'audit:update'" type="close" theme="twoTone" twoToneColor="#42b983" @click="reject(record)" title="拒绝"></a-icon>
          <a-badge v-hasNoPermission="'user:update','user:view'" status="warning" text="无权限"></a-badge>
        </template>
      </a-table>
    </div>
  </a-card>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: 'Audit',
  data () {
    return {
      advanced: false,
      dataSource: [],
      queryParams: {},
      pagination: {
        defaultPageSize: 10000000,
        hideOnSinglePage: true,
        indentSize: 100
      },
      loading: false
    }
  },
  computed: {
    columns () {
      return [{
        title: '问题内容',
        dataIndex: 'questionContent'
      }, {
        title: '回答内容',
        dataIndex: 'content'
      }, {
        title: '回答老师',
        dataIndex: 'teacherName'
      }, {
        title: '状态',
        dataIndex: 'status',
        customRender: (text, row, index) => {
          switch (text) {
            case 0:
              return '未审批'
            case 1:
              return '已审批'
            case 2:
              return '已拒绝'
            default:
              return text
          }
        }
      }, {
        title: '回答时间',
        dataIndex: 'createTime'
      }, {
        title: '操作',
        dataIndex: 'operation',
        scopedSlots: { customRender: 'operation' },
        fixed: 'right',
        width: 120
      }]
    },
    ...mapState({
      user: state => state.account.user
    })
  },
  mounted () {
    this.fetch()
  },
  methods: {
    access (record) {
      console.log(record)
      let that = this
      this.$confirm({
        title: '确定通过该回答吗?',
        content: '问da',
        centered: true,
        onOk () {
          that.$post(`audit/${record.id}`).then(() => {
            that.$message.success('踢出用户成功')
            if (that.user.id === record.id) {
              that.$db.clear()
              location.reload()
            } else {
              that.search()
            }
          }).catch((r) => {
            console.error(r)
            that.$message.error('踢出用户失败')
          })
        }
      })
      location.reload()
    },
    search () {
      this.fetch({
        ...this.queryParams
      })
    },
    kickout (record) {
      let that = this
      this.$confirm({
        title: '确定踢出该用户?',
        content: '当您点击确定按钮后，该用户的登录将会马上失效',
        centered: true,
        onOk () {
          that.$delete(`access/${record.id}`).then(() => {
            that.$message.success('踢出用户成功')
            if (that.user.id === record.id) {
              that.$db.clear()
              location.reload()
            } else {
              that.search()
            }
          }).catch((r) => {
            console.error(r)
            that.$message.error('踢出用户失败')
          })
        }
      })
    },
    reset () {
      // 重置查询参数
      this.queryParams = {}
      this.fetch()
    },
    handleTableChange (pagination, filters, sorter) {
      this.fetch({
        ...this.queryParams
      })
    },
    fetch (params = {}) {
      this.loading = true
      this.$get('qa/audit', {
        ...params
      }).then((r) => {
        console.log(r.data)
        let data = r.data.rows
        this.loading = false
        this.dataSource = data
      })
    }
  }
}
</script>

<style lang="less" scoped>
  @import "../../../static/less/Common";
</style>
