package com.zn.web.ds.impl;

import org.apache.commons.dbcp.BasicDataSource;


/**
 * 默认数据源工厂
 * 基于apache comons dbcp
 * Created by zhoun on 2018/7/23.
 **/
public class DefaultDatasourceFactory extends AbstractDatasourceFactory<BasicDataSource> {

    @Override
    public BasicDataSource createDataSource() {
        return new BasicDataSource();
    }

    @Override
    public void setDriver(BasicDataSource ds, String driver) {
        ds.setDriverClassName(driver);
    }

    @Override
    public void setUrl(BasicDataSource ds, String url) {
        ds.setUrl(url);
    }

    @Override
    public void setUsername(BasicDataSource ds, String username) {
        ds.setUsername(username);
    }

    @Override
    public void setPassword(BasicDataSource ds, String password) {
        ds.setPassword(password);
    }

    @Override
    public void setAdvancedConfig(BasicDataSource ds) {
        ds.setValidationQuery("select 1 from dual");
    }

}
