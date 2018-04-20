package sm.testability.samples.dashboard;

import sm.testability.samples.dashboard.businessLogic.inputs.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DashboardProjectRepository extends JpaRepository<Project, Integer> {
}