package ray.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * 자바빈과 맵과 관련된 유틸 클래스
 */
public class BeanMapUtil {
	/**
	 * pojo의 vo를 map으로 변환하는 유틸
	 * http://stackoverflow.com/questions/3215538/generate-mapstring-string-from-pojo
	 *
	 * @param bean (반드시 pojo여야 함)
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> mapProperties(Object bean) throws Exception {
		Map<String, Object> properties = new HashMap<>();
		for (Method method : bean.getClass().getDeclaredMethods()) {
			if (Modifier.isPublic(method.getModifiers())
					&& method.getParameterTypes().length == 0
					&& method.getReturnType() != void.class
					&& method.getName().matches("^(get|is).+")
					) {
				String name = method.getName().replaceAll("^(get|is)", "");
				name = Character.toLowerCase(name.charAt(0)) + (name.length() > 1 ? name.substring(1) : "");
				Object value = method.invoke(bean);
				properties.put(name, value);
			}
		}
		return properties;
	}

	/**
	 * 서로 두개의 맵을 비교해서 다른 것만을 맵으로 반환한다
	 * @param origin 원본
	 * @param alteration 비교대상
	 * @return
	 */
	public static Map<String, Object> getChangemap(Map<String, Object> origin, Map<String, Object> alteration) {
		if (origin.size() != alteration.size()) {
			return null;
		}
		final String EMPTY_STRING = "[삭제]";

		Map<String, Object> result = new HashMap<>();
		for (String key: origin.keySet()) {

			if(origin.get(key) == null && alteration.get(key) != null) {
				// 원본에는 없지만 비교대상에는 있을 경우
				result.put(key, alteration.get(key));
			} else if(origin.get(key) != null && alteration.get(key) == null) {
				// 원본이 있었다가 비교대상에서 없애버렸을 경우
				result.put(key, EMPTY_STRING);
			} else if (origin.get(key) != null && alteration.get(key) != null && !origin.get(key).equals(alteration.get(key))) {
				// 서로 다른 값일 경우
				result.put(key, "".equals(alteration.get(key)) ? EMPTY_STRING : alteration.get(key) );
			}
			// 당연하지만 원본도 비교대상도 null이면 같으니 추출할 필요가 없다
		}
		return result;
	}
}
