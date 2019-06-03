/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zene;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gergely.vajda
 */
public class Zene {

    static String[] megnyito() {
        String[] x = new String[1000];
        try {
            String s;
            int osszsor;
            FileReader buta = new FileReader("musor.txt");
            BufferedReader okos = new BufferedReader(buta);
            s = okos.readLine();
            s = s.trim();
            osszsor = Integer.parseInt(s);

            for (int i = 0; i < osszsor; i++) {
                x[i] = okos.readLine();

            }
        } catch (FileNotFoundException ex) {
            System.out.println("Nincs ilyen file!");
        } catch (IOException ex) {
            System.out.println("Hiba az olvasásnál!");
        }
        return x;
    }

    public static void kezdesek(int[] ado, int[] perc, int[] masodperc, String[] eloado, String[] cim, int[] kezdido) {
        int kezdoszam = 0;

//1. adó kezdőidői
        for (int i = 0; i < ado.length; i++) {
            if (eloado[i] == null) {
                break;
            }
            if (ado[i] == 1) {
                kezdido[i] = kezdoszam;
                kezdoszam = kezdoszam + (perc[i] * 60 + masodperc[i]);
            }

        }
        //2. adó kezdőidői
        kezdoszam = 0;
        for (int i = 0; i < ado.length; i++) {
            if (eloado[i] == null) {
                break;
            }
            if (ado[i] == 2) {
                kezdido[i] = kezdoszam;
                kezdoszam = kezdoszam + (perc[i] * 60 + masodperc[i]);
            }

        }
        //3. adó kezdőidői
        kezdoszam = 0;
        for (int i = 0; i < ado.length; i++) {
            if (eloado[i] == null) {
                break;
            }
            if (ado[i] == 3) {
                kezdido[i] = kezdoszam;
                kezdoszam = kezdoszam + (perc[i] * 60 + masodperc[i]);
            }

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] st = new String[1000];
        int[] kezdido = new int[1000];

        st = megnyito();
        int[] ado = new int[1000];
        int[] perc = new int[1000];
        int[] masodperc = new int[1000];
        String[] eloado = new String[1000];
        String[] cim = new String[1000];
        //Az egyes kiolvasott sorokat szétdarabolja, és szétválogatja az adott blokkokba.
        for (int i = 0; i < st.length; i++) {
            if (st[i] != null) {
                String[] atmeneti = st[i].split(" ");
                ado[i] = Integer.parseInt(atmeneti[0]);
                perc[i] = Integer.parseInt(atmeneti[1]);
                masodperc[i] = Integer.parseInt(atmeneti[2]);
                String atmeneti2 = "";
                for (int j = 3; j < atmeneti.length; j++) {
                    atmeneti2 = atmeneti2 + atmeneti[j];

                }
                String[] eloadocim = atmeneti2.split(":");
                eloado[i] = eloadocim[0];
                cim[i] = eloadocim[1];

            }

        }
        //szétválogató blokk vége
        //2.feladat
        int csat1 = 0;
        int csat2 = 0;
        int csat3 = 0;
        for (int i = 0; i < ado.length; i++) {
            switch (ado[i]) {
                case 1:
                    csat1++;
                    break;
                case 2:
                    csat2++;
                    break;
                case 3:
                    csat3++;
                    break;
            };
        }
        System.out.println("2. feladat");
        System.out.println("Egyes csatorna: " + csat1 + " dal");
        System.out.println("Kettes csatorna: " + csat2 + " dal");
        System.out.println("Hármas csatorna: " + csat3 + " dal");
        //3.feladat
        int Erik1Sorszam = 0;
        for (int i = 0; i < ado.length; i++) {

            if (eloado[i].equals("EricClapton") && ado[i] == 1) {
                Erik1Sorszam = i;

                break;

            }
        }

        int Erik2Sorszam = 0;
        for (int i = 676; i > 0; i--) {

            if (eloado[i].equals("EricClapton") && ado[i] == 1) {
                Erik2Sorszam = i;
                break;
            }
        }

        int idotartamPerc = 0;
        int idotartamMasodperc = 0;
        for (int i = Erik1Sorszam; i < Erik2Sorszam + 1; i++) {
            if (ado[i] == 1) {
                idotartamPerc = idotartamPerc + perc[i];
                idotartamMasodperc = idotartamMasodperc + masodperc[i];

            }
        }
        int x = idotartamPerc * 60 + idotartamMasodperc;
        int orak = x / 3600;
        x = x % 3600;
        int percek = x / 60;
        x = x % 60;

        System.out.println("3.Feladat");
        System.out.println("Az egyes adón, a két Eric Clapton szám között eltelt idő: " + orak + ":" + percek + ":" + x);
        //4. feladat
        System.out.println("4. feladat");
        int omleg = 0;
        int omlegado = 0;
        for (int i = 0; i < ado.length; i++) {
            if (eloado[i] == null) {
                break;
            }
            if (eloado[i].equals("Omega") && cim[i].equals("Legenda")) {
                omleg = i;
                omlegado = ado[i];

            }

        }
        kezdesek(ado, perc, masodperc, eloado, cim, kezdido);
        int idopont1 = kezdido[omleg];
        int utana = 0;
        int utanaado = 0;
        for (int i = 0; i < ado.length; i++) {
            if (eloado[i] == null) {
                break;
            }
            if (idopont1 < kezdido[i] && ado[i] != omlegado) {
                utana = i;
                utanaado = ado[i];
                break;
            }
        }
        for (int i = utana; i > 0; i--) {
            if (ado[i] == utanaado) {
                System.out.println("adó: " + ado[i] + " " + eloado[i] + ": " + cim[i]);
                break;
            }
        }

        int maradado = 6 - (omlegado + utanaado);

        for (int i = 0; i < ado.length; i++) {
            if (eloado[i] == null) {
                break;
            }
            if (idopont1 < kezdido[i] && ado[i] == maradado) {
                utana = i;
                utanaado = ado[i];
                break;
            }
        }
        for (int i = utana; i > 0; i--) {
            if (ado[i] == maradado) {
                System.out.println("adó: " + ado[i] + " " + eloado[i] + ": " + cim[i]);
                break;
            }
        }
        //5. feladat
        Scanner sc=new Scanner(System.in);
        String kamu=new String();
        

    }

}
