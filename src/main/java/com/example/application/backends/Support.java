package com.example.application.backends;

import java.math.BigDecimal;

public class Support {
    public BigDecimal Cx; //x coordinate
    public BigDecimal Cy; //y coordinate
    public short ST; //support type

    public Support(BigDecimal Cx, BigDecimal Cy, short ST) {
        this.Cx = Cx;
        this.Cy = Cy;
        this.ST = ST;
    }
}

    /* [Support Type]
    1: fixed (prevent displacement in x,y,z-axis, doesn't release force)
    2: pined (prevent displacement in x,y-axis, doesn't release force)
    3: roller (prevent displacement in y-axis, doesn't release force)
    4: vertical roller (prevent displacement in x-axis, doesn't release force)
    5: hinge (prevent displacement in z-axis, release force in z-axis)
    */