package sm.testability.samples.dashboard.businessLogic.businessRules;

import lombok.Data;

@Data
public class IncidentReportBO {

    private String incidentsDate;
    private int totalIncidents;

    public IncidentReportBO(String incidentsDate, int totalIncidents) {
        this.incidentsDate = incidentsDate;
        this.totalIncidents = totalIncidents;
    }
}
