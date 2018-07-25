package com.zn.web.ds;

import javax.sql.DataSource;

/**
 * 数据源工厂
 * Created by zhoun on 2018/7/23.
 **/
public interface DataSourceFactory {

    DataSource getDataSource();

}
