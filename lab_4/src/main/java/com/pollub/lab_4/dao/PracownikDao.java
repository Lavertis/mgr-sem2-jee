package com.pollub.lab_4.dao;

import com.pollub.lab_4.beans.Pracownik;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PracownikDao {
    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template; // wstrzyknięcie przez metodę set
    }

    public int save(Pracownik p) {
        String sql = "insert into pracownik (nazwisko, pensja, firma) values('" +
                p.getNazwisko() +
                "'," +
                p.getPensja() +
                ",'" +
                p.getFirma() +
                "')";
        return template.update(sql);
    }

    public int update(Pracownik p) {
        String sql = "update pracownik set nazwisko='" +
                p.getNazwisko() +
                "', pensja=" +
                p.getPensja() +
                ", firma='" +
                p.getFirma() +
                "' where id=" +
                p.getId();
        return template.update(sql);
    }

    public List<Pracownik> getAll() {
        return template.query("select * from pracownik",
                (rs, row) -> {
                    Pracownik e = new Pracownik();
                    e.setId(rs.getInt(1));
                    e.setNazwisko(rs.getString(2));
                    e.setPensja(rs.getFloat(3));
                    e.setFirma(rs.getString(4));
                    return e;
                }
        );
    }

    public Pracownik getById(int id) {
        String sql = "select * from pracownik where id=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Pracownik.class), id);
    }

    public int delete(int id) {
        String sql = "delete from pracownik where id=" + id;
        return template.update(sql);
    }
}
