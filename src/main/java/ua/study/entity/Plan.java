package ua.study.entity;

import lombok.Getter;
import lombok.Setter;

public class Plan {
    @Setter @Getter
    private Long planId;
    @Setter @Getter
    private Emergency emergency;
    @Setter @Getter
    private String steps;

    public Plan() {

    }

    public Plan(Emergency emergency, String steps) {
        this.emergency = emergency;
        this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return planId.equals(plan.planId) &&
                emergency.equals(plan.emergency) &&
                steps.equals(plan.steps);
    }

    @Override
    public String toString() {
        return "Plan{" +
                "planId=" + planId +
                ", emergency=" + emergency +
                ", steps='" + steps + '\'' +
                '}';
    }
}
