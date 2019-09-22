<template>
  <a-drawer
    title="修改课程信息"
    :maskClosable="false"
    width=650
    placement="right"
    :closable="false"
    @close="onClose"
    :visible="courseEditVisiable"
    style="height: calc(100% - 55px);overflow: auto;padding-bottom: 53px;">
    <a-form :form="form">
      <a-form-item label='图片列表' v-bind="formItemLayout">
        <a-input style="width: 100%"
                 v-decorator="['imageUrl',
                   {rules: [
                    { required: true, message: '图片列表不能为空'},
                    { max: 200, message: '长度不能超过200个字符'}
                  ]}]"/>
      </a-form-item>
      <a-form-item label='主标题' v-bind="formItemLayout">
        <a-input v-decorator="['title',
                   {rules: [
                    { required: true, message: '主标题不能为空'},
                    { max: 45, message: '长度不能超过45个字符'}
                  ]}]"/>
      </a-form-item>
       <a-form-item label='子标题' v-bind="formItemLayout">
        <a-input v-decorator="['subTitle',
                   {rules: [
                    { required: true, message: '子标题不能为空'},
                    { max: 45, message: '长度不能超过45个字符'}
                  ]}]"/>
      </a-form-item>
      <a-form-item label='价格' v-bind="formItemLayout">
        <a-input v-decorator="['price',
                   {rules: [
                    { required: true, message: '价格不能为空'}
                  ]}]"/>
      </a-form-item>
      <a-form-item label='老师' v-bind="formItemLayout">
        <a-input v-decorator="['teacherId',
                   {rules: [
                    { required: true, message: '老师不能为空'}
                  ]}]"/>
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
  name: 'CourseEdit',
  props: {
    courseEditVisiable: {
      default: false
    }
  },
  data () {
    return {
      loading: false,
      formItemLayout,
      form: this.$form.createForm(this),
      validateStatus: '',
      help: '',
      course: {}
    }
  },
  methods: {
    reset () {
      this.loading = false
      this.validateStatus = this.help = ''
      this.course = {}
      this.form.resetFields()
    },
    onClose () {
      this.reset()
      this.$emit('close')
    },
    setFormValues ({...course}) {
      this.course.id = course.id
      let fields = ['imageUrl', 'title', 'subTitle', 'price', 'teacherId', 'learningCount', 'createBy', 'createTime']
      Object.keys(course).forEach((key) => {
        if (fields.indexOf(key) !== -1) {
          this.form.getFieldDecorator(key)
          let obj = {}
          obj[key] = course[key]
          this.form.setFieldsValue(obj)
        }
      })
    },
    handleSubmit () {
      this.form.validateFields((err, values) => {
        if (!err) {
          let course = this.form.getFieldsValue()
          course.id = this.course.id
          this.$post('api/course/update', {
            ...course
          }).then(() => {
            this.reset()
            this.$emit('success')
          }).catch(() => {
            this.loading = false
          })
        }
      })
    }
  }
}
</script>
