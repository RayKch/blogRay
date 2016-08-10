package ray.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IP를 가져오는 유틸
 * L4 장비나 게이트웨이 등등에서 IP를 제대로 반환해주지 않는 문제를 해결하기 위해 작성되었다
 *
 * http://stackoverflow.com/questions/16558869/getting-ip-address-of-client
 */
@Slf4j
public class IpUtil {
	private static final String[] HEADERS_TO_TRY = {
			"X-Forwarded-For",
			"Proxy-Client-IP",
			"WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR",
			"HTTP_X_FORWARDED",
			"HTTP_X_CLUSTER_CLIENT_IP",
			"HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR",
			"HTTP_FORWARDED",
			"HTTP_VIA",
			"REMOTE_ADDR" };

	public static String getClientIpAddress(HttpServletRequest request) {
		for (String header : HEADERS_TO_TRY) {
			String ip = request.getHeader(header);
			if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}

	/**
	 * 맥주소를 반환하는 유틸
	 * @return
	 * @throws Exception
	 */
	public static String getMacAddress() throws Exception {
		byte mac[] = null;
		try {
			// centos에서는 접근하는 것만으로도 오류가 발생한다
			InetAddress address = InetAddress.getLocalHost();
			NetworkInterface nwi = NetworkInterface.getByInetAddress(address);
			mac = nwi.getHardwareAddress();
		} catch(Exception e) {}

		log.debug("mac-address : " + mac);

		if(mac == null) {
			// linux일 경우
			String macAddress = "";
			Process pid = Runtime.getRuntime().exec("/sbin/ifconfig -a ");
			BufferedReader in = new BufferedReader(new InputStreamReader(pid.getInputStream()));
			while (true) {
				String line = in.readLine();
				if (line == null) break;
				log.debug(line);

				Pattern p = Pattern.compile(".*([\\da-fA-F]{2}:[\\da-fA-F]{2}:[\\da-fA-F]{2}:[\\da-fA-F]{2}:[\\da-fA-F]{2}:[\\da-fA-F]{2}).*");
				Matcher m = p.matcher(line);
				if (m.matches()) {
					macAddress = m.group(1);
					break;
				}
			}
			return macAddress;
		} else {
			// windows일 경우

			StringBuilder sb = new StringBuilder();
			for (int i=0; i<mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
			}
			return sb.toString();
		}
	}
}
