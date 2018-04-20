package sm.testability.samples.dashboard.businessLogic.inputs;

import sm.testability.samples.dashboard.businessLogic.businessRules.IncidentReportBO;
import lombok.Data;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table( name = "t_project" )
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "project_id")
    private int id;

    @Column(name = "project_name", nullable = false, length = 45)
    private String name;

    @Column(name = "portfolio_id", nullable = false)
    private int portfolioId;

    @OrderBy("endDate DESC")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "projectId")
    private Set<CycleSnap> cycleSnapSet = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "projectId")
    private List<ReleaseSnap> releaseSnaps = new LinkedList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "projectId")
    private List<IncidentsReport> incidentsReports = new LinkedList<>();

    public List<String> getCyclesStartDates() {
        Set<CycleSnap> cycles = getCycleSnapSet();
        List<String> datesAsString = new LinkedList<>();
        for (CycleSnap cycle : cycles) {
            String dateAsString = cycle.getStartDate();
            datesAsString.add(dateAsString);
        }

        return datesAsString;
    }

    public List<IncidentReportBO> getIncidentsAsBusinessObjects() {
        List<IncidentReportBO> incidents = new LinkedList<>();
        for (IncidentsReport report : incidentsReports) {
            incidents.add(report.getAsBusinessObject());
        }

        return incidents;
    }
}