class PersonSort implements java.util.Comparator<Person>
{
    @Override
    public int compare(Person first, Person second)
    {
        return first.getId() - second.getId();
    }
}