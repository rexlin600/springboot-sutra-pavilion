package xyz.rexlin600.common.util;

/**
 * 坐标转换工具
 *
 * @author hekunlin
 * @since 2020-08-07
 *
 * <p> 百度坐标（BD09）、国测局坐标（火星坐标，GCJ02）、和WGS84坐标系之间的转换的工具 参考 https://github.com/wandergis/coordtransform 实现的Java版本
 */
@SuppressWarnings("unused")
public class CoordinateTransformUtil {

	/**
	 * X pi
	 */
	static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
	/**
	 * pi
	 */
	static double pi = 3.1415926535897932384626;
	/**
	 * 长半轴
	 */
	static double a = 6378245.0;
	/**
	 * 扁率
	 */
	static double ee = 0.00669342162296594323;

	/**
	 * 将原本坐标系的经纬度转换成新的坐标系的经纬度
	 *
	 * @param newCoordinateType      新坐标系，如baidu，wgs84
	 * @param originalCoordinateType 原坐标系，如baidu，wgs84
	 * @param lat                    原纬度
	 * @param lon                    原经度
	 * @return 新坐标系的经纬度 double [ ]
	 */
	public static double[] convertLatLonByCoordinate(String newCoordinateType, String originalCoordinateType, double lat, double lon) {
		if (originalCoordinateType == null) {
			return null;
		}

		boolean bd09ToWgs84 = ("bd09".equalsIgnoreCase(originalCoordinateType) || "baidu".equalsIgnoreCase(originalCoordinateType)
				&& ("google".equalsIgnoreCase(newCoordinateType)) || "wgs84".equalsIgnoreCase(newCoordinateType));
		boolean gcj02toWgs84 = ("gaode".equalsIgnoreCase(originalCoordinateType) || "gcj02".equalsIgnoreCase(originalCoordinateType)
				&& ("google".equalsIgnoreCase(newCoordinateType)) || "wgs84".equalsIgnoreCase(newCoordinateType));

		boolean wgs84ToBd09 = ("google".equalsIgnoreCase(originalCoordinateType) || "wgs84".equalsIgnoreCase(originalCoordinateType))
				&& ("bd09".equalsIgnoreCase(newCoordinateType) || "baidu".equalsIgnoreCase(newCoordinateType));
		boolean gcj02ToBd09 = ("gaode".equalsIgnoreCase(originalCoordinateType) || "gcj02".equalsIgnoreCase(originalCoordinateType))
				&& ("bd09".equalsIgnoreCase(newCoordinateType) || "baidu".equalsIgnoreCase(newCoordinateType));

		boolean wgs84ToGcj02 = ("google".equalsIgnoreCase(originalCoordinateType) || "wgs84".equalsIgnoreCase(originalCoordinateType))
				&& ("gaode".equalsIgnoreCase(newCoordinateType) || "gcj02".equalsIgnoreCase(newCoordinateType));
		boolean bd09ToGcj02 = ("bd09".equalsIgnoreCase(originalCoordinateType) || "baidu".equalsIgnoreCase(originalCoordinateType))
				&& ("gaode".equalsIgnoreCase(newCoordinateType) || "gcj02".equalsIgnoreCase(newCoordinateType));

		if (originalCoordinateType.equals(newCoordinateType)) {
			return new double[]{lat, lon};
		} else if (bd09ToWgs84) {
			return bd09towgs84(lon, lat);
		} else if (gcj02toWgs84) {
			return gcj02towgs84(lon, lat);
		} else if (wgs84ToBd09) {
			return wgs84tobd09(lon, lat);
		} else if (gcj02ToBd09) {
			return gcj02tobd09(lon, lat);
		} else if (wgs84ToGcj02) {
			return wgs84togcj02(lon, lat);
		} else if (bd09ToGcj02) {
			return bd09togcj02(lon, lat);
		} else {
			return null;
		}
	}

	/**
	 * 百度坐标系(BD-09)转WGS坐标
	 *
	 * @param lng 百度坐标纬度
	 * @param lat 百度坐标经度
	 * @return WGS84坐标数组 double [ ]
	 */
	public static double[] bd09towgs84(double lng, double lat) {
		double[] gcj = bd09togcj02(lng, lat);
		return gcj02towgs84(gcj[0], gcj[1]);
	}

	/**
	 * WGS坐标转百度坐标系(BD-09)
	 *
	 * @param lng WGS84坐标系的经度
	 * @param lat WGS84坐标系的纬度
	 * @return 百度坐标数组 double [ ]
	 */
	public static double[] wgs84tobd09(double lng, double lat) {
		double[] gcj = wgs84togcj02(lng, lat);
		return gcj02tobd09(gcj[0], gcj[1]);
	}

