package com.santrong.plt;

import com.google.gson.Gson;
import com.santrong.plt.log.Log;
import com.santrong.plt.opt.TaobaoAreaEntry;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MyUtils;




/**
 * @author weinianjie
 * @date 2014年7月24日
 * @time 下午5:28:37
 */
public class Test {
	public static void main(String[] args) {
		String clientIp = "183.17.255.255";
		String areaCode = Global.AreaCode;
		if(clientIp != null) {
			String areaServerAddr = "http://ip.taobao.com/service/getIpInfo.php";
			String json = MyUtils.getRemoteContent(areaServerAddr + "?ip=" + clientIp);
			if(MyUtils.isNotNull(json)) {
				Gson gson = new Gson();
				TaobaoAreaEntry entry = gson.fromJson(json, TaobaoAreaEntry.class);
				Log.info(entry.getCode());
			}
			
		}
		
		Log.info(areaCode);
	}
}
