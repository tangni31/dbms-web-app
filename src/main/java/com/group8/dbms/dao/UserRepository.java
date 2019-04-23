package com.group8.dbms.dao;

import com.group8.dbms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<User, String> {


    @Query(value = "select to_char(yelping_since, 'yyyy'), count(*) " +
                    " from user_ " +
                    " group by to_char(yelping_since, 'yyyy') " +
                    " order by to_char(yelping_since, 'yyyy')",
            nativeQuery = true)
    List<Object[]> registerUserNumByYear();

    @Query(value = "select to_char(yelping_since, 'yyyy-mm'), count(*) " +
            " from user_ " +
            " where to_char(yelping_since, 'yyyy')=?1 " +
            " group by to_char(yelping_since, 'yyyy-mm') " +
            " order by to_char(yelping_since, 'yyyy-mm')",
            nativeQuery = true)
    List<Object[]> registerUserNumByMonth(String year);

    @Query(value = " select count(user_id) from user_ ", nativeQuery = true)
    int countTotalUser();

}