	/**
	 * 火星坐标系(GCJ-02)转百度坐标系(BD-09)
	 * <p>
	 * 谷歌、高德——>百度
	 *
	 * @param lng 火星坐标经度
	 * @param lat 火星坐标纬度
	 * @return 百度坐标数组 double [ ]
	 */
	public static double[] gcj02tobd09(double lng, double lat) {
		double z = Math.sqrt(lng * lng + lat * lat) + 0.00002 * Math.sin(lat * x_pi);
		double theta = Math.atan2(lat, lng) + 0.000003 * Math.cos(lng * x_pi);
		double bdLng = z * Math.cos(theta) + 0.0065;
		double bdLat = z * Math.sin(theta) + 0.006;
		return new double[]{bdLng, bdLat};
	}

	/**
	 * 百度坐标系(BD-09)转火星坐标系(GCJ-02)
	 * <p>
	 * 百度——>谷歌、高德
	 *
	 * @param bdLon 百度坐标经度
	 * @param bdLat 百度坐标纬度
	 * @return 火星坐标数组 double [ ]
	 */
	public static double[] bd09togcj02(double bdLon, double bdLat) {
		double x = bdLon - 0.0065;
		double y = bdLat - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
		double ggLng = z * Math.cos(theta);
		double ggLat = z * Math.sin(theta);
		return new double[]{ggLng, ggLat};
	}

	/**
	 * WGS84转GCJ02(火星坐标系)
	 *
	 * @param lng WGS84坐标系的经度
	 * @param lat WGS84坐标系的纬度
	 * @return 火星坐标数组 double [ ]
	 */
	public static double[] wgs84togcj02(double lng, double lat) {
		if (outOfChina(lng, lat)) {
			return new double[]{lng, lat};
		}
		double dlat = transformlat(lng - 105.0, lat - 35.0);
		double dlng = transformlng(lng - 105.0, lat - 35.0);
		double radlat = lat / 180.0 * pi;
		double magic = Math.sin(radlat);
		magic = 1 - ee * magic * magic;
		double sqrtmagic = Math.sqrt(magic);
		dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * pi);
		dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * pi);
		double mglat = lat + dlat;
		double mglng = lng + dlng;
		return new double[]{mglng, mglat};
	}

	/**
	 * GCJ02(火星坐标系)转GPS84
	 *
	 * @param lng 火星坐标系的经度
	 * @param lat 火星坐标系纬度
	 * @return WGS84坐标数组 double [ ]
	 */
	public static double[] gcj02towgs84(double lng, double lat) {
		if (outOfChina(lng, lat)) {
			return new double[]{lng, lat};
		}
		double dlat = transformlat(lng - 105.0, lat - 35.0);
		double dlng = transformlng(lng - 105.0, lat - 35.0);
		double radlat = lat / 180.0 * pi;
		double magic = Math.sin(radlat);
		magic = 1 - ee * magic * magic;
		double sqrtmagic = Math.sqrt(magic);
		dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * pi);
		dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * pi);
		double mglat = lat + dlat;
		double mglng = lng + dlng;
		return new double[]{lng * 2 - mglng, lat * 2 - mglat};
	}

	/**
	 * 纬度转换
	 *
	 * @param lng lng
	 * @param lat lat
	 * @return double
	 */
	public static double transformlat(double lng, double lat) {
		double ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
		ret += (20.0 * Math.sin(6.0 * lng * pi) + 20.0 * Math.sin(2.0 * lng * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(lat * pi) + 40.0 * Math.sin(lat / 3.0 * pi)) * 2.0 / 3.0;
		ret += (160.0 * Math.sin(lat / 12.0 * pi) + 320 * Math.sin(lat * pi / 30.0)) * 2.0 / 3.0;
		return ret;
	}

	/**
	 * 经度转换
	 *
	 * @param lng lng
	 * @param lat lat
	 * @return double
	 */
	public static double transformlng(double lng, double lat) {
		double ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
		ret += (20.0 * Math.sin(6.0 * lng * pi) + 20.0 * Math.sin(2.0 * lng * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(lng * pi) + 40.0 * Math.sin(lng / 3.0 * pi)) * 2.0 / 3.0;
		ret += (150.0 * Math.sin(lng / 12.0 * pi) + 300.0 * Math.sin(lng / 30.0 * pi)) * 2.0 / 3.0;
		return ret;
	}

	/**
	 * 判断是否在国内，不在国内不做偏移
	 *
	 * @param lng lng
	 * @param lat lat
	 * @return boolean
	 */
	public static boolean outOfChina(double lng, double lat) {
		double a = 72.004;
		double b = 137.8347;
		if (lng < a || lng > b) {
			return true;
		} else {
			return lat < 0.8293 || lat > 55.8271;
		}
	}

}
