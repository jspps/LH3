package com.learnhall.metadata;

import com.bowlong.sql.freemarker.SK_Generate;
import com.learnhall.content.AppContext;

public class DBBuilderLH {
	public static void main(String[] args) throws Exception {
		buildEntityByDesign();
		// buildEntityByCfg();
		System.exit(1);
	}

	static void buildEntityByDesign() throws Exception {
		String cfgPath = ""; // "src/com/monster/metadata/template/"
		SK_Generate.runByMySql(AppContext.dsDesign(), AppContext.class,
				"src/com/war/db", cfgPath, false);
	}

	static void buildEntityByCfg() throws Exception {
		SK_Generate.runByMySql(AppContext.dsCfg(), AppContext.class,
				"src/com/war/db", "", true);
	}
}
