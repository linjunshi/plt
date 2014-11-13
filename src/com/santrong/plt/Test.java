package com.santrong.plt;

import java.util.List;

import org.jdom.Element;

import com.santrong.plt.util.HttpUtils;
import com.santrong.plt.util.XmlReader;





/**
 * @author weinianjie
 * @date 2014年7月24日
 * @time 下午5:28:37
 */
public class Test {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String b = HttpUtils.sendGet("http://support.renren.com/highschool/6201.html", null);
		String c = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><school>" + b + "</school>";
		XmlReader xml = new XmlReader();
		xml.parse(c);
		List<Element> ulList = xml.finds("/ul");
		for(int i=1;i<ulList.size();i++) {
			
			System.out.println(ulList.get(i).getAttributeValue("id").substring(8));
			List<Element> liList = ulList.get(i).getChildren();
			for(Element li:liList) {
				System.out.println(li.getName());
				System.out.println(li.getChild("a").getText());
			}
		}

		
	}
}
