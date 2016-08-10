package ray.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class CommandUtil {
	/**
	 * @param dir 실행시의 디렉토리, null일 경우엔 java_home
	 * @param command 명령어 (ex: "ls -al")
	 * @return
	 */
	public synchronized static String exec(String dir, String command) {
		StringBuffer sb = new StringBuffer();
		try{
			ProcessBuilder pb = new ProcessBuilder(command.split(" "));
			if(dir != null) {
				pb.directory(new File(dir));
			}

			pb.redirectErrorStream(true);
			Process p = pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while((line=br.readLine())!=null){
//				log.debug(line);
				if(!"".equals(sb.toString())) {
					sb.append("\n");
				}
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}


	public static String getHostName() {
		String hostname = exec(null, "hostname");
		if(hostname == null || "".equals(hostname)) {
			return "hostname을 불러올 수 없었습니다";
		}
		return hostname;
	}

	public static String getCpuInfo() {
		String cpuInfo = exec(null, "cat /proc/cpuinfo");
		if(cpuInfo != null && !"".equals(cpuInfo) && !cpuInfo.matches("^(cat:.*)")) {
			// 리눅스
			Matcher matcher = Pattern.compile("^\\s*model name\\s*:\\s*(.*)$", Pattern.MULTILINE).matcher(cpuInfo);
			if (matcher.find()) {
				cpuInfo = matcher.group(1).replaceAll("  ", " ");
			}
		} else {
			// OSX
			cpuInfo = exec("/usr/sbin", "sysctl -n machdep.cpu.brand_string");
		}
		if(cpuInfo == null || "".equals(cpuInfo)) {
			return "cpu 정보를 불러올 수 없었습니다";
		}
		return cpuInfo;
	}

	public static String getUptime() {
		String uptime = exec(null, "uptime");
		if(uptime == null || "".equals(uptime)) {
			return "uptime을 불러올 수 없었습니다";
		}
		return uptime;
	}

	public static String getBootTime() {
		String bootTime = exec(null, "who -b");
		if(bootTime != null && !"".equals(bootTime) && !bootTime.matches("^(reboot:.*)")) {
			Matcher matcher = Pattern.compile("^\\s*reboot\\s*~\\s*(.*)$", Pattern.MULTILINE).matcher(bootTime);
			if (matcher.find()) {
				bootTime = matcher.group(1).replaceAll("  ", " ");
			}
		}
		if(bootTime == null || "".equals(bootTime)) {
			return "부팅 내역을 불러올 수 없었습니다";
		}
		return bootTime;
	}

	public static String getMemory() {
		String mem = exec(null, "cat /proc/meminfo");
		if(mem != null && !"".equals(mem) && !mem.matches("^(cat:.*)")) {
			Matcher matcher = Pattern.compile("^\\s*MemTotal\\s*:\\s*(.*)$", Pattern.MULTILINE).matcher(mem);
			if (matcher.find()) {
				mem = matcher.group(1).replaceAll("  ", " ");
			}
		} else {
			mem = "메모리 정보를 불러올 수 없었습니다";
		}
		return mem;
	}
}
