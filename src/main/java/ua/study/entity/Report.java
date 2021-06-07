package ua.study.entity;

import lombok.Getter;
import lombok.Setter;
import ua.study.entity.enums.ReportStatus;
import ua.study.entity.enums.ReportType;

import java.sql.Timestamp;

public class Report {
    @Setter @Getter
    private Long reportId;
    @Setter @Getter
    private ReportType reportType;
    @Setter @Getter
    private String content;
    @Setter @Getter
    private Timestamp updated;
    @Setter @Getter
    private ReportStatus status;

    public Report() {

    }

    public Report(ReportType reportType, String content, Timestamp updated, ReportStatus status) {
        this.reportType = reportType;
        this.content = content;
        this.updated = updated;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return reportId.equals(report.reportId) &&
                reportType == report.reportType &&
                content.equals(report.content) &&
                updated.equals(report.updated) &&
                status == report.status;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", reportType=" + reportType +
                ", content='" + content + '\'' +
                ", updated=" + updated +
                ", status=" + status +
                '}';
    }
}
