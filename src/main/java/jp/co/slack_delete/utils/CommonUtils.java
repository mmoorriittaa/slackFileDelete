package jp.co.slack_delete.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class CommonUtils {

	public static String getProperties(String key) {

		String res = "";

				Properties properties = new Properties();
				String file = "./conf/slack_delete.properties";

				try {
					InputStream inputStream = new FileInputStream(file);
					properties.load(inputStream);
					inputStream.close();

					// 値の取得
					res = properties.getProperty(key);

				} catch (Exception ex) {
					System.out.println(ex.getMessage());

				}

		return res;

	}
}
