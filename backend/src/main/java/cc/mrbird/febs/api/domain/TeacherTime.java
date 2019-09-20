package cc.mrbird.febs.api.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "TeacherTime对象", description = "教师档期时间表")

public class TeacherTime implements Serializable{
	
	/**
	 * 教师档期时间表
	 * 
	 * @author hw
	 */
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
    private Integer id;

	@ApiModelProperty(value = "用户id")
    private Integer teacherId;

	@ApiModelProperty(value = "老师预约表id")
    private Integer teacherOrderId;

	@ApiModelProperty(value = "档期开始时间")
    private Date scheduleStartTime;

	@ApiModelProperty(value = "档期结束时间")
    private Date scheduleEndTime;
	
	@ApiModelProperty(value = "状态   空档  0 ;已约 1 ;完成 2 '")
    private Integer status;

	@ApiModelProperty(value = "创建时间")
    private String createBy;

	@ApiModelProperty(value = "创建人")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTeacherOrderId() {
        return teacherOrderId;
    }

    public void setTeacherOrderId(Integer teacherOrderId) {
        this.teacherOrderId = teacherOrderId;
    }

    public Date getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(Date scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public Date getScheduleEndTime() {
        return scheduleEndTime;
    }

    public void setScheduleEndTime(Date scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}