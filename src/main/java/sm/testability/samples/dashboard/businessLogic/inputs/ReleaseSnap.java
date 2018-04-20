package sm.testability.samples.dashboard.businessLogic.inputs;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_release_snap")
public class ReleaseSnap {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "release_id")
    private int id;

    @Column(name = "release_date", columnDefinition = "DATE", nullable = false)
    private String releaseDate;


    @Column(name = "name", nullable = false, length = 200)
    private String releaseName;

    private int projectId;
}