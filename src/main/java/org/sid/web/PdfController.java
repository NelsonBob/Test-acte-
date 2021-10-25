package org.sid.web;

/**
 *
 * @author Winnie
 */
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.sid.repository.ActeRepository;
import org.sid.repository.UserRepository;
import org.sid.service.UserServiceJasper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PdfController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserServiceJasper userServiceJasper;

    @Autowired
    ActeRepository acteRepository;

    @GetMapping("/getUsersListAsPdf")
    public void exportUser(ModelAndView model, HttpServletResponse response)
            throws IOException, JRException, SQLException {
        JasperPrint jasperPrint = null;
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"ListUsers.pdf\""));

        OutputStream out = response.getOutputStream();
        jasperPrint = userServiceJasper.exportPdfFileUser();

        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
    }

    @RequestMapping(value = "/acte/pdf/{id}", method = RequestMethod.POST)
    public void export(ModelAndView model, @PathVariable("id") Long id, HttpServletResponse response)
            throws IOException, JRException, SQLException {

        JasperPrint jasperPrint = null;
        if (acteRepository.findByID(id) != null) {
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"act.pdf\""));

            OutputStream out = response.getOutputStream();
            jasperPrint = userServiceJasper.exportPdfFile(id);
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        }
    }

}
