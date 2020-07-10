package xyz.rexlin600.actuator.endpoint;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.*;
import oshi.util.FormatUtil;
import oshi.util.Util;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * My end point
 *
 * @author hekunlin
 */
@Endpoint(id = "myEndPoint")
@Component
public class MyEndPoint {

	/**
	 * MAP
	 */
	public static final Map<String, Object> MAP = new LinkedHashMap<>();
	/**
	 * CONSTANT_0D
	 */
	private static final Double CONSTANT_0D = 0d;
	/**
	 * CONSTANT_1D
	 */
	private static final Double CONSTANT_1D = 1d;

	/**
	 * Inject computer system json object
	 *
	 * @param computerSystem computer system
	 * @return the json object
	 */
	public static JSONObject injectComputerSystem(final ComputerSystem computerSystem) {
		Gson gson = new Gson();
		JSONObject jsonObject = gson.fromJson(gson.toJsonTree(computerSystem), JSONObject.class);
		return jsonObject;
	}

	/**
	 * Inject processor json object
	 *
	 * @param processor processor
	 * @return the json object
	 */
	public static JSONObject injectProcessor(CentralProcessor processor) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("物理CPU", processor.getPhysicalProcessorCount());
		jsonObject.put("逻辑CPU", processor.getLogicalProcessorCount());
		jsonObject.put("Identifier", processor.getIdentifier());
		jsonObject.put("处理器ID", processor.getProcessorID());
		return jsonObject;
	}

	/**
	 * Inject memory json object
	 *
	 * @param memory memory
	 * @return the json object
	 */
	public static JSONObject injectMemory(GlobalMemory memory) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("内存概况", "总内存: " + FormatUtil.formatBytes(memory.getTotal()) + "   已使用内存: " + FormatUtil.formatBytes(memory.getAvailable()));
		jsonObject.put("交换内存", "总Swap Mem: " + FormatUtil.formatBytes(memory.getSwapTotal()) + "  已使用Swap Mem: " + FormatUtil.formatBytes(memory.getSwapUsed()));
		return jsonObject;
	}

	/**
	 * Inject cpu json object
	 *
	 * @param processor processor
	 * @return the json object
	 */
	public static JSONObject injectCpu(CentralProcessor processor) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Uptime", FormatUtil.formatElapsedSecs(processor.getSystemUptime()));

		long[] prevTicks = processor.getSystemCpuLoadTicks();
		jsonObject.put("CPU, IOWait, and IRQ ticks @ 0 sec", Arrays.toString(prevTicks));

		// Wait a second...
		Util.sleep(1000);
		long[] ticks = processor.getSystemCpuLoadTicks();
		jsonObject.put("CPU, IOWait, and IRQ ticks @ 1 sec", Arrays.toString(ticks));

		// 基本信息
		long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
		long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
		long sys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
		long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
		long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
		long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
		long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
		long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
		long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;

		String cpuInfo = String.format(
				"User: %.1f%% Nice: %.1f%% System: %.1f%% Idle: %.1f%% IOwait: %.1f%% IRQ: %.1f%% SoftIRQ: %.1f%% Steal: %.1f%%",
				100d * user / totalCpu, 100d * nice / totalCpu, 100d * sys / totalCpu, 100d * idle / totalCpu,
				100d * iowait / totalCpu, 100d * irq / totalCpu, 100d * softirq / totalCpu, 100d * steal / totalCpu);
		String cpuLoad1 = String.format("CPU load: %.1f%% (counting ticks)", processor.getSystemCpuLoadBetweenTicks() * 100);
		String cpuLoad2 = String.format("CPU load: %.1f%% (OS MXBean)", processor.getSystemCpuLoad() * 100);
		jsonObject.put("CPU详情", cpuInfo);
		jsonObject.put("CPU负载-1", cpuLoad1);
		jsonObject.put("CPU负载-2", cpuLoad2);

		double[] loadAverage = processor.getSystemLoadAverage(3);
		jsonObject.put("CPU load averages", (loadAverage[0] < 0 ? " N/A" : String.format(" %.2f", loadAverage[0]))
				+ (loadAverage[1] < 0 ? " N/A" : String.format(" %.2f", loadAverage[1]))
				+ (loadAverage[2] < 0 ? " N/A" : String.format(" %.2f", loadAverage[2])));

		// per core CPU
		StringBuilder procCpu = new StringBuilder("CPU load per processor");
		double[] load = processor.getProcessorCpuLoadBetweenTicks();
		for (double avg : load) {
			procCpu.append(String.format(" %.1f%%", avg * 100));
		}
		jsonObject.put("Per core CPU", procCpu.toString());

		return jsonObject;
	}

	/**
	 * Inject processes json object
	 *
	 * @param os           os
	 * @param memory       memory
	 * @param threadNumber thread number
	 * @param sort         sort
	 * @return the json object
	 */
	public static JSONObject injectProcesses(OperatingSystem os, GlobalMemory memory, Integer threadNumber, OperatingSystem.ProcessSort sort) {
		JSONObject jsonObject = new JSONObject();
		JSONObject subJsonObject = new JSONObject();

		jsonObject.put("进程数", os.getProcessCount());
		jsonObject.put("线程数", os.getThreadCount());

		// Sort by highest CPU, 取 threadNumber 个进程
		List<OSProcess> procs = Arrays.asList(os.getProcesses(threadNumber, sort));
		for (int i = 0; i < procs.size() && i < threadNumber; i++) {
			OSProcess p = procs.get(i);
			String str = String.format(" %5d %5.1f %4.1f %9s %9s %s", p.getProcessID(),
					100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime(),
					100d * p.getResidentSetSize() / memory.getTotal(), FormatUtil.formatBytes(p.getVirtualSize()),
					FormatUtil.formatBytes(p.getResidentSetSize()), p.getName());
			subJsonObject.put(i + " PID  %CPU %MEM       VSZ       RSS Name", str);
		}

		jsonObject.put("进程详情", subJsonObject);

		return jsonObject;
	}

	/**
	 * Inject sensors json object
	 *
	 * @param sensors sensors
	 * @return the json object
	 */
	public static JSONObject injectSensors(Sensors sensors) {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("CPU Temperature: %.1f°C", sensors.getCpuTemperature());
		jsonObject.put("Fan Speeds", Arrays.toString(sensors.getFanSpeeds()));
		jsonObject.put("CPU Voltage: %.1fV", sensors.getCpuVoltage());

		return jsonObject;
	}

	/**
	 * Inject power sources json object
	 *
	 * @param powerSources power sources
	 * @return the json object
	 */
	public static JSONObject injectPowerSources(PowerSource[] powerSources) {
		JSONObject jsonObject = new JSONObject();

		StringBuilder sb = new StringBuilder("Power: ");
		if (powerSources.length == 0) {
			sb.append("Unknown");
		} else {
			double timeRemaining = powerSources[0].getTimeRemaining();
			if (timeRemaining < CONSTANT_1D) {
				sb.append("Charging");
			} else if (timeRemaining < CONSTANT_0D) {
				sb.append("Calculating date remaining");
			} else {
				sb.append(String.format("%d:%02d remaining", (int) (timeRemaining / 3600),
						(int) (timeRemaining / 60) % 60));
			}
		}
		for (PowerSource pSource : powerSources) {
			sb.append(String.format(" %s @ %.1f%%", pSource.getName(), pSource.getRemainingCapacity() * 100d));
		}
		jsonObject.put("PowerSource", sb.toString());
		return jsonObject;
	}

	/**
	 * Inject disks json object
	 *
	 * @param diskStores disk stores
	 * @return the json object
	 */
	public static JSONObject injectDisks(HWDiskStore[] diskStores) {
		JSONObject jsonObject = new JSONObject();
		JSONObject subJsonObject = new JSONObject();

		for (int i = 0; i < diskStores.length; i++) {
			HWDiskStore disk = diskStores[i];
			boolean readwrite = disk.getReads() > 0 || disk.getWrites() > 0;
			// 磁盘
			String str1 = String.format("(model: %s - S/N: %s) size: %s, reads: %s (%s), writes: %s (%s), xfer: %s ms",
					disk.getModel(),
					disk.getSerial(),
					disk.getSize() > 0 ? FormatUtil.formatBytesDecimal(disk.getSize()) : "?",
					readwrite ? disk.getReads() : "?", readwrite ? FormatUtil.formatBytes(disk.getReadBytes()) : "?",
					readwrite ? disk.getWrites() : "?", readwrite ? FormatUtil.formatBytes(disk.getWriteBytes()) : "?",
					readwrite ? disk.getTransferTime() : "?");
			subJsonObject.put(disk.getName(), str1);

			// 磁盘分区
			HWPartition[] partitions = disk.getPartitions();
			if (partitions == null) {
				// TODO Remove when all OS's implemented
				continue;
			} else {
				JSONObject grandSubJsonObject = new JSONObject();
				for (int j = 0; j < partitions.length; j++) {
					HWPartition part = partitions[j];
					String str2 = String.format("|-- %s: %s (%s) Maj:Min=%d:%d, size: %s%s", part.getIdentification(),
							part.getName(), part.getType(), part.getMajor(), part.getMinor(),
							FormatUtil.formatBytesDecimal(part.getSize()),
							part.getMountPoint().isEmpty() ? "" : " @", part.getMountPoint());
					grandSubJsonObject.put(part.getName(), str1);
				}
				subJsonObject.put(disk.getName() + ":P:" + i, grandSubJsonObject);
			}
		}

		jsonObject.put("Disks", subJsonObject);

		return jsonObject;
	}

	/**
	 * Inject file system json object
	 *
	 * @param fileSystem file system
	 * @return the json object
	 */
	public static JSONObject injectFileSystem(FileSystem fileSystem) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("File Descriptors", fileSystem.getOpenFileDescriptors() + " / " + fileSystem.getMaxFileDescriptors());

		OSFileStore[] fsArray = fileSystem.getFileStores();
		for (int i = 0; i < fsArray.length; i++) {
			OSFileStore fs = fsArray[i];
			long usable = fs.getUsableSpace();
			long total = fs.getTotalSpace();
			String str = String.format(
					" %s (%s) [%s] %s of %s free (%.1f%%) is %s "
							+ (fs.getLogicalVolume() != null && fs.getLogicalVolume().length() > 0 ? "[%s]" : "%s")
							+ " and is mounted at %s",
					fs.getName(), fs.getDescription().isEmpty() ? "file system" : fs.getDescription(), fs.getType(),
					FormatUtil.formatBytes(usable), FormatUtil.formatBytes(fs.getTotalSpace()), 100d * usable / total,
					fs.getVolume(), fs.getLogicalVolume(), fs.getMount());
			jsonObject.put(i + "-fileSystem", str);
		}

		return jsonObject;
	}

	/**
	 * Inject network interfaces json object
	 *
	 * @param networkIfs network ifs
	 * @return the json object
	 */
	public static JSONObject injectNetworkInterfaces(NetworkIF[] networkIfs) {
		JSONObject jsonObject = new JSONObject();
		for (NetworkIF net : networkIfs) {
			jsonObject.put("Name: %s (%s)", net.getName() + " / " + net.getDisplayName());
			jsonObject.put("MAC Address: %s", net.getMacaddr());
			jsonObject.put("MTU: %s, Speed: %s", net.getMTU() + " / " + FormatUtil.formatValue(net.getSpeed(), "bps"));
			jsonObject.put("IPv4: %s", Arrays.toString(net.getIPv4addr()));
			jsonObject.put("IPv6: %s", Arrays.toString(net.getIPv6addr()));
			boolean hasData = net.getBytesRecv() > 0 || net.getBytesSent() > 0 || net.getPacketsRecv() > 0
					|| net.getPacketsSent() > 0;
			String str = String.format("Traffic: received %s/%s%s; transmitted %s/%s%s",
					hasData ? net.getPacketsRecv() + " packets" : "?",
					hasData ? FormatUtil.formatBytes(net.getBytesRecv()) : "?",
					hasData ? " (" + net.getInErrors() + " err)" : "",
					hasData ? net.getPacketsSent() + " packets" : "?",
					hasData ? FormatUtil.formatBytes(net.getBytesSent()) : "?",
					hasData ? " (" + net.getOutErrors() + " err)" : "");
			jsonObject.put("Traffic", str);
		}
		return jsonObject;
	}

	/**
	 * Inject network parameters json object
	 *
	 * @param networkParams network params
	 * @return the json object
	 */
	public static JSONObject injectNetworkParameters(NetworkParams networkParams) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Host name: %s", networkParams.getHostName());
		jsonObject.put("Domain name: %s", networkParams.getDomainName());
		jsonObject.put("DNS servers: %s", Arrays.toString(networkParams.getDnsServers()));
		jsonObject.put("IPv4 Gateway: %s", networkParams.getIpv4DefaultGateway());
		jsonObject.put("IPv6 Gateway: %s", networkParams.getIpv6DefaultGateway());
		return jsonObject;
	}

	/**
	 * Inject displays json object
	 *
	 * @param displays displays
	 * @return the json object
	 */
	public static JSONObject injectDisplays(Display[] displays) {
		JSONObject jsonObject = new JSONObject();
		int i = 0;
		for (Display display : displays) {
			jsonObject.put(i + "-Display", display.toString());
			i++;
		}
		return jsonObject;
	}

	/**
	 * Inject usb devices json object
	 *
	 * @param usbDevices usb devices
	 * @return the json object
	 */
	public static JSONObject injectUsbDevices(UsbDevice[] usbDevices) {
		JSONObject jsonObject = new JSONObject();
		int i = 0;
		for (UsbDevice usbDevice : usbDevices) {
			jsonObject.put(i + "-UsbDevice", usbDevice.toString().trim());
			i++;
		}
		return jsonObject;
	}

	/**
	 * Gets system info *
	 *
	 * @return the system info
	 */
	@ReadOperation
	public Map<String, Object> getSystemInfo() {
		Map<String, Object> map = new LinkedHashMap<>();
		SystemInfo systemInfo = new SystemInfo();

		HardwareAbstractionLayer hardware = systemInfo.getHardware();
		OperatingSystem operatingSystem = systemInfo.getOperatingSystem();

		map.put("计算机基本信息", injectComputerSystem(hardware.getComputerSystem()));
		map.put("处理器基本信息", injectProcessor(hardware.getProcessor()));
		map.put("内存基本信息", injectMemory(hardware.getMemory()));
		map.put("CPU详细信息", injectCpu(hardware.getProcessor()));
		map.put("传感器信息", injectSensors(hardware.getSensors()));
		map.put("电源信息", injectPowerSources(hardware.getPowerSources()));
		map.put("磁盘信息", injectDisks(hardware.getDiskStores()));
		map.put("文件系统信息", injectFileSystem(operatingSystem.getFileSystem()));
		map.put("网络接口信息", injectNetworkInterfaces(hardware.getNetworkIFs()));
		map.put("网路参数", injectNetworkParameters(operatingSystem.getNetworkParams()));
		map.put("显示设备", injectDisplays(hardware.getDisplays()));
		map.put("USB设备-true", injectUsbDevices(hardware.getUsbDevices(true)));
		map.put("USB设备-false", injectUsbDevices(hardware.getUsbDevices(false)));

		// 计算比较耗时，建议单独写一个 endpoint
		MAP.put("线程详情", injectProcesses(operatingSystem, hardware.getMemory(), 10, OperatingSystem.ProcessSort.MEMORY));

		return map;
	}

}