package xyz.rexlin600.oss.storage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Storage service
 *
 * @author hekunlin
 */
public interface StorageService {

	/**
	 * Upload string
	 *
	 * @param data     data
	 * @param fileName file name
	 * @param path     path
	 * @return the string
	 * @throws IOException io exception
	 */
	String upload(byte[] data, String fileName, String path) throws IOException;

	/**
	 * Upload string
	 *
	 * @param inputStream input stream
	 * @param fileName    file name
	 * @param path        path
	 * @return the string
	 * @throws IOException io exception
	 */
	String upload(InputStream inputStream, String fileName, String path) throws IOException;

	/**
	 * Download input stream
	 *
	 * @param key key
	 * @return the input stream
	 * @throws IOException io exception
	 */
	InputStream download(String key) throws IOException;

	/**
	 * Download *
	 *
	 * @param key  key
	 * @param path path
	 * @throws InterruptedException interrupted exception
	 * @throws IOException          io exception
	 */
	void download(String key, String path) throws InterruptedException, IOException;

	/**
	 * Delete *
	 *
	 * @param key key
	 * @throws IOException io exception
	 */
	void delete(String key) throws IOException;

}