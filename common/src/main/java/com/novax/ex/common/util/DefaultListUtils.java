package com.novax.ex.common.util;

import java.util.ArrayList;
import java.util.List;

/**
  * Description:List工具类
  * @author shaoqiping
  * @date 2018年5月23日下午3:05:44
*/
public class DefaultListUtils {


	/**
	 * Description:分段格式化数据，用于精简大量ID查询数据库语句，减少与数据库交互次数
	 * @param cartIds	全部ID
	 * @param count	拆分长度，每次执行多少次拆分多少
	 * @return List<List<Long>>
	 * @author shaoqiping
	 * @date 2018年5月23日下午3:06:00
	 */
	public static List<List<Long>> splitList(List<Long> cartIds, int count) {
		List<List<Long>> returnList = new ArrayList<>();
		//计算当前的数组大小，若id集合分段截取有多余，则多循环一次，若没有则直接计算
		int total = cartIds.size() % count == 0 ? cartIds.size()/count : (cartIds.size()/count)+1;
		for (int i = 0; i < total; i++) {
			if (i == total-1) {
				returnList.add(cartIds.subList(i*count, cartIds.size()));
			} else {
				returnList.add(cartIds.subList(i*count, i*count+count));
			}
		}
		return returnList;
	}

	/**
	 * Description: list分段
	 * @param: [list, count]
	 * @return: java.util.List<java.util.List<T>>
	 * @author: my.miao
	 * @date: 2018/12/14 17:48
	 */
	public static<T> List<List<T>> splitListT(List<T> list, int count) {
		List<List<T>> returnList = new ArrayList<>();
		//计算当前的数组大小，若id集合分段截取有多余，则多循环一次，若没有则直接计算
		int total = list.size() % count == 0 ? list.size()/count : (list.size()/count)+1;
		for (int i = 0; i < total; i++) {
			if (i == total-1) {
				returnList.add(list.subList(i*count, list.size()));
			} else {
				returnList.add(list.subList(i*count, i*count+count));
			}
		}
		return returnList;
	}

	/**
	 * Description:比较2个集合，在before中找after没有的元素返回
	 * @param before
	 * @param after
	 * @return list
	 * @author shaoqiping
	 * @date 2018年5月23日下午3:06:28
	 */
	public static List<String> equals(List<String> before, List<String> after) {
		boolean flag = false;
		List<String> removeList = new ArrayList<>();
		for (String language : before) {
			for (String s : after) {
				if (language.equals(s)) {
					flag = false;
					break;
				}
				flag = true;
			}
			if (after.size() == 0) {
				flag = true;
			}
			if (flag) {
				removeList.add(language);
			}
		}
		return removeList;
	}
}
