/**
 * 
 */
package jp.co.axa.apidemo.util;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import jp.co.axa.apidemo.exception.CustomServiceException;
import jp.co.axa.apidemo.vo.CommonVo;

/**
 * @author Peter
 *
 */
@Component
public final class CommonUtil {

	private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

	private static final String OBJECT_MAPPER = "Object mapping error";

	/**
	 * This class only static method. It's private constructor, can't be initialize
	 * the object
	 */
	private CommonUtil() {

	}

	/**
	 * Method to copy the object origin bean to destination bean
	 * 
	 * @param originBean Object
	 * @param destBean   Object
	 */
	public static void copyProperties(Object originBean, Object destBean) {
		try {
			if (null != originBean)
				BeanUtils.copyProperties(originBean, destBean);
		} catch (Exception e) {
			logger.error(OBJECT_MAPPER);
			throw new CustomServiceException(OBJECT_MAPPER);
		}
	}
	
	/**
	 * Method to copy the object origin list to destination list
	 * 
	 * @param originBeanList
	 *            List
	 * @param destBeanList
	 *            List
	 * @param destClass
	 *            Class
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void copyListProperties(List originBeanList, List destBeanList, Class destClass) {
		try {
			if (null != originBeanList) {
				Iterator itr = originBeanList.iterator();
				while (itr.hasNext()) {
					CommonVo commonVo = Class.forName(destClass.getName()).asSubclass(CommonVo.class).newInstance();
					BeanUtils.copyProperties(itr.next(), commonVo);
					destBeanList.add(commonVo);
				}
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			logger.error(OBJECT_MAPPER);
			throw new CustomServiceException(OBJECT_MAPPER);
		}
	}
}
