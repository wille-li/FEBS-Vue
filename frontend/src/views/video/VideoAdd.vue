<template>
  <a-drawer
    title="新增课程"
    :maskClosable="false"
    width=650
    placement="right"
    :closable="false"
    @close="onClose"
    :visible="courseAddVisiable"
    style="height: calc(100% - 55px);overflow: auto;padding-bottom: 53px;">
    <a-form :form="form">
      <a-form-item label='列表图片' v-bind="formItemLayout">
        <a-input style="width: 100%"
                        v-model="job.imageUrl"
                        v-decorator="['imageUrl',
                   {rules: [
                    { required: true, message: '列表图片不能为空'},
                    { max: 200, message: '长度不能超过200个字符'}
                  ]}]"/>
      </a-form-item>
      <a-form-item label='主标题' v-bind="formItemLayout">
        <a-input style="width: 100%"
                        v-model="job.title"
                        v-decorator="['title',
                   {rules: [
                    { required: true, message: '主标题不能为空'},
                    { max: 45, message: '长度不能超过45个字符'}
                  ]}]"/>
      </a-form-item>
      <a-form-item label='子标题' v-bind="formItemLayout">
        <a-input v-model="job.subTitle"
                 v-decorator="['subTitle',
                   {rules: [
                    { required: true, message: '子标题不能为空'},
                    { max: 45, message: '长度不能超过45个字符'}
                  ]}]"/>
      </a-form-item>
      <a-form-item label='价格' v-bind="formItemLayout">
        <a-input v-model="job.price"
                 v-decorator="['price',
                   {rules: [
                    { required: true, message: '价格不能为空'}
                  ]}]"/>
      </a-form-item>
      <a-form-item label='老师' v-bind="formItemLayout">
       <a-select
          v-model="job.teacherId"
          :allowClear="true"
          style="width: 100%"
          v-decorator="['teacherId',{rules: [{ required: true, message: '请选择老师' }]}]">
          <a-select-option v-for="r in roleData" :key="r.id">{{r.name}}</a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
    <div class="drawer-bootom-button">
      <a-popconfirm title="确定放弃编辑？" @confirm="onClose" okText="确定" cancelText="取消">
        <a-button style="margin-right: .8rem">取消</a-button>
      </a-popconfirm>
      <a-button @click="handleSubmit" type="primary" :loading="loading">提交</a-button>
    </div>
  </a-drawer>
</template>
<script>
const formItemLayout = {
  labelCol: { span: 4 },
  wrapperCol: { span: 18 }
}
export default {
  name: 'CourseAdd',
  props: {
    courseAddVisiable: {
      default: false
    }
  },
  data () {
    return {
      loading: false,
      formItemLayout,
      form: this.$form.createForm(this),
      job: {
        cronExpression: ''
      },
      roleData: [],
      validateStatus: '',
      help: ''
    }
  },
  methods: {
    reset () {
      this.loading = false
      this.validateStatus = this.help = ''
      this.job = {cronExpression: ''}
      this.form.resetFields()
    },
    onClose () {
      this.reset()
      this.$emit('close')
    },
    handleSubmit () {
      this.form.validateFields((err, values) => {
        if (!err) {
          this.$post('api/course/add', {
            ...this.job
          }).then(() => {
            this.reset()
            this.$emit('success')
          }).catch(() => {
            this.loading = false
          })
        }
      })
    }
  },
  watch: {
    courseAddVisiable () {
      if (this.courseAddVisiable) {
        this.$get('api/teacher/list').then((r) => {
          this.roleData = r.data.data
        })
      }
    }
  }
}
</script>
