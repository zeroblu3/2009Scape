package org.runite;
import java.io.File;
import java.math.BigInteger;

/**
 * A class with configurations stored.
 * @author Emperor
 * 
 */
public final class Configurations {

	/**
	 * The client build.
	 */
	public static final int CLIENT_BUILD = 530;

	/**
	 * The sub build.
	 */
	public static final int SUB_BUILD = 1;
	
	/**
	 * If the client is local.
	 */
	public static boolean LOCAL_MS = true;
	public static boolean LOCAL_SERVER = true;

	/**
	 * Packaging.
	 */
	public static final String PACKAGE_JAGEX = "org.runite.jagex";

	/**
	 * The cache name.
	 */
	public static final String CACHE_NAME = File.separator + ".runite_" + CLIENT_BUILD;

	/**
	 * The MS IP.
	 */
	public static String MS_IP = Configurations.LOCAL_MS ? "127.0.0.1" : Client.PUBLIC_IP_ADDRESS;

	/**
	 * IF RSA is enabled.
	 */
	public static final boolean USE_RSA = false;

	/**
	 * IF we use ISAAC cipher.
	 */
	public static final boolean USEISAAC = false;
	
	/**
	 * The cache path.
	 * @return the path.
	 */
	public static String getCachePath() { 
		final String OS = System.getProperty("os.name").toUpperCase();
		if (OS.contains("WIN")) {
			return new StringBuilder(System.getProperty("user.home") + CACHE_NAME).toString();
		} else if (OS.contains("MAC")) {
			return new StringBuilder(System.getProperty("user.home") + CACHE_NAME).toString();
		} else if (OS.contains("NUX")) {
			return System.getProperty("user.home") + CACHE_NAME;
		}
		return new StringBuilder(System.getProperty("user.dir")).toString() + CACHE_NAME;
	}

}