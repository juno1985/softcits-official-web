package org.softcits.web.interceptor;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CmsSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}

	//以防用户没用点击logout登出，而是直接关闭浏览器则下面方法会被调用
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		CmsSessionContext.removeSession(event.getSession());
		System.out.println("移除了Session:"+event.getSession().getId());
	}

}
