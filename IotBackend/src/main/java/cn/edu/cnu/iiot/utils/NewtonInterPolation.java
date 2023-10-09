package cn.edu.cnu.iiot.utils;


public class NewtonInterPolation {
    private static void copyVector(float from[], float to[]) {
        int fromLen = from.length;
        int toLen = to.length;
        if (fromLen != toLen) {
            System.out.println("the two vector's length is not equal!");
            System.exit(0);
        }
        for (int i = 0; i < fromLen; i++) {
            to[i] = from[i];
        }

    }

    /*牛顿插值法*/
    public float NewtonInterMethod(float[] X, float[] Y, float X0) {
        int xLen = X.length;
        float[] copyDataOfY = new float[xLen];

        int j = 0;
        copyVector(Y, copyDataOfY);

        /*求各级均差*/
        while (j < xLen - 1) {
            j = j + 1;
            for (int i = j; i < xLen; i++) {
                copyDataOfY[i] = (copyDataOfY[i] - copyDataOfY[j - 1]) / (X[i] - X[j - 1]);
            }
        }
        /*求插值结果*/
        float res = copyDataOfY[0];
        for (int i = 1; i <= xLen - 1; i++) {
            float u = 1;
            int index = 0;
            while (index < i) {
                u *= (X0 - X[index]);
                ++index;
            }
            res += copyDataOfY[i] * u;
        }
        return res;
    }

    public float BELIE(float x[],float y[],float x0) {
        int m = x.length;
        float y0;

        float j = 0,k = 0,l = 0;
        int ic = 0;
        for(int ia = 0;ia < m;ia ++) {
            double w = 1;
            for(int ib = 0;ib < m;ib ++) {
                if(ia != ib) w /= (x[ia] - x[ib]);
            }
            System.out.println(w);
            k += (w / ((x0 - x[ic]))) * y[ic];
            l += (w / ((x0 - x[ic])));
            ic ++ ;
            j = k / l;
        }
        y0 = j;
        return y0;
    }
}