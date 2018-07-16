package com.zn.web.mvc.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装批量文件上传对象
 * Created by zhoun on 2018/7/16.
 **/
public class Multiparts {

    private List<Multipart> multipartList = new ArrayList<Multipart>();

    public Multiparts(List<Multipart> multipartList) {
        this.multipartList = multipartList;
    }

    public int size() {
        return multipartList.size();
    }

    public List<Multipart> getAll() {
        return multipartList;
    }

    public Multipart getOne() {
        return size() == 1 ? multipartList.get(0) : null;
    }

}
