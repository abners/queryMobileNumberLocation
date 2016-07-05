package com.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

public class HttpClientUtil {
	private static String URL = "http://apis.baidu.com/apistore/mobilephoneservice/mobilephone";
	public static String httpGet(String mobileNumber) throws UnsupportedEncodingException {
		StringBuilder resultStringBuilder = new StringBuilder("");
        BufferedReader in = null;
		try {

			URL url = new URL(URL+"?tel="+mobileNumber);
			// Get HTTP client
			 // �򿪺�URL֮�������
            URLConnection connection = url.openConnection();
            // ����ͨ�õ���������
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","");
            connection.setRequestProperty("apikey", "d126569c03f1075e9381beb504152100");
            // ����ʵ�ʵ�����
            connection.connect();
            // ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = connection.getHeaderFields();
//            // �������е���Ӧͷ�ֶ�
//            for (Entry<String, List<String>> entrySet: map.entrySet()) {
//                System.out.println(entrySet.getKey() + "--->" + entrySet.getValue());
//            }
            // ���� BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                resultStringBuilder .append( line);
            }
            String result=resultStringBuilder.toString();
            System.out.println(mobileNumber+":"+result);
			return result;
        } catch (Exception e) {
            System.out.println("����GET��������쳣��" + e);
            e.printStackTrace();
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
		return null;
	}
}
