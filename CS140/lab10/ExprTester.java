package lab10;

import static org.junit.Assert.*;
import org.junit.Test;

public class ExprTester {

    @Test
    public void simpleNum() {
        Expr e = new Num(4);
        assertEquals("4", e.toString());
    }

    @Test
    public void simpleAdd() {
        Expr e = new Add(
            new Num(5),
            new Num(5)
            );
        assertEquals("(5 + 5)", e.toString());
    }

    @Test
    public void simpleNeg() {
        Expr e1 = new Neg(
            new Num(5)
            );
        assertEquals("neg(5)", e1.toString());
    }

    @Test
    public void complexAdd() {
        Expr e = new Add(
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
        assertEquals("((1 + (10 + 1)) + (((3 + 3) + 7) + 1))", e.toString());
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
        Expr all = new Add(
            new Add(a,
                    b),
            c
            );
        assertEquals("((((1 + (10 + 1)) + (((3 + 3) + 7) + 1)) + ((2 + (3 + (2 * 3))) * (neg(0) * 10))) + (neg(neg(1)) * 10))", all.toString());
    }
}

