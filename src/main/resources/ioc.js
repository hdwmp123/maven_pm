var ioc = {
    dao : {
        type : "org.nutz.dao.impl.NutDao",
        args : [{refer:"dataSource"}]
    },
    dataSource : {
        type : "org.nutz.dao.impl.SimpleDataSource",
        fields : {
            jdbcUrl : 'jdbc:mysql://localhost:3306/pm?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=UTF8&amp;characterSetResults=UTF8',
            username : 'root',
            password : 'root'
        }
    }
};