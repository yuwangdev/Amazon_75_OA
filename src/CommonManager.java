import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CommonManager {
    static class Employee {
        String name;
        List<Employee> reporters;

        public Employee(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static Employee solve(Employee ceo, Employee firstEmployee, Employee secondEmployee) {
        if (ceo == null || firstEmployee == null || secondEmployee == null)
            return null;
        if (ceo.name.equalsIgnoreCase(firstEmployee.name) || ceo.name.equalsIgnoreCase(secondEmployee.name))
            return null;
        if (!hasPath(ceo, firstEmployee) && hasPath(ceo, secondEmployee))
            return null;

        Queue<Employee> queue = new LinkedList<>();
        queue.offer(ceo);
        Employee result = null;
        while (!queue.isEmpty()) {
            Employee currentEmployee = queue.poll();
            if (hasPath(currentEmployee, firstEmployee) && hasPath(currentEmployee, secondEmployee)) {
                result = currentEmployee;
                for (Employee em : currentEmployee.reporters) {
                    queue.offer(em);
                }
            }
        }
        return result.name.equalsIgnoreCase(ceo.name) ? null : result;
    }

    private static boolean hasPath(final Employee manager, final Employee employee) {
        if (manager == null)
            return false;
        if (manager.name.equals(employee.name))
            return true;
        if (manager.reporters == null)
            return false;

        boolean hasPath = false;
        for (Employee em : manager.reporters) {
            hasPath = (hasPath || hasPath(em, employee)) ? true : false;
        }
        return hasPath;
    }

    public static void main(String[] args) {
        Employee samir = new Employee("samir");
        Employee dom = new Employee("dom");
        Employee michael = new Employee("michael");

        Employee peter = new Employee("peter");
        Employee porter = new Employee("porter");
        Employee bob = new Employee("bob");

        dom.reporters = Arrays.asList(bob, peter, porter);

        Employee milton = new Employee("milton");
        Employee nina = new Employee("nina");

        peter.reporters = Arrays.asList(milton, nina);

        Employee bill = new Employee("bill");
        bill.reporters = Arrays.asList(dom, samir, michael);

        System.out.println(solve(bill, milton, nina) + " ==peter");
        System.out.println(solve(bill, nina, porter) + " ==dom");
        System.out.println(solve(bill, nina, samir) + " ==null (bill ceo)");
        System.out.println(solve(bill, peter, nina) + " ==peter");
    }
}