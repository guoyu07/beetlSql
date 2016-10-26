package xyz.hrz.test.beetlsql;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.druid.DruidPlugin;
import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Created by paul on 2016/10/26.
 */
public class Main {

    public static DataSource getDataSource() {
        Prop p = PropKit.use("test.txt");
        DruidPlugin druidPlugin = new DruidPlugin(p.get("dbUrl"), p.get("dbName"), p.get("dbPwd"));
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }


    public static SQLManager getSqlManager() {
        ConnectionSource source = ConnectionSourceHelper.getSingle(getDataSource());
        DBStyle mysql = new MySqlStyle();
        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql/mysql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion下划线风格的
        UnderlinedNameConversion nc = new UnderlinedNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[]{new
                DebugInterceptor()});
        return sqlManager;
    }


    public static void main(String[] args) {
        //直接使用sql
        //List<HashMap> re =getSqlManager().execute(new SQLReady("select * from category"), HashMap.class);

        String pName = "xyz.hrz.gen";

        // 代码生成
        GenConfig config = new GenConfig();
        config.preferBigDecimal(true);

        try {

            //getSqlManager().genSQLFile("user");//根据表名 生成md格式的sql文件
            //getSqlManager().genPojoCode("pic",pName,config);//根据类名 生成跟数据库表名(设置的表名忽略大小写  生成的实体类名首字母大写)对应的实体对象

            //根据md文件名(约定为类名)跟方法标识
            //HashMap param = new HashMap();
            //param.put("name","paul");
            //System.out.println(getSqlManager().select("users.select", HashMap.class,param));//简单查询
            //System.out.println(getSqlManager().intValue("users.allcount",param));//查询结果转换

            //分页
            //PageQuery query = new PageQuery();
            //query.setParas(param);
            //getSqlManager().pageQuery("users.select",HashMap.class,query);
            //System.out.println(query.getList());
            //System.out.println(query.getPageSize());
            //System.out.println(query.getTotalRow());

            //getSqlManager().genALL(pName,config,null);  //整个数据库生成
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
