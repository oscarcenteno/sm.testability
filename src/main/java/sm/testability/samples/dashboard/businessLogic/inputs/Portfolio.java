package sm.testability.samples.dashboard.businessLogic.inputs;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_portfolio")
public class Portfolio {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "portfolio_id")
    private int portfolioId;

    @Column(name = "portfolio_name", nullable = false, length = 200)
    private String portfolioName;
}

