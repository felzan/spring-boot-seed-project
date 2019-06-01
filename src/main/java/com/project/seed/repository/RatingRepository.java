package com.project.seed.repository;

import com.project.seed.Base;
import com.project.seed.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Transactional
@Repository
public class RatingRepository extends Base {

  private final JdbcTemplate db;

  @Autowired
  public RatingRepository(JdbcTemplate db) {this.db = db;}

  public Integer insert(Rating rating) throws Exception {
    try {

      KeyHolder keyHolder = new GeneratedKeyHolder();
      db.update(connection -> {
        PreparedStatement ps = connection
        .prepareStatement("INSERT INTO rating (customer_id, account_type, rating, comment, type_id) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        int i = 1;
        ps.setString(i++, rating.getCustomerId());
        ps.setString(i++, rating.getAccountType());
        ps.setFloat(i++, rating.getRating());
        ps.setString(i++, rating.getComment());
        ps.setInt(i++, rating.getTypeId());
        return ps;
      }, keyHolder);

      return keyHolder.getKey().intValue();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new Exception("Was not possible to insert.");
    }
  }
}
