package com.rnsit.weather.reducers;

public class WFRegressionCalculationReducer {

    public static double[] lreg(double[] x,double[] y) {
       // int MAXN = 1000;
        int n = 0;

        // first pass: read in data, compute xbar and ybar
        double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;
        while(n!=y.length) {
           // x[n] = StdIn.readDouble();
            //y[n] = StdIn.readDouble();
            sumx  += x[n];
            sumx2 += x[n] * x[n];
            sumy  += y[n];
            n++;


        }
        double xbar = sumx / n;
        double ybar = sumy / n;

        // second pass: compute summary statistics
        double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
        for (int i = 0; i < n; i++) {
            xxbar += (x[i] - xbar) * (x[i] - xbar);
            yybar += (y[i] - ybar) * (y[i] - ybar);
            xybar += (x[i] - xbar) * (y[i] - ybar);
        }
        double theta[] = new double[2];
        theta[1] = xybar / xxbar;
        theta[0] = ybar - theta[1] * xbar;

        return theta;
    }

        // print results
       // System.out.println("y   = " + theta[1] + " * x + " + theta[0]);}

        /*// analyze results
        int df = n - 2;
        double rss = 0.0;      // residual sum of squares
        double ssr = 0.0;      // regression sum of squares
        for (int i = 0; i < n; i++) {
            double fit = beta1*x[i] + beta0;
            rss += (fit - y[i]) * (fit - y[i]);
            ssr += (fit - ybar) * (fit - ybar);
        }
        double R2    = ssr / yybar;
        double svar  = rss / df;
        double svar1 = svar / xxbar;
        double svar0 = svar/n + xbar*xbar*svar1;
        System.out.println("R^2                 = " + R2);
        System.out.println("std error of beta_1 = " + Math.sqrt(svar1));
        System.out.println("std error of beta_0 = " + Math.sqrt(svar0));
        svar0 = svar * sumx2 / (n * xxbar);
        System.out.println("std error of beta_0 = " + Math.sqrt(svar0));

        System.out.println("SSTO = " + yybar);
        System.out.println("SSE  = " + rss);
        System.out.println("SSR  = " + ssr);
    }*/

    public static void main(String[] args) {
        double[] x= new double[]{1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002};
        double[] y= new double[]{29.239,27.732,28.443,27.129,27.885,29.08,27.676,28.12,28.216,28.227,28.129,27.585,27.121,27.326,28.598,27.266,27.128,27.32,29.161,27.626,28.821,28.275,26.942,28.049,28.12,28.348,28.824,28.494,28.609,28.423,28.146,27.07,27.878,27.985,28.31,28.756,28.022,28.387,27.568,26.907,28.474,28.059,28.408,28.36,28.381,27.289,28.595,28.542,27.626,27.891,28.143,28.443,28.345,28.558,27.998,27.842,27.872,28.637,28.843,28.86,28.379,28.142,27.658,28.244,27.608,29.221,28.001,28.111,28.242,28.626,28.643,27.45,28.825,28.214,28.024,26.844,28.319,28.564,28.925,28.217,28.986,28.142,28.526,28.939,29.167,28.135,28.484,28.354,28.396,28.542,29.648,27.534,28.364,28.65,28.516,29.129,28.76,29.676,28.852,29.319,29.342,29.494};
        lreg(x,y);
    }
}
