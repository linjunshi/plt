package com.santrong.plt.opt.area;

import java.io.File;

import com.google.gson.Gson;
import com.santrong.plt.log.Log;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.home.dao.AreaDao;
import com.santrong.plt.webpage.home.entry.AreaItem;

/**
 * @author weinianjie
 * @date 2014年10月17日
 * @time 上午11:35:43
 */
public class AreaService {
	
	private static final String taobaoServiceAddr = "http://ip.taobao.com/service/getIpInfo.php";
	
	private static IPSeeker ipSeeker = null;
	
	/**
	 * 用淘宝服务解析IP对应的行政区划
	 * @param clientIp
	 * @return
	 */
	public AreaEntry getAreaByTaobao(String clientIp) {
		AreaEntry area = new AreaEntry();
		area.setCityName(Global.City);
		area.setCityCode(Global.AreaCode);
		try{
			if(clientIp != null) {
				String json = MyUtils.getRemoteContent(taobaoServiceAddr + "?ip=" + clientIp);
				if(MyUtils.isNotNull(json)) {
					Gson gson = new Gson();
					TaobaoAreaEntry entry = gson.fromJson(json, TaobaoAreaEntry.class);
					if(entry.getCode() == 0) {
						area.setCityName(entry.getData().getCity());
						area.setCityCode(entry.getData().getCity_id());
					}
				}
			}
		}catch(Exception e) {
			Log.printStackTrace(e);
		}
		return area;
	}
	
	
	/**
	 * 使用本地纯真IP库获取IP对应的行政区划
	 * @param clientIp
	 */
	public AreaEntry getIpAreaByCz88(String clientIp) {
		AreaEntry area = new AreaEntry();
		area.setCityName(Global.City);
		area.setCityCode(Global.AreaCode);
		try{
			if(ipSeeker == null) {
				String dataFilePath = AreaService.class.getClassLoader().getResource("") + "qqwry.dat";
		        if (dataFilePath.startsWith("file:/")) {
		        	dataFilePath = dataFilePath.substring(5);
		        }
				ipSeeker = new IPSeeker(new File(dataFilePath));
			}
			String country = ipSeeker.getCountry(clientIp);
			if(country != null && !country.endsWith("局域网")) {
				String city;
				if(country.contains("省")) {
					city = country.split("省")[1];
				}else if(country.contains("自治区")) {
					city = country.split("自治区")[1];
				}else {
					city = country;
				}
				if(city.endsWith("市")) {
					city = city.substring(0, city.length() - 1);
				}
				AreaDao areaDao = new AreaDao();
				AreaItem item = areaDao.selectByAreaName(city);
				if(item != null) {
					area.setCityName(item.getAreaName());
					area.setCityCode(item.getAreaCode());
				}else {
					Log.warn("coun't not get areaCode from city " + country + " and ip " + clientIp);
				}
			}
		}catch(Exception e) {
			Log.printStackTrace(e);
		}
		return area;
	}
}
