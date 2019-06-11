package assignment07;

import static org.junit.Assert.*;
import org.junit.Test;

public class ExprTester {

    @Test
    public void simpleAdd() {
        assertEquals(10, new Add(new Num(5), new Num(5)).eval());
    }

    @Test
    public void simpleNeg() {
        assertEquals(0, new Neg(new Num(5)).eval());
        assertEquals(1, new Neg(new Num(0)).eval());
        assertEquals(0, new Neg(new Num(1)).eval());
    }

    @Test
    public void complexAdd() {
        assertEquals(26,
                     new Add(
                         new Add(
                             new Num(1),
                             new Add(
                                 new Num(10),
                                 new Num(1)
                                 )
                             ),
                         new Add(
                             new Add(
                                 new Add(
                                     new Num(3),
                                     new Num(3)
                                     ),
                                 new Num(7)
                                 ),
                             new Num(1)
                             )
                         ).eval());
    }

    @Test
    public void complexMixed() {
        Expr a = new Add(
            new Add(
                new Num(1),
                new Add(
                    new Num(10),
                    new Num(1)
                    )
                ),
            new Add(
                new Add(
                    new Add(
                        new Num(3),
                        new Num(3)
                        ),
                    new Num(7)
                    ),
                new Num(1)
                )
            );
        Expr b = new Mul(
            new Add(
                new Num(2),
                new Add(
                    new Num(3),
                    new Mul(
                        new Num(2),
                        new Num(3)
                        )
                    )
                ),
            new Mul(
                new Neg(
                    new Num(0)
                    ),
                new Num(10)
                )
            );
        Expr c = new Mul(
            new Neg(
                new Neg(
                    new Num(1)
                    )
                ),
            new Num(10)
            );
        assertEquals(146,
                     new Add(
                         new Add(a,
                                 b),
                         c
                         ).eval());
    }
}