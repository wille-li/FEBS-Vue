package cc.mrbird.febs.api.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.mrbird.febs.api.dao.MessageMapper;
import cc.mrbird.febs.api.domain.Message;
import cc.mrbird.febs.api.service.MessageService;

/**
 * @author hw
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

	@Autowired
    private MessageMapper messageMapper;
	@Override
	public List<Message> selectMessageList(Integer userId) {
		// TODO Auto-generated method stub
		return messageMapper.selectMessageList(userId);
	}
	@Override
	@Transactional
	public Integer addMessage(@Valid Message message) {
		// TODO Auto-generated method stub
		 return messageMapper.addMessage(message);
	}

    
}
