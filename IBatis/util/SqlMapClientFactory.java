package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapClientFactory {
	public static SqlMapClient getSqlMapClient() {
		SqlMapClient smc = null;
		Reader rd = null;
		
		try {
			Charset charset = Charset.forName("utf-8");
			Resources.setCharset(charset);

			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/sqlMapconfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(rd != null) try {rd.close();} catch(IOException e) {}
		}
		return smc;
	}
}
