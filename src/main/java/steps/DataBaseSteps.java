package steps;

import lombok.extern.slf4j.Slf4j;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.datasource.DataSourceBuilder;
import org.postgresql.Driver;

import java.io.FileInputStream;
import java.util.Optional;
import java.util.Properties;


@Slf4j
public class DataBaseSteps extends BaseMethods {

    private ServerRuntime cayenneRuntime;

    private ServerRuntime dataBaseConnection(String login,
                                            String password,
                                            String url){

        //По умолчанию будет использоваться test среда
        String enviroment = Optional.ofNullable(System.getProperty("env")).orElse("dev");

        try {
            FileInputStream fis;
            Properties property = new Properties();
            fis = new FileInputStream("src/main/resources/"+enviroment+"/application.properties");
            property.load(fis);
            this.cayenneRuntime = ServerRuntime.builder()
                    .addConfig(""+enviroment+"/cayenne-project.xml")
                    .dataSource(DataSourceBuilder.url(property.getProperty(url))
                            .driver(Driver.class.getName())
                            .userName(property.getProperty(login))
                            .password(property.getProperty(password))
                            .pool(1, 3).build())
                    .build();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return this.cayenneRuntime;
    }

}
