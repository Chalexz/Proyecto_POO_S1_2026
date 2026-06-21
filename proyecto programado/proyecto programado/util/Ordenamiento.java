package util;

import java.util.ArrayList;
import model.Comparable;

public class Ordenamiento {

  public static void ordenar(ArrayList<Comparable> arreglo) {
    int n = arreglo.size();
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (arreglo.get(j + 1).menorQue(arreglo.get(j))) {
          Comparable temp = arreglo.get(j);
          arreglo.set(j, arreglo.get(j + 1));
          arreglo.set(j + 1, temp);
        }
      }
    }
  }
}
