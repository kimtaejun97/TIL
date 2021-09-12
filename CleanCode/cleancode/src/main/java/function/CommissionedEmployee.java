package function;

public class CommissionedEmployee extends Employee {
    public CommissionedEmployee(EmployeeRecord r) {
        super();
    }

    @Override
    public long calculatePay() {
        return 0;
    }
}
