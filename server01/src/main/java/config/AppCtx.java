package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.ReservationDao;

@Configuration
public class AppCtx {

    //< > 괄호 안은 내용에 맞게 변경해야함
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/reservationDB?characterEncoding=utf8"); //DB명
        ds.setUsername("ku_DSC_DB"); //ID
        ds.setPassword("ku_DSC_DB"); //password
        ds.setInitialSize(2);
        ds.setMaxActive(100);
        return ds;
    }

    @Bean
    public ReservationDao reservationDao() {
        return new ReservationDao(dataSource());
    }

}
