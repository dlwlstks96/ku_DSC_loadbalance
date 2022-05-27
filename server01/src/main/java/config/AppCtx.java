package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.TicketDao;

@Configuration
public class AppCtx {

    //< > 괄호 안은 내용에 맞게 변경해야함
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {

        //
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/ticketingService?characterEncoding=utf8");
        ds.setUsername("ku_DSC_test01");
        ds.setPassword("test01");
        ds.setInitialSize(2);
        ds.setMaxActive(100);
        return ds;
    }

    @Bean
    public TicketDao ticketDao() {
        return new TicketDao(dataSource());
    }

}
