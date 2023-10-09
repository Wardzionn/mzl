package pl.lodz.p.it.ssbd2023.ssbd04.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "managers")
@DiscriminatorValue("MANAGER")
@NamedQueries( {
        @NamedQuery(name = "Manager.findAll", query = "SELECT d FROM Manager d"),
        @NamedQuery(name = "Manager.findAllAccountsThatAreManager", query = "SELECT d.account FROM Manager d"),
        @NamedQuery(name = "Manager.getTeam", query = "SELECT d FROM Manager d WHERE d.team = :team"),
})
public class Manager extends Role {

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    public Manager(Team team) {
        this.setRoleType(RoleType.MANAGER);
        this.team = team;
    }

    public Manager() {
        this.setRoleType(RoleType.MANAGER);
    }
}
