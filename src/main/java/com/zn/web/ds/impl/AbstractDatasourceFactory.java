package com.zn.web.ds.impl;

import com.zn.web.core.ConfigHelper;
import com.zn.web.ds.DataSourceFactory;

import javax.sql.DataSource;


/**
 * 抽象数据源工厂
 * Created by zhoun on 2018/7/23.
 **/
public abstract class AbstractDatasourceFactory<T extends DataSource> implements DataSourceFactory {

    protected final String driver = ConfigHelper.getString("smart.framework.jdbc.driver");
    protected final String url = ConfigHelper.getString("smart.framework.jdbc.url");
    protected final String username = ConfigHelper.getString("smart.framework.jdbc.username");
    protected final String password = ConfigHelper.getString("smart.framework.jdbc.password");

    @Override
    public final T getDataSource() {
        //创建数据源
        T ds=createDataSource();
        //设置基础属性
        setDriver(ds,driver);
        setUrl(ds,url);
        setUsername(ds, username);
        setPassword(ds, password);
        // 设置高级属性
        setAdvancedConfig(ds);
        return ds;

    }

    public abstract T createDataSource();

    public abstract void setDriver(T ds, String driver);

    public abstract void setUrl(T ds, String url);

    public abstract void setUsername(T ds, String username);

    public abstract void setPassword(T ds, String password);

    public abstract void setAdvancedConfig(T ds);

}
