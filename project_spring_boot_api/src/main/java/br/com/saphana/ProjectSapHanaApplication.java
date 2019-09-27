package br.com.saphana;

import br.com.saphana.models.Employee;
import br.com.saphana.service.EmployeeService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class ProjectSapHanaApplication {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(ProjectSapHanaApplication.class, args);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/plain")
    @ResponseBody
    String home() {
        StringBuilder builder = new StringBuilder();
        builder.append("Hello World !!");
        
        List<Employee> findAllEmployee = employeeService.findAllEmployee();

        // Remove comment if Hana database support is enabled.
//        builder.append("\n\nJDBC connection available: ");
//        try {
//            Connection conn = dataSource.getConnection();
//            if (conn != null) {
//                builder.append("yes");
//                builder.append("\n\nCurrent Hana DB user:\n");
//                String userName = getCurrentUser(conn);
//                builder.append(userName);
//                builder.append("\n\nCurrent Hana schema:\n");
//                builder.append(getCurrentSchema(conn));
//            } else {
//                builder.append("no");
//            }
//        } catch (SQLException e) {
//            builder.append("no");
//        }

        return builder.toString();
    }

    //  Remove comment if Hana database support is enabled.
    private String getCurrentUser(Connection conn) throws SQLException {
        String currentUser = "";
        PreparedStatement prepareStatement = conn.prepareStatement("SELECT CURRENT_USER \"current_user\" FROM DUMMY;");
        ResultSet resultSet = prepareStatement.executeQuery();
        int column = resultSet.findColumn("current_user");
        while (resultSet.next()) {
            currentUser += resultSet.getString(column);
        }
        return currentUser;
    }

    private String getCurrentSchema(Connection conn) throws SQLException {
        String currentSchema = "";
        PreparedStatement prepareStatement = conn.prepareStatement("SELECT CURRENT_SCHEMA \"current_schema\" FROM DUMMY;");
        ResultSet resultSet = prepareStatement.executeQuery();
        int column = resultSet.findColumn("current_schema");
        while (resultSet.next()) {
            currentSchema += resultSet.getString(column);
        }
        return currentSchema;
    }
}
