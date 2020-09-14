package home;



import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseController {
    private static Connection connection = null;

    public DatabaseController() {
    	try {
        	connection = DriverManager.getConnection("jdbc:sqlserver:" +
                    "//localhost:1433;databaseName=org_testdatabase",
            "sa", "sqlserverpassword");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void creat_database() throws SQLException {

        String sql = "create table organization(\n" +
                "\torg_name varchar(30) ,\n" +
                "\torg_id varchar(20) PRIMARY KEY\n" +
                "\t\n" +
                ");\n" +
                "\n" +
                "create table department (\n" +
                "\tdept_name varchar (30),\n" +
                "\tdept_id varchar (20) PRIMARY KEY,\n" +
                "\torg_id varchar (20),\n" +
                "\tdept_parentId varchar (20),\n" +
                "\tFOREIGN KEY (org_id) REFERENCES organization (org_id) ,\n" +
                "\tFOREIGN KEY (dept_parentId) REFERENCES department (dept_id) \n" +
                ");\n" +
                "\n" +
                "create table roles(\n" +
                "\trole_id varchar(20) PRIMARY KEY ,\n" +
                "\trole_name varchar(30) ,\n" +
                "\tdept_id varchar (20),\n" +
                "\tFOREIGN KEY (dept_id) REFERENCES department (dept_id) ON UPDATE CASCADE\n" +
                ");\n" +
                "\n" +
                "\n" +
                "create table staff(\n" +
                "\tst_personalCode varchar(20) PRIMARY KEY ,\n" +
                "\tst_employeeDate date,\n" +
                "\tst_name varchar(30),\n" +
                "\trole_id varchar(20),\n" +
                "    FOREIGN KEY (role_id) REFERENCES roles (role_id) ON UPDATE CASCADE\n" +
                "\n" +
                ");\n" +
                "\n" +
                "\n" +
                "create table element (\n" +
                "\telement_id varchar(20) PRIMARY KEY ,\n" +
                "\telement_name varchar(30),\n" +
                "\telement_show varchar(30),\n" +
                "\tst_personalCode varchar(20),\n" +
                "\tFOREIGN KEY (st_personalCode) REFERENCES staff (st_personalCode) ON UPDATE CASCADE,\n" +
                ");\n" +
                "\n" +
                "\n" +
                "create table destination_table(\n" +
                "\telement_id varchar (20) ,\n" +
                "\tdestination_elements_id varchar(20),\n" +
                "\tFOREIGN KEY (element_id) REFERENCES element (element_id),\n" +
                "\tFOREIGN KEY (destination_elements_id) REFERENCES element (element_id),\n" +
                "\tPRIMARY KEY (element_id, destination_elements_id)\n" +
                ");";


        Statement statement1 = null;
        try {
            statement1 = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, "APP", "ORGANIZATION", null);

        if(! tables.next()){
            System.out.println("tables already exist");
        }else {
            statement1 = connection.createStatement();
            statement1.execute(sql);
            statement1.close();
            System.out.println("data base created...");
        }


    }

    public void insertToOrganizationTable (String org_name , String org_id) throws SQLException {

        Statement statement1 = null;
        try {
            statement1 = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String q1 = "select * from organization where org_id = '" + org_id + "'";
        statement1 = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(q1);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            String query = null;
            try {
                if (org_name == null) {
                    query = "insert into organization(org_name,org_id) values(null , '" + org_id + "')";
                } else {
                    query = "insert into organization(org_name,org_id) values('" + org_name + "' , '" + org_id + "')";
                }
                statement1.execute(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("the organization you add with org id " +org_id +" is already exist in database");
        }
        statement1.close();
        
    }
    public void insertToDepartmanTable (String dept_name , String dept_id , String org_id , String deptParent_id) throws SQLException {
        Statement statement1 = null;

        try {
            statement1 = connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String q1 = "select * from department where dept_id = '" + dept_id + "'";
        statement1 = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(q1);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {

            String query = null;
            try {
                if (dept_name == null) {
                    query = "insert into department(dept_name,dept_id,org_id,dept_parentId) values" +
                            "(null , '" + dept_id + "','" + org_id + "' , '" + deptParent_id + ", )";
                } else if (deptParent_id == null && org_id == null) {
                    query = "insert into department(dept_name,dept_id,org_id,dept_parentId) values" +
                            "('" + dept_name + "' , '" + dept_id + "',null,  null )";
                } else if (org_id == null) {
                    query = "insert into department(dept_name,dept_id,org_id,dept_parentId) values" +
                            "('" + dept_name + "' , '" + dept_id + "',null, '" + deptParent_id + "' )";
                } else if (deptParent_id == null) {
                    query = "insert into department(dept_name,dept_id,org_id,dept_parentId) values" +
                            "('" + dept_name + "' , '" + dept_id + "','" + org_id + "' , null )";
                } else {
                    query = "insert into department(dept_name,dept_id,org_id,dept_parentId) values" +
                            "('" + dept_name + "' , '" + dept_id + "','" + org_id + "' , '" + deptParent_id + "' )";
                }

                statement1.execute(query);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("the departmet you add with dept_id " +dept_id +" is already exist in database");}
        statement1.close();

        
    }
    public void insertToStaffTable (String st_personalCode , String st_employeeDate , String st_name ,String role_id) throws SQLException {
        Statement statement1 = null;
        try {
            statement1 = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String q1 = "select * from staff where st_personalCode = '" + st_personalCode + "'";
        statement1 = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(q1);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {

            String query = null;

            try {
                if (st_employeeDate == null) {
                    query = "insert into staff(st_personalCode,st_employeeDate,st_name ,role_id) values" +
                            "('" + st_personalCode + "' , null,'" + st_name + "' , '" + role_id + "' )";
                } else if (st_name == null) {
                    query = "insert into staff(st_personalCode,st_employeeDate,st_name ,role_id) values" +
                            "('" + st_personalCode + "' , '" + st_employeeDate + "', null , '" + role_id + "' )";
                } else if (role_id == null) {
                    query = "insert into staff(st_personalCode,st_employeeDate,st_name ,role_id) values" +
                            "('" + st_personalCode + "' , '" + st_employeeDate + "','" + st_name + "' , null)";
                } else {
                    query = "insert into staff(st_personalCode,st_employeeDate,st_name ,role_id) values" +
                            "('" + st_personalCode + "' , '" + st_employeeDate + "','" + st_name + "' , '" + role_id + "' )";
                }

                statement1.execute(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("the staff you add with st_personalCode" +st_personalCode +" is already exist in database");}
        statement1.close();
        
    }
    public void insertToRoleTable(String role_id , String role_name , String dept_id ) throws SQLException {
        Statement statement1 = null;
        try {
            statement1 = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String q1 = "select * from roles where role_id = '" + role_id  + "'";
        statement1 = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(q1);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {

            String query = null;
            try {
                if (role_name == null) {
                    query = "insert into roles(role_id,role_name,dept_id) values" +
                            "('" + role_id + "' , null,'" + dept_id + "' )";
                } else if (dept_id == null) {
                    query = "insert into roles(role_id,role_name,dept_id) values" +
                            "('" + role_id + "' , '" + role_name + "',null )";
                } else {
                    query = "insert into roles(role_id,role_name,dept_id) values" +
                            "('" + role_id + "' , '" + role_name + "','" + dept_id + "' )";
                }

                statement1.execute(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("the role you add with role_id " +role_id +" is already exist in database");}
        statement1.close();
        
    }
    public void  insertToElementTable(String element_id , String element_name , String element_show , String st_personalId) throws SQLException {
        Statement statement1 = null;
        try {
            statement1 = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String q1 = "select * from element where element_id = '" + element_id  + "'";
        statement1 = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(q1);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {

            String query = null;
            try {
                if (element_name == null) {
                    query = "insert into element(element_id,element_name,element_show," +
                            "st_personalCode) values" +
                            "('" + element_id + "' , null,'" + element_show + "','" + st_personalId + "' )";
                } else if (element_show == null) {
                    query = "insert into element(element_id,element_name,element_show," +
                            "st_personalCode) values" +
                            "('" + element_id + "' , '" + element_name + "', null ,'" + st_personalId + "' )";
                } else if (st_personalId == null) {
                    query = "insert into element(element_id,element_name,element_show," +
                            "st_personalCode) values" +
                            "('" + element_id + "' , '" + element_name + "','" + element_show + "',null )";
                } else {
                    query = "insert into element(element_id,element_name,element_show," +
                            "st_personalCode) values" +
                            "('" + element_id + "' , '" + element_name + "','" + element_show + "','" + st_personalId + "' )";
                }

                statement1.execute(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("the element you add with element_id " +element_id +" is already exist in database");
        }
        statement1.close();
        
    }

    public void insertToDestinationTable (String element_id  , String destination_element_id )  throws SQLException {
        Statement statement1 = null;
        try {
            statement1 = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String q1 = "select * from destination_table where element_id = '" + element_id  + "' and destination_elements_id = '"+destination_element_id+"'";
        statement1 = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(q1);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            String query = null;
            try {
                query = "insert into destination_table(element_id , [destination_elements_id]) values"+
                        "('"+element_id+"' ,'"+ destination_element_id+"' )";
                statement1.execute(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("the destination element you add with element_id " +element_id +" is already exist in database");
        }
        statement1.close();


    }


    //update query fot set orgnazation id of departmaan
    public void updateOrganizationIdInDepartment(String departmentId , String organization_id) throws SQLException {
        Statement statement1 = null;
        try {
            statement1 = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = null;
        try {
            query = "update department set org_id='"+organization_id+"' where dept_id= '"+departmentId +"'";
            statement1.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        statement1.close();
        
    }
    // update query for set st_personalCode for task
    public void updatePersonalCodeForelement( String taskID , String st_personalCode) throws SQLException {
        Statement statement1 = null;
        try {
            statement1 = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = null;
        try {
            query = "update element set st_personalCode='"+st_personalCode+"' where elelemt_id="+taskID;
            statement1.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        statement1.close();
        
    }
    // update query for set st_personalCode for task
    public void updateDeptIdForRole( String role_id , String dept_id) throws SQLException {
        Statement statement1 = null;
        try {
            statement1 = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = null;
        try {
            query = "update roles set role_id='"+role_id+"' where dept_id="+dept_id;
            statement1.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        statement1.close();
        
    }

    public void updateRollIdInStaff( String role_id , String st_personal_code) throws SQLException {
        Statement statement1 = null;
        try {
            statement1 = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = null;
        try {
            query = "update staff set role_id='"+st_personal_code+"' where role_id="+role_id;
            statement1.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        statement1.close();
        
    }

    public void updateAllForeignKey(String organization_id , String departmentId  , String role_id , String st_personal_code ,String taskID) throws SQLException {
        //nemidooonm in method khoobi bashe ya na !
        updateOrganizationIdInDepartment(departmentId,organization_id);
        updateDeptIdForRole(role_id , departmentId);
        updateRollIdInStaff(role_id , st_personal_code);
        updatePersonalCodeForelement(taskID , st_personal_code);
    }
    public ArrayList<ArrayList<String> > getNameAndIdOfColumnInTable(String tableName) throws SQLException {
        ArrayList<ArrayList<String> > result =
                new ArrayList<ArrayList<String> >();
        Statement statement1 = null;
        try {
            statement1 = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = null;
        ResultSet rs =null;
        try {
            if(tableName.equals("department")){
                query="select dept_id , dept_name from department";
                PreparedStatement pst = connection.prepareStatement(query);
                rs = pst.executeQuery();
                int j=0;
                while (rs.next()) {
                    // and add to array
                    ArrayList<String> temp  =new ArrayList<>();
                    temp.add(rs.getString("dept_id"));
                    temp.add(rs.getString("dept_name"));
                    result.add(temp);


                }
            }else if(tableName.equals("organization")){
                query= "select org_id,org_name from organization";
                PreparedStatement pst = connection.prepareStatement(query);
                rs = pst.executeQuery();

                while (rs.next()) {
                    // and add to array
                    ArrayList<String> temp  =new ArrayList<>();
                    temp.add(rs.getString("org_id"));
                    temp.add(rs.getString("org_name"));
                    result.add(temp);


                }
            }else if(tableName.equals("element")){

                query="select element_id , element_show from element where element_name= 'task'";
                PreparedStatement pst = connection.prepareStatement(query);
                rs = pst.executeQuery();
                while (rs.next()) {
                    // and add to array
                    ArrayList<String> temp  =new ArrayList<>();
                    temp.add(rs.getString("element_id"));
                    temp.add(rs.getString("element_show"));
                    result.add(temp);


                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        statement1.close();


        return result;
    }

    public ArrayList<ArrayList<String>> get_departmentOfOrganization ( String orgId) throws SQLException {
        ArrayList<ArrayList<String> > result =
                new ArrayList<ArrayList<String> >();
        String query ="select dept_id , dept_name \n" +
                "from department\n" +
                "where org_id = '"+orgId+"'";
        Statement statement1 = null;
        statement1 = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
         while (rs.next()) {

             ArrayList<String> temp = new ArrayList<>();
        temp.add(rs.getString("dept_id")) ;
        temp.add(rs.getString("dept_name"));
        result.add(temp);

        }

        statement1.close();

        return result;
    }


    public ArrayList<ArrayList<String>> get_elementsOfdepartment ( String deptid) throws SQLException {
        ArrayList<ArrayList<String> > result = new ArrayList<ArrayList<String> >();
        String query ="select element_id , element_show\n" +
                "from element join staff on element.st_personalCode = staff.st_personalCode\n" +
                "\t\t\t join roles on staff.role_id = roles.role_id\n" +
                "\t\t\t join department on roles.dept_id = department.dept_id \n" +
                "\t\t\t join organization on department.org_id = organization.org_id\n" +
                "where department.dept_id='"+deptid+"'";
        Statement statement1 = null;
        statement1 = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

            ArrayList<String> temp = new ArrayList<>();
            temp.add(rs.getString("element_id")) ;
            temp.add(rs.getString("element_show"));
            result.add(temp);

        }

        statement1.close();

        return result;
    }

    public ArrayList<ArrayList<String>> get_tasksOfdepartment ( String deptid) throws SQLException {
        ArrayList<ArrayList<String> > result = new ArrayList<ArrayList<String> >();
        String query ="select element_id , element_show\n" +
                "from element join staff on element.st_personalCode = staff.st_personalCode\n" +
                "\t\t\t join roles on staff.role_id = roles.role_id\n" +
                "\t\t\t join department on roles.dept_id = department.dept_id \n" +
                "\t\t\t join organization on department.org_id = organization.org_id\n" +
                "where department.dept_id='"+deptid+"' and element_name = 'Task' ";
        Statement statement1 = null;
        statement1 = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

            ArrayList<String> temp = new ArrayList<>();
            temp.add(rs.getString("element_id")) ;
            temp.add(rs.getString("element_show"));
            result.add(temp);

        }

        statement1.close();

        return result;
    }

    public ArrayList<ArrayList<String>> get_startEventsOfdepartment ( String deptid) throws SQLException {
        ArrayList<ArrayList<String> > result = new ArrayList<ArrayList<String> >();
        String query ="select element_id , element_show\n" +
                "from element join staff on element.st_personalCode = staff.st_personalCode\n" +
                "\t\t\t join roles on staff.role_id = roles.role_id\n" +
                "\t\t\t join department on roles.dept_id = department.dept_id \n" +
                "\t\t\t join organization on department.org_id = organization.org_id\n" +
                "where department.dept_id='"+deptid+"' and element_name = 'StartEvent' ";
        Statement statement1 = null;
        statement1 = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

            ArrayList<String> temp = new ArrayList<>();
            temp.add(rs.getString("element_id")) ;
            temp.add(rs.getString("element_show"));
            result.add(temp);

        }

        statement1.close();

        return result;
    }
    public ArrayList<ArrayList<String>> get_EndEventsOfdepartment ( String deptid) throws SQLException {
        ArrayList<ArrayList<String> > result = new ArrayList<ArrayList<String> >();
        String query ="select element_id , element_show\n" +
                "from element join staff on element.st_personalCode = staff.st_personalCode\n" +
                "\t\t\t join roles on staff.role_id = roles.role_id\n" +
                "\t\t\t join department on roles.dept_id = department.dept_id \n" +
                "\t\t\t join organization on department.org_id = organization.org_id\n" +
                "where department.dept_id='"+deptid+"' and element_name = 'EndEvent' ";
        Statement statement1 = null;
        statement1 = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

            ArrayList<String> temp = new ArrayList<>();
            temp.add(rs.getString("element_id")) ;
            temp.add(rs.getString("element_show"));
            result.add(temp);

        }

        statement1.close();

        return result;
    }
    public ArrayList<ArrayList<String>> get_gatewayOfdepartment ( String deptid) throws SQLException {
        ArrayList<ArrayList<String> > result = new ArrayList<ArrayList<String> >();
        String query ="select element_id , element_show\n" +
                "from element join staff on element.st_personalCode = staff.st_personalCode\n" +
                "\t\t\t join roles on staff.role_id = roles.role_id\n" +
                "\t\t\t join department on roles.dept_id = department.dept_id \n" +
                "\t\t\t join organization on department.org_id = organization.org_id\n" +
                "where department.dept_id='"+deptid+"' and element_name = 'Gateway' ";
        Statement statement1 = null;
        statement1 = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

            ArrayList<String> temp = new ArrayList<>();
            temp.add(rs.getString("element_id")) ;
            temp.add(rs.getString("element_show"));
            result.add(temp);

        }

        statement1.close();

        return result;
    }


    // get org id of element
    public String get_orgId_ofElement(String element_id) throws SQLException {
        String result = null;
        String query = "select  org_id " +
                        "from (((department inner join organization on department.org_id = organization.org_id)" +
                        " inner join roles on roles.dept_id = department.dept_id)" +
                        " inner join staff on staff.role_id = roles.role_id)" +
                        "inner join element on element.st_personalCode = staff.st_personalCode" +
                        " where element_id = '"+ element_id +"'" ;

        Statement statement1 = null;

            statement1 = connection.createStatement();



            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
           // while (rs.next()) {
                // in ishala ke bayad ye doone bashe
                result =rs.getString("org_id");

            //}

        statement1.close();
        
        return result;

    }
    // get dept_id of element
    public String get_deptid_deptname_ofElement(String element_id , int nameOrgid) throws SQLException {

        String result = null;
        String query= "select department.dept_id , department.dept_name \n" +
                "from department join organization on department.org_id = organization.org_id\n" +
                "join roles on roles.dept_id = department.dept_id\n" +
                "join staff on staff.role_id = roles.role_id\n" +
                "join element on element.st_personalCode = staff.st_personalCode\n" +
                "where element_id = '"+element_id+"'";

        Statement statement1 = null;
        statement1 = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

            if ( nameOrgid==0){
                result =rs.getString("dept_id");

            }else if (nameOrgid==1){
                result =rs.getString("dept_name");
            }
        }
        statement1.close();
        return result;

    }




    public String get_orgid__ofdepartment( String dept_id) throws SQLException {//ye array ozve avalesh org id va dovom org name
        String result = null;
        String query = "select  organization.org_id as org_id " +
                "from department join organization on department.org_id = organization.org_id" +
                " where dept_id = '"+ dept_id +"'";

        Statement statement1 = null;
            statement1 = connection.createStatement();
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
             while (rs.next()) {
            result =rs.getString("org_id");
            }
        statement1.close();
        
        return result;
    }
    public String get_orgname__ofdepartment( String dept_id) throws SQLException {//ye array ozve avalesh org id va dovom org name
        String result = null;
        String query = "select  organization.org_name as org_name " +
                "from (department right join organization on department.org_id = organization.org_id)" +
                " where department.dept_id = "+ dept_id ;

        Statement statement1 = null;
        statement1 = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
         while (rs.next()) {

        result =rs.getString("org_name");
        }
        statement1.close();

        return result;
    }

    public String getDeptNameOfdeptid(String deptid) throws SQLException {
        String result = null;
        String query = "select dept_name" +
                "from department " +
                "where dept_id  = '"+deptid+"'";

        Statement statement1 = null;
        statement1 = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            result =rs.getString("dept_name");
        }
        statement1.close();
        return result;
    }

    //tamome destination ha ye elemnt ro mide /*peida kardane dest*/
    /*peida kardane source*/
    public ArrayList<ArrayList<String> > get_sourcesofElement ( String element_id) throws SQLException {
        ArrayList<ArrayList<String> > result = new ArrayList<ArrayList<String> >();
        Statement statement1 = connection.createStatement();
        String query = null;
        query = "select   element_show ,destination_table.element_id " +
                        "from (destination_table join element on element.element_id = destination_table.element_id)" +
                        " where destination_table.destination_elements_id = '"+element_id+"' ";
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            ArrayList<String> temp = new ArrayList<>();
            temp.add(rs.getString("element_id")) ;
            temp.add(rs.getString("element_show"));
            result.add(temp);

        }
        return result;
    }


    //tamome destination ha ye elemnt ro mide /*peida kardane dest*/
    public ArrayList<ArrayList<String> > get_destination_ofElement ( String element_id) throws SQLException {
        ArrayList<ArrayList<String> > result = new ArrayList<ArrayList<String> >();
        Statement statement1 = connection.createStatement();
        String query = null;
        query = "select   element_show ,destination_table.destination_elements_id " +
                        " from (destination_table join element on element.element_id = destination_table.destination_elements_id)  " +
                        "where destination_table.element_id = '"+ element_id+"' " ;
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            ArrayList<String> temp = new ArrayList<>();
            temp.add(rs.getString("destination_elements_id")) ;
            temp.add(rs.getString("element_show"));
            result.add(temp);

        }
        return result;
    }


//
//
//
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        DataBaseController dataBaseController = new DataBaseController();
//        dataBaseController.creat_database();
//        dataBaseController.insertToOrganizationTable("ogranization1" , "1");
//        dataBaseController.insertToOrganizationTable("ogranization2" , "2");
//        dataBaseController.insertToOrganizationTable("ogranization3" , "3");
//        dataBaseController.insertToOrganizationTable("ogranization4" , "4");
//       dataBaseController.insertToDepartmanTable("department1", "1" ,"1",null);
//       dataBaseController.insertToDepartmanTable("department2", "2" ,"2",null);
//       dataBaseController.insertToDepartmanTable("department3", "3" ,"3",null);
//       dataBaseController.insertToDepartmanTable("department4", "4" ,"4",null);
//       dataBaseController.insertToRoleTable("1" ,"role1","1");
//       dataBaseController.insertToRoleTable("2" ,"role2","2");
//       dataBaseController.insertToRoleTable("3" ,"role3","3");
//       dataBaseController.insertToRoleTable("4" ,"role4","4");
//     dataBaseController.insertToStaffTable("1",null,"name1","1");
//     dataBaseController.insertToStaffTable("2",null,"name2","2");
//     dataBaseController.insertToStaffTable("3",null,"name3","3");
//     dataBaseController.insertToStaffTable("4",null,"name4","4");
//
//
//        dataBaseController.connection.close();// ino bayad hame ja bezari tahe estefade az data base
//
//
//
//        System.out.println("finish");
//
//
//    }
}