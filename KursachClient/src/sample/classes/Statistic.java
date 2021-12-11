package sample.classes;

public class Statistic {
    public int id;
    public int idCompany;
    public String name;
    public String date;
    public double cost;

    public Statistic(int id, int idCompany, String name, String date, double cost)
    {
        this.id = id;
        this.idCompany = idCompany;
        this.name = name;
        this.date = date;
        this.cost = cost;
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

    public String getDate()
    {
        return this.date;
    }

    public double getCost()
    {
        return this.cost;
    }

}
