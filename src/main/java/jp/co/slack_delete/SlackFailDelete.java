package jp.co.slack_delete;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import jp.co.slack_delete.model.SlackFile;
import jp.co.slack_delete.model.SlackFileList;
import jp.co.slack_delete.utils.CommonUtils;

public class SlackFailDelete {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 E曜日");

	public static void main(String[] args) {

		// 自身のファイルリストを取得し、7日以前のファイルは削除する。
		SlackFileList sfl = getMyFileList();

		if (sfl != null && sfl.getOk() && sfl.getPaging().getTotal() > 0) {
			fileDeleteXDaysAgo(sfl);

			//			TODO 動作チェック出来てない。
			//			// ページ数が1件超過の場合、残ページ分処理。
			//			if (sfl.getPaging().getPages() > 1) {
			//				for (int i = 0; i > sfl.getPaging().getPages() - 1; i++) {
			//					fileDeleteXDaysAgo(getMyFileList());
			//				}
			//			}
		}

	}

	/**
	 * 取得したリストのファイル削除
	 *
	 * @param sfl
	 */
	private static void fileDeleteXDaysAgo(SlackFileList sfl) {
		if (sfl == null) {
			return;
		}

		for (SlackFile sf : sfl.getFiles()) {

			fileDelete(sf);
		}
	}

	/**
	 * ファイル削除
	 *
	 * @param sf
	 */
	private static void fileDelete(SlackFile sf) {

		if (sf == null) {
			return;
		}

		// TODO 削除ファイルをログファイルに外だし。
		System.out.println("削除ファイル名： " + sf.getName());
		System.out.println("作成日： " + sdf.format(sf.getCreatedDate()));

		// ファイルリスト取得
		String urlString = "https://slack.com/api/files.delete";

		// POSTするデータ
		String postStr = "token=" + CommonUtils.getProperties("slack_token");
		postStr += "&file=" + sf.getId();

		String s = runPostApi(urlString, postStr);
		Map<String, Object> map = new Gson().fromJson(s, Map.class);
		System.out.println("実行結果 ：" + map.get("ok"));

		System.out.println("/////////////////////////");
	}

	/**
	 * ファイルリスト取得
	 *
	 * @param page
	 * @return
	 */
	private static SlackFileList getMyFileList() {

		// ファイルリスト取得
		String urlString = "https://slack.com/api/files.list";

		// POSTするデータ
		String postStr = "token=" + CommonUtils.getProperties("slack_token");

		// ユーザ指定(option=all場合、他人のファイルも消せちゃうので注意。)
		// option=all以外の場合は、user_idを指定、nullの場合はnullのままリクエストしてリクエスト結果でエラーになる。
		String option = CommonUtils.getProperties("option");
		if (!StringUtils.equals(option, "all")) {
			String user = CommonUtils.getProperties("user_id");
			postStr += "&user=" + user;
		}

		// 日付(TO)指定
		long tsTo = getTsTo();
		if (tsTo > 0) {
			postStr += "&ts_to=" + tsTo;
		}

		// チャンネル指定
		String channel = CommonUtils.getProperties("channel_id");
		if (StringUtils.isNotBlank(channel)) {
			postStr += "&channel=" + channel;
		}

		String s = runPostApi(urlString, postStr);
		SlackFileList sfl = null;
		if (StringUtils.isNotBlank(s)) {
			sfl = new Gson().fromJson(s, SlackFileList.class);
		}

		return sfl;
	}

	/**
	 * POST API実行。<br>
	 * URL、ポストデータを取得して実行。<br>
	 * 結果をStringで返却する。
	 *
	 * @param urlString
	 * @param postStr
	 * @return
	 */
	private static String runPostApi(String urlString, String postStr) {

		if (StringUtils.isBlank(urlString)) {
			return null;
		}

		String res = null;

		try {
			URL url = new URL(urlString);
			URLConnection uc = url.openConnection();
			uc.setDoOutput(true);//POST可能にする

			uc.setRequestProperty("User-Agent", "@IT java-tips URLConnection");// ヘッダを設定
			uc.setRequestProperty("Accept-Language", "ja");// ヘッダを設定
			OutputStream os = uc.getOutputStream();//POST用のOutputStreamを取得

			if (StringUtils.isNotBlank(postStr)) {
				PrintStream ps = new PrintStream(os);
				ps.print(postStr);//データをPOSTする
				ps.close();
			}
			// POSTした結果を取得
			InputStream is = uc.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String s;

			// レスポンスのコンバート。
			while ((s = reader.readLine()) != null) {
				res = s;
			}

			reader.close();

		} catch (MalformedURLException e) {
			System.err.println("Invalid URL format: " + urlString);
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("Can't connect to " + urlString);
			System.exit(-1);
		}

		return res;
	}

	/**
	 * TOに指定する日付(数値)取得。
	 *
	 * @return
	 */
	private static long getTsTo() {
		long res = 0L;
		try {
			int i = Integer.parseInt(CommonUtils.getProperties("to_date"));

			if (i > 0) {
				// 指定日付
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				cal.add(Calendar.DAY_OF_MONTH, -i);

				// slackはYYYYMMDDhhmmssのlong値の為、割る1000(sss分)する。
				res = cal.getTime().getTime() / 1000;
			}
		} catch (NumberFormatException e) {
			// TODO ログ出力
			System.out.println("日付指定失敗");
		}
		return res;
	}
}
