package JDAbot.Logic;

public class SampleClass
{
    public static int sv;
    public int v;
    public void setValues(int value)
    {
        sv = value ;
        v = 2 * sv;
    }
    public static void main(String [] args)
    {
        SampleClass c1 = new SampleClass();
        SampleClass c2 = c1;
        SampleClass c3 = new SampleClass();
        SampleClass c4 = c3;

        c1.setValues(1);
        c4.setValues(2);
        c2.setValues(3);
        c3.setValues(4);
        System.out.println(c1.sv);
        System.out.println(c1.v);
        System.out.println(c2.sv);
        System.out.println(c2.v);
        System.out.println(c3.sv);
        System.out.println(c3.v);
        System.out.println(c4.sv);
        System.out.println(c4.v);
    }
}