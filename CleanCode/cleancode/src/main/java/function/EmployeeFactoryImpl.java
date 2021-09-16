package function;

public class EmployeeFactoryImpl implements EmployeeFactory{
    private final String COMMISSIONED = "commisioned";
    private final String HOURLY = "hourly";
    private final String SALARIED = "salaried";


    @Override
    public Employee makeEmployee(EmployeeRecord r) throws Exception {
        switch (r.getType()){
            case COMMISSIONED:
                return new CommissionedEmployee(r);
            case HOURLY:
                return new HourlyEmployee(r);
            case SALARIED:
                return new SalariedEmployee(r);
            default:
                throw new Exception();
        }
    }

}
