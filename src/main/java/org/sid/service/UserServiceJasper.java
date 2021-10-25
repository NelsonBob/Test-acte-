package org.sid.service;

/**
 *
 * @author Winnie
 */
import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class UserServiceJasper {

    @Autowired
    private UserServiceImpl userDao;

    public JasperPrint exportPdfFile(Long id) throws SQLException, JRException, IOException {
        return userDao.exportPdfFile(id);
    }

    public JasperPrint exportPdfFileUser() throws SQLException, JRException, IOException {
        return userDao.exportPdfFileUser();
    }
}
