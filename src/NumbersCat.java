import java.util.Arrays;
import java.util.Objects;

public class NumbersCat {

    public static String say(long n) {
        long nP = 0;

        if (n < 0) {
            nP = n * -1;
        } else {
            nP = n;
        }

        if (nP == 0) {
            return "Zero";
        } else if (nP <= 99) {
            return PrimeraLetraMayus(Decenas(n));
        } else if (nP <= 999) {
            return PrimeraLetraMayus(Centenas(n));
        } else if (nP <= 999_999) {
            return PrimeraLetraMayus(Miles(n));
        } else if (nP <= 999_999_999_999L) {
            return PrimeraLetraMayus(Millones(n));
        } else if (nP <= 999_999_999_999_999_999L) {
            return PrimeraLetraMayus(Billones(n));
        } else {
            return PrimeraLetraMayus(Trillones(n));
        }

    }

    public static long words(String s) {
        String[] array = arrayFrase(s);
        long resultado = 0;
        int tMil = 0;
        int tCien = 0;
        int tMillon = 0;
        int tBillon = 0;
        int tTrillon = 0;

        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], "cents")) {
                tCien++;
            } else if (Objects.equals(array[i], "mil")) {
                tMil++;
            } else if (Objects.equals(array[i], "milions")) {
                tMillon++;
            } else if (Objects.equals(array[i], "bilions")) {
                tBillon++;
            } else if (Objects.equals(array[i], "trilions")) {
                tTrillon++;
            }
        }

        if (tTrillon == 1) {
            resultado = Trillones(s);
        } else if (tBillon == 1) {
            resultado = Billones(s);
        } else if (tMillon == 1) {
            resultado = Millones(s);
        } else if (tMil == 1) {
            resultado = Miles(s);
        } else if (tCien == 1) {
            resultado = Centenas(s);
        } else {
            resultado = Decenas(s);
        }

        if (Objects.equals(array[0], "menys")) {
            resultado = resultado * (-1);
        }
        return resultado;
    }

    public static String oper(String s) {
        long[] arrOperadores = ArrayDeOperadores(s);
        String[] arrSignos = ArraydeSignos(s);
        long resultado = 0;

        if (arrOperadores[0] == 0){
            resultado = arrOperadores[1] * -1;
        } else {
            resultado = arrOperadores[0];
        }



        for (int i = 0; i < arrSignos.length; i++) {
            if (Objects.equals(arrSignos[i], "més")){
                resultado += arrOperadores[i+1];
            } else if (Objects.equals(arrSignos[i], "menys")) {
                resultado -= arrOperadores[i+1];
            } else if (Objects.equals(arrSignos[i], "per")) {
                resultado *= arrOperadores[i+1];
            } else if (Objects.equals(arrSignos[i], "dividit")) {
                resultado /= arrOperadores[i+1];
            }
        }

        System.out.println(Arrays.toString(arrOperadores));


        return say(resultado);
    }

    public static String PrimeraLetraMayus(String p) {
        String resultado = "";
        resultado = String.valueOf(p.charAt(0)).toUpperCase();
        for (int i = 1; i < p.length(); i++) {
            resultado += p.charAt(i);
        }
        System.out.println(resultado);
        return resultado;

    }

    public static long[] CrearArray(long n) {
        String numero = String.valueOf(n);

        long[] array = new long[numero.length()];

        for (int i = 0; i < numero.length(); i++) {
            array[i] = Character.getNumericValue(numero.charAt(i));
        }

        return array;

    }

    public static String Decenas(long n) {
        long nP = 0;

        if (n < 0) {
            nP = n * -1;
        } else {
            nP = n;
        }

        if (nP >= 0 && nP <= 19) {
            return NumeroUnicoString(n).trim();
        } else if (nP % 10 == 0) {
            return MultiploDeDiez(n).trim();
        } else if (nP >= 21 && nP <= 29) {
            return Del20al29(n).trim();
        } else if (nP >= 31 && nP <= 99) {
            return Del31al99(n).trim();
        } else {
            return "";
        }

    }

    public static String Centenas(long n) {
        String resultado = "";

        if (n < 0) {
            resultado += "menys ";
            n = n * -1;
        }

        long[] arNumeros = CrearArray(n);

        if (n < 100) {
            resultado += Decenas(n);
        } else if (arNumeros[0] == 1 && arNumeros[1] == 0 && arNumeros[2] == 0) {
            resultado += "cent";
        } else if (arNumeros[0] == 1 && arNumeros[1] == 0) {
            resultado += "cent ";
            resultado += NumeroUnicoString(arNumeros[2]);
        } else if (arNumeros[0] == 1 && arNumeros[1] > 0) {
            resultado += "cent ";
            resultado += Decenas(n - 100);
        } else if (n % 100 == 0) {
            resultado += NumeroUnicoString(arNumeros[0]);
            resultado += "-cents";
        } else if (arNumeros[0] > 1 && arNumeros[1] == 0) {
            resultado += NumeroUnicoString(arNumeros[0]);
            resultado += "-cents ";
            resultado += NumeroUnicoString(arNumeros[2]);
        } else {
            resultado += NumeroUnicoString(arNumeros[0]);
            resultado += "-cents ";
            resultado += Decenas(n - (arNumeros[0] * 100));
        }

        return resultado.trim();

    }

    public static String Miles(long n) {
        String resultado = "";

        if (n < 0) {
            resultado += "menys ";
            n = n * -1;
        }

        if (n < 100) {
            return resultado + Centenas(n);
        }

        long[] arNumeros = CrearArray(n);
        long[] arDos = {arNumeros[0], arNumeros[1]};
        long[] arTres = {arNumeros[0], arNumeros[1], arNumeros[2]};

        if (n % 1000 == 0 && n < 9999) {
            if (n == 1000) {
                resultado += "mil";
            } else {
                resultado += Decenas(arNumeros[0]);
                resultado += " mil";
            }
        } else if (n % 10000 == 0 && n < 99999) {
            resultado += Decenas(CrearNum(arDos, 0));
            resultado += " mil";
        } else if (n % 100000 == 0 && n < 999999) {
            resultado += Centenas(CrearNum(arTres, 0));
            resultado += " mil";
        } else if (n < 2000) {
            resultado += "mil ";
            resultado += Centenas(n - 1000);
        } else if (arNumeros.length == 4) {
            resultado += Decenas(arNumeros[0]);
            resultado += " mil ";
            resultado += Centenas(CrearNum(arNumeros, 1));
        } else if (arNumeros.length == 5) {
            resultado += Decenas(CrearNum(arDos, 0));
            resultado += " mil ";
            resultado += Centenas(CrearNum(arNumeros, 2));
        } else if (arNumeros.length == 6) {
            resultado += Centenas(CrearNum(arTres, 0));
            resultado += " mil ";
            resultado += Centenas(CrearNum(arNumeros, 3));
        }

        return resultado.trim();
    }

    public static String Millones(long n) {
        String resultado = "";

        if (n < 0) {
            resultado += "menys ";
            n = n * -1;
        }

        long[] arNumeros = CrearArray(n);
        long[] arDos = {arNumeros[0], arNumeros[1]};
        long[] arTres = {arNumeros[0], arNumeros[1], arNumeros[2]};
        long[] arCuatro = {arNumeros[0], arNumeros[1], arNumeros[2], arNumeros[3]};
        long[] arCinco = {arNumeros[0], arNumeros[1], arNumeros[2], arNumeros[3], arNumeros[4]};
        long[] arSeis = {arNumeros[0], arNumeros[1], arNumeros[2], arNumeros[3], arNumeros[4], arNumeros[5]};

        if (arNumeros[0] == 1 && arNumeros.length == 7) {
            if (n % 1_000_000 == 0) {
                resultado += Decenas(arNumeros[0]);
                resultado += " milió";
            } else {
                resultado += Decenas(arNumeros[0]);
                resultado += " milió ";
                resultado += Miles(CrearNum(arNumeros, 1));
            }
        } else if (n % 1_000_000 == 0 && n < 9_999_999) {
            resultado += Decenas(arNumeros[0]);
            resultado += " milions";
        } else if (n % 10_000_000 == 0 && n < 99_999_999) {
            resultado += Decenas(CrearNum(arDos, 0));
            resultado += " milions";
        } else if (n % 100_000_000 == 0 && n < 999_999_999) {
            resultado += Centenas(CrearNum(arTres, 0));
            resultado += " milions";
        } else if (arNumeros.length == 8) {
            resultado += Decenas(CrearNum(arDos, 0));
            resultado += " milions ";
            resultado += Miles(CrearNum(arNumeros, 2));
        } else if (arNumeros.length == 9) {
            resultado += Centenas(CrearNum(arTres, 0));
            resultado += " milions ";
            resultado += Miles(CrearNum(arNumeros, 3));
        } else if (arNumeros.length == 10 && arNumeros[0] == 1) {
            resultado += Miles(CrearNum(arCuatro, 0));
            resultado += " milions ";
        } else if (arNumeros.length == 11) {
            resultado += Miles(CrearNum(arCinco, 0));
            resultado += " milions ";
            resultado += Miles(CrearNum(arNumeros, 5));
        } else if (arNumeros.length == 12) {
            resultado += Miles(CrearNum(arSeis, 0));
            resultado += " milions ";
            resultado += Miles(CrearNum(arNumeros, 6));
        }

        return resultado.trim();

    }

    public static String Billones(long n) {
        String resultado = "";

        if (n < 0) {
            resultado += "menys ";
            n = n * -1;
        }

        long[] arNumeros = CrearArray(n);
        long[] arDos = {arNumeros[0], arNumeros[1]};
        long[] arTres = {arNumeros[0], arNumeros[1], arNumeros[2]};
        long[] arCuatro = {arNumeros[0], arNumeros[1], arNumeros[2], arNumeros[3]};
        long[] arCinco = {arNumeros[0], arNumeros[1], arNumeros[2], arNumeros[3], arNumeros[4]};
        long[] arSeis = {arNumeros[0], arNumeros[1], arNumeros[2], arNumeros[3], arNumeros[4], arNumeros[5]};


        if (n == 1_000_000_000_000L) {
            resultado += Decenas(arNumeros[0]);
            resultado += " bilió";
        } else if (n % 1_000_000_000_000L == 0 && arNumeros.length == 13) {
            resultado += Decenas(arNumeros[0]);
            resultado += " bilions ";
        } else if (n % 1_000_000_000_000L == 0 && arNumeros.length == 14) {
            resultado += Decenas(CrearNum(arDos, 0));
            resultado += " bilions ";
        } else if (n % 1_000_000_000_000L == 0 && arNumeros.length == 15) {
            resultado += Centenas(CrearNum(arTres, 0));
            resultado += " bilions ";
        } else if (n % 1_000_000_000_000L == 0 && arNumeros.length == 16) {
            resultado += Miles(CrearNum(arCuatro, 0));
            resultado += " bilions ";
        } else if (n % 1_000_000_000_000L == 0 && arNumeros.length == 17) {
            resultado += Miles(CrearNum(arCinco, 0));
            resultado += " bilions ";
        } else if (n % 1_000_000_000_000L == 0 && arNumeros.length == 18) {
            resultado += Miles(CrearNum(arSeis, 0));
            resultado += " bilions ";
        } else if (arNumeros.length == 13) {
            resultado += Decenas(arNumeros[0]);
            resultado += " bilions ";
            resultado += Millones(CrearNum(arNumeros, 1));
        } else if (arNumeros.length == 14) {
            resultado += Decenas(CrearNum(arDos, 0));
            resultado += " bilions ";
            resultado += Millones(CrearNum(arNumeros, 2));
        } else if (arNumeros.length == 15) {
            resultado += Centenas(CrearNum(arTres, 0));
            resultado += " bilions ";
            resultado += Millones(CrearNum(arNumeros, 3));
        } else if (arNumeros.length == 16) {
            resultado += Miles(CrearNum(arCuatro, 0));
            resultado += " bilions ";
            resultado += Millones(CrearNum(arNumeros, 4));
        } else if (arNumeros.length == 17) {
            resultado += Miles(CrearNum(arCinco, 0));
            resultado += " bilions ";
            resultado += Millones(CrearNum(arNumeros, 5));
        } else if (arNumeros.length == 18) {
            resultado += Miles(CrearNum(arSeis, 0));
            resultado += " bilions ";
            resultado += Millones(CrearNum(arNumeros, 6));
        }


        return resultado.trim();
    }

    public static String Trillones(long n) {
        String resultado = "";

        if (n < 0) {
            resultado += "menys ";
            n = n * -1;
        }

        long[] arNumeros = CrearArray(n);
        long[] arDos = {arNumeros[0], arNumeros[1]};
        long[] arTres = {arNumeros[0], arNumeros[1], arNumeros[2]};
        long[] arCuatro = {arNumeros[0], arNumeros[1], arNumeros[2], arNumeros[3]};
        long[] arCinco = {arNumeros[0], arNumeros[1], arNumeros[2], arNumeros[3], arNumeros[4]};
        long[] arSeis = {arNumeros[0], arNumeros[1], arNumeros[2], arNumeros[3], arNumeros[4], arNumeros[5]};

        if (n == 1_000_000_000_000_000_000L) {
            resultado += Decenas(arNumeros[0]);
            resultado += " trilió";
        } else if (n % 1_000_000_000_000L == 0 && arNumeros.length == 19) {
            resultado += Decenas(arNumeros[0]);
            resultado += " trilions ";
        } else if (n % 1_000_000_000_000L == 0 && arNumeros.length == 20) {
            resultado += Decenas(CrearNum(arDos, 0));
            resultado += " trilions ";
        } else if (n % 1_000_000_000_000L == 0 && arNumeros.length == 21) {
            resultado += Centenas(CrearNum(arTres, 0));
            resultado += " trilions ";
        } else if (n % 1_000_000_000_000L == 0 && arNumeros.length == 22) {
            resultado += Miles(CrearNum(arCuatro, 0));
            resultado += " trilions ";
        } else if (n % 1_000_000_000_000L == 0 && arNumeros.length == 23) {
            resultado += Miles(CrearNum(arCinco, 0));
            resultado += " trilions ";
        } else if (n % 1_000_000_000_000L == 0 && arNumeros.length == 24) {
            resultado += Miles(CrearNum(arSeis, 0));
            resultado += " trilions ";
        } else if (arNumeros.length == 19) {
            resultado += Decenas(arNumeros[0]);
            resultado += " trilions ";
            resultado += Billones(CrearNum(arNumeros, 1));
        } else if (arNumeros.length == 20) {
            resultado += Decenas(CrearNum(arDos, 0));
            resultado += " trilions ";
            resultado += Billones(CrearNum(arNumeros, 2));
        } else if (arNumeros.length == 21) {
            resultado += Centenas(CrearNum(arTres, 0));
            resultado += " trilions ";
            resultado += Billones(CrearNum(arNumeros, 3));
        } else if (arNumeros.length == 22) {
            resultado += Miles(CrearNum(arCuatro, 0));
            resultado += " trilions ";
            resultado += Billones(CrearNum(arNumeros, 4));
        } else if (arNumeros.length == 23) {
            resultado += Miles(CrearNum(arCinco, 0));
            resultado += " trilions ";
            resultado += Billones(CrearNum(arNumeros, 5));
        } else if (arNumeros.length == 18) {
            resultado += Miles(CrearNum(arSeis, 0));
            resultado += " trilions ";
            resultado += Billones(CrearNum(arNumeros, 6));
        }

        return resultado.trim();
    }

    public static long CrearNum(long[] arNumeros, int indice) {
        long resultado = 0;

        for (int i = indice; i < arNumeros.length; i++) {
            resultado = resultado * 10 + arNumeros[i];
        }
        return resultado;
    }

    public static String NumeroUnicoString(long n) {
        String resultado = "";

        if (n < 0) {
            resultado += "menys ";
            n = n * -1;
        }

        if (n == 1) {
            resultado += "un";
        } else if (n == 2) {
            resultado += "dos";
        } else if (n == 3) {
            resultado += "tres";
        } else if (n == 4) {
            resultado += "quatre";
        } else if (n == 5) {
            resultado += "cinc";
        } else if (n == 6) {
            resultado += "sis";
        } else if (n == 7) {
            resultado += "set";
        } else if (n == 8) {
            resultado += "vuit";
        } else if (n == 9) {
            resultado += "nou";
        } else if (n == 10) {
            resultado += "deu";
        } else if (n == 11) {
            resultado += "onze";
        } else if (n == 12) {
            resultado += "dotze";
        } else if (n == 13) {
            resultado += "tretze";
        } else if (n == 14) {
            resultado += "catorze";
        } else if (n == 15) {
            resultado += "quinze";
        } else if (n == 16) {
            resultado += "setze";
        } else if (n == 17) {
            resultado += "disset";
        } else if (n == 18) {
            resultado += "divuit";
        } else if (n == 19) {
            resultado += "dinou";
        }
        return resultado;

    }


    public static String MultiploDeDiez(long n) {
        String resultado = "";

        if (n < 0) {
            resultado += "menys ";
            n = n * -1;
        }

        if (n == 10) {
            resultado += "deu";
        } else if (n == 20) {
            resultado += "vint";
        } else if (n == 30) {
            resultado += "trenta";
        } else if (n == 40) {
            resultado += "quaranta";
        } else if (n == 50) {
            resultado += "cinquanta";
        } else if (n == 60) {
            resultado += "seixanta";
        } else if (n == 70) {
            resultado += "setanta";
        } else if (n == 80) {
            resultado += "vuitanta";
        } else if (n == 90) {
            resultado += "noranta";
        }
        return resultado;
    }

    public static String Del20al29(long n) {
        String resultado = "";

        if (n < 0) {
            resultado += "menys ";
            n = n * -1;
        }

        if (n == 20) {
            resultado += "vint";
        } else if (n == 21) {
            resultado += "vint-i-un";
        } else if (n == 22) {
            resultado += "vint-i-dos";
        } else if (n == 23) {
            resultado += "vint-i-tres";
        } else if (n == 24) {
            resultado += "vint-i-quatre";
        } else if (n == 25) {
            resultado += "vint-i-cinc";
        } else if (n == 26) {
            resultado += "vint-i-sis";
        } else if (n == 27) {
            resultado += "vint-i-set";
        } else if (n == 28) {
            resultado += "vint-i-vuit";
        } else if (n == 29) {
            resultado += "vint-i-nou";
        }
        return resultado;
    }

    public static String Del31al99(long n) {
        String resultado = "";

        if (n < 0) {
            resultado += "menys ";
            n = n * -1;
        }
        long[] arNumeros = CrearArray(n);

        if (arNumeros[1] == 0) {
            resultado += MultiploDeDiez(arNumeros[0] * 10);
        } else {
            resultado += MultiploDeDiez(arNumeros[0] * 10);
            resultado += "-";
            resultado += NumeroUnicoString(arNumeros[1]);
        }

        return resultado;
    }

    // ------------------Empezamos el metodo words-------------------------------- //

    public static String[] arrayFrase(String s) {
        s = s.toLowerCase();
        String res = s.replace("-", " ");
        return res.split(" ");
    }

    public static String arrayRestante(String[] arrFrase, int indice) {
        String resultado = "";


        for (int i = indice + 1; i < arrFrase.length; i++) {
            resultado += arrFrase[i];
            resultado += " ";
        }

        return resultado;
    }

    public static long Decenas(String s) {
        String[] array = arrayFrase(s);
        long resultado = 0;

        for (int i = 0; i < array.length; i++) {
            resultado += NumeroUnicoLong(array[i]);
        }
        return resultado;
    }

    public static long Centenas(String s) {
        String[] array = arrayFrase(s);
        long resultado = 0;

        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], "cents")) {
                resultado = resultado * 100;
            } else {
                resultado += NumeroUnicoLong(array[i]);
            }
        }
        return resultado;
    }

    public static long Miles(String s) {
        String[] array = arrayFrase(s);
        long resultado = 0;

        for (int i = 0; i < array.length; i++) {

            if (Objects.equals(array[0], "mil")) {
                resultado = 1000;
                resultado += Centenas(arrayRestante(array, i));
                break;
            } else if (Objects.equals(array[i], "mil")) {
                resultado = resultado * 1000;
                resultado += Centenas(arrayRestante(array, i));
                break;
            } else if (Objects.equals(array[i], "cents")) {
                resultado = resultado * 100;
            } else {
                resultado += NumeroUnicoLong(array[i]);
            }
        }


        return resultado;
    }

    public static long Millones(String s) {
        String[] array = arrayFrase(s);
        long resultado = 0;

        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], "milions")) {
                resultado = resultado * 1_000_000;
                resultado += Miles(arrayRestante(array, i));
                break;

            } else if (Objects.equals(array[i], "mil") && !Objects.equals(array[0], "mil")) {
                resultado *= 1000;
            } else if (Objects.equals(array[i], "cents")) {
                resultado -= NumeroUnicoLong(array[i-1]);
                resultado += NumeroUnicoLong(array[i-1]) * 100 ;
            } else {
                resultado += NumeroUnicoLong(array[i]);
            }
        }

        return resultado;
    }

    public static long Billones(String s) {
        String[] array = arrayFrase(s);
        long resultado = 0;

        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], "bilions")) {
                resultado = resultado * 1_000_000_000_000L;
                resultado += Millones(arrayRestante(array, i));
                break;

            } else if (Objects.equals(array[i], "milions")) {
                resultado *= 1_000_000;
            } else if (Objects.equals(array[i], "mil") && !Objects.equals(array[0], "mil")) {
                resultado *= 1000;
            } else if (Objects.equals(array[i], "cents")) {
                resultado -= NumeroUnicoLong(array[i-1]);
                resultado += NumeroUnicoLong(array[i-1]) * 100 ;
            } else {
                resultado += NumeroUnicoLong(array[i]);
            }
        }
        return resultado;
    }

    public static long Trillones(String s) {
        String[] array = arrayFrase(s);
        long resultado = 0;

        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], "trilions")) {
                resultado = resultado * 1_000_000_000_000_000_000L;
                resultado += Billones(arrayRestante(array, i));
                break;

            } else if (Objects.equals(array[i], "bilions")) {
                resultado *= 1_000_000_000;
            } else if (Objects.equals(array[i], "milions")) {
                resultado *= 1_000_000;
            } else if (Objects.equals(array[i], "mil") && !Objects.equals(array[0], "mil")) {
                resultado *= 1000;
            } else if (Objects.equals(array[i], "cents")) {
                resultado -= NumeroUnicoLong(array[i - 1]);
                resultado += NumeroUnicoLong(array[i - 1]) * 100;
            } else {
                resultado += NumeroUnicoLong(array[i]);
            }
        }
        return resultado;
    }

    public static long NumeroUnicoLong(String s){

        if (Objects.equals(s, "un")){
            return 1;
        } else if (Objects.equals(s, "dos")) {
            return 2;
        } else if (Objects.equals(s, "tres")) {
            return 3;
        } else if (Objects.equals(s, "quatre")) {
            return 4;
        } else if (Objects.equals(s, "cinc")) {
            return 5;
        } else if (Objects.equals(s, "sis")) {
            return 6;
        } else if (Objects.equals(s, "set")) {
            return 7;
        } else if (Objects.equals(s, "vuit")) {
            return 8;
        } else if (Objects.equals(s, "nou")) {
            return 9;
        } else if (Objects.equals(s, "deu")) {
            return 10;
        } else if (Objects.equals(s, "onze")) {
            return 11;
        } else if (Objects.equals(s, "dotze")) {
            return 12;
        } else if (Objects.equals(s, "tretze")) {
            return 13;
        } else if (Objects.equals(s, "catorze")) {
            return 14;
        } else if (Objects.equals(s, "quinze")) {
            return 15;
        } else if (Objects.equals(s, "setze")) {
            return 16;
        } else if (Objects.equals(s, "disset")) {
            return 17;
        } else if (Objects.equals(s, "divuit")) {
            return 18;
        } else if (Objects.equals(s, "dinou")) {
            return 19;
        } else if (Objects.equals(s, "vint")) {
            return 20;
        } else if (Objects.equals(s, "trenta")) {
            return 30;
        } else if (Objects.equals(s, "quaranta")) {
            return 40;
        } else if (Objects.equals(s, "cinquanta")) {
            return 50;
        } else if (Objects.equals(s, "seixanta")) {
            return 60;
        } else if (Objects.equals(s, "setanta")) {
            return 70;
        } else if (Objects.equals(s, "vuitanta")) {
            return 80;
        } else if (Objects.equals(s, "noranta")) {
            return 90;
        } else if (Objects.equals(s, "cent")) {
            return 100;
        } else if (Objects.equals(s, "mil")) {
            return 1000;
        } else if (Objects.equals(s, "milió")) {
            return 999_999;
        } else if (Objects.equals(s, "bilió")) {
            return 999_999_999_999L;
        } else if (Objects.equals(s, "trilió")) {
            return 999_999_999_999_999_999L;
        }
        return 0;
    }

    //---------------------------Empezamos metodo oper---------------//

    public static long[] ArrayDeOperadores(String s){
        String[] arrCompleta = arrayFrase(s);
        int tOperaciones = 0;

        for (int i = 0; i < arrCompleta.length; i++) {
            if (Objects.equals(arrCompleta[i], "més")){
                tOperaciones++;
            } else if (Objects.equals(arrCompleta[i], "menys")) {
                tOperaciones++;
            } else if (Objects.equals(arrCompleta[i], "per")) {
                tOperaciones++;
            } else if (Objects.equals(arrCompleta[i], "dividit")) {
                tOperaciones++;
            }
        }

        String operador = "";
        long[] arrOperadores = new long[tOperaciones+1];
        int indiceArrOperadores = 0;
        int indiceUltimoOperador = 0;
        for (int i = 0; i < arrCompleta.length; i++) {
            if (Objects.equals(arrCompleta[i], "més") || Objects.equals(arrCompleta[i], "menys") || Objects.equals(arrCompleta[i], "per") || Objects.equals(arrCompleta[i], "dividit")){
                arrOperadores[indiceArrOperadores] = words(operador);
                indiceArrOperadores++;
                operador = "";
                indiceUltimoOperador = i;
            } else {
                operador += arrCompleta[i];
                operador += " ";
            }
        }
        operador = arrayRestante(arrCompleta, indiceUltimoOperador);
        arrOperadores[indiceArrOperadores] = words(operador);
        return arrOperadores;
    }
    
    public static String[] ArraydeSignos(String s){
        String[] arrCompleta = arrayFrase(s);
        String operadores = "";

        for (int i = 0; i < arrCompleta.length; i++) {
            if (Objects.equals(arrCompleta[i], "més")){
                operadores += arrCompleta[i] + " ";
            } else if (Objects.equals(arrCompleta[i], "menys") && !Objects.equals(arrCompleta[0], "menys") ) {
                operadores += arrCompleta[i] + " ";
            } else if (Objects.equals(arrCompleta[i], "per")) {
                operadores += arrCompleta[i] + " ";
            } else if (Objects.equals(arrCompleta[i], "dividit")) {
                operadores += arrCompleta[i] + " ";
            }
        }

        return arrayFrase(operadores.trim());
    }
}
