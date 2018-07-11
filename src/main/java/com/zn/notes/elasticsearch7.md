---
layout: posts
title: ELASTICSEARCH:使用restful api操作数据
date: 2017-09-28 09:59:23
tags: elasticsearch
categories: elasticsearch
---

windows环境,我这里用git客户端操作curl命令(因为格式之类的问题，cmd会报错，git可以直接复制官网的例子进行操作学习)
https://www.elastic.co/guide/en/elasticsearch/reference/2.3/docs-index_.html
#### 添加数据:
    $ curl -XPUT 'http://localhost:9200/twitter/tweet/1' -d '{
        "user" : "kimchy",
        "post_date" : "2009-11-15T14:12:12",
        "message" : "trying out Elasticsearch"
    }'
<!--more-->     
#### 修改数据:
(更新是先获取文档，删除旧文件，更改_source属性，把它当做新的文件来索引,通过_update的一个脚本来实现)
    curl -XPOST 'localhost:9200/test/type1/1/_update' -d '{
        "script" : {
            "inline": "ctx._source.counter += count",
            "params" : {
                "count" : 4
            }
        }
    }'
#### 删除数据:
     curl -XDELETE 'http://localhost:9200/twitter/tweet/1'
#### 检索数据:
    curl -XGET 'http://localhost:9200/twitter/tweet/1'

			