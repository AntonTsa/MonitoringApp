package ua.study.bl.service.handlers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.study.entity.Report;
import ua.study.entity.Substance;

import java.util.List;

@NoArgsConstructor
public class Request {
    @Getter @Setter
    private List<Substance> substanceList;
    @Setter @Getter
    private Report report;
}
