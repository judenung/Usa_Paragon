package com.example.application.backends;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Joint {
    public BigDecimal Cx; //x coordinate
    public BigDecimal Cy; //y coordinate
    public short JT; //joint type

    public ArrayList<Short> MN = new ArrayList<>(); //member number
    public short[] order = new short[3]; //order of joint displacement label
    public boolean[] DR; //displacement restrain [x-axis movement, y-axis movement, z-axis rotation]
    public boolean[] FR; //force release in [x, y, z] axis

    public Joint (BigDecimal Cx, BigDecimal Cy) {
        this.Cx = Cx;
        this.Cy = Cy;
        this.JT = -1;
        this.setJoinType(this.JT);
    }

    public void setJoinType (short JT) {
        this.JT = JT;
        if (JT == 0) {
            DR = new boolean[]{false, false, false};
            FR = new boolean[]{false, false, false};
        }
        else if (JT == 1) {
            DR = new boolean[]{true, true, true};
            FR = new boolean[]{false, false, false};
        }
        else if (JT == 2) {
            DR = new boolean[]{true, true, false};
            FR = new boolean[]{false, false, false};
        }
        else if (JT == 3) {
            DR = new boolean[]{false, true, false};
            FR = new boolean[]{false, false, false};
        }
        else if (JT == 4) {
            DR = new boolean[]{true, false, false};
            FR = new boolean[]{false, false, false};
        }
        else if (JT == 5) {
            DR = new boolean[]{false, false, true};
            FR = new boolean[]{false, false, true};
        }
    }
}

    /* [Joint Type]
    0: rigid / free joint (allow displacement in x,y,z-axis, doesn't release force)
    1: fixed joint (prevent displacement in x,y,z-axis, doesn't release force)
    2: pined joint (prevent displacement in x,y-axis, doesn't release force)
    3: roller joint (prevent displacement in y-axis, doesn't release force)
    4: vertical roller joint (prevent displacement in x-axis, doesn't release force)
    5: hinge joint (prevent displacement in z-axis, release force in z-axis)
     */
