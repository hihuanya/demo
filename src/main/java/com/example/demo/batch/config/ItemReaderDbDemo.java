package com.example.demo.batch.config;




import com.example.demo.bean.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chuliuhuan
 * @date 2022-11-25 0:01
 */
@Configuration
@EnableBatchProcessing
@Slf4j
public class ItemReaderDbDemo {

    //注入任务对象工厂
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    //任务的执行由Step决定,注入step对象的factory
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    //创建Job对象
    @Bean
    public Job jobDemodb() {
        return jobBuilderFactory.get("jobDemodb")
                .start(step_db())
                .build();
    }

    //创建Step对象
    @Bean
    public Step step_db() {
        return stepBuilderFactory.get("step_db")
                .<Student, Student>chunk(2)
                .reader(jdbcReader())
                .writer(new ItemWriter<Student>() {
                    @Override
                    public void write(List<? extends Student> list) throws Exception {
                        System.out.println("输出list");
                        for(Student user:list){
//                            System.out.println(JSONObject.toJSONString(user));
                        }
                    }
                })
                .build();
    }


//    这里jdbcReader()是我们的核心，利用JdbcPagingItemReader读取数据
    @Bean
    public JdbcPagingItemReader<Student> jdbcReader() {
        JdbcPagingItemReader<Student> reader=new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);//设置数据源
        reader.setFetchSize(3); //设置每次抓取的数量(分页的大小)
        reader.setRowMapper(new RowMapper<Student>() { //转换器，将ResultSet 转换为User对象
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student user=new Student();
                user.setId((long) rs.getInt("id"));
                user.setName(rs.getString("name"));
                return user;
            }
        });

        MySqlPagingQueryProvider provider=new MySqlPagingQueryProvider();
        provider.setSelectClause("id,name");//需要返回的字段
        provider.setFromClause("from user"); //从什么表查
        provider.setWhereClause("where id >3"); //条件

        Map<String, Order> sort=new HashMap<>(); //排序
        sort.put("id", Order.DESCENDING);//设置降序
        provider.setSortKeys(sort);

        reader.setQueryProvider(provider);//设置sql查询相关信息
        return reader;
    }
}

