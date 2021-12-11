package sample.classes;

public class Employee {
    public int id;
    public int idCompany;
    public String name;
    public String surname;
    public String department;
    public int salary;

    public Employee(int id, int idCompany, String name, String surname,String department ,int salary)
    {
        this.id = id;
        this.idCompany = idCompany;
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
    }

    public Employee(int id, String name, String surname,String department ,int salary)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
    }
    public int getId()
    {
        return this.id;
    }

    public int getIdCompany()
    {
        return this.idCompany;
    }

    public String getName()
    {
        return this.name;
    }

    public String getDepartment()  {return this.department;}

    public String getSurname()
    {
        return this.surname;
    }

    public double getSalary()
    {
        return this.salary;
    }

}
