package cc.mrbird.febs.api.service;

import java.util.List;

import javax.validation.Valid;

import com.baomidou.mybatisplus.extension.service.IService;

import cc.mrbird.febs.api.domain.Message;

/**
 * 
 * @author hw
 *
 */
public interface MessageService extends IService<Message> {


	Integer addMessage(@Valid Message message);

	List<Message> selectMessageList(Integer userId);
}
