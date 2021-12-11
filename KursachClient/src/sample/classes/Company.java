package sample.classes;

public class Company {
    public int id;
    public int idOwner;
    public String name;
    public String field;

    public Company(int id, int idOwner, String name, String field)
    {
        this.id = id;
        this.idOwner = idOwner;
        this.name = name;
        this.field = field;
    }

    public Company(int id, String name, String field)
    {
        this.id = id;
        this.name = name;
        this.field = field;
    }

    public int getId()
    {
        return this.id;
    }

    public int getIdOwner()
    {
        return this.idOwner;
    }

    public String getName()
    {
        return this.name;
    }

    public String getField()
    {
        return this.field;
    }
}