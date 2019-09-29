<template>
  <a-drawer
    title="新增视频"
    :maskClosable="false"
    width=650
    placement="right"
    :closable="false"
    @close="onClose"
    :visible="videoAddVisiable"
    style="height: calc(100% - 55px);overflow: auto;padding-bottom: 53px;">
    <a-form :form="form">
      <a-form-item label='视频名称' v-bind="formItemLayout">
        <a-input style="width: 100%"
                        v-model="job.name"
                        v-decorator="['name',
                   {rules: [
                    { required: true, message: '视频名称不能为空'},
                    { max: 45, message: '长度不能超过45个字符'}
                  ]}]"/>
      </a-form-item>
      <a-form-item label='视频URL' v-bind="formItemLayout">
        <a-input style="width: 100%"
                        v-model="job.videoUrl"
                        v-decorator="['videoUrl',
                   {rules: [
                    { required: true, message: '视频URL不能为空'},
                    { max: 200, message: '长度不能超过200个字符'}
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
  name: 'VideoAdd',
  props: {
    videoAddVisiable: {
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
          this.$post('api/video/add', {
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
  }
}
</script>
