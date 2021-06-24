package ua.study.bl.service.handlers;

import ua.study.entity.Report;
import ua.study.entity.Substance;
import ua.study.entity.enums.ReportStatus;
import ua.study.entity.enums.ReportType;

import java.sql.Timestamp;
import java.util.List;

public class Convertor implements Handler{
    private Handler next;
    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handle(Request request) {
        request.setReport(convertToReport(request.getSubstanceList()));
        if (next != null) {
            next.handle(request);
        }
    }

    public Report convertToReport(List<Substance> substanceList) {
        Report report = new Report();

        report.setReportType(ReportType.ENVIRONMENT_REPORT);
        report.setContent(substanceList.toString());
        report.setStatus(ReportStatus.NEW);
        report.setUpdated(new Timestamp(System.currentTimeMillis()));

        return report;
    }
}
