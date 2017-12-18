package org.softcits.web.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.softcits.basic.model.Pager;
import org.softcits.basic.model.SystemContext;
import org.softcits.core.model.Attachment;
import org.softcits.core.model.Channel;
import org.softcits.core.model.ChannelType;
import org.softcits.core.model.Topic;
import org.softcits.core.service.IAttachmentService;
import org.softcits.core.service.IChannelService;
import org.softcits.core.service.IKeywordService;
import org.softcits.core.service.ITopicService;
import org.softcits.web.interceptor.BaseInfoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	private IChannelService channelService;
	private ITopicService topicService;
	private IAttachmentService attachmentService;
	private IKeywordService keywordService;
	

	public IKeywordService getKeywordService() {
		return keywordService;
	}
	@Inject
	public void setKeywordService(IKeywordService keywordService) {
		this.keywordService = keywordService;
	}

	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}
	
	@Inject
	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	public IChannelService getChannelService() {
		return channelService;
	}
	@Inject
	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}

	public ITopicService getTopicService() {
		return topicService;
	}
	@Inject
	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}

	@RequestMapping({"/","/index"})
	public String index(Model model) {
		model.addAttribute("baseInfo", BaseInfoUtil.getInstacne().read());
		return "index/index";
	}
	
	@RequestMapping("/channel/{cid}")
	public String showChannel(@PathVariable int cid,Model model,HttpServletResponse resp,HttpServletRequest req) throws IOException {
		Channel c = channelService.load(cid);
		System.out.println(c.getType());
		Channel pc = null;
		if(c.getType()==ChannelType.NAV_CHANNEL) {
			pc = c;
			//如果是导航栏目，需要获取该栏目中的第一个栏目
			c = channelService.loadFirstChannelByNav(c.getId());
		} else {
			pc = c.getParent();
		}
//		System.out.println(c.getType()==ChannelType.TOPIC_LIST);
//		System.out.println(c.getType());
		if(c.getType()==ChannelType.TOPIC_CONTENT) {
			resp.sendRedirect(req.getContextPath()+"/topic/"+topicService.loadLastedTopicByColumn(cid).getId());
		} else if(c.getType()==ChannelType.TOPIC_IMG){
			SystemContext.setPageSize(16);
			SystemContext.setSort("a.topic.publishDate");
			SystemContext.setOrder("desc");
			Pager<Attachment> atts = attachmentService.findChannelPic(c.getId());
			model.addAttribute("datas", atts);
		} else if(c.getType()==ChannelType.TOPIC_LIST) {
			SystemContext.setSort("t.publishDate");
			SystemContext.setOrder("desc");
			//System.out.println(c.getType());
			model.addAttribute("datas", topicService.find(c.getId(),null,1));
		}
		SystemContext.removeSort();
		SystemContext.removeOrder();
		model.addAttribute("pc", pc);
		model.addAttribute("cs", channelService.listUseChannelByParent(pc.getId()));
		model.addAttribute("channel", c);
		if(c.getType()==ChannelType.TOPIC_LIST) {
			return "index/channel";
		} else {
			return "index/channel_pic";
		}
	}
	
	@RequestMapping("/topic/{tid}")
	public String showTopic(@PathVariable int tid,Model model) {
		model.addAttribute("topic", topicService.load(tid));
		List<Attachment> atts = attachmentService.listAttachByTopic(tid);
		if(atts.size()>0) {
			model.addAttribute("hasAtts", true);
			model.addAttribute("atts", atts);
		} else {
			model.addAttribute("hasAtts",false);
		}
		return "index/topic";
	}
	
	@RequestMapping("/search/{con}")
	public String search(@PathVariable String con,Model model) {
		SystemContext.setOrder("asc");
		SystemContext.setSort("c.orders");
		model.addAttribute("cs", channelService.listChannelByType(ChannelType.NAV_CHANNEL));
		SystemContext.setOrder("desc");
		SystemContext.setSort("t.publishDate");
		Pager<Topic> topics = topicService.searchTopic(con);
		emp(topics,con);
		model.addAttribute("datas", topics);
		model.addAttribute("con", con);
		return "index/search";
	}
	
	@RequestMapping("/keyword/{con}")
	public String keyword(@PathVariable String con,Model model) {
		model.addAttribute("kws", keywordService.getMaxTimesKeyword(9));
		SystemContext.setOrder("desc");
		SystemContext.setSort("t.publishDate");
		Pager<Topic> topics = topicService.searchTopicByKeyword(con);
		emp(topics,con);
		model.addAttribute("datas", topics);
		model.addAttribute("con", con);
		return "index/keyword";
	}

	private void emp(Pager<Topic> topics, String con) {
		for(Topic t:topics.getDatas()) {
			if(t.getTitle().contains(con)) {
				String tt = t.getTitle().replaceAll(con, "<span class='emp'>"+con+"</span>");
				t.setTitle(tt);
			}
		}
	}
}
