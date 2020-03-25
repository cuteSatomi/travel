package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid, String rname) {
        String sql = "SELECT COUNT(*) FROM tab_route WHERE 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List list = new ArrayList();
        if (cid != 0) {
            sb.append(" AND cid = ? ");
            list.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sb.append(" AND rname LIKE ? ");
            list.add("%" + rname + "%");
        }

        sql = sb.toString();

        return template.queryForObject(sql, Integer.class, list.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
//        String sql = "SELECT * FROM tab_route WHERE cid = ? LIMIt ? ,? ";

        String sql = "SELECT * FROM tab_route WHERE 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List list = new ArrayList();
        if (cid != 0) {
            sb.append(" AND cid = ? ");
            list.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sb.append(" AND rname LIKE ? ");
            list.add("%" + rname + "%");
        }
        sb.append(" LIMIT ?, ? ");
        list.add(start);
        list.add(pageSize);

        sql = sb.toString();
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), list.toArray());
    }
}
