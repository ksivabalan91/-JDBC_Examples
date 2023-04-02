package paf.revision.jdbctutorial.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//! for addtion SQL methods

@Repository
public class NorthwindRepo extends BasicCrud {

    @Autowired
    JdbcTemplate template;

    
    
}
