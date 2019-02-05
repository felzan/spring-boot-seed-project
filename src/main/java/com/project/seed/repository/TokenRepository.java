package com.project.seed.repository;

import com.project.seed.Base;
import com.project.seed.model.Token;
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
public class TokenRepository extends Base {

  private final JdbcTemplate db;

  @Autowired
  public TokenRepository(JdbcTemplate db) {this.db = db;}

  public Token insert(Token token) throws Exception {
    try {

      KeyHolder keyHolder = new GeneratedKeyHolder();
      db.update(connection -> {
        PreparedStatement ps = connection
        .prepareStatement("INSERT INTO token (token) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, token.getToken());
        return ps;
      }, keyHolder);

      token.setId(keyHolder.getKey().intValue());
      return token;
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new Exception("Was not possible to insert.");
    }
  }
}
