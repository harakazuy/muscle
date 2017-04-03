package com.example;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class BaseballTeamRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Team> rowMapper = (rs, i) -> {
		Team team = new Team();
		team.setId(rs.getInt("id"));
		team.setLeagueName(rs.getString("league_name"));
		team.setTeamName(rs.getString("team_name"));
		team.setHeadquarters(rs.getString("headquarters"));
		team.setInauguration(rs.getString("inauguration"));
		team.setHistory(rs.getString("history"));
		return team;
	};

	public List<Team> findAll(){
		System.out.println("xxxx");
		String sql = "";
		sql += "select * ";
		sql += "from baseball_teams ";
		sql += "order by team_name";
		List<Team> teamlist = template.query(sql, rowMapper);
		return teamlist;

	}

	public Team findById(Integer id){
		String sql = "";
		sql += "select id, league_name, team_name, headquarters, inauguration, history ";
		sql += "from baseball_teams ";
		sql += "where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Team team = template.queryForObject(sql, param, rowMapper);
		return team;
	}

}
