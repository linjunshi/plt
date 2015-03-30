package com.santrong.plt.webpage.weixin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.weixin.mp.entry.WeixinConfigureEntry;
import com.santrong.plt.weixin.mp.util.WeixinUtil;

/**
 * @author huangweihua
 * @date 2015年3月18日 
 * @time 上午10:54:57
 */
@Controller
@RequestMapping("/weixin")
public class WeixinAction extends BaseAction{

	@RequestMapping("/demo")
	public String showDemo(){
		HttpServletRequest request = this.getRequest();
		
        // 注意 URL 一定要动态获取，不能 hardcode
        String url = request.getRequestURL().toString().split("#")[0];
//        System.out.println(request.getRequestURL().toString().split("#")[0]);
        WeixinConfigureEntry config = new WeixinConfigureEntry();
		Map<String, String> ret = WeixinUtil.getSignPackage(url);
		for (@SuppressWarnings("rawtypes") Map.Entry entry : ret.entrySet()) {     
//			System.out.println(entry.getKey() + ", " + entry.getValue());
			if (entry.getKey() == "appId") {
				config.setAppId(entry.getValue().toString());
			}else if (entry.getKey() == "timestamp") {
				config.setTimestamp(entry.getValue().toString());
			}else if (entry.getKey() == "nonceStr") {
				config.setNonceStr(entry.getValue().toString());
			}else if (entry.getKey() == "signature") {
				config.setSignature(entry.getValue().toString());
			}
		}
		request.setAttribute("weixinConfig", config);
		return "/weixin/demo";
	}
	
}
