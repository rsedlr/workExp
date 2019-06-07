package com.sky.atom.test.reisstest;

import com.sky.atom.test.reisstest.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

@RestController
public class OtherController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String query = "SELECT * FROM public.tbl";
    private final String add = "INSERT INTO public.tbl(type, platform) VALUES (?, ?);";
    private final String del = "DELETE FROM public.tbl WHERE type LIKE 'SOMERANDOMTYPE%'";

    private Random rand = new Random();

    @RequestMapping("/data")
    public List<Device> data() {
        List<Device> devices = jdbcTemplate.query(this.query, new DeviceRowMapper());
        return devices;
    }

    @RequestMapping("/add/{amount}")
    public RedirectView add(@PathVariable int amount) {
        System.out.println(amount);
        if (amount <= 0) { amount = 10; }
        for (int i=0; i <= amount; i++) {
            jdbcTemplate.update(add, ("SOMERANDOMTYPE" + rand.nextInt(amount << 6)),
                                     ("SOMERANDOMPLATFORM" +  rand.nextInt(amount << 6)));
        }
        System.out.println("***************************** added randoms to db *****************************");
        return new RedirectView("/");
    }

    @RequestMapping("/delRand")
    public RedirectView deleteRandoms() {
        jdbcTemplate.execute(del);
        System.out.println("***************************** deleted randoms from db *****************************");
        return new RedirectView("/");
    }

    @RequestMapping(value = "/postData", method = RequestMethod.POST)
    public RedirectView postData(@RequestParam String type, @RequestParam String platform) throws Exception {
        jdbcTemplate.update(add, type, platform);
        System.out.println("***************************** added (" + type + ", " + platform + ") to db *****************************");
        return new RedirectView("/");
    }

    @RequestMapping(value = "/delData", method = RequestMethod.POST)
    public RedirectView remData(@RequestParam String type, @RequestParam String platform) throws Exception {
        List<Device> devices = jdbcTemplate.query("SELECT * FROM public.tbl WHERE type LIKE '" + type + "' AND platform LIKE '" + platform + "'", new DeviceRowMapper());
        if (!devices.isEmpty()) {
            jdbcTemplate.update("DELETE FROM public.tbl WHERE type LIKE '" + type + "' AND platform LIKE '" + platform + "'");
            System.out.println("***************************** deleted (" + type + ", " + platform + ") from db *****************************");
            return new RedirectView("/");
        } else {
            return new RedirectView("/error");
        }
    }

    @RequestMapping(value = "/delAll", method = RequestMethod.GET)
    public RedirectView remAll() throws Exception {
        jdbcTemplate.update("DELETE FROM public.tbl WHERE type LIKE '%'");
        System.out.println("***************************** deleted ALL from db *****************************");
        return new RedirectView("/");
    }
}

class DeviceRowMapper implements RowMapper<Device> {
    @Override
    public Device mapRow(ResultSet rs, int rowNum) throws SQLException {
        Device device = new Device();
        device.setType(rs.getString("type"));
        device.setPlatform(rs.getString("platform"));
        return device;
    }
}