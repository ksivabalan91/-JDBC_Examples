package paf.revision.jdbctutorial.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class BasicCrud {
    @Autowired
    private JdbcTemplate template;

    //! QUERY STATEMENTS
    //* Useful for finding a list of single value items strings/integers/float
    public <T> List<T> findOneField (String selectField, String fromTable, Class<T> classType){
        String SQL = "select "+selectField+" from "+fromTable;
        return template.queryForList(SQL, classType);
    }
    
    //* able to accept multiple fields for equals operation in sql statement
    public <T> List<T> findItemBy (String selectField, String fromTable, String where, String equalsTo, Class<T> classType){
        String SQL="";
        try{
            SQL = "select "+selectField+" from "+fromTable+" where "+where+"="+equalsTo;
            return template.query(SQL, BeanPropertyRowMapper.newInstance(classType));
        } catch(Exception e){
            System.out.println(e.getMessage());
            SQL = "select "+selectField+" from "+fromTable+" where "+where+"="+"\""+equalsTo+"\"";
            return template.query(SQL, BeanPropertyRowMapper.newInstance(classType));
        }        
    }
    //* open ended where statement to include other operations
    public <T> List<T> findItems (String selectField, String fromTable, String where, Class<T> classType){
        String SQL = "select "+selectField+" from "+fromTable+" where "+where;
        return template.query(SQL, BeanPropertyRowMapper.newInstance(classType));
    }

    //* finds all items and maps to model object
    public <T> List<T> findall(String fromTable,Class<T> classType){
        return findOneField("*", fromTable, classType);
    }
    //* Generic object
    public <T> List<T> findAnything (String SQL, Class<T> classType){
        return template.query(SQL, BeanPropertyRowMapper.newInstance(classType));
    }

    
    //* takes any and every SQL statements and returns a map
    public List<Map<String,Object>> genericMap(String SQL){
        return template.queryForList(SQL);       
    }

    //! INSERT STATEMENTS
    //* insert items with partial information
    public int insert(String tableName, String fieldsList, String valuesList){        
        String SQL = "insert into "+tableName+"("+ fieldsList+") values("+valuesList+")";
        return template.update(SQL);
    }
    
    //* insert items with all information, auto increment id can be input as null
    public int insert(String tableName, String valuesList){
        String SQL = "insert into "+tableName+" values("+valuesList+")";
        return template.update(SQL);
    }
    
    //! DELETE STATMENTS
    public int deleteById(String tableName, String where, String equalsTo){
        try{
            String SQL = "delete from "+tableName+" where "+where+"="+equalsTo;
            return template.update(SQL);
        } catch(Exception ex){
            String SQL = "delete from "+tableName+" where "+where+"='"+equalsTo+"'";
            return template.update(SQL);            
        }
    }
}
